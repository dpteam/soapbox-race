package br.com.soapboxrace.jaxb.announcements;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "LoginAnnouncementDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginAnnouncementDefinitionType", propOrder = {"imageUrl", "target", "context", "id", "imageChecksum", "type"})
public class LoginAnnouncementDefinitionType
{
    @XmlElement(name = "ImageUrl")
    private String imageUrl;
    
    @XmlElement(name = "Target")
    private String target;
    
    @XmlElement(name = "Context")
    private LoginAnnouncementContext context;
    
    @XmlElement(name = "Id")
    private int id;
    
    @XmlElement(name = "ImageChecksum")
    private int imageChecksum;
    
    @XmlElement(name = "Type")
    private LoginAnnouncementType type;

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public LoginAnnouncementContext getContext()
    {
        return context;
    }

    public void setContext(LoginAnnouncementContext context)
    {
        this.context = context;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getImageChecksum()
    {
        return imageChecksum;
    }

    public void setImageChecksum(int imageChecksum)
    {
        this.imageChecksum = imageChecksum;
    }

    public LoginAnnouncementType getType()
    {
        return type;
    }

    public void setType(LoginAnnouncementType type)
    {
        this.type = type;
    }
}
