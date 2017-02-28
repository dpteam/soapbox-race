package br.com.soapboxrace.jaxb.events.packets;

import br.com.soapboxrace.bo.legacy.LegacyPersonaBO;
import br.com.soapboxrace.jaxb.events.AbstractArbitrationPacket;
import br.com.soapboxrace.jaxb.events.AbstractEntrantResult;
import br.com.soapboxrace.jaxb.events.ArbitrationPacketWithJumps;
import br.com.soapboxrace.jaxb.events.FinishReason;
import br.com.soapboxrace.jaxb.events.rewards.Reward;
import br.com.soapboxrace.jaxb.events.rewards.RewardCategory;
import br.com.soapboxrace.jaxb.events.rewards.RewardPart;
import br.com.soapboxrace.jaxb.events.rewards.RewardType;
import br.com.soapboxrace.jpa.EventDefinitionEntity;
import br.com.soapboxrace.jpa.OwnedCarEntity;
import br.com.soapboxrace.jpa.PersonaEntity;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@XmlRootElement(name = "PursuitArbitrationPacket")
@XmlAccessorType(XmlAccessType.FIELD)
public class PursuitArbitrationPacket extends AbstractArbitrationPacket implements ArbitrationPacketWithJumps
{
    private final LegacyPersonaBO personaBO = new LegacyPersonaBO();

    @XmlElement(name = "CopsDeployed")
    private int copsDeployed;

    @XmlElement(name = "CopsDisabled")
    private int copsDisabled;

    @XmlElement(name = "CopsRammed")
    private int copsRammed;

    @XmlElement(name = "CostToState")
    private int costToState;

    @XmlElement(name = "Heat")
    private float heat;

    @XmlElement(name = "Infractions")
    private int infractions;

    @XmlElement(name = "LongestJumpDurationInMilliseconds")
    private long longestJumpDurationInMilliseconds;

    @XmlElement(name = "RoadBlocksDodged")
    private int roadBlocksDodged;

    @XmlElement(name = "SpikeStripsDodged")
    private int spikeStripsDodged;

    @XmlElement(name = "SumOfJumpsDurationInMilliseconds")
    private long sumOfJumpsDurationInMilliseconds;

    @XmlElement(name = "TopSpeed")
    private float topSpeed;

    public int getCopsDeployed()
    {
        return copsDeployed;
    }

    public void setCopsDeployed(int copsDeployed)
    {
        this.copsDeployed = copsDeployed;
    }

    public int getCopsDisabled()
    {
        return copsDisabled;
    }

    public void setCopsDisabled(int copsDisabled)
    {
        this.copsDisabled = copsDisabled;
    }

    public int getCopsRammed()
    {
        return copsRammed;
    }

    public void setCopsRammed(int copsRammed)
    {
        this.copsRammed = copsRammed;
    }

    public int getCostToState()
    {
        return costToState;
    }

    public void setCostToState(int costToState)
    {
        this.costToState = costToState;
    }

    public float getHeat()
    {
        return heat;
    }

    public void setHeat(float heat)
    {
        this.heat = heat;
    }

    public int getInfractions()
    {
        return infractions;
    }

    public void setInfractions(int infractions)
    {
        this.infractions = infractions;
    }

    public long getLongestJumpDurationInMilliseconds()
    {
        return longestJumpDurationInMilliseconds;
    }

    public void setLongestJumpDurationInMilliseconds(long longestJumpDurationInMilliseconds)
    {
        this.longestJumpDurationInMilliseconds = longestJumpDurationInMilliseconds;
    }

    public int getRoadBlocksDodged()
    {
        return roadBlocksDodged;
    }

    public void setRoadBlocksDodged(int roadBlocksDodged)
    {
        this.roadBlocksDodged = roadBlocksDodged;
    }

    public int getSpikeStripsDodged()
    {
        return spikeStripsDodged;
    }

    public void setSpikeStripsDodged(int spikeStripsDodged)
    {
        this.spikeStripsDodged = spikeStripsDodged;
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
        int level = getPersona().getLevel();
        boolean limited = level >= 60;
        int rankMultiplier = getFinishReason() == FinishReason.Busted.getCode()
                ? 3 : 5;

        if (getFinishReason() == FinishReason.Busted.getCode()) {
            return new Reward()
            {
                {
                    setRep((rankMultiplier * level + (getPersona().getRepToPass() / 350)) * -1);
                    setTokens(rankMultiplier * level * -250);
                }
            };
        } else {
            return new Reward()
            {
                {
                    setRep(limited ? 0 : ((rankMultiplier * level) + (getPersona().getRepToPass() / 250)) * 8);
                    setTokens(rankMultiplier * level * 300);
                }
            };
        }
    }

    @Override
    public Pair<Reward, List<RewardPart>> calculateRewardParts(EventDefinitionEntity eventDefinition, Reward rewardBase)
    {
        final PersonaEntity persona = getPersona();
        final OwnedCarEntity defaultCar = personaBO.defaultcar(persona.getId());

        List<RewardPart> rewardParts = new ArrayList<>();
        int level = persona.getLevel();
        boolean limited = level >= 60;
        float carHeat = defaultCar.getHeatLevel();
        int rankMultiplier = 5 - (getFinishReason() == FinishReason.Busted.getCode() ? 3 : 0);

        rewardBase.setRep(0);
        rewardBase.setTokens(0);

        // Base reward
        Reward baseReward = calculateBaseReward(eventDefinition);
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(baseReward.getRep());
                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(getFinishReason() == FinishReason.Busted.getCode() ? RewardType.Busted : RewardType.Evaded);
                setTokenPart(baseReward.getTokens());
            }
        });

        // Cops deployed bonus
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(copsDeployed * level * rankMultiplier * 2);
                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(RewardType.CopCarsDeployed);
                setTokenPart(copsDeployed * level * rankMultiplier * 12);
            }
        });

        if (copsDisabled > 0 && copsDeployed > 0) {
            // Cops disabled bonus
            rewardParts.add(new RewardPart()
            {
                {
                    setRepPart(((copsDeployed / copsDisabled) * 100) * level * rankMultiplier);
                    setRewardCategory(RewardCategory.Pursuit);
                    setRewardType(RewardType.CopCarsDisabled);
                    setTokenPart(((copsDeployed / copsDisabled) * 100) * level * rankMultiplier * 2);
                }
            });
        }

        // Cops rammed bonus
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart((copsRammed * rankMultiplier * 10) + copsDisabled > 0 ? copsDisabled * rankMultiplier * 10 : 0);
                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(RewardType.CopCarsRammed);
                setTokenPart((copsRammed * rankMultiplier * 50) + copsDisabled > 0 ? copsDisabled * rankMultiplier * 20 : 0);
            }
        });

        if (costToState > 0) {
            // Cost to state bonus
            rewardParts.add(new RewardPart()
            {
                {
                    setRepPart((costToState / 100) * (level / (9 - rankMultiplier)));
                    setRewardCategory(RewardCategory.Pursuit);
                    setRewardType(RewardType.CostToState);
                    setTokenPart((costToState / 100) * (level / (3 - rankMultiplier)) * 10);
                }
            });
        }

        // Heat increase bonus
        if (heat > carHeat) {
            rewardParts.add(new RewardPart()
            {
                {
                    setRepPart(
                            Math.round(
                                    ((heat / carHeat) * 100) * rankMultiplier * level * 2
                            )
                    );

                    setRewardCategory(RewardCategory.Pursuit);
                    setRewardType(RewardType.HeatLevel);
                    setTokenPart(
                            Math.round(
                                    ((heat / carHeat) * 100) * rankMultiplier * level * 3
                            )
                    );
                }
            });
        }

        // Infraction bonus
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(infractions * (level / 2) * 3);
                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(RewardType.Infractions);
                setTokenPart(infractions * level);
            }
        });

        // Pursuit length bonus
        long minutes = TimeUnit.MILLISECONDS.toMinutes(getEventDurationInMilliseconds());
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(
                        (int) Math.round(Math.pow(1.5, Math.max(1, minutes)))
                                * level * rankMultiplier * 20
                );

                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(RewardType.PursuitLength);
                setTokenPart(
                        (int) Math.round(Math.pow(2, Math.max(1, minutes)))
                                * level * rankMultiplier * 45
                );
            }
        });

        // Roadblocks dodged
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(roadBlocksDodged * rankMultiplier * 6);
                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(RewardType.RoadblocksDodged);
                setTokenPart(roadBlocksDodged * rankMultiplier * 2 * 12);
            }
        });

        // Spike strips dodged
        rewardParts.add(new RewardPart()
        {
            {
                setRepPart(spikeStripsDodged * rankMultiplier * 5);
                setRewardCategory(RewardCategory.Pursuit);
                setRewardType(RewardType.SpikeStripsDodged);
                setTokenPart(spikeStripsDodged * rankMultiplier * 2 * 12);
            }
        });

        for (final RewardPart rewardPart : rewardParts) {
            rewardBase.setRep(rewardBase.getRep() + rewardPart.getRepPart());
            rewardBase.setTokens(rewardBase.getTokens() + rewardPart.getTokenPart());
        }

        rewardBase.setTokens(rewardBase.getTokens() * 2);
        
        return Pair.of(rewardBase, rewardParts);
    }

    @Override
    public AbstractEntrantResult getEntrantResult()
    {
        throw new UnsupportedOperationException();
    }
}
