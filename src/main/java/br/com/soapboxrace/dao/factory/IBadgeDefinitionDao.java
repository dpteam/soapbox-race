package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.BadgeDefinitionEntity;

import java.util.List;

public interface IBadgeDefinitionDao extends ISoapboxDao
{
    BadgeDefinitionEntity findById(Long id);

    BadgeDefinitionEntity findByIcon(String icon);
    
    List<BadgeDefinitionEntity> getAll();
}
