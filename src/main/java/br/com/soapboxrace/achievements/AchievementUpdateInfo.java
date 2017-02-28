package br.com.soapboxrace.achievements;

import br.com.soapboxrace.jpa.PersonaAchievementEntity;
import br.com.soapboxrace.jpa.PersonaAchievementRankEntity;

import java.util.ArrayList;
import java.util.List;

public class AchievementUpdateInfo
{
    private PersonaAchievementEntity personaAchievement;
    
    private List<PersonaAchievementRankEntity> ranks = new ArrayList<>();
    
    private int score;

    public List<PersonaAchievementRankEntity> getRanks()
    {
        return ranks;
    }

    public void setRanks(List<PersonaAchievementRankEntity> ranks)
    {
        this.ranks = ranks;
    }

    public PersonaAchievementEntity getPersonaAchievement()
    {
        return personaAchievement;
    }

    public void setPersonaAchievement(PersonaAchievementEntity personaAchievement)
    {
        this.personaAchievement = personaAchievement;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
