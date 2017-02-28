package br.com.soapboxrace.achievements;

import java.util.List;

/**
 * Holds rewards for a single achievement.
 */
public class AchievementReward
{
    private List<AchievementRankReward> ranks;

    public List<AchievementRankReward> getRanks()
    {
        return ranks;
    }

    public void setRanks(List<AchievementRankReward> ranks)
    {
        this.ranks = ranks;
    }

    @Override
    public String toString()
    {
        return "AchievementReward(ranks=" + ranks + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof AchievementReward)) return false;

        AchievementReward reward = (AchievementReward) obj;
        
        System.out.println(reward);

        return reward.ranks.size() == ranks.size() && ranksMatch(ranks, reward.ranks);
    }

    private boolean ranksMatch(List<AchievementRankReward> set1, List<AchievementRankReward> set2)
    {
        boolean match = false;
        
        if (set1.size() == set2.size()) {
            for (int i = 0; i < set1.size(); i++) {
                AchievementRankReward first = set1.get(i);
                AchievementRankReward second = set2.get(i);
                
                match = first.getRank() == second.getRank();
                
                System.out.println("|| Ranks match? " + match);
                
                match = match && first.getRewardType().equals(second.getRewardType());
                System.out.println("|| Reward types match? " + match);
                match = match && first.itemsMatch(first.getItems(), second.getItems());
                System.out.println("|| Items match? " + match);
            }
        }
        
        System.out.println("final match = " + match);
        
        return match;
    }
}
