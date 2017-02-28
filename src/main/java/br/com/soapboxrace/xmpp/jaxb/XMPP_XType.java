package br.com.soapboxrace.xmpp.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XMPP_XType
{
    @XmlElement(name = "item")
    private List<XMPP_ItemType> itemTypes;

    public List<XMPP_ItemType> getItemTypes()
    {
        return itemTypes;
    }

    public void setItemTypes(List<XMPP_ItemType> itemTypes)
    {
        this.itemTypes = itemTypes;
    }
}
