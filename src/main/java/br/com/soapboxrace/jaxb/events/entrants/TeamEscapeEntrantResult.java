package br.com.soapboxrace.jaxb.events.entrants;

import br.com.soapboxrace.jaxb.events.AbstractEntrantResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TeamEscapeEntrantResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamEscapeEntrantResult extends AbstractEntrantResult
{
    @XmlElement(name = "DistanceToFinish")
    private float distanceToFinish;
    
    @XmlElement(name = "FractionCompleted")
    private float fractionCompleted;

    public float getDistanceToFinish()
    {
        return distanceToFinish;
    }

    public void setDistanceToFinish(float distanceToFinish)
    {
        this.distanceToFinish = distanceToFinish;
    }

    public float getFractionCompleted()
    {
        return fractionCompleted;
    }

    public void setFractionCompleted(float fractionCompleted)
    {
        this.fractionCompleted = fractionCompleted;
    }
}
