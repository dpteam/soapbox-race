package br.com.soapboxrace.jaxb.events;

import br.com.soapboxrace.jaxb.events.rewards.Accolades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEventResult
{
    @XmlElement(name = "Accolades")
    private Accolades accolades;
    
    @XmlElement(name = "Durability")
    private int durability;
    
    @XmlElement(name = "EventId")
    private Long eventId;
    
    @XmlElement(name = "EventSessionId")
    private long eventSessionId;
    
    @XmlElement(name = "ExitPath")
    private ExitPath exitPath;
    
    @XmlElement(name = "InviteLifetimeInMilliseconds")
    private int inviteLifetimeInMilliseconds;
    
    @XmlElement(name = "LobbyInviteId")
    private long lobbyInviteId;
    
    @XmlElement(name = "PersonaId")
    private long personaId;

    public Accolades getAccolades()
    {
        return accolades;
    }

    public void setAccolades(Accolades accolades)
    {
        this.accolades = accolades;
    }

    public int getDurability()
    {
        return durability;
    }

    public void setDurability(int durability)
    {
        this.durability = durability;
    }

    public Long getEventId()
    {
        return eventId;
    }

    public void setEventId(Long eventId)
    {
        this.eventId = eventId;
    }

    public long getEventSessionId()
    {
        return eventSessionId;
    }

    public void setEventSessionId(long eventSessionId)
    {
        this.eventSessionId = eventSessionId;
    }

    public ExitPath getExitPath()
    {
        return exitPath;
    }

    public void setExitPath(ExitPath exitPath)
    {
        this.exitPath = exitPath;
    }

    public int getInviteLifetimeInMilliseconds()
    {
        return inviteLifetimeInMilliseconds;
    }

    public void setInviteLifetimeInMilliseconds(int inviteLifetimeInMilliseconds)
    {
        this.inviteLifetimeInMilliseconds = inviteLifetimeInMilliseconds;
    }

    public long getLobbyInviteId()
    {
        return lobbyInviteId;
    }

    public void setLobbyInviteId(long lobbyInviteId)
    {
        this.lobbyInviteId = lobbyInviteId;
    }

    public long getPersonaId()
    {
        return personaId;
    }

    public void setPersonaId(long personaId)
    {
        this.personaId = personaId;
    }
}
