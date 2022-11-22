package cards;

import lombok.Getter;

@Getter
public enum CardSuit {
    CLUBS("Clubs"),
    SPADES("Spades"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds");

    private final String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }
}
