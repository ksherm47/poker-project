package rules.hand.handevaluation;

import cards.PlayingCard;
import com.google.common.collect.Sets;
import rules.hand.handranking.PokerHandRankingRuleSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OmahaPokerhandEvaluator extends PokerHandEvaluatorBase implements PokerHandEvaluator {

    private final PokerHandRankingRuleSet handRankingRuleSet;

    public OmahaPokerhandEvaluator(PokerHandRankingRuleSet handRankingRuleSet) {
        this.handRankingRuleSet = handRankingRuleSet;
    }

    @Override
    public PokerHand evaluatePokerHand(Set<PlayingCard> boardCards, Set<PlayingCard> handCards) {
        Set<PokerHand> possiblePokerHands = new HashSet<>();

        for (Set<PlayingCard> twoHandCards : Sets.combinations(handCards, 2)) {
            for (Set<PlayingCard> threeBoardCards : Sets.combinations(boardCards, 3)) {
                possiblePokerHands.add(evaluateFiveCardHand(Sets.union(twoHandCards, threeBoardCards)));
            }
        }

        return Collections.max(possiblePokerHands, handRankingRuleSet);
    }
}
