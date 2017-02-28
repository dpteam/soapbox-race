package br.com.soapboxrace.jpa;

import br.com.soapboxrace.achievements.AchievementReward;
import br.com.soapboxrace.jaxb.convert.RewardConverter;

import javax.persistence.*;

@Entity
@Table(name = "ACHIEVEMENTREWARD")
public class AchievementRewardEntity implements ISoapBoxEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "achievementId", referencedColumnName = "id")
    private AchievementDefinitionEntity achievement;
    
    @Column(name = "rewards")
    @Convert(converter = RewardConverter.class)
    private AchievementReward reward;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    public AchievementDefinitionEntity getAchievement()
    {
        return achievement;
    }

    public void setAchievement(AchievementDefinitionEntity achievement)
    {
        this.achievement = achievement;
    }

    public AchievementReward getReward()
    {
        return reward;
    }

    public void setReward(AchievementReward reward)
    {
        this.reward = reward;
    }
}
