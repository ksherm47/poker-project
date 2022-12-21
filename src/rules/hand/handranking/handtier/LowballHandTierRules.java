package rules.hand.handranking.handtier;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class LowballHandTierRules implements PokerHandTierRules {

    private static final Map<PokerHandTier, Integer> LOWBALL_HAND_TIER_ORDERING =
            ImmutableMap.<PokerHandTier, Integer>builder()
                    .put(PokerHandTier.HIGH_CARD, 10)
                    .put(PokerHandTier.PAIR, 9)
                    .put(PokerHandTier.TWO_PAIR, 8)
                    .put(PokerHandTier.THREE_OF_A_KIND, 7)
                    .put(PokerHandTier.STRAIGHT, 6)
                    .put(PokerHandTier.FLUSH, 5)
                    .put(PokerHandTier.FULL_HOUSE, 4)
                    .put(PokerHandTier.FOUR_OF_A_KIND, 3)
                    .put(PokerHandTier.STRAIGHT_FLUSH, 2)
                    .put(PokerHandTier.ROYAL_FLUSH, 1)
                    .build();
    @Override
    public boolean handTierIsBetter(PokerHandTier handTier, PokerHandTier comparedHandTier) {
        return LOWBALL_HAND_TIER_ORDERING.get(handTier) > LOWBALL_HAND_TIER_ORDERING.get(comparedHandTier);
    }
}
