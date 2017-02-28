package br.com.soapboxrace.jaxb.events.rewards.lucky;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "LuckyDrawItem")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LuckyDrawItemType", propOrder = {"description", "hash", "icon", "remainingUseCount", "resellPrice",
        "virtualItem", "virtualItemType", "wasSold"})
public class LuckyDrawItem
{
    @XmlElement(name = "Description", required = true)
    private String description;
    @XmlElement(name = "Hash", required = true)
    private Long hash;
    @XmlElement(name = "Icon", required = true)
    private String icon;
    @XmlElement(name = "RemainingUseCount", required = true)
    private Integer remainingUseCount;
    @XmlElement(name = "ResellPrice", required = true)
    private Integer resellPrice;
    @XmlElement(name = "VirtualItem", required = true)
    private String virtualItem;
    @XmlElement(name = "VirtualItemType", required = true)
    private String virtualItemType;
    @XmlElement(name = "WasSold", defaultValue = "false", required = true)
    private Boolean wasSold = false;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getHash()
    {
        return hash;
    }

    public void setHash(Long hash)
    {
        this.hash = hash;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public Integer getRemainingUseCount()
    {
        return remainingUseCount;
    }

    public void setRemainingUseCount(Integer remainingUseCount)
    {
        this.remainingUseCount = remainingUseCount;
    }

    public Integer getResellPrice()
    {
        return resellPrice;
    }

    public void setResellPrice(Integer resellPrice)
    {
        this.resellPrice = resellPrice;
    }

    public String getVirtualItem()
    {
        return virtualItem;
    }

    public void setVirtualItem(String virtualItem)
    {
        this.virtualItem = virtualItem;
    }

    public String getVirtualItemType()
    {
        return virtualItemType;
    }

    public void setVirtualItemType(String virtualItemType)
    {
        this.virtualItemType = virtualItemType;
    }

    public Boolean getWasSold()
    {
        return wasSold;
    }

    public void setWasSold(Boolean wasSold)
    {
        this.wasSold = wasSold;
    }

    @Override
    public String toString()
    {
        return String.format(
                "LuckyDrawItem(description=%s,hash=%d,icon=%s,remainingUseCount=%d,resellPrice=%d,virtualItem=%s,virtualItemType=%s,wasSold=%s)",
                description,
                hash,
                icon,
                remainingUseCount,
                resellPrice,
                virtualItem,
                virtualItemType,
                wasSold
        );
    }
}
