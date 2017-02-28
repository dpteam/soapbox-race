package br.com.soapboxrace.jaxb.events.rewards;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reward", propOrder = { "rep", "tokens" })
public class Reward
{
    @XmlElement(name = "Rep")
    private int rep;
    
    @XmlElement(name = "Tokens")
    private int tokens;

    public int getRep()
    {
        return rep;
    }

    public void setRep(int rep)
    {
        this.rep = rep;
    }

    public int getTokens()
    {
        return tokens;
    }

    public void setTokens(int tokens)
    {
        this.tokens = tokens;
    }
}
