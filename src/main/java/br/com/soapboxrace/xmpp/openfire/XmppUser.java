package br.com.soapboxrace.xmpp.openfire;

public class XmppUser
{
    private long personaId;

    public XmppUser(long personaId)
    {
        this.personaId = personaId;
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
