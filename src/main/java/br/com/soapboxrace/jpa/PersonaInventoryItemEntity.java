package br.com.soapboxrace.jpa;

import br.com.soapboxrace.jaxb.InventoryItemTransType;

import javax.persistence.*;

@Table(name = "PERSONAINVENTORYITEM")
@Entity
public class PersonaInventoryItemEntity implements ISoapBoxEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventoryId", referencedColumnName = "id")
    private PersonaInventoryEntity inventory;

    @ManyToOne
    @JoinColumn(name = "personaId", referencedColumnName = "id")
    private PersonaEntity persona;

    private String entitlementTag;

    private String expirationDate;

    private long hash;

    private String productId;

    private int remainingUseCount;

    private float resalePrice;

    private String status;

    private String stringHash;

    private String virtualItemType;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    public PersonaInventoryEntity getInventory()
    {
        return inventory;
    }

    public void setInventory(PersonaInventoryEntity inventory)
    {
        this.inventory = inventory;
    }

    public PersonaEntity getPersona()
    {
        return persona;
    }

    public void setPersona(PersonaEntity persona)
    {
        this.persona = persona;
    }

    public String getEntitlementTag()
    {
        return entitlementTag;
    }

    public void setEntitlementTag(String entitlementTag)
    {
        this.entitlementTag = entitlementTag;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public long getHash()
    {
        return hash;
    }

    public void setHash(long hash)
    {
        this.hash = hash;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public int getRemainingUseCount()
    {
        return remainingUseCount;
    }

    public void setRemainingUseCount(int remainingUseCount)
    {
        this.remainingUseCount = remainingUseCount;
    }

    public float getResalePrice()
    {
        return resalePrice;
    }

    public void setResalePrice(float resalePrice)
    {
        this.resalePrice = resalePrice;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStringHash()
    {
        return stringHash;
    }

    public void setStringHash(String stringHash)
    {
        this.stringHash = stringHash;
    }

    public String getVirtualItemType()
    {
        return virtualItemType;
    }

    public void setVirtualItemType(String virtualItemType)
    {
        this.virtualItemType = virtualItemType;
    }

    public InventoryItemTransType toInventoryItem()
    {
        InventoryItemTransType inventoryItemTransType = new InventoryItemTransType();
        
        inventoryItemTransType.setRemainingUseCount(remainingUseCount);
        
        inventoryItemTransType.setStatus(status);
        inventoryItemTransType.setStringHash(stringHash);
        inventoryItemTransType.setProductId(productId);
        inventoryItemTransType.setExpirationDate(expirationDate);
        inventoryItemTransType.setEntitlementTag(entitlementTag);
        inventoryItemTransType.setVirtualItemType(virtualItemType);
        inventoryItemTransType.setInventoryId(id);
        inventoryItemTransType.setHash(hash);
        inventoryItemTransType.setResellPrice(resalePrice);
        
        return inventoryItemTransType;
    }
}
