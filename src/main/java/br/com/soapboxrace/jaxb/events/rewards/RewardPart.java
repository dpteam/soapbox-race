package br.com.soapboxrace.jaxb.events.rewards;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "RewardPart")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"repPart", "rewardCategory", "rewardType", "tokenPart"})
public class RewardPart
{
    @XmlElement(name = "RepPart")
    private int repPart;
    
    @XmlElement(name = "RewardCategory")
    private RewardCategory rewardCategory;
    
    @XmlElement(name = "RewardType")
    private RewardType rewardType;
    
    @XmlElement(name = "TokenPart")
    private int tokenPart;

    public int getRepPart()
    {
        return repPart;
    }

    public void setRepPart(int repPart)
    {
        this.repPart = repPart;
    }

    public int getTokenPart()
    {
        return tokenPart;
    }

    public void setTokenPart(int tokenPart)
    {
        this.tokenPart = tokenPart;
    }

    public RewardCategory getRewardCategory()
    {
        return rewardCategory;
    }

    public void setRewardCategory(RewardCategory rewardCategory)
    {
        this.rewardCategory = rewardCategory;
    }

    public RewardType getRewardType()
    {
        return rewardType;
    }

    public void setRewardType(RewardType rewardType)
    {
        this.rewardType = rewardType;
    }
}
