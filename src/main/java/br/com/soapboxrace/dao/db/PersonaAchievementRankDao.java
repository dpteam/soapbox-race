package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.IPersonaAchievementRankDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonaAchievementRankDao extends SoapboxDao implements IPersonaAchievementRankDao
{
    @Override
    public ISoapBoxEntity findById(Long id)
    {
        return null;
    }

    @Override
    public List<PersonaAchievementRankEntity> getForPersona(PersonaEntity personaEntity, AchievementDefinitionEntity achievement)
    {
        EntityManager manager = getManager();
        TypedQuery<PersonaAchievementRankEntity> query = manager.createQuery(
                "FROM PersonaAchievementRankEntity WHERE persona = :persona AND achievement = :achievement",
                PersonaAchievementRankEntity.class
        ).setParameter("persona", personaEntity).setParameter("achievement", achievement);

        return query.getResultList();
    }

    @Override
    public List<PersonaAchievementRankEntity> getForRank(AchievementRankEntity rank)
    {
        EntityManager manager = getManager();
        TypedQuery<PersonaAchievementRankEntity> query = manager.createQuery(
                "FROM PersonaAchievementRankEntity WHERE rank = :rank",
                PersonaAchievementRankEntity.class
        ).setParameter("rank", rank);

        return query.getResultList();
    }

    @Override
    public PersonaAchievementRankEntity getForPersona(PersonaEntity personaEntity, AchievementDefinitionEntity achievement, AchievementRankEntity rank)
    {
        EntityManager manager = getManager();
        TypedQuery<PersonaAchievementRankEntity> query = manager.createQuery(
                "FROM PersonaAchievementRankEntity WHERE persona = :persona AND achievement = :achievement AND rank = :rank",
                PersonaAchievementRankEntity.class
        ).setParameter("persona", personaEntity).setParameter("achievement", achievement).setParameter("rank", rank);

        List<PersonaAchievementRankEntity> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }
}
