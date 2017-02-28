package br.com.soapboxrace.jaxb.events.results;

import br.com.soapboxrace.jaxb.events.AbstractEventResult;
import br.com.soapboxrace.jaxb.events.entrants.RouteEntrantResult;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "RouteEventResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteEventResult extends AbstractEventResult
{
    @XmlElementWrapper(name = "Entrants")
    @XmlElement(name = "RouteEntrantResult")
    private List<RouteEntrantResult> entrants;

    public List<RouteEntrantResult> getEntrants()
    {
        return entrants;
    }

    public void setEntrants(List<RouteEntrantResult> entrants)
    {
        this.entrants = entrants;
    }
}
