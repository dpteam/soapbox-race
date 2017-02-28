package br.com.soapboxrace.jaxb.announcements;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "LoginAnnouncementsDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginAnnouncementsDefinitionType", propOrder = {"announcements", "imagesPath"})
public class LoginAnnouncementsDefinitionType
{
    @XmlElement(name = "ImagesPath")
    private String imagesPath;
    
    @XmlElementWrapper(name = "Announcements")
    @XmlElement(name = "LoginAnnouncementDefinition")
    private List<LoginAnnouncementDefinitionType> announcements;

    public String getImagesPath()
    {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath)
    {
        this.imagesPath = imagesPath;
    }

    public List<LoginAnnouncementDefinitionType> getAnnouncements()
    {
        return announcements;
    }

    public void setAnnouncements(List<LoginAnnouncementDefinitionType> announcements)
    {
        this.announcements = announcements;
    }
}
