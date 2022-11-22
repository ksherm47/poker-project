package cards;

import lombok.Getter;

@Getter
public enum CardRank {
    TWO(2, "Deuce"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(11, "Jack"),
    QUEEN(12, "Queen"),
    KING(13, "King"),
    ACE(14, "Ace");

    private final int value;
    private final String rank;

    CardRank(int value, String rank) {
        this.value = value;
        this.rank = rank;
    }

    public boolean betterThan(CardRank otherCardRank) {
        return this.value > otherCardRank.value;
    }
}
