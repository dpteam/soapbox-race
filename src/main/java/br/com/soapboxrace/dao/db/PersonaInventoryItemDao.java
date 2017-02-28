package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.IPersonaInventoryItemDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.jpa.PersonaInventoryEntity;
import br.com.soapboxrace.jpa.PersonaInventoryItemEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class PersonaInventoryItemDao extends SoapboxDao implements IPersonaInventoryItemDao
{
    @Override
    public PersonaInventoryItemEntity findById(Long id)
    {
        return (PersonaInventoryItemEntity) super.findById(PersonaInventoryItemEntity.class, id);
    }

    @Override
    public List<PersonaInventoryItemEntity> getForPersona(PersonaEntity personaEntity)
    {
        TypedQuery<PersonaInventoryItemEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaInventoryItemEntity obj WHERE obj.persona.id = :id",
                PersonaInventoryItemEntity.class
        ).setParameter("id", personaEntity.getId());

        return query.getResultList();
    }

    @Override
    public PersonaInventoryItemEntity updateRemaining(PersonaInventoryItemEntity inventoryItemEntity, long count)
    {
        inventoryItemEntity.setRemainingUseCount((int) count);

        return (PersonaInventoryItemEntity) save(inventoryItemEntity);
    }

    @Override
    public PersonaInventoryItemEntity findByHash(PersonaInventoryEntity inventoryEntity, Long hash)
    {
        TypedQuery<PersonaInventoryItemEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaInventoryItemEntity obj WHERE obj.hash = :hash",
                PersonaInventoryItemEntity.class
        ).setParameter("hash", hash);
        
        List<PersonaInventoryItemEntity> items = query.getResultList();
        
        return items.isEmpty() ? null : items.get(0);
    }
}
