package rules;

import lombok.Getter;
import rules.handevaluation.OmahaPokerhandEvaluator;
import rules.handevaluation.PokerHandEvaluator;
import rules.handevaluation.StandardPokerHandEvaluator;
import rules.handranking.PokerHandRankingRuleSet;

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
