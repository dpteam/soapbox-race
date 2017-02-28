package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.IAchievementRankDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.AchievementRankEntity;

import java.util.List;

public class AchievementRankDao extends SoapboxDao implements IAchievementRankDao
{
    @Override
    public List<AchievementRankEntity> getForAchievement(AchievementDefinitionEntity achievement)
    {
        AchievementRankEntity achievementRankEntity = new AchievementRankEntity();
        achievementRankEntity.setAchievementDefinition(achievement);
        
        return (List<AchievementRankEntity>) (List<?>) super.find(achievementRankEntity);
    }

    @Override
    public AchievementRankEntity findById(Long id)
    {
        return (AchievementRankEntity) super.findById(AchievementRankEntity.class, id);
    }
}
