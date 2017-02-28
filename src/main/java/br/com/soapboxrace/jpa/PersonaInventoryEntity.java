package br.com.soapboxrace.jpa;

import javax.persistence.*;
import java.util.List;

@Table(name = "PERSONAINVENTORY")
@Entity
public class PersonaInventoryEntity implements ISoapBoxEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "personaId", referencedColumnName = "ID")
    private PersonaEntity persona;

    private int performancePartsCapacity;
    
    private int performancePartsUsedSlotCount;
    
    private int skillModPartsCapacity;
    
    private int skillModPartsUsedSlotCount;
    
    private int visualPartsCapacity;
    
    private int visualPartsUsedSlotCount;
    
    @OneToMany(mappedBy = "inventory", targetEntity = PersonaInventoryItemEntity.class)
    private List<PersonaInventoryItemEntity> items;
    
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

    public PersonaEntity getPersona()
    {
        return persona;
    }

    public void setPersona(PersonaEntity persona)
    {
        this.persona = persona;
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

    public List<PersonaInventoryItemEntity> getItems()
    {
        return items;
    }

    public void setItems(List<PersonaInventoryItemEntity> items)
    {
        this.items = items;
    }
}
