package rules;

import evaluator.PokerHand;

public interface PokerHandRules {
    boolean handIsBetter(PokerHand hand, PokerHand comparedHand);
    boolean handIsSame(PokerHand hand, PokerHand comparedHand);
}
