package br.com.soapboxrace.engine;

import br.com.soapboxrace.bo.legacy.LegacyPersonaBO;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.definition.ServerExceptions;
import br.com.soapboxrace.jaxb.BadgeBundleType;
import br.com.soapboxrace.jaxb.util.UnmarshalXML;
import br.com.soapboxrace.jpa.*;

public class Badges extends Router
{
    private final LegacyPersonaBO personaBO = new LegacyPersonaBO();
    
    public String set() throws ServerExceptions.EngineException
    {
        checkSecurityToken();
        
        String badges = readInputStream();
        BadgeBundleType badgeBundle = (BadgeBundleType) UnmarshalXML.unMarshal(badges, new BadgeBundleType());
        PersonaEntity persona = DaoFactory.getPersonaDao().findById(getLoggedPersonaId());
        
        System.out.println(badges);
        
        personaBO.updateBadges(persona.getId(), badgeBundle);
        
        return "";
    }
}
