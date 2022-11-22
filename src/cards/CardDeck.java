package cards;

import exceptions.EmptyDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<PlayingCard> cards;

    public CardDeck () {
        cards = new ArrayList<>();
        for (CardRank rank : CardRank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                cards.add(new PlayingCard(rank, suit));
            }
        }
    }

    public CardDeck(List<PlayingCard> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public PlayingCard draw() throws EmptyDeckException {
        if (cards.isEmpty()) {
            throw new EmptyDeckException("Cannot draw from empty deck");
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
