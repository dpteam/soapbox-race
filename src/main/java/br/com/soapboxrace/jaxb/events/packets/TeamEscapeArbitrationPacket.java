package br.com.soapboxrace.jaxb.events.packets;

import br.com.soapboxrace.jaxb.events.AbstractArbitrationPacket;
import br.com.soapboxrace.jaxb.events.AbstractEntrantResult;
import br.com.soapboxrace.jaxb.events.ArbitrationPacketWithJumps;
import br.com.soapboxrace.jaxb.events.rewards.Reward;
import br.com.soapboxrace.jaxb.events.rewards.RewardPart;
import br.com.soapboxrace.jpa.EventDefinitionEntity;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "TeamEscapeArbitrationPacket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamEscapeArbitrationPacket extends AbstractArbitrationPacket implements ArbitrationPacketWithJumps
{
    @XmlElement(name = "BustedCount")
    private int bustedCount;
    
    @XmlElement(name = "CopsDeployed")
    private int copsDeployed;
    
    @XmlElement(name = "CopsDisabled")
    private int copsDisabled;
    
    @XmlElement(name = "CopsRammed")
    private int copsRammed;
    
    @XmlElement(name = "CostToState")
    private int costToState;
    
    @XmlElement(name = "DistanceToFinish")
    private float distanceToFinish;
    
    @XmlElement(name = "FractionCompleted")
    private float fractionCompleted;
    
    @XmlElement(name = "Infractions")
    private int infractions;
    
    @XmlElement(name = "LongestJumpDurationInMilliseconds")
    private long longestJumpDurationInMilliseconds;
    
    @XmlElement(name = "NumberOfCollisions")
    private int numberOfCollisions;
    
    @XmlElement(name = "PerfectStart")
    private int perfectStart;
    
    @XmlElement(name = "RoadBlocksDodged")
    private int roadBlocksDodged;
    
    @XmlElement(name = "SpikeStripsDodged")
    private int spikeStripsDodged;
    
    @XmlElement(name = "SumOfJumpsDurationInMilliseconds")
    private long sumOfJumpsDurationInMilliseconds;
    
    @XmlElement(name = "TopSpeed")
    private float topSpeed;

    public int getBustedCount()
    {
        return bustedCount;
    }

    public void setBustedCount(int bustedCount)
    {
        this.bustedCount = bustedCount;
    }

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

    public float getDistanceToFinish()
    {
        return distanceToFinish;
    }

    public void setDistanceToFinish(float distanceToFinish)
    {
        this.distanceToFinish = distanceToFinish;
    }

    public float getFractionCompleted()
    {
        return fractionCompleted;
    }

    public void setFractionCompleted(float fractionCompleted)
    {
        this.fractionCompleted = fractionCompleted;
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

    public int getNumberOfCollisions()
    {
        return numberOfCollisions;
    }

    public void setNumberOfCollisions(int numberOfCollisions)
    {
        this.numberOfCollisions = numberOfCollisions;
    }

    public int getPerfectStart()
    {
        return perfectStart;
    }

    public void setPerfectStart(int perfectStart)
    {
        this.perfectStart = perfectStart;
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
        return null;
    }

    @Override
    public Pair<Reward, List<RewardPart>> calculateRewardParts(EventDefinitionEntity eventDefinition, Reward rewardBase)
    {
        return Pair.of(rewardBase, new ArrayList<>());
    }

    @Override
    public AbstractEntrantResult getEntrantResult()
    {
        return null;
    }
}
