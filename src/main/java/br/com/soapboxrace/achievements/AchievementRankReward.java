package br.com.soapboxrace.achievements;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * Holds a reward for a specific rank.
 */
public class AchievementRankReward
{
    private int rank;

    private String rewardType;

    private List<RewardItem> items;

    private JsonObject info;

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public String getRewardType()
    {
        return rewardType;
    }

    public void setRewardType(String rewardType)
    {
        this.rewardType = rewardType;
    }

    public List<RewardItem> getItems()
    {
        return items;
    }

    public void setItems(List<RewardItem> items)
    {
        this.items = items;
    }

    public JsonObject getInfo()
    {
        return info;
    }

    public void setInfo(JsonObject info)
    {
        this.info = info;
    }

    public boolean itemsMatch(List<RewardItem> set1, List<RewardItem> set2)
    {
        boolean match = false;

        if (set1 == null || set2 == null)
            return false;

        if (set1.size() == set2.size()) {
            for (int i = 0; i < set1.size(); i++) {
                RewardItem first = set1.get(i);
                RewardItem second = set2.get(i);

                match = first.getCount() == second.getCount();

                System.out.println("|| Count matches? " + match);

                if (first.getId() != null && second.getId() != null) {
                    match = match && first.getId().equals(second.getId());
                    System.out.println("|| IDs match? " + match);
                }
                
                if (first.getTitle() != null && second.getTitle() != null) {
                    match = match && first.getTitle().equals(second.getTitle());
                    System.out.println("|| Titles match? " + match);
                }

                if (first.getRandom() != null && second.getRandom() != null) {
                    match = match && first.getRandom().equals(second.getRandom());
                    System.out.println("|| Random objects match? " + match);
                }

//                match = first.getCount() == second.getCount()
//                        && (!(first.getId() != null && second.getId() != null) || first.getId().equals(second.getId()))
//                        && (!(first.getTitle() != null && second.getTitle() != null) || first.getTitle().equals(second.getTitle()))
//                        && (!(first.getRandom() != null && second.getRandom() != null) || first.getRandom().equals(second.getRandom()));
            }
        }
        
        System.out.println("match=" + match);

        return match;
    }

    @Override
    public String toString()
    {
        return String.format(
                "AchievementRankReward(rank=%d,rewardType=%s,items=%s,info=%s)",
                rank,
                rewardType,
                items,
                info == null ? "N/A" : info.toString()
        );
    }
}
