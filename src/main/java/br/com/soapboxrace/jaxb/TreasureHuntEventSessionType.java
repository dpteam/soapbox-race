package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "TreasureHuntEventSession")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"coinsCollected", "isStreakBroken", "numCoins", "seed", "streak"})
public class TreasureHuntEventSessionType
{
    @XmlElement(name = "CoinsCollected")
    private int coinsCollected;
    
    @XmlElement(name = "IsStreakBroken")
    private boolean isStreakBroken;
    
    @XmlElement(name = "NumCoins")
    private int numCoins;
    
    @XmlElement(name = "Seed")
    private long seed;
    
    @XmlElement(name = "Streak")
    private int streak;

    public int getCoinsCollected()
    {
        return coinsCollected;
    }

    public void setCoinsCollected(int coinsCollected)
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

    public int getNumCoins()
    {
        return numCoins;
    }

    public void setNumCoins(int numCoins)
    {
        this.numCoins = numCoins;
    }

    public long getSeed()
    {
        return seed;
    }

    public void setSeed(long seed)
    {
        this.seed = seed;
    }

    public int getStreak()
    {
        return streak;
    }

    public void setStreak(int streak)
    {
        this.streak = streak;
    }
}
