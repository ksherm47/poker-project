package cards;

import lombok.Getter;

@Getter
public enum CardSuit {
    CLUBS(CardFormatting.CLUB),
    SPADES(CardFormatting.SPADE),
    HEARTS(CardFormatting.HEART),
    DIAMONDS(CardFormatting.DIAMOND);

    private final String symbol;

    CardSuit(String symbol) {
        this.symbol = symbol;
    }
}
