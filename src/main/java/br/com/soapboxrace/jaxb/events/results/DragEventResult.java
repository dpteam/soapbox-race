package br.com.soapboxrace.jaxb.events.results;

import br.com.soapboxrace.jaxb.events.AbstractEventResult;
import br.com.soapboxrace.jaxb.events.entrants.DragEntrantResult;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "DragEventResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class DragEventResult extends AbstractEventResult
{
    @XmlElementWrapper(name = "Entrants")
    @XmlElement(name = "DragEntrantResult")
    private List<DragEntrantResult> entrants;

    public List<DragEntrantResult> getEntrants()
    {
        return entrants;
    }

    public void setEntrants(List<DragEntrantResult> entrants)
    {
        this.entrants = entrants;
    }
}
