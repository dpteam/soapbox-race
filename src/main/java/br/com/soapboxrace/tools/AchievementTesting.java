package br.com.soapboxrace.tools;

import br.com.soapboxrace.bo.AchievementsBO;
import br.com.soapboxrace.config.Config;
import br.com.soapboxrace.dao.factory.*;
import br.com.soapboxrace.jpa.PersonaEntity;

public class AchievementTesting
{
    public static void main(String[] args) {
        Config.getInstance();
        
        final IPersonaDao personaDao = DaoFactory.getPersonaDao();
        final IAchievementDao achievementDao = DaoFactory.getAchievementDao();
        final IAchievementRankDao achievementRankDao = DaoFactory.getAchievementRankDao();
        final AchievementsBO achievementsBO = new AchievementsBO();
        
        final IPersonaAchievementDao personaAchievementDao = DaoFactory.getPersonaAchievementDao();
        
        final PersonaEntity persona = personaDao.findById(102L);
        
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_DRIVE_MILES"), 40233613L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_OWN_BMW"), 7L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_CLOCKED_AIRTIME"), 2406092L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_IS_DEVELOPER"), 1L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_REACH_DRIVERLEVEL"), 60L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_COMPLETE_TH"), 500L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_WINSTREAK_TH"), 125L);
        personaAchievementDao.update(persona, achievementDao.findByIdentifier("achievement_ACH_EARN_DRIVERSCORE"), 5L);
    }
}
