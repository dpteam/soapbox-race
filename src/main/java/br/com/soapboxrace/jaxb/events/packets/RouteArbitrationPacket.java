package br.com.soapboxrace.jaxb.events.packets;

import br.com.soapboxrace.jaxb.events.AbstractArbitrationPacket;
import br.com.soapboxrace.jaxb.events.AbstractEntrantResult;
import br.com.soapboxrace.jaxb.events.ArbitrationPacketWithJumps;
import br.com.soapboxrace.jaxb.events.entrants.RouteEntrantResult;
import br.com.soapboxrace.jaxb.events.rewards.Reward;
import br.com.soapboxrace.jaxb.events.rewards.RewardCategory;
import br.com.soapboxrace.jaxb.events.rewards.RewardPart;
import br.com.soapboxrace.jaxb.events.rewards.RewardType;
import br.com.soapboxrace.jpa.EventDefinitionEntity;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "RouteArbitrationPacket")
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteArbitrationPacket extends AbstractArbitrationPacket implements ArbitrationPacketWithJumps
{
    @XmlElement(name = "BestLapDurationInMilliseconds")
    private long bestLapDurationInMilliseconds;

    @XmlElement(name = "FractionCompleted")
    private float fractionCompleted;

    @XmlElement(name = "LongestJumpDurationInMilliseconds")
    private long longestJumpDurationInMilliseconds;

    @XmlElement(name = "NumberOfCollisions")
    private int numberOfCollisions;

    @XmlElement(name = "PerfectStart")
    private Boolean perfectStart;

    @XmlElement(name = "SumOfJumpsDurationInMilliseconds")
    private long sumOfJumpsDurationInMilliseconds;

    @XmlElement(name = "TopSpeed")
    private float topSpeed;

    public long getBestLapDurationInMilliseconds()
    {
        return bestLapDurationInMilliseconds;
    }

    public void setBestLapDurationInMilliseconds(long bestLapDurationInMilliseconds)
    {
        this.bestLapDurationInMilliseconds = bestLapDurationInMilliseconds;
    }

    public float getFractionCompleted()
    {
        return fractionCompleted;
    }

    public void setFractionCompleted(float fractionCompleted)
    {
        this.fractionCompleted = fractionCompleted;
    }

    public long getLongestJumpDurationInMilliseconds()
    {
        return longestJumpDurationInMilliseconds;
    }

    public void setLongestJumpDurationInMilliseconds(long longestJumpDurationInMilliseconds)
    {
        this.longestJumpDurationInMilliseconds = longestJumpDurationInMilliseconds;
    }

    public int getNumberOfCollisions()
    {
        return numberOfCollisions;
    }

    public void setNumberOfCollisions(int numberOfCollisions)
    {
        this.numberOfCollisions = numberOfCollisions;
    }

    public Boolean getPerfectStart()
    {
        return perfectStart;
    }

    public void setPerfectStart(Boolean perfectStart)
    {
        this.perfectStart = perfectStart;
    }

    public long getSumOfJumpsDurationInMilliseconds()
    {
        return sumOfJumpsDurationInMilliseconds;
    }

    public void setSumOfJumpsDurationInMilliseconds(long sumOfJumpsDurationInMilliseconds)
    {
        this.sumOfJumpsDurationInMilliseconds = sumOfJumpsDurationInMilliseconds;
    }

    public float getTopSpeed()
    {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed)
    {
        this.topSpeed = topSpeed;
    }

    @Override
    public Reward calculateBaseReward(EventDefinitionEntity eventDefinition)
    {
        if (getRank() > 3) {
            return new Reward()
            {
                {
                    setRep(0);
                    setTokens(1000);
                }
            };
        } else {
            int multiplier = Math.max(1, (eventDefinition.getMaxEntrants() / (2 * getRank())) * 2);
            int laps = Math.max(1, eventDefinition.getLaps());

            return new Reward()
            {
                {
                    setRep(multiplier * getPersona().getLevel() * (2 + laps));
                    setTokens(multiplier * getPersona().getLevel() * (250 + laps));
                }
            };
        }
    }

    /**
     * Reward calculation algorithm by berkay2578
     */
    @Override
    public Pair<Reward, List<RewardPart>> calculateRewardParts(EventDefinitionEntity eventDefinition, Reward rewardBase)
    {
        List<RewardPart> rewardParts = new ArrayList<>();
        int level = getPersona().getLevel();
        boolean limited = level >= 60;
        int laps = Math.max(1, eventDefinition.getLaps());
        short rank = getRank();

        rewardBase.setRep(0);
        rewardBase.setTokens(0);

        // Base reward
        Reward baseReward = calculateBaseReward(eventDefinition);
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(baseReward.getRep());
                setRewardCategory(rank > 3 ? RewardCategory.Objective : RewardCategory.Rank);
                setRewardType(rank > 3 ? RewardType.Finished : RewardType.None);
                setTokenPart(baseReward.getTokens());
            }
        });

        // Bonus for Perfect Start
        if (perfectStart) {
            rewardParts.add(new RewardPart()
            {
                {
                    setRepPart(limited ? 0 : 300 * level);
                    setRewardCategory(RewardCategory.Bonus);
                    setRewardType(RewardType.PowerupBonus);
                    setTokenPart(400 * level);
                }
            });
        }

        if (numberOfCollisions > 0) {
            // Collision penalty...
            rewardParts.add(new RewardPart()
            {
                {
                    setRepPart(numberOfCollisions * ((-10 + laps) * 3) * level);
                    setRewardCategory(RewardCategory.Base);
                    setRewardType(RewardType.Infractions);
                    setTokenPart(numberOfCollisions * ((-25 + laps) * 6) * level);
                }
            });
        } else {
            // Bonus for no collisions
            rewardParts.add(new RewardPart()
            {
                {
                    setRepPart(limited ? 0 : 500 * level * laps);
                    setRewardCategory(RewardCategory.Base);
                    setRewardType(RewardType.Infractions);
                    setTokenPart(600 * level * laps);
                }
            });
        }

        if (laps > 1) {
            // Anti-abuse for circuits
            // Best lap time must be better than 1/3 of the circuit race time.
            // The second check blocks abuse, for example, completing a lap in one minute and roaming for two.
            if (bestLapDurationInMilliseconds < getEventDurationInMilliseconds() / 3
                    && bestLapDurationInMilliseconds * laps > getEventDurationInMilliseconds() - bestLapDurationInMilliseconds) {
                rewardParts.add(new RewardPart()
                {
                    {
                        setRepPart(2 * getPersona().getRepToPass() / 100);
                        setRewardCategory(RewardCategory.Bonus);
                        setRewardType(RewardType.TimeBonus);
                        setTokenPart(2000 * getPersona().getLevel());
                    }
                });
            }
        }

        for (final RewardPart rewardPart : rewardParts) {
            rewardBase.setRep(rewardBase.getRep() + rewardPart.getRepPart());
            rewardBase.setTokens(rewardBase.getTokens() + rewardPart.getTokenPart());
        }

        return Pair.of(rewardBase, rewardParts);
    }

    @Override
    public AbstractEntrantResult getEntrantResult()
    {
        RouteEntrantResult entrantResult = new RouteEntrantResult();
        entrantResult.setBestLapDurationInMilliseconds(bestLapDurationInMilliseconds);
        entrantResult.setEventDurationInMilliseconds(getEventDurationInMilliseconds());
        entrantResult.setEventSessionId(getEventSessionId());
        entrantResult.setFinishReason(getFinishReason());
        entrantResult.setPersonaId(getPersona().getId());
        entrantResult.setRanking(getRank());
        entrantResult.setTopSpeed(topSpeed);

        return entrantResult;
    }
}
