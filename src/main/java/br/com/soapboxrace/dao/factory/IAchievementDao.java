package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;

import java.util.List;

public interface IAchievementDao extends ISoapboxDao
{
    AchievementDefinitionEntity findByIdentifier(String friendlyIdentifier);
    
    AchievementDefinitionEntity findByBadgeId(Long badgeId);
    
    List<AchievementDefinitionEntity> getAll();
}
