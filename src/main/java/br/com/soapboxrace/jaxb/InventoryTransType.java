package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "InventoryTrans")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InventoryTransType", propOrder = {
        "inventoryItems",
        "performancePartsCapacity",
        "performancePartsUsedSlotCount",
        "skillModPartsCapacity",
        "skillModPartsUsedSlotCount",
        "visualPartsCapacity",
        "visualPartsUsedSlotCount",
})
public class InventoryTransType
{
    @XmlElement(name = "InventoryItems")
    private InventoryItemsType inventoryItems;
    
    @XmlElement(name = "PerformancePartsCapacity")
    private int performancePartsCapacity;
    
    @XmlElement(name = "PerformancePartsUsedSlotCount")
    private int performancePartsUsedSlotCount;
    
    @XmlElement(name = "SkillModPartsCapacity")
    private int skillModPartsCapacity;
    
    @XmlElement(name = "SkillModPartsUsedSlotCount")
    private int skillModPartsUsedSlotCount;
    
    @XmlElement(name = "VisualPartsCapacity")
    private int visualPartsCapacity;
    
    @XmlElement(name = "VisualPartsUsedSlotCount")
    private int visualPartsUsedSlotCount;

    public InventoryItemsType getInventoryItems()
    {
        return inventoryItems;
    }

    public void setInventoryItems(InventoryItemsType inventoryItems)
    {
        this.inventoryItems = inventoryItems;
    }

    public int getPerformancePartsCapacity()
    {
        return performancePartsCapacity;
    }

    public void setPerformancePartsCapacity(int performancePartsCapacity)
    {
        this.performancePartsCapacity = performancePartsCapacity;
    }

    public int getPerformancePartsUsedSlotCount()
    {
        return performancePartsUsedSlotCount;
    }

    public void setPerformancePartsUsedSlotCount(int performancePartsUsedSlotCount)
    {
        this.performancePartsUsedSlotCount = performancePartsUsedSlotCount;
    }

    public int getSkillModPartsCapacity()
    {
        return skillModPartsCapacity;
    }

    public void setSkillModPartsCapacity(int skillModPartsCapacity)
    {
        this.skillModPartsCapacity = skillModPartsCapacity;
    }

    public int getSkillModPartsUsedSlotCount()
    {
        return skillModPartsUsedSlotCount;
    }

    public void setSkillModPartsUsedSlotCount(int skillModPartsUsedSlotCount)
    {
        this.skillModPartsUsedSlotCount = skillModPartsUsedSlotCount;
    }

    public int getVisualPartsCapacity()
    {
        return visualPartsCapacity;
    }

    public void setVisualPartsCapacity(int visualPartsCapacity)
    {
        this.visualPartsCapacity = visualPartsCapacity;
    }

    public int getVisualPartsUsedSlotCount()
    {
        return visualPartsUsedSlotCount;
    }

    public void setVisualPartsUsedSlotCount(int visualPartsUsedSlotCount)
    {
        this.visualPartsUsedSlotCount = visualPartsUsedSlotCount;
    }
}
