package br.com.soapboxrace.jaxb.events.results;

import br.com.soapboxrace.jaxb.events.AbstractEventResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PursuitEventResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class PursuitEventResult extends AbstractEventResult
{
    @XmlElement(name = "Heat")
    private float heat;

    public float getHeat()
    {
        return heat;
    }

    public void setHeat(float heat)
    {
        this.heat = heat;
    }
}
