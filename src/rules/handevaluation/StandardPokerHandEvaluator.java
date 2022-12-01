package rules.handevaluation;

import cards.CardRank;
import cards.PlayingCard;
import com.google.common.collect.Sets;
import rules.handranking.PokerHandRankingRuleSet;
import rules.handranking.handtier.PokerHandTier;
import exceptions.PokerHandEvaluationException;

import java.util.*;
import java.util.stream.Collectors;

public class StandardPokerHandEvaluator extends PokerHandEvaluatorBase implements PokerHandEvaluator {

    private final PokerHandRankingRuleSet handRankingRuleSet;

    public StandardPokerHandEvaluator(PokerHandRankingRuleSet handRankingRuleSet) {
        this.handRankingRuleSet = handRankingRuleSet;
    }

    @Override
    public PokerHand evaluatePokerHand(Set<PlayingCard> boardCards, Set<PlayingCard> handCards) {
        Set<PlayingCard> cards = Sets.union(boardCards, handCards);

        if (cards.isEmpty()) {
            throw new PokerHandEvaluationException("Card set must not be empty or null");
        }

        return switch (cards.size()) {
            case 1 -> PokerHand.builder()
                    .cards(cards)
                    .tier(PokerHandTier.HIGH_CARD)
                    .kickers(cards.stream().map(PlayingCard::rank).toList())
                    .build();
            case 2 -> evaluateTwoCardHand(cards);
            case 3 -> evaluateThreeCardHand(cards);
            case 4 -> evaluateFourCardHand(cards);
            default -> Collections.max(Sets.combinations(cards, 5).stream()
                    .map(this::evaluateFiveCardHand)
                    .collect(Collectors.toSet()), handRankingRuleSet);
        };
    }
}
