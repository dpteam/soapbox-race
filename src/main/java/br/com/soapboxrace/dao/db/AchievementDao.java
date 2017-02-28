package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.IAchievementDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class AchievementDao extends SoapboxDao implements IAchievementDao
{
    @Override
    public AchievementDefinitionEntity findByIdentifier(String friendlyIdentifier)
    {
        TypedQuery<AchievementDefinitionEntity> query = getManager().createQuery(
                "SELECT obj FROM AchievementDefinitionEntity obj WHERE obj.friendlyIdentifier = :friendlyIdentifier",
                AchievementDefinitionEntity.class
        ).setParameter("friendlyIdentifier", friendlyIdentifier);
        
        List<AchievementDefinitionEntity> results = query.getResultList();
        
        return results.isEmpty() ? null : results.get(0);
        
//        AchievementDefinitionEntity achievement = new AchievementDefinitionEntity();
//        achievement.setFriendlyIdentifier(friendlyIdentifier);
//
//        List<AchievementDefinitionEntity> result = (List<AchievementDefinitionEntity>) (List<?>) super.find(achievement);
//
//        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public AchievementDefinitionEntity findByBadgeId(Long badgeId)
    {
        TypedQuery<AchievementDefinitionEntity> query = getManager().createQuery(
                "SELECT obj FROM AchievementDefinitionEntity obj WHERE obj.badgeDefinition.id = :badgeId",
                AchievementDefinitionEntity.class
        ).setParameter("badgeId", badgeId);

        List<AchievementDefinitionEntity> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<AchievementDefinitionEntity> getAll()
    {
        TypedQuery<AchievementDefinitionEntity> query = getManager().createQuery(
                "SELECT obj FROM AchievementDefinitionEntity obj",
                AchievementDefinitionEntity.class
        );
        
        return query.getResultList();
    }

    @Override
    public AchievementDefinitionEntity findById(Long id)
    {
        return (AchievementDefinitionEntity) super.findById(AchievementDefinitionEntity.class, id);
    }
}
