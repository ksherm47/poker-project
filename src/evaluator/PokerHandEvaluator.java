package evaluator;

import cards.CardRank;
import cards.PlayingCard;
import com.google.common.collect.Sets;
import exceptions.PokerHandEvaluationException;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHandEvaluator {

    private static final List<CardRank> WHEEL_STRAIGHT_RANKS =
            List.of(CardRank.ACE, CardRank.FIVE, CardRank.FOUR, CardRank.THREE, CardRank.TWO);

    private PokerHandEvaluator() {}

    public static PokerHand evaluatePokerHand(Set<PlayingCard> cards) {
        if (cards == null || cards.isEmpty()) {
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
                    .map(PokerHandEvaluator::evaluateFiveCardHand)
                    .collect(Collectors.toSet()));
        };
    }

    private static PokerHand evaluateTwoCardHand(Set<PlayingCard> cards) {
        if (cards.size() != 2) {
            throw new PokerHandEvaluationException("Number of cards in set to evaluate must be 2.");
        }

        List<Map.Entry<CardRank, Integer>> cardRankCounts = sortHand(cards);
        List<CardRank> kickers = cardRankCounts.stream().map(Map.Entry::getKey).toList();

        PokerHandTier pokerHandTier = cardRankCounts.size() == 1 ? PokerHandTier.PAIR : PokerHandTier.HIGH_CARD;
        return PokerHand.builder()
                .cards(cards)
                .tier(pokerHandTier)
                .kickers(kickers)
                .build();
    }

    private static PokerHand evaluateThreeCardHand(Set<PlayingCard> cards) {
        if (cards.size() != 3) {
            throw new PokerHandEvaluationException("Number of cards in set to evaluate must be 3.");
        }

        List<Map.Entry<CardRank, Integer>> cardRankCounts = sortHand(cards);
        List<CardRank> kickers = cardRankCounts.stream().map(Map.Entry::getKey).toList();

        PokerHandTier pokerHandTier = switch (cardRankCounts.size()) {
            case 1 -> PokerHandTier.THREE_OF_A_KIND;
            case 2 -> PokerHandTier.PAIR;
            case 3 -> PokerHandTier.HIGH_CARD;
            default -> null;
        };

        return PokerHand.builder()
                .cards(cards)
                .tier(pokerHandTier)
                .kickers(kickers)
                .build();
    }

    private static PokerHand evaluateFourCardHand(Set<PlayingCard> cards) {
        if (cards.size() != 4) {
            throw new PokerHandEvaluationException("Number of cards in set to evaluate must be 4.");
        }

        List<Map.Entry<CardRank, Integer>> cardRankCounts = sortHand(cards);
        List<CardRank> kickers = cardRankCounts.stream().map(Map.Entry::getKey).toList();

        PokerHandTier pokerHandTier = null;
        switch (cardRankCounts.size()) {
            case 1:
                pokerHandTier = PokerHandTier.FOUR_OF_A_KIND;
                break;
            case 2:
                if (cardRankCounts.get(0).getValue() == 2) {
                    pokerHandTier = PokerHandTier.TWO_PAIR;
                } else {
                    pokerHandTier = PokerHandTier.THREE_OF_A_KIND;
                }
                break;
            case 3:
                pokerHandTier = PokerHandTier.PAIR;
                break;
            case 4:
                pokerHandTier = PokerHandTier.HIGH_CARD;
                break;
        }

        return PokerHand.builder()
                .cards(cards)
                .tier(pokerHandTier)
                .kickers(kickers)
                .build();
    }

    private static PokerHand evaluateFiveCardHand(Set<PlayingCard> cards) {
        if (cards.size() != 5) {
            throw new PokerHandEvaluationException("Number of cards in set to evaluate must be 5.");
        }

        List<Map.Entry<CardRank, Integer>> cardRankCounts = sortHand(cards);
        List<CardRank> kickers = cardRankCounts.stream().map(Map.Entry::getKey).toList();

        PokerHandTier pokerHandTier = null;
        switch (cardRankCounts.size()) {
            case 2:
                if (cardRankCounts.get(0).getValue() == 4) {
                    pokerHandTier = PokerHandTier.FOUR_OF_A_KIND;
                } else {
                    pokerHandTier = PokerHandTier.FULL_HOUSE;
                }
                break;
            case 3:
                if (cardRankCounts.get(0).getValue() == 3) {
                    pokerHandTier = PokerHandTier.THREE_OF_A_KIND;
                } else {
                    pokerHandTier = PokerHandTier.TWO_PAIR;
                }
                break;
            case 4:
                pokerHandTier = PokerHandTier.PAIR;
                break;
            case 5:
                int lastRankValue = kickers.get(0).getValue();
                int firstRankValue = kickers.get(4).getValue();

                boolean isWheelStraight = kickers.equals(WHEEL_STRAIGHT_RANKS);
                boolean isStraight = lastRankValue - firstRankValue == 4 || isWheelStraight;
                boolean isFlush = cards.stream().map(PlayingCard::suit).collect(Collectors.toSet()).size() == 1;

                if (isStraight) {
                    kickers = List.of(isWheelStraight ? kickers.get(1) : kickers.get(0));
                    if (isFlush) {
                        pokerHandTier = kickers.get(0) == CardRank.ACE ?
                                PokerHandTier.ROYAL_FLUSH : PokerHandTier.STRAIGHT_FLUSH;
                    } else {
                        pokerHandTier = PokerHandTier.STRAIGHT;
                    }
                } else if (isFlush) {
                    pokerHandTier = PokerHandTier.FLUSH;
                } else {
                    pokerHandTier = PokerHandTier.HIGH_CARD;
                }
        }

        return PokerHand.builder()
                .cards(cards)
                .tier(pokerHandTier)
                .kickers(kickers)
                .build();
    }

    private static List<Map.Entry<CardRank, Integer>> sortHand(Set<PlayingCard> cards) {
        Map<CardRank, Integer> cardRankCounts = new HashMap<>();

        for (PlayingCard card : cards) {
            CardRank rank = card.rank();
            cardRankCounts.put(rank, cardRankCounts.getOrDefault(rank, 0) + 1);
        }

        // Sort entries primarily by rank count and then secondarily by card rank itself
        List<Map.Entry<CardRank, Integer>> entryList = new ArrayList<>(cardRankCounts.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getKey().getValue() - entry1.getKey().getValue());
        entryList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
        return entryList;
    }
}
