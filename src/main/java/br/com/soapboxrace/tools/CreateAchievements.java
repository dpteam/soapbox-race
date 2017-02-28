package br.com.soapboxrace.tools;

import br.com.soapboxrace.achievements.AchievementReward;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.jaxb.AchievementDefinitionPacketType;
import br.com.soapboxrace.jaxb.AchievementsPacketType;
import br.com.soapboxrace.jaxb.BadgeDefinitionPacketType;
import br.com.soapboxrace.jaxb.util.UnmarshalXML;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.AchievementRankEntity;
import br.com.soapboxrace.jpa.AchievementRewardEntity;
import br.com.soapboxrace.jpa.BadgeDefinitionEntity;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateAchievements
{
    public static void main(String[] args) throws IOException
    {
        Gson gson = new Gson();
        File file = Paths.get("data/achievements.xml").toFile();
        
        if (!file.exists())
            return;
        
        String content = new String(
                Files.readAllBytes(file.toPath()),
                StandardCharsets.UTF_8
        );

        AchievementsPacketType achievementsPacketType = (AchievementsPacketType) UnmarshalXML.unMarshal(content, new AchievementsPacketType());
        
        for (BadgeDefinitionPacketType badgePacket : achievementsPacketType.getBadgeDefinitionPackets()) {
            BadgeDefinitionEntity badge = new BadgeDefinitionEntity();
            badge.setId((long) badgePacket.getBadgeDefinitionId());
            badge.setBackground(badgePacket.getBackground());
            badge.setBorder(badgePacket.getBorder());
            badge.setDescription(badgePacket.getDescription());
            badge.setName(badgePacket.getName());
            badge.setIcon(badgePacket.getIcon());
            
            System.out.printf("creating badge %s", badge.getName());
            System.out.println();

            DaoFactory.getBadgeDefinitionDao().save(badge);
        }
        
        for (AchievementDefinitionPacketType achievementPacket : achievementsPacketType.getAchievementDefinitionPackets()) {
            AchievementDefinitionEntity achievement = new AchievementDefinitionEntity();
            achievement.setBadgeDefinition(DaoFactory.getBadgeDefinitionDao().findById(achievementPacket.getBadgeDefinitionId()));
            achievement.setProgressText(achievementPacket.getProgressText());
            achievement.setVisible(achievementPacket.isVisible());
            achievement.setStatConversion(achievementPacket.getStatConversion());
            
            if (achievement.getBadgeDefinition() == null) {
                System.err.println("null badge def (#" + (achievementsPacketType.getAchievementDefinitionPackets().indexOf(achievementPacket) + 1) + ")");
                continue;
            }
            
            achievement.setFriendlyIdentifier("achievement_" + achievement.getBadgeDefinition().getIcon());
            
            AchievementDefinitionEntity find = DaoFactory.getAchievementDao().findByIdentifier(achievement.getFriendlyIdentifier());
            
            if (find != null)
                continue;
            
            System.out.println(String.format("creating achievement %s", achievement.getFriendlyIdentifier()));
            
            DaoFactory.getAchievementDao().save(achievement);
            
            for (AchievementDefinitionPacketType.AchievementRankPacket rankPacket : achievementPacket.getAchievementRankPackets()) {
                AchievementRankEntity rankEntity = new AchievementRankEntity();
                rankEntity.setId(rankPacket.getId());
                rankEntity.setAchievementDefinition(achievement);
                
                rankEntity.setPoints(rankPacket.getPoints());
                rankEntity.setRare(rankPacket.isRare());
                rankEntity.setRank(rankPacket.getRank());
                
                rankEntity.setRewardType(rankPacket.getRewardType());
                rankEntity.setRewardVisualStyle(rankPacket.getRewardVisualStyle());
                rankEntity.setRewardDescription(rankPacket.getRewardDescription());
                
                rankEntity.setThresholdValue(rankPacket.getThresholdValue());
                
                DaoFactory.getAchievementRankDao().save(rankEntity);
            }
        }
        
        // rewards
        File rewardsFolder = Paths.get("data/achievement_rewards").toFile();
        
        if (rewardsFolder.exists()) {
            System.out.println("Inserting reward data.");
            
            File[] files = rewardsFolder.listFiles((dir, name) -> name.endsWith(".json"));
            
            for (File file1 : files) {
                String identifier = file1.getName().replace(".json", "");
                identifier = "achievement_" + identifier;
                AchievementReward reward = gson.fromJson(new FileReader(file1), AchievementReward.class);

                AchievementDefinitionEntity achievement = DaoFactory.getAchievementDao().findByIdentifier(identifier);
                AchievementRewardEntity rewardEntity = DaoFactory.getAchievementRewardDao().findByAchievementIdentifier(identifier);
                
                boolean replace = false;

                if (rewardEntity != null && !rewardEntity.getReward().equals(reward)) {
                    System.out.println("updating " + identifier);
                    replace = true;
                } else if (rewardEntity != null || achievement == null) {
                    continue;
                }
                
                if (!replace) {
                    System.out.println("inserting rewards for " + identifier);

                    AchievementRewardEntity stub = new AchievementRewardEntity();
                    AchievementDefinitionEntity achievementDefinitionEntity = new AchievementDefinitionEntity();
                    achievementDefinitionEntity.setId(achievement.getId());
                    stub.setAchievement(achievementDefinitionEntity);
                    stub.setReward(reward);

                    DaoFactory.getAchievementRewardDao().save(stub);

                    System.out.println("inserted rewards for " + identifier);
                } else {
                    rewardEntity.setReward(reward);
                    
                    DaoFactory.getAchievementRewardDao().save(rewardEntity);
                    
                    System.out.println("updated " + identifier);
                }
            }
        }
    }
}
