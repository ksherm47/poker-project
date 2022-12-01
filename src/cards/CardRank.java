package cards;

import lombok.Getter;

@Getter
public enum CardRank {
    TWO(2, "Deuce", "2"),
    THREE(3, "Three", "3"),
    FOUR(4, "Four", "4"),
    FIVE(5, "Five", "5"),
    SIX(6, "Six", "6"),
    SEVEN(7, "Seven", "7"),
    EIGHT(8, "Eight", "8"),
    NINE(9, "Nine", "9"),
    TEN(10, "Ten", "10"),
    JACK(11, "Jack", "J"),
    QUEEN(12, "Queen", "Q"),
    KING(13, "King", "K"),
    ACE(14, "Ace", "A");

    private final int value;
    private final String rank;
    private final String symbol;

    CardRank(int value, String rank, String symbol) {
        this.value = value;
        this.rank = rank;
        this.symbol = symbol;
    }
}
