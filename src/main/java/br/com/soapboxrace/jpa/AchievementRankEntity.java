package br.com.soapboxrace.jpa;

import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.jaxb.AchievementDefinitionPacketType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ACHIEVEMENTRANK")
public class AchievementRankEntity implements ISoapBoxEntity
{
    private static final long serialVersionUID = 5314835854384144788L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "achievementId", referencedColumnName = "id")
    @ManyToOne
    private AchievementDefinitionEntity achievementDefinition;

    @Column(name = "isRare")
    private boolean isRare;

    @Column(name = "points")
    private int points;

    @Column(name = "rank")
    private int rank;

//	@Column(name = "rarity")
//	private float rarity;

    @Column(name = "rewardDescription")
    private String rewardDescription;

    @Column(name = "rewardType")
    private String rewardType;

    @Column(name = "rewardVisualStyle")
    private String rewardVisualStyle;

    @Column(name = "thresholdValue")
    private Long thresholdValue;

    public AchievementDefinitionPacketType.AchievementRankPacket toBasePacket()
    {
        AchievementDefinitionPacketType.AchievementRankPacket packet = new AchievementDefinitionPacketType.AchievementRankPacket();
        packet.setId(id);
        packet.setRare(isRare);
        packet.setRarity(getRarity());
        packet.setRank(rank);
        packet.setThresholdValue(thresholdValue);
        packet.setRewardDescription(rewardDescription);
        packet.setRewardType(rewardType);
        packet.setRewardVisualStyle(rewardVisualStyle);
        packet.setPoints(points);

        return packet;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public AchievementDefinitionEntity getAchievementDefinition()
    {
        return achievementDefinition;
    }

    public void setAchievementDefinition(AchievementDefinitionEntity achievementDefinition)
    {
        this.achievementDefinition = achievementDefinition;
    }

    public boolean isRare()
    {
        return isRare;
    }

    public void setRare(boolean rare)
    {
        isRare = rare;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }


    //	public float getRarity()
//	{
//		return rarity;
//	}
//
//	public void setRarity(float rarity)
//	{
//		this.rarity = rarity;
//	}
    public float getRarity()
    {
        int personas = DaoFactory.getPersonaDao().getAll().size();
        int withRank = DaoFactory.getPersonaAchievementRankDao().getForRank(this).size();

        return new BigDecimal((withRank / personas) * 100).floatValue();
    }

    public String getRewardDescription()
    {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription)
    {
        this.rewardDescription = rewardDescription;
    }

    public String getRewardType()
    {
        return rewardType;
    }

    public void setRewardType(String rewardType)
    {
        this.rewardType = rewardType;
    }

    public String getRewardVisualStyle()
    {
        return rewardVisualStyle;
    }

    public void setRewardVisualStyle(String rewardVisualStyle)
    {
        this.rewardVisualStyle = rewardVisualStyle;
    }

    public Long getThresholdValue()
    {
        return thresholdValue;
    }

    public void setThresholdValue(Long thresholdValue)
    {
        this.thresholdValue = thresholdValue;
    }
}
