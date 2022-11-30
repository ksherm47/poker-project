package rules;

import evaluator.PokerHand;

public class StandardPokerHandRules implements PokerHandRules {

    @Override
    public boolean handIsBetter(PokerHand hand, PokerHand comparedHand) {
        return hand != null && (comparedHand == null || hand.compareTo(comparedHand) > 0);
    }

    @Override
    public boolean handIsSame(PokerHand hand, PokerHand comparedHand) {
        return (hand == null && comparedHand == null) ||
                (hand != null && comparedHand != null && hand.compareTo(comparedHand) == 0);
    }
}
