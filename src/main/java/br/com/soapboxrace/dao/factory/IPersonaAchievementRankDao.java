package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.AchievementRankEntity;
import br.com.soapboxrace.jpa.PersonaAchievementRankEntity;
import br.com.soapboxrace.jpa.PersonaEntity;

import java.util.List;

public interface IPersonaAchievementRankDao extends ISoapboxDao
{
    List<PersonaAchievementRankEntity> getForPersona(PersonaEntity personaEntity, AchievementDefinitionEntity achievement);

    List<PersonaAchievementRankEntity> getForRank(AchievementRankEntity rank);
    
    PersonaAchievementRankEntity getForPersona(PersonaEntity personaEntity, AchievementDefinitionEntity achievement, AchievementRankEntity rank);
}
