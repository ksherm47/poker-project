package cards;

import java.util.ArrayList;
import java.util.List;

public enum CardDeckType {
    STANDARD,
    SHORT;

    public List<PlayingCard> getCards() {
        List<PlayingCard> cards = new ArrayList<>();
        switch (this) {
            case SHORT:
                for (CardSuit suit : CardSuit.values()) {
                    for (CardRank rank : CardRank.values()) {
                        if (rank.getValue() > CardRank.FIVE.getValue()) {
                            cards.add(new PlayingCard(rank, suit));
                        }
                    }
                }
                break;
            case STANDARD:
            default:
                for (CardSuit suit : CardSuit.values()) {
                    for (CardRank rank : CardRank.values()) {
                        cards.add(new PlayingCard(rank, suit));
                    }
                }
        }
        return cards;
    }
}
