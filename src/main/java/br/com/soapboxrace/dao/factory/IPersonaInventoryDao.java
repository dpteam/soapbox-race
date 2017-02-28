package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.*;

import java.util.List;

public interface IPersonaInventoryDao extends ISoapboxDao
{
    PersonaInventoryEntity getForPersona(PersonaEntity personaEntity);
    
    PersonaInventoryItemEntity getByEntitlementTag(PersonaInventoryEntity inventoryEntity, String entitlementTag);
    
    PersonaInventoryItemEntity getByHash(PersonaInventoryEntity inventoryEntity, Long hash);
    
    PersonaInventoryItemEntity createOrUpdate(PersonaInventoryEntity inventoryEntity, Long hash, Long count);
}
