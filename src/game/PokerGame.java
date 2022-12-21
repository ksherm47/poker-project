package game;

import cards.CardDeck;
import cards.CardDeckType;
import rules.game.PokerGameFlow;
import rules.hand.PokerHandRuleSet;

public class PokerGame {

    private final PokerGameFlow pokerGameFlow;
    private final PokerHandRuleSet handRuleSet;
    private final CardDeck deck;

    public PokerGame(PokerGameFlow pokerGameFlow, PokerHandRuleSet handRuleSet, CardDeckType cardDeckType) {
        this.pokerGameFlow = pokerGameFlow;
        this.handRuleSet = handRuleSet;
        this.deck = new CardDeck(cardDeckType);
    }
}
