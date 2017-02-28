package br.com.soapboxrace.achievements;

public class RewardRandomness
{
    private float chance;

    public float getChance()
    {
        return chance;
    }

    public void setChance(float chance)
    {
        this.chance = chance;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof RewardRandomness && ((RewardRandomness) obj).getChance() == chance;
    }
}
