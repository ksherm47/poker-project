package rules.hand.handranking.handtier;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class StandardHandTierRules implements PokerHandTierRules {

    private static final Map<PokerHandTier, Integer> STANDARD_HAND_TIER_ORDERING =
        ImmutableMap.<PokerHandTier, Integer>builder()
                .put(PokerHandTier.HIGH_CARD, 1)
                .put(PokerHandTier.PAIR, 2)
                .put(PokerHandTier.TWO_PAIR, 3)
                .put(PokerHandTier.THREE_OF_A_KIND, 4)
                .put(PokerHandTier.STRAIGHT, 5)
                .put(PokerHandTier.FLUSH, 6)
                .put(PokerHandTier.FULL_HOUSE, 7)
                .put(PokerHandTier.FOUR_OF_A_KIND, 8)
                .put(PokerHandTier.STRAIGHT_FLUSH, 9)
                .put(PokerHandTier.ROYAL_FLUSH, 10)
                .build();

    @Override
    public boolean handTierIsBetter(PokerHandTier handTier, PokerHandTier comparedHandTier) {
        return STANDARD_HAND_TIER_ORDERING.get(handTier) > STANDARD_HAND_TIER_ORDERING.get(comparedHandTier);
    }
}
