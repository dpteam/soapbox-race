package br.com.soapboxrace.jaxb.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TreasureHuntEventSession")
@XmlAccessorType(XmlAccessType.FIELD)
public class TreasureHuntEventSession
{
    @XmlElement(name = "CoinsCollected")
    private int coinsCollected;
    
    @XmlElement(name = "IsStreakBroken")
    private boolean isStreakBroken;
    
    @XmlElement(name = "NumCoins")
    private int numCoins;
    
    @XmlElement(name = "Seed")
    private int seed;
    
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

    public int getSeed()
    {
        return seed;
    }

    public void setSeed(int seed)
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
