package br.com.soapboxrace.jaxb.events.entrants;

import br.com.soapboxrace.jaxb.events.AbstractEntrantResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RouteEntrantResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteEntrantResult extends AbstractEntrantResult
{
    @XmlElement(name = "BestLapDurationInMilliseconds")
    private long bestLapDurationInMilliseconds;
    
    @XmlElement(name = "TopSpeed")
    private float topSpeed;

    public float getTopSpeed()
    {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed)
    {
        this.topSpeed = topSpeed;
    }

    public long getBestLapDurationInMilliseconds()
    {
        return bestLapDurationInMilliseconds;
    }

    public void setBestLapDurationInMilliseconds(long bestLapDurationInMilliseconds)
    {
        this.bestLapDurationInMilliseconds = bestLapDurationInMilliseconds;
    }
}
