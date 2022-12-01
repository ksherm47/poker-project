package rules.handranking.cardrank;

import cards.CardRank;

public interface CardRankRules {
    boolean cardRankIsBetter(CardRank cardRank, CardRank comparedCardRank);
}
