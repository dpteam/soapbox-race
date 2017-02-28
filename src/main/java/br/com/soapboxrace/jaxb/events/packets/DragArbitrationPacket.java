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

@XmlRootElement(name = "DragArbitrationPacket")
@XmlAccessorType(XmlAccessType.FIELD)
public class DragArbitrationPacket extends AbstractArbitrationPacket implements ArbitrationPacketWithJumps
{
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
