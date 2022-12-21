package rules.hand.handranking.cardrank;

import cards.CardRank;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class StandardCardRankRules implements CardRankRules {

    private static final Map<CardRank, Integer> STANDARD_CARD_ORDERING = ImmutableMap.<CardRank, Integer>builder()
            .put(CardRank.TWO, 1)
            .put(CardRank.THREE, 2)
            .put(CardRank.FOUR, 3)
            .put(CardRank.FIVE, 4)
            .put(CardRank.SIX, 5)
            .put(CardRank.SEVEN, 6)
            .put(CardRank.EIGHT, 7)
            .put(CardRank.NINE, 8)
            .put(CardRank.TEN, 9)
            .put(CardRank.JACK, 10)
            .put(CardRank.QUEEN, 11)
            .put(CardRank.KING, 12)
            .put(CardRank.ACE, 13)
            .build();

    @Override
    public boolean cardRankIsBetter(CardRank cardRank, CardRank comparedCardRank) {
        return STANDARD_CARD_ORDERING.get(cardRank) > STANDARD_CARD_ORDERING.get(comparedCardRank);
    }
}
