package br.com.soapboxrace.jpa;

import javax.persistence.*;

@Entity
@Table(name = "TREASUREHUNTSESSION")
public class TreasureHuntSessionEntity implements ISoapBoxEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = PersonaEntity.class)
    @JoinColumn(name = "personaId", referencedColumnName = "id")
    private PersonaEntity persona;
    
    private Long coinsCollected;
    
    private boolean isStreakBroken;
    
    private Long seed;
    
    private Long streak;
    
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

    public PersonaEntity getPersona()
    {
        return persona;
    }

    public void setPersona(PersonaEntity persona)
    {
        this.persona = persona;
    }

    public Long getCoinsCollected()
    {
        return coinsCollected;
    }

    public void setCoinsCollected(Long coinsCollected)
    {
        this.coinsCollected = coinsCollected;
    }

    public boolean isStreakBroken()
    {
        return isStreakBroken;
    }

    public void setStreakBroken(boolean streakBroken)
    {
        isStreakBroken = streakBroken;
    }

    public Long getSeed()
    {
        return seed;
    }

    public void setSeed(Long seed)
    {
        this.seed = seed;
    }

    public Long getStreak()
    {
        return streak;
    }

    public void setStreak(Long streak)
    {
        this.streak = streak;
    }
}
