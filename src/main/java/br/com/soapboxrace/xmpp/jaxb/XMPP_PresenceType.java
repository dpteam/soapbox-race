package br.com.soapboxrace.xmpp.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "presence")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMPP_PresenceType
{
    @XmlAttribute(name = "to")
    private String toJid;
    
    @XmlAttribute(name = "from")
    private String fromJid;
    
    @XmlAttribute
    private String type;
    
    @XmlElement(name = "x")
    private XMPP_XType x;

    public String getToJid()
    {
        return toJid;
    }

    public void setToJid(String toJid)
    {
        this.toJid = toJid;
    }

    public String getFromJid()
    {
        return fromJid;
    }

    public void setFromJid(String fromJid)
    {
        this.fromJid = fromJid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public XMPP_XType getX()
    {
        return x;
    }

    public void setX(XMPP_XType x)
    {
        this.x = x;
    }
}
