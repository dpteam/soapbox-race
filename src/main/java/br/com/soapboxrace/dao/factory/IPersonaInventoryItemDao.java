package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.jpa.PersonaInventoryEntity;
import br.com.soapboxrace.jpa.PersonaInventoryItemEntity;

import java.util.List;

public interface IPersonaInventoryItemDao extends ISoapboxDao
{
    List<PersonaInventoryItemEntity> getForPersona(PersonaEntity personaEntity);

    PersonaInventoryItemEntity updateRemaining(PersonaInventoryItemEntity inventoryItemEntity, long count);
    
    PersonaInventoryItemEntity findByHash(PersonaInventoryEntity inventoryEntity, Long hash);
}
