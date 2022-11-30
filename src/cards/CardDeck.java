package cards;

import com.google.common.collect.Iterables;
import exceptions.DeckSizeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CardDeck {
    private final List<PlayingCard> cards;

    public CardDeck(CardDeckType deckType) {
        cards = deckType.getCards();
    }

    public CardDeck(List<PlayingCard> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public PlayingCard draw() {
        return Iterables.getOnlyElement(draw(1));
    }

    public Set<PlayingCard> draw(int numberOfCards) {
        if (cards.size() < numberOfCards) {
            throw new DeckSizeException("Cannot draw " + numberOfCards + " cards, deck size = " + cards.size());
        }

        PlayingCard[] drawnCards = new PlayingCard[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            drawnCards[i] = cards.remove(0);
        }
        return Set.of(drawnCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
