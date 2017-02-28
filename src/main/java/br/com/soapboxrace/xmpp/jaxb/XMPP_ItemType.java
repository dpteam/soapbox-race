package br.com.soapboxrace.xmpp.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class XMPP_ItemType
{
    @XmlAttribute
    private String jid;
    
    @XmlAttribute
    private String affiliation;
    
    @XmlAttribute
    private String role;

    public String getJid()
    {
        return jid;
    }

    public void setJid(String jid)
    {
        this.jid = jid;
    }

    public String getAffiliation()
    {
        return affiliation;
    }

    public void setAffiliation(String affiliation)
    {
        this.affiliation = affiliation;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
