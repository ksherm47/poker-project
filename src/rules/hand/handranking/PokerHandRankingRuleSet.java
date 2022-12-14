package rules.hand.handranking;

import cards.CardRank;
import rules.hand.handevaluation.PokerHand;
import lombok.Getter;
import rules.hand.handranking.cardrank.CardRankRules;
import rules.hand.handranking.cardrank.LowballCardRankRules;
import rules.hand.handranking.cardrank.StandardCardRankRules;
import rules.hand.handranking.handtier.LowballHandTierRules;
import rules.hand.handranking.handtier.PokerHandTierRules;
import rules.hand.handranking.handtier.ShortDeckHandTierRules;
import rules.hand.handranking.handtier.StandardHandTierRules;

import java.util.Comparator;

@Getter
public enum PokerHandRankingRuleSet implements Comparator<PokerHand> {

    STANDARD(new StandardHandTierRules(), new StandardCardRankRules()),
    SHORT_DECK(new ShortDeckHandTierRules(), new StandardCardRankRules()),
    LOWBALL(new LowballHandTierRules(), new LowballCardRankRules());

    private final PokerHandTierRules handTierRules;
    private final CardRankRules cardRankRules;

    PokerHandRankingRuleSet(PokerHandTierRules handTierRules, CardRankRules cardRankRules) {
        this.handTierRules = handTierRules;
        this.cardRankRules = cardRankRules;
    }

    @Override
    public int compare(PokerHand pokerHand, PokerHand comparedPokerHand) {
        if (pokerHand != null && comparedPokerHand == null) {
            return 1;
        }

        if (pokerHand == null) {
            if (comparedPokerHand == null) {
                return 0;
            }
            return -1;
        }

        if (handTierRules.handTierIsBetter(pokerHand.getTier(), comparedPokerHand.getTier())) {
            return 1;
        }

        if (handTierRules.handTierIsBetter(comparedPokerHand.getTier(), pokerHand.getTier())) {
            return -1;
        }

        int i = 0;
        for (CardRank cardRank : pokerHand.getKickers()) {
            if (cardRankRules.cardRankIsBetter(cardRank, comparedPokerHand.getKickers().get(i))) {
                return 1;
            }
            if (cardRankRules.cardRankIsBetter(comparedPokerHand.getKickers().get(i), cardRank)) {
                return -1;
            }
            i++;
        }

        return 0;
    }
}
