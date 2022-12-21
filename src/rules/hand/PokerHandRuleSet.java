package rules.hand;

import lombok.Getter;
import rules.hand.handevaluation.OmahaPokerhandEvaluator;
import rules.hand.handevaluation.PokerHandEvaluator;
import rules.hand.handevaluation.StandardPokerHandEvaluator;
import rules.hand.handranking.PokerHandRankingRuleSet;

@Getter
public enum PokerHandRuleSet {

    STANDARD(PokerHandRankingRuleSet.STANDARD, new StandardPokerHandEvaluator(PokerHandRankingRuleSet.STANDARD)),
    LOWBALL(PokerHandRankingRuleSet.LOWBALL, new StandardPokerHandEvaluator(PokerHandRankingRuleSet.LOWBALL)),
    SHORT_DECK(PokerHandRankingRuleSet.SHORT_DECK, new StandardPokerHandEvaluator(PokerHandRankingRuleSet.SHORT_DECK)),
    OMAHA(PokerHandRankingRuleSet.STANDARD, new OmahaPokerhandEvaluator(PokerHandRankingRuleSet.STANDARD));

    private final PokerHandRankingRuleSet handRankingRuleSet;
    private final PokerHandEvaluator handEvaluator;

    PokerHandRuleSet(PokerHandRankingRuleSet handRankingRuleSet, PokerHandEvaluator handEvaluator) {
        this.handRankingRuleSet = handRankingRuleSet;
        this.handEvaluator = handEvaluator;
    }
}
