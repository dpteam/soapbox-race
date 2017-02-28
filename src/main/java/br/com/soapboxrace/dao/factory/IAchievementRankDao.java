package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.AchievementRankEntity;

import java.util.List;

public interface IAchievementRankDao extends ISoapboxDao
{
    List<AchievementRankEntity> getForAchievement(AchievementDefinitionEntity achievement);
}
