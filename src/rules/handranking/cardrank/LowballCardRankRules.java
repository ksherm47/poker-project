package rules.handranking.cardrank;

import cards.CardRank;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class LowballCardRankRules implements CardRankRules {

    private static final Map<CardRank, Integer> LOWBALL_CARD_ORDERING = ImmutableMap.<CardRank, Integer>builder()
            .put(CardRank.TWO, 13)
            .put(CardRank.THREE, 12)
            .put(CardRank.FOUR, 11)
            .put(CardRank.FIVE, 10)
            .put(CardRank.SIX, 9)
            .put(CardRank.SEVEN, 8)
            .put(CardRank.EIGHT, 7)
            .put(CardRank.NINE, 6)
            .put(CardRank.TEN, 5)
            .put(CardRank.JACK, 4)
            .put(CardRank.QUEEN, 3)
            .put(CardRank.KING, 2)
            .put(CardRank.ACE, 1)
            .build();

    @Override
    public boolean cardRankIsBetter(CardRank cardRank, CardRank comparedCardRank) {
        return LOWBALL_CARD_ORDERING.get(cardRank) > LOWBALL_CARD_ORDERING.get(comparedCardRank);
    }
}
