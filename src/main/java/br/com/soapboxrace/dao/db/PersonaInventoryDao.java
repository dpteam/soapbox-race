package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IPersonaInventoryDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.jpa.PersonaInventoryEntity;
import br.com.soapboxrace.jpa.PersonaInventoryItemEntity;

import javax.persistence.TypedQuery;

public class PersonaInventoryDao extends SoapboxDao implements IPersonaInventoryDao
{
    @Override
    public PersonaInventoryEntity findById(Long id)
    {
        return (PersonaInventoryEntity) super.findById(PersonaInventoryEntity.class, id);
    }

    @Override
    public PersonaInventoryEntity getForPersona(PersonaEntity personaEntity)
    {
        TypedQuery<PersonaInventoryEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaInventoryEntity obj WHERE obj.persona.id = :id",
                PersonaInventoryEntity.class
        ).setParameter("id", personaEntity.getId());

        return query.getSingleResult();
    }

    @Override
    public PersonaInventoryItemEntity getByEntitlementTag(PersonaInventoryEntity inventoryEntity, String entitlementTag)
    {
        TypedQuery<PersonaInventoryItemEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaInventoryItemEntity obj WHERE obj.inventory.id = :id AND obj.entitlementTag = :tag",
                PersonaInventoryItemEntity.class
        ).setParameter("id", inventoryEntity.getId()).setParameter("tag", entitlementTag);

        return query.getSingleResult();
    }

    @Override
    public PersonaInventoryItemEntity getByHash(PersonaInventoryEntity inventoryEntity, Long hash)
    {
        TypedQuery<PersonaInventoryItemEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaInventoryItemEntity obj WHERE obj.inventory.id = :id AND obj.hash = :hash",
                PersonaInventoryItemEntity.class
        ).setParameter("id", inventoryEntity.getId()).setParameter("hash", hash);

        return query.getSingleResult();
    }

    @Override
    public PersonaInventoryItemEntity createOrUpdate(PersonaInventoryEntity inventoryEntity, Long hash, Long count)
    {
        PersonaInventoryItemEntity itemEntity = DaoFactory.getPersonaInventoryItemDao().findByHash(inventoryEntity, hash);
        
        if (itemEntity == null) {
            itemEntity = new PersonaInventoryItemEntity();
            itemEntity.setRemainingUseCount(count.intValue());
            itemEntity.setStatus("ACTIVE");
            itemEntity.setStringHash("0x" + Long.toHexString(hash));
            itemEntity.setHash(hash);
            itemEntity.setInventory(inventoryEntity);
            itemEntity.setVirtualItemType(DaoFactory.getProductDao().findByHash(hash).getProductType().toLowerCase());
            itemEntity.setEntitlementTag(DaoFactory.getProductDao().findByHash(hash).getIcon());
            itemEntity.setProductId(DaoFactory.getProductDao().findByHash(hash).getProductId());
            itemEntity.setPersona(inventoryEntity.getPersona());
            itemEntity.setResalePrice(DaoFactory.getProductDao().findByHash(hash).getPrice());
        } else {
            itemEntity.setRemainingUseCount((int) (itemEntity.getRemainingUseCount() + count));
        }
        
        itemEntity = (PersonaInventoryItemEntity) DaoFactory.getPersonaInventoryItemDao().save(itemEntity);
        
        return itemEntity;
    }
}
