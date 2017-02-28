package br.com.soapboxrace.jaxb.events.results;

import br.com.soapboxrace.jaxb.events.AbstractEventResult;
import br.com.soapboxrace.jaxb.events.entrants.TeamEscapeEntrantResult;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "TeamEscapeEventResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamEscapeEventResult extends AbstractEventResult
{
    @XmlElementWrapper(name = "Entrants")
    @XmlElement(name = "TeamEscapeEntrantResult")
    private List<TeamEscapeEntrantResult> entrants;

    public List<TeamEscapeEntrantResult> getEntrants()
    {
        return entrants;
    }

    public void setEntrants(List<TeamEscapeEntrantResult> entrants)
    {
        this.entrants = entrants;
    }
}
