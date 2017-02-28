package br.com.soapboxrace.jaxb.events.rewards;

import br.com.soapboxrace.jaxb.events.rewards.lucky.LuckyDrawInfo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Accolades (rewards)
 */
@XmlRootElement(name = "Accolades")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accolades", propOrder = { "finalRewards", "hasLeveledUp", "luckyDrawInfo", "originalRewards",
        "rewardInfo" })
public class Accolades
{
    @XmlElement(name = "FinalRewards")
    private Reward finalRewards;
    
    @XmlElement(name = "HasLeveledUp")
    private boolean hasLeveledUp;
    
    @XmlElement(name = "LuckyDrawInfo")
    private LuckyDrawInfo luckyDrawInfo;
    
    @XmlElement(name = "OriginalRewards")
    private Reward originalRewards;
    
    @XmlElementWrapper(name = "RewardInfo")
    @XmlElement(name = "RewardPart")
    private List<RewardPart> rewardInfo;

    public Reward getFinalRewards()
    {
        return finalRewards;
    }

    public void setFinalRewards(Reward finalRewards)
    {
        this.finalRewards = finalRewards;
    }

    public boolean isHasLeveledUp()
    {
        return hasLeveledUp;
    }

    public void setHasLeveledUp(boolean hasLeveledUp)
    {
        this.hasLeveledUp = hasLeveledUp;
    }

    public LuckyDrawInfo getLuckyDrawInfo()
    {
        return luckyDrawInfo;
    }

    public void setLuckyDrawInfo(LuckyDrawInfo luckyDrawInfo)
    {
        this.luckyDrawInfo = luckyDrawInfo;
    }

    public Reward getOriginalRewards()
    {
        return originalRewards;
    }

    public void setOriginalRewards(Reward originalRewards)
    {
        this.originalRewards = originalRewards;
    }

    public List<RewardPart> getRewardInfo()
    {
        return rewardInfo;
    }

    public void setRewardInfo(List<RewardPart> rewardInfo)
    {
        this.rewardInfo = rewardInfo;
    }
}
