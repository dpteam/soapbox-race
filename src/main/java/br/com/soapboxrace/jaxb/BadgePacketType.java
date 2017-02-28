package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "BadgePacket")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"achievementRankId", "badgeDefinitionId", "isRare", "rarity", "slotId"})
public class BadgePacketType
{
    @XmlElement(name = "AchievementRankId")
    private long achievementRankId;
    
    @XmlElement(name = "BadgeDefinitionId")
    private long badgeDefinitionId;
    
    @XmlElement(name = "IsRare")
    private boolean isRare;
    
    @XmlElement(name = "Rarity")
    private float rarity;
    
    @XmlElement(name = "SlotId")
    private short slotId;

    public long getAchievementRankId()
    {
        return achievementRankId;
    }

    public void setAchievementRankId(long achievementRankId)
    {
        this.achievementRankId = achievementRankId;
    }

    public long getBadgeDefinitionId()
    {
        return badgeDefinitionId;
    }

    public void setBadgeDefinitionId(long badgeDefinitionId)
    {
        this.badgeDefinitionId = badgeDefinitionId;
    }

    public boolean isRare()
    {
        return isRare;
    }

    public void setRare(boolean rare)
    {
        isRare = rare;
    }

    public float getRarity()
    {
        return rarity;
    }

    public void setRarity(float rarity)
    {
        this.rarity = rarity;
    }

    public short getSlotId()
    {
        return slotId;
    }

    public void setSlotId(short slotId)
    {
        this.slotId = slotId;
    }
}
