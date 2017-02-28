package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "AchievementDefinitionPacket")
@XmlAccessorType(XmlAccessType.FIELD)
public class AchievementDefinitionPacketType
{
    @XmlElement(name = "AchievementDefinitionId")
    private Long id;

    @XmlElementWrapper(name = "AchievementRanks")
    @XmlElement(name = "AchievementRankPacket")
    private List<AchievementRankPacket> achievementRankPackets;
    
    @XmlElement(name = "BadgeDefinitionId")
    private Long badgeDefinitionId;

    @XmlElement(name = "CanProgress")
    private boolean canProgress;

    @XmlElement(name = "CurrentValue")
    private Long currentValue;

    @XmlElement(name = "IsVisible")
    private boolean isVisible;

    @XmlElement(name = "ProgressText")
    private String progressText;

    @XmlElement(name = "StatConversion")
    private String statConversion;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<AchievementRankPacket> getAchievementRankPackets()
    {
        return achievementRankPackets;
    }

    public void setAchievementRankPackets(List<AchievementRankPacket> achievementRankPackets)
    {
        this.achievementRankPackets = achievementRankPackets;
    }

    public Long getBadgeDefinitionId()
    {
        return badgeDefinitionId;
    }

    public void setBadgeDefinitionId(Long badgeDefinitionId)
    {
        this.badgeDefinitionId = badgeDefinitionId;
    }

    public boolean isCanProgress()
    {
        return canProgress;
    }

    public void setCanProgress(boolean canProgress)
    {
        this.canProgress = canProgress;
    }

    public Long getCurrentValue()
    {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue)
    {
        this.currentValue = currentValue;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }

    public String getProgressText()
    {
        return progressText;
    }

    public void setProgressText(String progressText)
    {
        this.progressText = progressText;
    }

    public String getStatConversion()
    {
        return statConversion;
    }

    public void setStatConversion(String statConversion)
    {
        this.statConversion = statConversion;
    }

    @XmlRootElement(name = "AchievementRankPacket")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AchievementRankPacket
    {
        @XmlElement(name = "AchievedOn")
        private String achievedOn;
        
        @XmlElement(name = "AchievementRankId")
        private Long id;
        
        @XmlElement(name = "IsRare")
        private boolean isRare;
        
        @XmlElement(name = "Points")
        private int points;
        
        @XmlElement(name = "Rank")
        private int rank;
        
        @XmlElement(name = "Rarity")
        private float rarity;
        
        @XmlElement(name = "RewardDescription")
        private String rewardDescription;
        
        @XmlElement(name = "RewardType")
        private String rewardType;
        
        @XmlElement(name = "RewardVisualStyle")
        private String rewardVisualStyle;
        
        @XmlElement(name = "State")
        private String state;
        
        @XmlElement(name = "ThresholdValue")
        private Long thresholdValue;

        public String getAchievedOn()
        {
            return achievedOn;
        }

        public void setAchievedOn(String achievedOn)
        {
            this.achievedOn = achievedOn;
        }

        public Long getId()
        {
            return id;
        }

        public void setId(Long id)
        {
            this.id = id;
        }

        public boolean isRare()
        {
            return isRare;
        }

        public void setRare(boolean rare)
        {
            isRare = rare;
        }

        public int getPoints()
        {
            return points;
        }

        public void setPoints(int points)
        {
            this.points = points;
        }

        public int getRank()
        {
            return rank;
        }

        public void setRank(int rank)
        {
            this.rank = rank;
        }

        public float getRarity()
        {
            return rarity;
        }

        public void setRarity(float rarity)
        {
            this.rarity = rarity;
        }

        public String getRewardDescription()
        {
            return rewardDescription;
        }

        public void setRewardDescription(String rewardDescription)
        {
            this.rewardDescription = rewardDescription;
        }

        public String getRewardType()
        {
            return rewardType;
        }

        public void setRewardType(String rewardType)
        {
            this.rewardType = rewardType;
        }

        public String getRewardVisualStyle()
        {
            return rewardVisualStyle;
        }

        public void setRewardVisualStyle(String rewardVisualStyle)
        {
            this.rewardVisualStyle = rewardVisualStyle;
        }

        public String getState()
        {
            return state;
        }

        public void setState(String state)
        {
            this.state = state;
        }

        public Long getThresholdValue()
        {
            return thresholdValue;
        }

        public void setThresholdValue(Long thresholdValue)
        {
            this.thresholdValue = thresholdValue;
        }
    }
}
