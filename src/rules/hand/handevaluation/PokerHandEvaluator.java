package rules.hand.handevaluation;

import cards.PlayingCard;

import java.util.Set;

public interface PokerHandEvaluator {
    PokerHand evaluatePokerHand(Set<PlayingCard> boardCards, Set<PlayingCard> handCards);
}
