package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "BadgeDefinitionPacket")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"background", "badgeDefinitionId", "border", "description", "icon", "name"})
public class BadgeDefinitionPacketType
{
    @XmlElement(name = "Background")
    private String background;
    
    @XmlElement(name = "BadgeDefinitionId")
    private int badgeDefinitionId;
    
    @XmlElement(name = "Border")
    private String border;
    
    @XmlElement(name = "Description")
    private String description;
    
    @XmlElement(name = "Icon")
    private String icon;
    
    @XmlElement(name = "Name")
    private String name;

    public String getBackground()
    {
        return background;
    }

    public void setBackground(String background)
    {
        this.background = background;
    }

    public int getBadgeDefinitionId()
    {
        return badgeDefinitionId;
    }

    public void setBadgeDefinitionId(int badgeDefinitionId)
    {
        this.badgeDefinitionId = badgeDefinitionId;
    }

    public String getBorder()
    {
        return border;
    }

    public void setBorder(String border)
    {
        this.border = border;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
