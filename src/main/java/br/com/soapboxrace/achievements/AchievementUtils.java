package br.com.soapboxrace.achievements;

import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.engine.Session;
import br.com.soapboxrace.jaxb.BadgePacketType;
import br.com.soapboxrace.jaxb.achievements.AchievementAwarded;
import br.com.soapboxrace.jaxb.achievements.AchievementProgress;
import br.com.soapboxrace.jaxb.achievements.AchievementsAwarded;
import br.com.soapboxrace.jpa.*;
import br.com.soapboxrace.xmpp.IXmppSender;
import br.com.soapboxrace.xmpp.XmppFactory;
import br.com.soapboxrace.xmpp.jaxb.XMPP_ResponseTypeAchievementsAwarded;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Achievement utilities
 * 
 * @author leorblx
 */
public class AchievementUtils
{
    public static void broadcastProgress(PersonaEntity persona, PersonaAchievementEntity personaAchievementEntity, List<PersonaAchievementRankEntity> ranks, Integer score)
    {
        IXmppSender xmppSender = XmppFactory.getXmppSenderInstance(Session.getXmppServerType());

        XMPP_ResponseTypeAchievementsAwarded achievementsAwardedResponse = new XMPP_ResponseTypeAchievementsAwarded();
        AchievementsAwarded achievementsAwarded = new AchievementsAwarded();

        List<AchievementProgress> achievementProgressList = new ArrayList<>();
        List<AchievementAwarded> achievementAwardedList = new ArrayList<>();
        List<BadgePacketType> badgeList = new ArrayList<>();

        AchievementDefinitionEntity achievement = personaAchievementEntity.getAchievement();
        List<PersonaAchievementRankEntity> personaRanks = DaoFactory.getPersonaAchievementRankDao().getForPersona(
                persona, achievement);

        System.out.println(personaAchievementEntity);
        System.out.println(ranks);

        if (!ranks.isEmpty()) {
            for (PersonaAchievementRankEntity personaRank : ranks) {
                if (personaRank.getState().equals("RewardWaiting")) {
                    AchievementRankEntity baseRank = personaRank.getRank();

                    AchievementAwarded achievementAwarded = new AchievementAwarded();
                    achievementAwarded.setAchievementDefinitionId(achievement.getId());
                    achievementAwarded.setAchievedOn(personaRank.getAchievedOn());
                    achievementAwarded.setAchievementRankId(baseRank.getId());
                    achievementAwarded.setDescription(achievement.getBadgeDefinition().getDescription());
                    achievementAwarded.setIcon(achievement.getBadgeDefinition().getIcon());
                    achievementAwarded.setRare(false);
                    achievementAwarded.setName(achievement.getBadgeDefinition().getName());
                    achievementAwarded.setPoints(baseRank.getPoints());
                    achievementAwarded.setRarity(0f);

                    achievementAwardedList.add(achievementAwarded);
                } else if (personaRank.getState().equals("InProgress")) {
                    System.out.println(achievement.getId());
                    boolean alreadyHas = achievementProgressList.stream().anyMatch(ap -> ap.getAchievementDefinitionId().equals(achievement.getId()));
                    
                    if (alreadyHas) {
                        break;
                    }

                    AchievementProgress achievementProgress = new AchievementProgress();
                    achievementProgress.setAchievementDefinitionId(achievement.getId());
                    achievementProgress.setCurrentValue(personaAchievementEntity.getCurrentValue());

                    achievementProgressList.add(achievementProgress);
                }
            }
        }

        achievementsAwarded.setAchievements(achievementAwardedList.stream().distinct().collect(Collectors.toList()));
        achievementsAwarded.setProgressed(achievementProgressList.stream().distinct().collect(Collectors.toList()));
        achievementsAwarded.setPersonaId(persona.getId());
        achievementsAwarded.setScore(score);

        achievementsAwardedResponse.setAchievementsAwarded(achievementsAwarded);

        xmppSender.send(achievementsAwardedResponse, persona.getId());
    }
}
