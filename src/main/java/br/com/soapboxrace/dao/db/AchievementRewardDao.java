package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.IAchievementRewardDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.AchievementRewardEntity;
import br.com.soapboxrace.jpa.ISoapBoxEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class AchievementRewardDao extends SoapboxDao implements IAchievementRewardDao
{
    @Override
    public AchievementRewardEntity findByAchievementId(Long achievementId)
    {
        TypedQuery<AchievementRewardEntity> query = getManager().createQuery(
                "SELECT obj FROM AchievementRewardEntity obj WHERE obj.achievement.id = :achievementId",
                AchievementRewardEntity.class
        ).setParameter("achievementId", achievementId);

        List<AchievementRewardEntity> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public AchievementRewardEntity findByAchievementIdentifier(String identifier)
    {
        TypedQuery<AchievementRewardEntity> query = getManager().createQuery(
                "SELECT obj FROM AchievementRewardEntity obj WHERE obj.achievement.friendlyIdentifier = :friendlyIdentifier",
                AchievementRewardEntity.class
        ).setParameter("friendlyIdentifier", identifier);

        List<AchievementRewardEntity> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public AchievementRewardEntity findById(Long id)
    {
        return (AchievementRewardEntity) super.findById(AchievementRewardEntity.class, id);
    }
}
