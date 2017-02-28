package br.com.soapboxrace.tools;

import br.com.soapboxrace.bo.legacy.LegacyPersonaBO;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IAchievementDao;
import br.com.soapboxrace.jaxb.BadgeBundleType;
import br.com.soapboxrace.jaxb.BadgeInputType;
import br.com.soapboxrace.jpa.BadgeDefinitionEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BadgeTesting
{
    private static final LegacyPersonaBO personaBO = new LegacyPersonaBO();
    private static final IAchievementDao achievementDao = DaoFactory.getAchievementDao();
    
    public static void main(String[] args)
    {
        BadgeBundleType badgeBundleType = new BadgeBundleType();
        List<BadgeInputType> badgeInputs = new ArrayList<>();

        BadgeDefinitionEntity ownBmw = achievementDao.findByIdentifier("achievement_ACH_OWN_BMW")
                .getBadgeDefinition();
        BadgeDefinitionEntity completeTH = achievementDao.findByIdentifier("achievement_ACH_COMPLETE_TH")
                .getBadgeDefinition();
        BadgeDefinitionEntity thWinStreak = achievementDao.findByIdentifier("achievement_ACH_WINSTREAK_TH")
                .getBadgeDefinition();
        
        List<BadgeDefinitionEntity> list = Arrays.asList(ownBmw, completeTH, thWinStreak);
        
        for (int i = 0; i < list.size(); i++) {
            BadgeInputType input = new BadgeInputType();
            input.setSlotId((short) i);
            input.setBadgeDefinitionId(list.get(i).getId().intValue());
            
            badgeInputs.add(input);
        }
        
        badgeBundleType.setBadgeInputs(badgeInputs);
        
        personaBO.updateBadges(100, badgeBundleType);
        
//        BadgeInputType 
        
//        personaBO.updateBadges(100);
    }
}
