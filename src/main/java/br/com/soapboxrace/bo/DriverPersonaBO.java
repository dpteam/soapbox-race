package br.com.soapboxrace.bo;

import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IPersonaDao;
import br.com.soapboxrace.definition.PresetExceptions;
import br.com.soapboxrace.engine.Router;
import br.com.soapboxrace.jaxb.*;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.jpa.PersonaInventoryEntity;
import br.com.soapboxrace.jpa.UserEntity;
import br.com.soapboxrace.utils.MiscUtils;
import br.com.soapboxrace.xmpp.openfire.RestApiCli;

import java.util.List;

import static br.com.soapboxrace.definition.ServerExceptions.*;

/**
 * Responsible for retrieving persona-specific information,
 * such as presence and persona profile data.
 * <p>
 * Also responsible for:
 * - name checking
 * - status updates
 * - creating/deleting personas
 *
 * @author leorblx
 * @see DriverPersonaBO#reserveName(String)
 * @see DriverPersonaBO#createPersona(Long, String, int)
 */
public class DriverPersonaBO
{
    private final IPersonaDao personaDao = DaoFactory.getPersonaDao();

    /**
     * Creates name suggestions for a given name.
     * Suggestions are only included if the given name is not available.
     *
     * @param name The requested persona name.
     * @return An array of name suggestions; empty if the name is available
     */
    public ArrayOfstringType reserveName(String name)
    {
        ArrayOfstringType arrayOfstringType = new ArrayOfstringType();

        if (personaDao.existsByName(name)) {
            for (int i = 0; i < 3; i++) {
                String recommendation;

                do {
                    recommendation = name + MiscUtils.getRandomNumber(3);
                } while (personaDao.existsByName(recommendation));

                arrayOfstringType.add(recommendation);
            }
        }

        return arrayOfstringType;
    }

    /**
     * Create a persona and return a profile data object.
     *
     * @param userId    The ID of the user that will own the persona.
     * @param name      The name of the persona.
     * @param iconIndex The persona's icon index. (0-26)
     * @return A profile data object for the newly created persona.
     */
    public ProfileDataType createPersona(Long userId, String name, int iconIndex)
    {
        // Creating a dummy user
        UserEntity user = new UserEntity();
        user.setId(userId);

        // Persona entity setup
        PersonaEntity persona = new PersonaEntity();
        persona.setCash(1000000);
        persona.setName(name);
        persona.setIconIndex(iconIndex);
        persona.setUser(user);
        persona.setPercentToLevel(0);
        persona.setRating(0);
        persona.setRep(0);
        persona.setRepAtCurrentLevel(0);
        persona.setScore(0);
        persona.setLevel(1);

        persona = personaDao.save(persona);

        ProfileDataType profileData = new ProfileDataType();
        profileData.setName(persona.getName());
        profileData.setCash(persona.getCash());
        profileData.setIconIndex(persona.getIconIndex());
        profileData.setPersonaId(persona.getId());
        profileData.setLevel(persona.getLevel());

        RestApiCli.createUpdatePersona(persona.getId(), "1234567890123456");
        
        // Inventory creation
        PersonaInventoryEntity inventory = new PersonaInventoryEntity();
        inventory.setPersona(persona);
        
        DaoFactory.getPersonaInventoryDao().save(inventory);

        return profileData;
    }

    /**
     * Deletes a persona by ID.
     *
     * @param personaId The ID of the persona to delete.
     * @throws IllegalArgumentException if no persona is found
     */
    public void deletePersona(long personaId)
    {
        PersonaEntity persona = personaDao.findById(personaId);

        if (persona == null)
            throw new IllegalArgumentException("Persona not found by ID: " + personaId);

        personaDao.del(persona);
    }

    /**
     * Get profile data for a persona.
     *
     * @param personaId The ID of the persona to get data for.
     * @return The profile data.
     * @throws IllegalArgumentException if no persona is found
     */
    public ProfileDataType getPersonaInfo(long personaId)
    {
        PersonaEntity persona = personaDao.findById(personaId);

        if (persona == null)
            throw new IllegalArgumentException("Persona not found by ID: " + personaId);

        ProfileDataType profileDataType = new ProfileDataType();

        profileDataType.setBadges(persona.getBadges());
        profileDataType.setCash(persona.getCash());
        profileDataType.setIconIndex(persona.getIconIndex());
        profileDataType.setLevel(persona.getLevel());
        profileDataType.setMotto(persona.getMotto());
        profileDataType.setName(persona.getName());
        profileDataType.setPercentToLevel(persona.getPercentToLevel());
        profileDataType.setPersonaId(personaId);
        profileDataType.setRating(persona.getRating());
        profileDataType.setRep(persona.getRep());
        profileDataType.setRepAtCurrentLevel(persona.getRepAtCurrentLevel());
        profileDataType.setScore(persona.getScore());

        return profileDataType;
    }

    /**
     * Get an array of persona base objects from a list of persona IDs.
     *
     * @param personaIds The list of persona IDs.
     * @return The array of persona base objects.
     */
    public ArrayOfPersonaBaseType getPersonaBaseFromList(List<Long> personaIds)
    {
        ArrayOfPersonaBaseType arrayOfPersonaBaseType = new ArrayOfPersonaBaseType();
        List<PersonaBaseType> personaBase = arrayOfPersonaBaseType.getPersonaBase();

        for (Long personaId : personaIds) {
            PersonaEntity persona = personaDao.findById(personaId);

            if (persona == null)
                continue;
            PersonaBaseType personaBaseType = new PersonaBaseType();
            personaBaseType.setIconIndex(persona.getIconIndex());
            personaBaseType.setLevel(persona.getLevel());
            personaBaseType.setName(persona.getName());
            personaBaseType.setPersonaId(persona.getId());
            personaBaseType.setMotto(persona.getMotto());
            personaBaseType.setPresence(2);
            personaBaseType.setUserId(persona.getUser().getId());
            personaBaseType.setScore(persona.getScore());
            personaBaseType.setBadges(persona.getBadges());

            personaBase.add(personaBaseType);
        }

        return arrayOfPersonaBaseType;
    }

    /**
     * Get a presence object for a persona by their name.
     *
     * @param name The name of the target persona.
     * @return The persona presence object.
     * @throws EngineException if no persona is found
     */
    public PersonaPresenceType getPersonaPresenceByName(String name) throws EngineException
    {
        PersonaEntity persona = personaDao.findByName(name);

        if (persona == null)
            throw PresetExceptions.PERSONA_NOT_FOUND;
        PersonaPresenceType personaPresenceType = new PersonaPresenceType();
        personaPresenceType.setPersonaId(persona.getId());
        personaPresenceType.setUserId(persona.getUser().getId());

        boolean online = Router.activeUsers.values().stream()
                .anyMatch(s -> s.getPersonaId().equals(persona.getId()));
        personaPresenceType.setPresence(online ? 1 : 0);

        return personaPresenceType;
    }

    /**
     * Update a persona's status message.
     *
     * @param personaId The ID of the persona whose status should be updated.
     * @param message   The new status message.
     * @return A persona motto object.
     * @throws EngineException if no persona is found
     */
    public PersonaMottoType updateStatusMessage(Long personaId, String message) throws EngineException
    {
        PersonaEntity persona = personaDao.findById(personaId);

        if (persona == null)
            throw PresetExceptions.PERSONA_NOT_FOUND;
        PersonaMottoType personaMottoType = new PersonaMottoType();
        personaMottoType.setPersonaId(personaId);
        personaMottoType.setMessage(message);
        
        return personaMottoType;
    }
}
