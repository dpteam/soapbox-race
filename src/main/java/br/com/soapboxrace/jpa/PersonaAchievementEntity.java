package br.com.soapboxrace.jpa;

import br.com.soapboxrace.jaxb.OwnedCarTransType;
import br.com.soapboxrace.jaxb.convert.OwnedCarTransConverter;

import javax.persistence.*;

@Entity
@Table(name = "PERSONAACHIEVEMENT")
public class PersonaAchievementEntity implements ISoapBoxEntity {

	private static final long serialVersionUID = 5314835854384144787L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@JoinColumn(name = "personaId", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private PersonaEntity persona;
	
	@JoinColumn(name = "achievementId", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private AchievementDefinitionEntity achievement;
	
	@Column(name = "currentValue")
	private Long currentValue;
	
	@Column(name = "canProgress")
	private boolean canProgress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public AchievementDefinitionEntity getAchievement()
	{
		return achievement;
	}

	public void setAchievement(AchievementDefinitionEntity achievement)
	{
		this.achievement = achievement;
	}

	public Long getCurrentValue()
	{
		return currentValue;
	}

	public void setCurrentValue(Long currentValue)
	{
		this.currentValue = currentValue;
	}

	public boolean isCanProgress()
	{
		return canProgress;
	}

	public void setCanProgress(boolean canProgress)
	{
		this.canProgress = canProgress;
	}
}
