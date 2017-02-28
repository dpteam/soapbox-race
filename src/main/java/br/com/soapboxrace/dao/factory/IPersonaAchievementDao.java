package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.achievements.AchievementUpdateInfo;
import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.PersonaAchievementEntity;
import br.com.soapboxrace.jpa.PersonaEntity;

import java.util.List;
import java.util.function.Consumer;

public interface IPersonaAchievementDao extends ISoapboxDao
{
    List<PersonaAchievementEntity> getForPersona(PersonaEntity personaEntity);
    
    List<PersonaAchievementEntity> getForAchievement(AchievementDefinitionEntity achievementEntity);
    
    PersonaAchievementEntity getForPersona(PersonaEntity personaEntity, AchievementDefinitionEntity achievement);
    
    void update(PersonaEntity personaEntity, AchievementDefinitionEntity achievement, Long value);
    
    void update(PersonaEntity personaEntity, AchievementDefinitionEntity achievement, Long value, Consumer<AchievementUpdateInfo> callback);
}
