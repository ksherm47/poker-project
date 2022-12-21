package rules.hand.handranking.handtier;

public interface PokerHandTierRules {
    boolean handTierIsBetter(PokerHandTier handTier, PokerHandTier comparedHandTier);
}
