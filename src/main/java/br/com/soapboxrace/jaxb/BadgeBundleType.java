package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "BadgeBundle")
@XmlAccessorType(XmlAccessType.FIELD)
public class BadgeBundleType
{
    @XmlElementWrapper(name = "Badges")
    @XmlElement(name = "BadgeInput")
    private List<BadgeInputType> badgeInputs = new ArrayList<>();

    public List<BadgeInputType> getBadgeInputs()
    {
        return badgeInputs;
    }

    public void setBadgeInputs(List<BadgeInputType> badgeInputs)
    {
        this.badgeInputs = badgeInputs;
    }
}
