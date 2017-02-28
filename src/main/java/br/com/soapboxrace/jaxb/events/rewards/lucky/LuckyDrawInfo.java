package br.com.soapboxrace.jaxb.events.rewards.lucky;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "LuckyDrawInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LuckyDrawInfoType", propOrder = {"boxes", "cardDeck", "currentStreak", "isStreakBroken",
        "luckyDrawItem", "numBoxAnimations", "numCards"})
public class LuckyDrawInfo
{
    @XmlElement(name = "Boxes", required = true, nillable = true)
    private String boxes = null;
    @XmlElement(name = "CardDeck", required = true)
    private String cardDeck;
    @XmlElement(name = "CurrentStreak", defaultValue = "0", required = true)
    private Integer currentStreak = 0;
    @XmlElement(name = "IsStreakBroken", defaultValue = "false", required = true)
    private Boolean isStreakBroken = false;
    @XmlElement(name = "LuckyDrawItem", type = LuckyDrawItem.class)
    @XmlElementWrapper(name = "Items")
    private List<LuckyDrawItem> luckyDrawItem;
    @XmlElement(name = "NumBoxAnimations", defaultValue = "100", required = true)
    private Integer numBoxAnimations = 100;
    @XmlElement(name = "NumCards", defaultValue = "5", required = true)
    private Integer numCards = 5;

    public String getBoxes()
    {
        return boxes;
    }

    public void setBoxes(String boxes)
    {
        this.boxes = boxes;
    }

    public String getCardDeck()
    {
        return cardDeck;
    }

    public void setCardDeck(String cardDeck)
    {
        this.cardDeck = cardDeck;
    }

    public Integer getCurrentStreak()
    {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak)
    {
        this.currentStreak = currentStreak;
    }

    public Boolean getIsStreakBroken()
    {
        return isStreakBroken;
    }

    public void setIsStreakBroken(Boolean isStreakBroken)
    {
        this.isStreakBroken = isStreakBroken;
    }

    public LuckyDrawItem getLuckyDrawItem()
    {
        return luckyDrawItem.get(0);
    }

    public void setLuckyDrawItem(LuckyDrawItem luckyDrawItem)
    {
        List<LuckyDrawItem> dummyList = new ArrayList<>();
        dummyList.add(luckyDrawItem);
        this.luckyDrawItem = dummyList;
    }

    public void setLuckyDrawItem(List<LuckyDrawItem> luckyDrawItem)
    {
        this.luckyDrawItem = luckyDrawItem;
    }

    public Integer getNumBoxAnimations()
    {
        return numBoxAnimations;
    }

    public void setNumBoxAnimations(Integer numBoxAnimations)
    {
        this.numBoxAnimations = numBoxAnimations;
    }

    public Integer getNumCards()
    {
        return numCards;
    }

    public void setNumCards(Integer numCards)
    {
        this.numCards = numCards;
    }

    @Override
    public String toString()
    {
        return String.format(
                "LuckyDrawInfo(boxes=%s,cardDeck=%s,currentStreak=%d,isStreakBroken=%s,luckyDrawItem=%s,numBoxAnimations=%d,numCards=%d)",
                boxes,
                cardDeck,
                currentStreak,
                isStreakBroken,
                luckyDrawItem,
                numBoxAnimations,
                numCards
        );
    }
}
