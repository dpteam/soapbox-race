package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.AchievementRewardEntity;

public interface IAchievementRewardDao extends ISoapboxDao
{
    AchievementRewardEntity findByAchievementId(Long achievementId);
    
    AchievementRewardEntity findByAchievementIdentifier(String identifier);
}
