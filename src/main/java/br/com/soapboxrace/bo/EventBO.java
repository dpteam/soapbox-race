package br.com.soapboxrace.bo;

import br.com.soapboxrace.achievements.AchievementQueue;
import br.com.soapboxrace.bo.legacy.LegacyPersonaBO;
import br.com.soapboxrace.dao.factory.*;
import br.com.soapboxrace.definition.CarClasses;
import br.com.soapboxrace.definition.CardDecks;
import br.com.soapboxrace.definition.EventMode;
import br.com.soapboxrace.engine.Router;
import br.com.soapboxrace.engine.Session;
import br.com.soapboxrace.http.HttpSessionVO;
import br.com.soapboxrace.jaxb.events.*;
import br.com.soapboxrace.jaxb.events.entrants.DragEntrantResult;
import br.com.soapboxrace.jaxb.events.entrants.RouteEntrantResult;
import br.com.soapboxrace.jaxb.events.packets.DragArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.PursuitArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.RouteArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.TeamEscapeArbitrationPacket;
import br.com.soapboxrace.jaxb.events.results.DragEventResult;
import br.com.soapboxrace.jaxb.events.results.PursuitEventResult;
import br.com.soapboxrace.jaxb.events.results.RouteEventResult;
import br.com.soapboxrace.jaxb.events.results.TeamEscapeEventResult;
import br.com.soapboxrace.jaxb.events.rewards.Accolades;
import br.com.soapboxrace.jaxb.events.rewards.Reward;
import br.com.soapboxrace.jaxb.events.rewards.RewardPart;
import br.com.soapboxrace.jaxb.events.rewards.lucky.LuckyDrawInfo;
import br.com.soapboxrace.jaxb.events.rewards.lucky.LuckyDrawItem;
import br.com.soapboxrace.jpa.EventDataEntity;
import br.com.soapboxrace.jpa.EventDefinitionEntity;
import br.com.soapboxrace.jpa.OwnedCarEntity;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.xmpp.IXmppSender;
import br.com.soapboxrace.xmpp.XmppFactory;
import br.com.soapboxrace.xmpp.jaxb.XMPP_EventTimingOutType;
import br.com.soapboxrace.xmpp.jaxb.XMPP_ResponseTypeEventTimingOut;
import br.com.soapboxrace.xmpp.jaxb.XMPP_ResponseTypeRouteEntrantResult;
import br.com.soapboxrace.xmpp.jaxb.XMPP_RouteEntrantResultType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Re-written event BO to handle:
 * <p>
 * - arbitration (all types)
 * - pursuit bust
 * - rewards
 *
 * @author leorblx
 */
public class EventBO
{
    /* DAO declarations */
    private final IPersonaDao personaDao = DaoFactory.getPersonaDao();
    private final IOwnedCarDao ownedCarDao = DaoFactory.getOwnedCarDao();
    private final IEventDataDao eventDataDao = DaoFactory.getEventDataDao();
    private final IPersonaAchievementDao personaAchievementDao = DaoFactory.getPersonaAchievementDao();
    private final IAchievementDao achievementDao = DaoFactory.getAchievementDao();

    /* BO declarations */
    private final LegacyPersonaBO personaBO = new LegacyPersonaBO();

    /**
     * Insert initial event session data into the database.
     * Called when the game requests "/event/launched".
     *
     * @param userId         The current user's ID.
     * @param eventSessionId The ID of the event session.
     * @return An empty string.
     */
    public String launched(Long userId, Long eventSessionId)
    {
        HttpSessionVO session = Router.getHttpSessionVo(userId);

        if (session == null) {
            throw new IllegalArgumentException("No session for user " + userId + "!");
        }

        session.getAlternateEventTimer().start();
        Long personaId = session.getPersonaId();
        
        /* Find the entry in the database. */
        EventDataEntity eventData = eventDataDao.findByEventSessionIdAndPersonaId(
                eventSessionId, personaId
        );

        eventData.setEventDurationInMS(0L);
        eventData.setEventLaunched(true);
        eventData.setFinishReason(0);
        eventData.setRank((short) 0);
        eventData.setTopSpeed(0F);
        
        /* Save the data */
        eventDataDao.save(eventData);

        return "";
    }

    /**
     * Generate an arbitration result that can be marshalled to XML.
     *
     * @param userId The ID of the user that made the request
     * @param packet The arbitration packet sent by the client.
     * @return The arbitration result.
     */
    public AbstractEventResult arbitration(Long userId, AbstractArbitrationPacket packet)
    {
        HttpSessionVO session = Router.getHttpSessionVo(userId);

        if (session == null) {
            throw new IllegalArgumentException("No session for user " + userId + "!");
        }

        Long sessionId = session.getEventSessionId();
        Long personaId = session.getPersonaId();
        PersonaEntity persona = personaDao.findById(personaId);
        EventDataEntity eventData = eventDataDao.findByEventSessionIdAndPersonaId(sessionId, personaId);

        if (eventData == null || !eventData.getEventLaunched())
            return null;

        packet.setPersona(persona);
        packet.setEventSessionId(sessionId);

        Long duration = session.getAlternateEventTimer().getElapsed();

        EventMode mode = EventMode.forId(eventData.getEventDefinition().getEventModeId());

        if (mode == null)
            throw new IllegalArgumentException("Invalid mode: " + eventData.getEventDefinition().getEventModeId());

        if (packet instanceof ArbitrationPacketWithJumps) {
            personaAchievementDao.update(
                    persona,
                    achievementDao.findByIdentifier("achievement_ACH_CLOCKED_AIRTIME"),
                    ((ArbitrationPacketWithJumps) packet).getSumOfJumpsDurationInMilliseconds(),
                    updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
            );
        }

        EventDefinitionEntity eventDefinition = eventData.getEventDefinition();

        if (eventDefinition.getLength() > 0) {
            personaAchievementDao.update(
                    persona,
                    achievementDao.findByIdentifier("achievement_ACH_DRIVE_MILES"),
                    Float.valueOf(eventDefinition.getLength() * 1609.344f).longValue(),
                    updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
            );
        }

        Accolades accolades = createAccolades(eventData, mode, packet);
        AbstractEventResult eventResult = null;

        AchievementQueue.get(personaId).unlock();

        switch (mode) {
            case Circuit:
            case Sprint:
                eventResult = routeArbitration(
                        eventData,
                        personaDao.findById(personaId),
                        accolades,
                        (RouteArbitrationPacket) packet
                );
                break;
            case Drag:
                eventResult = dragArbitration(
                        eventData,
                        personaDao.findById(personaId),
                        accolades,
                        (DragArbitrationPacket) packet
                );
                break;
            case MeetingPlace:
                break;
            case Pursuit_MP:
                eventResult = teamEscapeArbitration(
                        eventData,
                        personaDao.findById(personaId),
                        accolades,
                        (TeamEscapeArbitrationPacket) packet
                );
                break;
            case Pursuit_SP:
                eventResult = pursuitArbitration(
                        eventData,
                        personaDao.findById(personaId),
                        accolades,
                        (PursuitArbitrationPacket) packet
                );
                break;
            default:
                break;
        }

        AchievementQueue.get(personaId).lock();

        return eventResult;
    }

    /**
     * Creates a drag arbitration result.
     *
     * @param eventData         The event data entity.
     * @param persona           The persona entity.
     * @param accolades         The accolades instance (holds rewards)
     * @param arbitrationPacket The arbitration packet sent by the client.
     * @return The event result
     */
    private DragEventResult dragArbitration(
            EventDataEntity eventData,
            PersonaEntity persona,
            Accolades accolades,
            DragArbitrationPacket arbitrationPacket
    )
    {
        Long eventSessionId = eventData.getEventSessionId();
        DragEventResult result = new DragEventResult();
        List<DragEntrantResult> dragEntrants = new ArrayList<>();

        for (EventDataEntity racer : eventDataDao.getRacers(eventSessionId)) {
            DragEntrantResult entrant = new DragEntrantResult();
            entrant.setTopSpeed(racer.getTopSpeed());
            entrant.setEventDurationInMilliseconds(racer.getEventDurationInMS());
            entrant.setEventSessionId(eventSessionId);
            entrant.setFinishReason(racer.getFinishReason());
            entrant.setPersonaId(racer.getPersonaId());
            entrant.setRanking(racer.getRank());

            dragEntrants.add(entrant);
        }

        result.setEntrants(dragEntrants);
        result.setPersonaId(persona.getId());
        result.setEventSessionId(eventSessionId);
        result.setAccolades(accolades);
        result.setDurability(100);
        result.setEventId(eventData.getEventId());
        result.setExitPath(ExitPath.ExitToFreeroam);
        result.setInviteLifetimeInMilliseconds(0);

        eventData.setCarId(arbitrationPacket.getCarId());
        eventData.setEventDurationInMS(arbitrationPacket.getEventDurationInMilliseconds());
        eventData.setFinishReason(arbitrationPacket.getFinishReason());
        eventData.setPerfectStart(arbitrationPacket.getPerfectStart());
        eventData.setRank(arbitrationPacket.getRank());
        eventData.setTopSpeed(arbitrationPacket.getTopSpeed());
        eventData.setFractionCompleted(arbitrationPacket.getFractionCompleted());

        eventDataDao.save(eventData);

        if (arbitrationPacket.getRank() == 1) {
            personaAchievementDao.update(
                    persona,
                    achievementDao.findByIdentifier("achievement_ACH_WIN_DRAG"),
                    1L,
                    updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
            );
        }

        return result;
    }

    /**
     * Creates a route arbitration result.
     * Applicable to sprints and circuits.
     *
     * @param eventData         The event data entity.
     * @param persona           The persona entity.
     * @param accolades         The accolades instance (holds rewards)
     * @param arbitrationPacket The pursuit arbitration packet sent by the client.
     * @return The event result
     */
    private RouteEventResult routeArbitration(
            EventDataEntity eventData,
            PersonaEntity persona,
            Accolades accolades,
            RouteArbitrationPacket arbitrationPacket
    )
    {
        Long eventSessionId = eventData.getEventSessionId();
        RouteEventResult result = new RouteEventResult();

//        System.out.println("packet = " + arbitrationPacket);

        result.setAccolades(accolades);
        result.setDurability(100);
        result.setEventId(eventData.getEventId());
        result.setEventSessionId(eventSessionId);

        // Stats/XMPP
        XMPP_RouteEntrantResultType xmppRouteResult = new XMPP_RouteEntrantResultType();
        xmppRouteResult.setBestLapDurationInMilliseconds(arbitrationPacket.getBestLapDurationInMilliseconds());
        xmppRouteResult.setEventDurationInMilliseconds(arbitrationPacket.getEventDurationInMilliseconds());
        xmppRouteResult.setEventSessionId(eventSessionId);
        xmppRouteResult.setFinishReason(arbitrationPacket.getFinishReason());
        xmppRouteResult.setPersonaId(persona.getId());
        xmppRouteResult.setRanking(arbitrationPacket.getRank());
        xmppRouteResult.setTopSpeed(arbitrationPacket.getTopSpeed());

        XMPP_ResponseTypeRouteEntrantResult routeEntrantResultResponse = new XMPP_ResponseTypeRouteEntrantResult();
        routeEntrantResultResponse.setRouteEntrantResult(xmppRouteResult);

        // Saving data
        boolean routeIsFirstPlace = arbitrationPacket.getRank() == 1;

        eventData.setBestLapTimeInMS(arbitrationPacket.getBestLapDurationInMilliseconds());
        eventData.setCarId(arbitrationPacket.getCarId());
        eventData.setEventDurationInMS(arbitrationPacket.getEventDurationInMilliseconds());
        eventData.setEventModeId(eventData.getEventDefinition().getEventModeId());
        eventData.setFinishReason(arbitrationPacket.getFinishReason());
        eventData.setPerfectStart(arbitrationPacket.getPerfectStart());
        eventData.setRank(arbitrationPacket.getRank());
        eventData.setTopSpeed(arbitrationPacket.getTopSpeed());

        eventDataDao.save(eventData);

        // entrant data
        List<RouteEntrantResult> routeEntrants = new ArrayList<>();

        for (EventDataEntity racer : eventDataDao.getRacers(eventSessionId)) {
            RouteEntrantResult routeEntrantResult = new RouteEntrantResult();
            routeEntrantResult.setBestLapDurationInMilliseconds(racer.getBestLapTimeInMS());
            routeEntrantResult.setEventSessionId(racer.getEventDurationInMS());
            routeEntrantResult.setEventSessionId(eventSessionId);
            routeEntrantResult.setFinishReason(racer.getFinishReason());
            routeEntrantResult.setPersonaId(racer.getPersonaId());
            routeEntrantResult.setRanking(racer.getRank());
            routeEntrantResult.setTopSpeed(racer.getTopSpeed());

            routeEntrants.add(routeEntrantResult);

            if (!racer.getPersonaId().equals(persona.getId())) {
                IXmppSender xmppSender = XmppFactory.getXmppSenderInstance(Session.getXmppServerType());
                xmppSender.send(routeEntrantResultResponse, racer.getPersonaId());

                if (routeIsFirstPlace) {
                    XMPP_EventTimingOutType eventTimingOut = new XMPP_EventTimingOutType();
                    eventTimingOut.setEventSessionId(eventSessionId);
                    XMPP_ResponseTypeEventTimingOut eventTimingOutResponse = new XMPP_ResponseTypeEventTimingOut();
                    eventTimingOutResponse.setEventTimingOut(eventTimingOut);

                    xmppSender.send(eventTimingOutResponse, racer.getPersonaId());
                }
            }
        }

        result.setEntrants(routeEntrants);

        if (arbitrationPacket.getRank() == 1) {
            // first place, we consider this a win.
            EventDefinitionEntity eventDefinitionEntity = eventData.getEventDefinition();
            String achievementId = null;
            String carClass;
            
            switch ((carClass = CarClasses.getCarClassFromHash(eventDefinitionEntity.getCarClassHash()))) {
                case "E":
                case "D":
                case "C":
                case "B":
                case "A":
                case "S":
                    achievementId = "achievement_ACH_WIN_RACES_" + carClass + "CLASS";
                    break;
                default:
                    break;
            }

//            switch (CarClass.byHash(eventDefinitionEntity.getCarClassHash())) {
//                case A_CLASS: {
//                    achievementId = "achievement_ACH_WIN_RACES_ACLASS";
//                    break;
//                }
//                case B_CLASS: {
//                    achievementId = "achievement_ACH_WIN_RACES_BCLASS";
//                    break;
//                }
//                case C_CLASS: {
//                    achievementId = "achievement_ACH_WIN_RACES_CCLASS";
//                    break;
//                }
//                case D_CLASS: {
//                    achievementId = "achievement_ACH_WIN_RACES_DCLASS";
//                    break;
//                }
//                case E_CLASS: {
//                    achievementId = "achievement_ACH_WIN_RACES_ECLASS";
//                    break;
//                }
//                case S_CLASS: {
//                    achievementId = "achievement_ACH_WIN_RACES_SCLASS";
//                    break;
//                }
//                default:
//                    break;
//            }

            if (achievementId != null) {
                personaAchievementDao.update(
                        persona,
                        achievementDao.findByIdentifier(achievementId),
                        1L,
                        updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
                );
            }
        }

        if (!eventData.getIsSinglePlayer()) {
            // "World Racer" achievement, ONLY multiplayer sprints and circuits
            personaAchievementDao.update(
                    persona,
                    achievementDao.findByIdentifier("achievement_ACH_PLAY_EVENTS"),
                    1L,
                    updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
            );
        }

        return result;
    }

    /**
     * Creates a pursuit arbitration result.
     *
     * @param eventData         The event data entity.
     * @param persona           The persona entity.
     * @param accolades         The accolades instance (holds rewards)
     * @param arbitrationPacket The pursuit arbitration packet sent by the client.
     * @return The event result
     */
    private PursuitEventResult pursuitArbitration(
            EventDataEntity eventData,
            PersonaEntity persona,
            Accolades accolades,
            PursuitArbitrationPacket arbitrationPacket
    )
    {
        Long eventSessionId = eventData.getEventSessionId();
        boolean evaded = arbitrationPacket.getFinishReason() == FinishReason.Evaded.getCode();

        if (evaded) {
            personaAchievementDao.update(
                    persona,
                    achievementDao.findByIdentifier("achievement_ACH_PURSUIT"),
                    1L,
                    updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
            );
        }

        personaAchievementDao.update(
                persona,
                achievementDao.findByIdentifier("achievement_ACH_INCUR_COSTTOSTATE"),
                (long) arbitrationPacket.getCostToState(),
                updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
        );

        PursuitEventResult pursuitEventResult = new PursuitEventResult();

        pursuitEventResult.setDurability(100);
        pursuitEventResult.setEventId(eventData.getEventId());
        pursuitEventResult.setEventSessionId(eventSessionId);
        pursuitEventResult.setExitPath(ExitPath.ExitToFreeroam);
        pursuitEventResult.setInviteLifetimeInMilliseconds(0);
        pursuitEventResult.setLobbyInviteId(0);
        pursuitEventResult.setPersonaId(persona.getId());
        pursuitEventResult.setHeat(arbitrationPacket.getHeat());

        pursuitEventResult.setAccolades(accolades);

        eventData.setCarId(arbitrationPacket.getCarId());
        eventData.setEventDurationInMS(arbitrationPacket.getEventDurationInMilliseconds());
        eventData.setFinishReason(arbitrationPacket.getFinishReason());
        eventData.setRank(arbitrationPacket.getRank());
        eventData.setTopSpeed(arbitrationPacket.getTopSpeed());

        eventDataDao.save(eventData);

        OwnedCarEntity defaultCar = personaBO.defaultcar(persona.getId());
        defaultCar.setHeatLevel(Float.valueOf(arbitrationPacket.getHeat()).shortValue());

        ownedCarDao.save(defaultCar);

        return pursuitEventResult;
    }

    /**
     * Creates a Team Escape arbitration result.
     *
     * @param eventData         The event data entity.
     * @param persona           The persona entity.
     * @param accolades         The accolades instance (holds rewards)
     * @param arbitrationPacket The pursuit arbitration packet sent by the client.
     * @return The event result
     */
    private TeamEscapeEventResult teamEscapeArbitration(
            EventDataEntity eventData,
            PersonaEntity persona,
            Accolades accolades,
            TeamEscapeArbitrationPacket arbitrationPacket
    )
    {
        return null;
    }

    /**
     * Creates a pursuit "busted" result. Pretty much the same as {@link EventBO#pursuitArbitration(EventDataEntity, PersonaEntity, Accolades, PursuitArbitrationPacket)}
     * except for a few differences.
     *
     * @param eventData                The event data entity
     * @param persona                  The persona entity
     * @param eventSessionId           The event session ID
     * @param pursuitArbitrationPacket The pursuit arbitration packet sent by the client
     * @return The event result
     */
    public PursuitEventResult busted(EventDataEntity eventData, PersonaEntity persona, Long eventSessionId, PursuitArbitrationPacket pursuitArbitrationPacket)
    {
        Reward reward = new Reward();
        reward.setRep(0);
        reward.setTokens(0);

        Accolades accolades = new Accolades();
        accolades.setHasLeveledUp(false);
        accolades.setFinalRewards(reward);
        accolades.setOriginalRewards(reward);
        accolades.setRewardInfo(new ArrayList<>());

        PursuitEventResult pursuitEventResult = new PursuitEventResult();

        pursuitEventResult.setDurability(100);
        pursuitEventResult.setEventId(eventData.getEventDefinition().getId());
        pursuitEventResult.setEventSessionId(eventSessionId);
        pursuitEventResult.setExitPath(ExitPath.ExitToFreeroam);
        pursuitEventResult.setInviteLifetimeInMilliseconds(0);
        pursuitEventResult.setLobbyInviteId(0);
        pursuitEventResult.setPersonaId(persona.getId());
        pursuitEventResult.setHeat(pursuitArbitrationPacket.getHeat());
        pursuitEventResult.setAccolades(accolades);

        OwnedCarEntity defaultCar = personaBO.defaultcar(persona.getId());

        defaultCar.setHeatLevel(Float.valueOf(pursuitArbitrationPacket.getHeat()).shortValue());

        ownedCarDao.save(defaultCar);

        // Cops/cost
        eventData.setCopsDeployed(pursuitArbitrationPacket.getCopsDeployed());
        eventData.setCopsDisabled(pursuitArbitrationPacket.getCopsDisabled());
        eventData.setCopsRammed(pursuitArbitrationPacket.getCopsRammed());
        eventData.setCostToState(pursuitArbitrationPacket.getCostToState());
        eventData.setInfractions(pursuitArbitrationPacket.getInfractions());

        // dodged
        eventData.setRoadBlocksDodged(pursuitArbitrationPacket.getRoadBlocksDodged());
        eventData.setSpikeStripsDodged(pursuitArbitrationPacket.getSpikeStripsDodged());

        eventData.setIsSinglePlayer(true);
        eventData.setTopSpeed(pursuitArbitrationPacket.getTopSpeed());
        eventData.setCarId(pursuitArbitrationPacket.getCarId());
        eventData.setEventDurationInMS(pursuitArbitrationPacket.getEventDurationInMilliseconds());
        eventData.setEventModeId(eventData.getEventDefinition().getEventModeId());

        eventDataDao.save(eventData);

        return pursuitEventResult;
    }


    /**
     * Generate accolades for an event data entity, based on its associated event's mode.
     *
     * @param eventData The event data entity.
     * @param mode      The mode of the event data's event.
     * @return The accolades.
     * <p>
     * TODO: Amplifiers?
     */
    private Accolades createAccolades(EventDataEntity eventData, EventMode mode, AbstractArbitrationPacket arbitrationPacket)
    {
        Accolades accolades = new Accolades();

        if (!finishedEvent(arbitrationPacket))
            return accolades;
        Reward baseReward = new Reward();
        baseReward.setRep(0);
        baseReward.setTokens(0);

        PersonaEntity persona = personaDao.findById(eventData.getPersonaId());

        EventDefinitionEntity eventDefinition = eventData.getEventDefinition();
        Pair<Reward, List<RewardPart>> rewardPartsPair = arbitrationPacket.calculateRewardParts(eventDefinition, baseReward);

        baseReward.setRep(rewardPartsPair.getKey().getRep());
        baseReward.setTokens(rewardPartsPair.getKey().getTokens());

        accolades.setFinalRewards(new Reward()
        {
            {
                setRep(baseReward.getRep());
                setTokens(baseReward.getTokens());
            }
        });

        accolades.setHasLeveledUp(false);
        accolades.setLuckyDrawInfo(createDropForMode(eventData, mode, arbitrationPacket));
        accolades.setOriginalRewards(arbitrationPacket.calculateBaseReward(eventDefinition));
        accolades.setRewardInfo(rewardPartsPair.getValue());

        persona.setCash(persona.getCash() + baseReward.getTokens());
        personaDao.save(persona);

        if (baseReward.getTokens() > 0) {
            // giving a negative amount would basically travel back in progress. we don't want that.
            personaAchievementDao.update(
                    persona,
                    achievementDao.findByIdentifier("achievement_ACH_EARN_CASH_EVENT"),
                    (long) baseReward.getTokens(),
                    updateInfo -> AchievementQueue.get(persona.getId()).add(updateInfo)
            );
        }

        return accolades;

//        Reward currencyReward = new Reward();
//        List<RewardPart> rewardParts = new ArrayList<>();
//
//        int repBase = 2000;
//        int tokenBase = 5000;
//
//        tokenBase += 100000;
//        tokenBase += 10000000;
//
//        repBase += 10000000;
//        repBase += 10000000;
//
//        // Reward generation
//        currencyReward.setRep(repBase);
//        currencyReward.setTokens(tokenBase);
//
//        RewardPart tokenAmplifier = new RewardPart();
//        tokenAmplifier.setRewardCategory(RewardCategory.Amplifier);
//        tokenAmplifier.setRewardType(RewardType.TokenAmplifier);
//        tokenAmplifier.setTokenPart(100000);
//
//        RewardPart repAmplifier = new RewardPart();
//        repAmplifier.setRewardCategory(RewardCategory.Amplifier);
//        repAmplifier.setRewardType(RewardType.RepAmplifier);
//        repAmplifier.setRepPart(10000000);
//
//        RewardPart dankBonus = new RewardPart();
//        dankBonus.setRewardCategory(RewardCategory.Bonus);
//        dankBonus.setRewardType(RewardType.None);
//        dankBonus.setRepPart(10000000);
//        dankBonus.setTokenPart(10000000);
//
//        rewardParts.addAll(Arrays.asList(tokenAmplifier, repAmplifier, dankBonus));
//
//        // Finish
//        accolades.setHasLeveledUp(false);
//        accolades.setFinalRewards(currencyReward);
//        accolades.setOriginalRewards(currencyReward);
//        accolades.setRewardInfo(rewardParts);
//        accolades.setLuckyDrawInfo(createDropForMode(eventData, mode, arbitrationPacket));
//
//        return accolades;
    }

    /**
     * Generate Lucky Draw data for an event data entity, based on its associated event's mode.
     *
     * @param eventData The event data entity.
     * @param mode      The mode of the event data's event.
     * @return The Lucky Draw info.
     */
    private LuckyDrawInfo createDropForMode(EventDataEntity eventData, EventMode mode, AbstractArbitrationPacket arbitrationPacket)
    {
        LuckyDrawInfo luckyDrawInfo = new LuckyDrawInfo();
        luckyDrawInfo.setCardDeck(mode == EventMode.Pursuit_SP ? CardDecks.Silver.getCardDeckType() : CardDecks.forRank(arbitrationPacket.getRank()));

        LuckyDrawItem luckyDrawItem = new LuckyDrawItem();

        switch (mode) {
            case Sprint:
            case Circuit:
            case Pursuit_SP:
            case Pursuit_MP:
            case Drag:
                luckyDrawItem.setDescription("DANK MEME");
                luckyDrawItem.setHash(-611661916L);
                luckyDrawItem.setIcon("prod_powerup_stack_evade");
                luckyDrawItem.setRemainingUseCount(0);
                luckyDrawItem.setResellPrice(0);
                luckyDrawItem.setVirtualItem("emergencyevade");
                luckyDrawItem.setVirtualItemType("POWERUP");
                luckyDrawItem.setWasSold(false);
                break;
            default:
                break;
        }

        luckyDrawInfo.setLuckyDrawItem(luckyDrawItem);

        System.out.println("luckyDrawInfo = " + luckyDrawInfo);
        System.out.println("luckyDrawItem = " + luckyDrawItem);

        return luckyDrawInfo;
    }

    /**
     * Determine if the player finished an event.
     */
    private boolean finishedEvent(AbstractArbitrationPacket arbitrationPacket)
    {
        FinishReason finishReason = FinishReason.fromCode(arbitrationPacket.getFinishReason());
        switch (finishReason) {
            case Aborted:
            case DidNotFinish:
            case FalseStart:
            case KnockedOut:
            case TimedOut:
            case TimeLimitExpired:
            case Totalled:
            case Unknown:
                return false;
        }

        return true;
    }
}
