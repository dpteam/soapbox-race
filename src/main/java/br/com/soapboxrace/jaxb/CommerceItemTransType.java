package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "CommerceItemTrans")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "hash"})
public class CommerceItemTransType
{
    @XmlElement(name = "Title")
    private String title;
    
    @XmlElement(name = "Hash")
    private long hash;

    public CommerceItemTransType()
    {
    }

    public CommerceItemTransType(String title, long hash)
    {
        this.title = title;
        this.hash = hash;
    }

    public long getHash()
    {
        return hash;
    }

    public void setHash(long hash)
    {
        this.hash = hash;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
