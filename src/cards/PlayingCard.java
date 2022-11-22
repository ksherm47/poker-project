package cards;

public record PlayingCard(CardRank rank, CardSuit suit) {
    public String toString() {
        return rank.getRank() + " of " + suit.getSuit();
    }
}
