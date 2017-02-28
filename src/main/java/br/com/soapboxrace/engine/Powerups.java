package br.com.soapboxrace.engine;

import br.com.soapboxrace.achievements.AchievementUtils;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IAchievementDao;
import br.com.soapboxrace.dao.factory.IPersonaAchievementDao;
import br.com.soapboxrace.dao.factory.IPersonaDao;
import br.com.soapboxrace.jaxb.achievements.AchievementAwarded;
import br.com.soapboxrace.jaxb.achievements.AchievementProgress;
import br.com.soapboxrace.jaxb.achievements.AchievementsAwarded;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.jpa.PersonaInventoryItemEntity;
import br.com.soapboxrace.xmpp.IXmppSender;
import br.com.soapboxrace.xmpp.XmppFactory;
import br.com.soapboxrace.xmpp.jaxb.XMPP_PowerupActivatedType;
import br.com.soapboxrace.xmpp.jaxb.XMPP_ResponseTypeAchievementsAwarded;
import br.com.soapboxrace.xmpp.jaxb.XMPP_ResponseTypePowerupActivated;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Powerups extends Router
{
    private final Map<Long, String> powerupHashMap = ImmutableMap.<Long, String>builder()
            .put(-1681514783L, "nosshot")
            .put(-537557654L, "runflattires")
            .put(-1692359144L, "instantcooldown")
            .put(-364944936L, "shield")
            .put(2236629L, "slingshot")
            .put(957701799L, "ready")
            .put(1805681994L, "juggernaut")
            .put(-611661916L, "emergencyevade")
            .put(-1564932069L, "team_emergencyevade")
            .put(1627606782L, "onemorelap")
            .put(1113720384L, "team_slingshot")
            .put(125509666L, "trafficmagnet")
            .build();
    
    private Long getPowerupHash()
    {
        long powerupHash = 0L;
        String[] targetSplitted = getTarget().split("/");
        if (targetSplitted.length == 6) {
            powerupHash = Long.valueOf(targetSplitted[5]);
        }
        return powerupHash;
    }

    public String activated()
    {
        final PersonaEntity personaEntity = DaoFactory.getPersonaDao().findById(getLoggedPersonaId());
        XMPP_ResponseTypePowerupActivated powerupActivatedResponse = new XMPP_ResponseTypePowerupActivated();
        XMPP_PowerupActivatedType powerupActivated = new XMPP_PowerupActivatedType();
        powerupActivated.setId(getPowerupHash());
        powerupActivated.setTargetPersonaId(Long.valueOf(getParam("targetId")));
        powerupActivated.setPersonaId(getLoggedPersonaId());
        powerupActivatedResponse.setPowerupActivated(powerupActivated);

        for (String receiver : getParam("receivers").split("-")) {
            Long receiverPersonaId = Long.valueOf(receiver);
            if (receiverPersonaId > 10) {
                IXmppSender xmppSenderInstance = XmppFactory.getXmppSenderInstance(Session.getXmppServerType());
                xmppSenderInstance.send(powerupActivatedResponse, receiverPersonaId);
            }
        }
        
        DaoFactory.getPersonaAchievementDao().update(
                personaEntity,
                DaoFactory.getAchievementDao().findByIdentifier("achievement_ACH_USE_NOS"),
                1L,
                updateInfo -> AchievementUtils.broadcastProgress(personaEntity, updateInfo.getPersonaAchievement(), updateInfo.getRanks(), updateInfo.getScore())
        );

        final PersonaInventoryItemEntity inventoryItemEntity = DaoFactory.getPersonaInventoryDao().getByEntitlementTag(
                DaoFactory.getPersonaInventoryDao().getForPersona(personaEntity),
                powerupHashMap.get(getPowerupHash())
        );
        
        DaoFactory.getPersonaInventoryItemDao().updateRemaining(
                inventoryItemEntity,
                inventoryItemEntity.getRemainingUseCount() - 1
        );
        
        return "";
    }
}