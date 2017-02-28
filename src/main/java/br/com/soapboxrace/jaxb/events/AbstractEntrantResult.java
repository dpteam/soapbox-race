package br.com.soapboxrace.jaxb.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEntrantResult
{
    @XmlElement(name = "EventDurationInMilliseconds")
    private long eventDurationInMilliseconds;
    
    @XmlElement(name = "EventSessionId")
    private long eventSessionId;
    
    @XmlElement(name = "FinishReason")
    private int finishReason;
    
    @XmlElement(name = "PersonaId")
    private long personaId;
    
    @XmlElement(name = "Ranking")
    private int ranking;

    public long getEventDurationInMilliseconds()
    {
        return eventDurationInMilliseconds;
    }

    public void setEventDurationInMilliseconds(long eventDurationInMilliseconds)
    {
        this.eventDurationInMilliseconds = eventDurationInMilliseconds;
    }

    public long getEventSessionId()
    {
        return eventSessionId;
    }

    public void setEventSessionId(long eventSessionId)
    {
        this.eventSessionId = eventSessionId;
    }

    public int getFinishReason()
    {
        return finishReason;
    }

    public void setFinishReason(int finishReason)
    {
        this.finishReason = finishReason;
    }

    public long getPersonaId()
    {
        return personaId;
    }

    public void setPersonaId(long personaId)
    {
        this.personaId = personaId;
    }

    public int getRanking()
    {
        return ranking;
    }

    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }
}
