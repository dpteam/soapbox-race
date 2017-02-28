package br.com.soapboxrace.dao.db;

import br.com.soapboxrace.achievements.AchievementUpdateInfo;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IPersonaAchievementDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.*;

import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class PersonaAchievementDao extends SoapboxDao implements IPersonaAchievementDao
{
    @Override
    public PersonaAchievementEntity findById(Long id)
    {
        return (PersonaAchievementEntity) super.findById(PersonaAchievementEntity.class, id);
    }

    @Override
    public List<PersonaAchievementEntity> getForPersona(PersonaEntity personaEntity)
    {
        TypedQuery<PersonaAchievementEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaAchievementEntity obj WHERE obj.persona.id = :id",
                PersonaAchievementEntity.class
        ).setParameter("id", personaEntity.getId());

        return query.getResultList();
    }

    @Override
    public List<PersonaAchievementEntity> getForAchievement(AchievementDefinitionEntity achievementEntity)
    {
        TypedQuery<PersonaAchievementEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaAchievementEntity obj WHERE obj.achievement.id = :id",
                PersonaAchievementEntity.class
        ).setParameter("id", achievementEntity.getId());

        return query.getResultList();
    }

    @Override
    public PersonaAchievementEntity getForPersona(PersonaEntity personaEntity, AchievementDefinitionEntity achievement)
    {
//        System.out.println(personaEntity);
//        System.out.println(achievement);

        TypedQuery<PersonaAchievementEntity> query = getManager().createQuery(
                "SELECT obj FROM PersonaAchievementEntity obj WHERE obj.persona.id = :id AND obj.achievement.id = :achId",
                PersonaAchievementEntity.class
        ).setParameter("id", personaEntity.getId()).setParameter("achId", achievement.getId());

        List<PersonaAchievementEntity> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public void update(PersonaEntity personaEntity, AchievementDefinitionEntity achievement, Long value)
    {
        update(personaEntity, achievement, value, (personaAchievementEntity) -> {});
    }

    @Override
    public void update(PersonaEntity personaEntity, AchievementDefinitionEntity achievement, Long value, Consumer<AchievementUpdateInfo> callback)
    {
        PersonaAchievementEntity personaAchievement = getForPersona(personaEntity, achievement);

        AchievementUpdateInfo updateInfo = new AchievementUpdateInfo();

        if (personaAchievement == null)
            personaAchievement = createPersonaAchievement(personaEntity, achievement);
        Long maxValue = achievement.getMaximumValue();

        value = personaAchievement.getCurrentValue() + value;

        if (value > maxValue)
            value = maxValue;

//        System.out.println(String.format("Max value for %s = %d", achievement.getFriendlyIdentifier(), maxValue));

        Long currentValue = personaAchievement.getCurrentValue();
        personaAchievement.setCurrentValue(value);

        personaAchievement = (PersonaAchievementEntity) save(personaAchievement);

        AchievementRankEntity previous = null;
        AchievementRankEntity first = achievement.getRanks().get(0);

        System.out.println("AchievementId=" + achievement.getId() + " (" + achievement.getFriendlyIdentifier() + ")");
        
        for (AchievementRankEntity rank : achievement.getRanks()) {
            PersonaAchievementRankEntity personaRank = DaoFactory.getPersonaAchievementRankDao().getForPersona(personaEntity, achievement, rank);
            PersonaAchievementRankEntity previousRank = null;

            if (personaRank == null) {
                personaRank = createPersonaAchievementRank(personaEntity, achievement, rank);
            }

            if (previous != null) {
                previousRank = DaoFactory.getPersonaAchievementRankDao().getForPersona(personaEntity, achievement, previous);

                if (previousRank == null) {
                    previousRank = createPersonaAchievementRank(personaEntity, achievement, previous);
                }
            }

            String currentState = personaRank.getState();
            String state = "";
            boolean complete = false;
            boolean stop = false;

            Long threshold = rank.getThresholdValue();

            System.out.println("|| Threshold = " + threshold + "; value = " + value);

            if (value >= threshold && !currentValue.equals(value)) {
                System.out.println("    Value >= threshold!");
                state = "RewardWaiting";
                complete = true;
            } else {
                System.out.println("    Not at threshold OR not RewardWaiting or Completed");

                if (value == 0) {
                    System.out.println("     Value is zero, assuming \"Locked\"");
                    state = "Locked";
                } else if (value > 0 && value < first.getThresholdValue()) {
                    System.out.println("     First rank in progress.");
                    state = "InProgress";
                    stop = true;
                } else if (previous != null && previousRank.getState().equals("InProgress")) {
                    System.out.println("     Previous rank in progress.");
                    state = "Locked";
                    stop = true;
                } else if (value > 0 && value < threshold) {
                    System.out.println("     Value is more than zero, but less than threshold.");
                    state = "InProgress";
                }
            }

            state = state.trim();

            if (state.isEmpty()) {
                return;
            }

            if (complete) {
                String timeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                timeString = timeString.replace(" ", "T");
                personaRank.setAchievedOn(timeString);
            } else {
                personaRank.setAchievedOn("0001-01-01T00:00:00");
            }

            // if reward is now waiting, add
            if (state.equalsIgnoreCase("RewardWaiting") 
                    && !personaRank.getState().equalsIgnoreCase("RewardWaiting")
                    && !personaRank.getState().equalsIgnoreCase("Completed")) {
                System.out.println("||| State was just set to RewardWaiting.");
                System.out.println("|||| Updating score.");
                
                personaEntity.setScore(personaEntity.getScore() + rank.getPoints());
                personaEntity = DaoFactory.getPersonaDao().save(personaEntity);
                
                updateInfo.getRanks().add(personaRank);
            } else if (state.equals("InProgress") && !value.equals(currentValue)) {
                // if progress has been made, add
                System.out.println("||| Currently InProgress, but new value (" + value + ") > old value (" + currentValue + ")");
                updateInfo.getRanks().add(personaRank);
            }

            personaRank.setState(state);

            DaoFactory.getPersonaAchievementRankDao().save(personaRank);

            if (stop)
                break;

            personaAchievement.setCanProgress(!value.equals(maxValue));
            personaAchievement = (PersonaAchievementEntity) save(personaAchievement);

            previous = rank;
        }

        updateInfo.setPersonaAchievement(personaAchievement);
        updateInfo.setScore(Float.valueOf(personaEntity.getScore()).intValue());

        callback.accept(updateInfo);
    }

    private PersonaAchievementEntity createPersonaAchievement(PersonaEntity personaEntity, AchievementDefinitionEntity achievement)
    {
        PersonaAchievementEntity entity = new PersonaAchievementEntity();
        entity.setCanProgress(true);
        entity.setCurrentValue(0L);
        entity.setAchievement(achievement);
        entity.setPersona(personaEntity);

        return (PersonaAchievementEntity) save(entity);
    }

    private PersonaAchievementRankEntity createPersonaAchievementRank(
            PersonaEntity personaEntity, AchievementDefinitionEntity achievement, AchievementRankEntity achievementRank)
    {
        PersonaAchievementRankEntity personaRank = new PersonaAchievementRankEntity();

        personaRank.setPersona(personaEntity);
        personaRank.setAchievement(achievement);
        personaRank.setRank(achievementRank);
        personaRank.setState("Locked");
        personaRank.setAchievedOn("0001-01-01T00:00:00");

        return (PersonaAchievementRankEntity) DaoFactory.getPersonaAchievementRankDao().save(personaRank);
    }
}
