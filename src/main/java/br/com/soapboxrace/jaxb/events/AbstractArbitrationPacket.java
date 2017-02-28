package br.com.soapboxrace.jaxb.events;

import br.com.soapboxrace.jaxb.events.rewards.Reward;
import br.com.soapboxrace.jaxb.events.rewards.RewardPart;
import br.com.soapboxrace.jpa.EventDefinitionEntity;
import br.com.soapboxrace.jpa.PersonaEntity;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractArbitrationPacket
{
    @XmlElement(name = "AlternateEventDurationInMilliseconds")
    private long alternateEventDurationInMilliseconds;

    @XmlElement(name = "CarId")
    private long carId;

    @XmlElement(name = "EventDurationInMilliseconds")
    private long eventDurationInMilliseconds;

    @XmlElement(name = "FinishReason")
    private int finishReason;

    @XmlElement(name = "HacksDetected")
    private long hacksDetected;

    @XmlElement(name = "Rank")
    private short rank;
    
    @XmlTransient
    private long eventSessionId;
    
    @XmlTransient
    private PersonaEntity persona;

    public long getAlternateEventDurationInMilliseconds()
    {
        return alternateEventDurationInMilliseconds;
    }

    public void setAlternateEventDurationInMilliseconds(long alternateEventDurationInMilliseconds)
    {
        this.alternateEventDurationInMilliseconds = alternateEventDurationInMilliseconds;
    }

    public long getCarId()
    {
        return carId;
    }

    public void setCarId(long carId)
    {
        this.carId = carId;
    }

    public long getEventDurationInMilliseconds()
    {
        return eventDurationInMilliseconds;
    }

    public void setEventDurationInMilliseconds(long eventDurationInMilliseconds)
    {
        this.eventDurationInMilliseconds = eventDurationInMilliseconds;
    }

    public int getFinishReason()
    {
        return finishReason;
    }

    public void setFinishReason(int finishReason)
    {
        this.finishReason = finishReason;
    }

    public long getHacksDetected()
    {
        return hacksDetected;
    }

    public void setHacksDetected(long hacksDetected)
    {
        this.hacksDetected = hacksDetected;
    }

    public short getRank()
    {
        return rank;
    }

    public void setRank(short rank)
    {
        this.rank = rank;
    }

    public PersonaEntity getPersona()
    {
        return persona;
    }

    public void setPersona(PersonaEntity persona)
    {
        this.persona = persona;
    }

    public long getEventSessionId()
    {
        return eventSessionId;
    }

    public void setEventSessionId(long eventSessionId)
    {
        this.eventSessionId = eventSessionId;
    }

    public abstract Reward calculateBaseReward(EventDefinitionEntity eventDefinition);

    public abstract Pair<Reward, List<RewardPart>> calculateRewardParts(EventDefinitionEntity eventDefinition, Reward rewardBase);
    
    public abstract AbstractEntrantResult getEntrantResult();
}
