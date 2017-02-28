package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.dao.factory.IBadgeDefinitionDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.BadgeDefinitionEntity;

import java.util.List;

public class BadgeDefinitionDao extends SoapboxDao implements IBadgeDefinitionDao
{
    @Override
    public BadgeDefinitionEntity findById(Long id)
    {
        return (BadgeDefinitionEntity) super.findById(BadgeDefinitionEntity.class, id);
    }

    @Override
    public BadgeDefinitionEntity findByIcon(String icon)
    {
        BadgeDefinitionEntity badge = new BadgeDefinitionEntity();
        badge.setIcon(icon);

        List<BadgeDefinitionEntity> result = (List<BadgeDefinitionEntity>) (List<?>) super.find(badge);

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<BadgeDefinitionEntity> getAll()
    {
        return (List<BadgeDefinitionEntity>) (List<?>) super.find(new BadgeDefinitionEntity());
    }
}
