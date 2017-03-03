package br.com.soapboxrace.achievements;

/**
 * An item for a reward.
 * 
 * @author leorblx
 */
public class RewardItem
{
    private String id;
    
    private int count;
    
    private String title;
    
    private RewardRandomness random;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public RewardRandomness getRandom()
    {
        return random;
    }

    public void setRandom(RewardRandomness random)
    {
        this.random = random;
    }

    @Override
    public String toString()
    {
        String toString = String.format("RewardItem(id=%s,count=%d", id, count);
        
        if (this.title != null) {
            toString += String.format(",title=%s", title);
        }
        
        if (this.random != null) {
            toString += String.format(",chance=%f", random.getChance());
        }
        
        toString += ")";
        
        return toString;
    }
}
