package evaluator;

import lombok.Getter;

@Getter
public enum PokerHandTier {
    HIGH_CARD(0, "High Card"),
    PAIR(1, "Pair"),
    TWO_PAIR(2, "Two Pair"),
    THREE_OF_A_KIND(3, "Three of a Kind"),
    STRAIGHT(4, "Straight"),
    FLUSH(5, "Flush"),
    FULL_HOUSE(6, "Full House"),
    FOUR_OF_A_KIND(7, "Four of a Kind"),
    STRAIGHT_FLUSH(8, "Straight Flush"),
    ROYAL_FLUSH(9, "Royal Flush");

    private final int value;
    private final String tier;

    PokerHandTier(int value, String tier) {
        this.value = value;
        this.tier = tier;
    }

    public boolean betterThan(PokerHandTier otherTier) {
        return this.value > otherTier.value;
    }
}
