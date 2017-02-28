package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "AchievementsPacket")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"badgeDefinitionPackets", "achievementDefinitionPackets", "personaId"})
public class AchievementsPacketType
{
    @XmlElementWrapper(name = "Badges")
    @XmlElement(name = "BadgeDefinitionPacket")
    private List<BadgeDefinitionPacketType> badgeDefinitionPackets;
    
    @XmlElementWrapper(name = "Definitions")
    @XmlElement(name = "AchievementDefinitionPacket")
    private List<AchievementDefinitionPacketType> achievementDefinitionPackets;
    
    @XmlElement(name = "PersonaId")
    private Long personaId;

    public List<BadgeDefinitionPacketType> getBadgeDefinitionPackets()
    {
        return badgeDefinitionPackets;
    }

    public void setBadgeDefinitionPackets(List<BadgeDefinitionPacketType> badgeDefinitionPackets)
    {
        this.badgeDefinitionPackets = badgeDefinitionPackets;
    }

    public List<AchievementDefinitionPacketType> getAchievementDefinitionPackets()
    {
        return achievementDefinitionPackets;
    }

    public void setAchievementDefinitionPackets(List<AchievementDefinitionPacketType> achievementDefinitionPackets)
    {
        this.achievementDefinitionPackets = achievementDefinitionPackets;
    }

    public Long getPersonaId()
    {
        return personaId;
    }

    public void setPersonaId(Long personaId)
    {
        this.personaId = personaId;
    }
}
