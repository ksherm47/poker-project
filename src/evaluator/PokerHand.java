package evaluator;

import cards.CardRank;
import cards.PlayingCard;
import exceptions.PokerHandComparisonException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder(access = AccessLevel.PACKAGE)
public class PokerHand implements Comparable<PokerHand> {

    private final PokerHandTier tier;
    private final Set<PlayingCard> cards;
    private final List<CardRank> kickers;

    @Override
    public int compareTo(PokerHand other) {
        if (tier.betterThan(other.tier)) {
            return 1;
        }

        if (other.tier.betterThan(tier)) {
            return -1;
        }

        if (kickers.size() != other.kickers.size()) {
            throw new PokerHandComparisonException("Poker hands of same tier must contain same number of kickers.");
        }

        int i = 0;
        for (CardRank rank : kickers) {
            if (rank.betterThan(other.kickers.get(i))) {
                return 1;
            }
            if (other.kickers.get(i).betterThan(rank)) {
                return -1;
            }
            i++;
        }

        return 0;
    }

    @Override
    public String toString() {
        if (tier == PokerHandTier.ROYAL_FLUSH) {
            return tier.getTier();
        }

        String pokerHandString = tier.getTier() + ", ";
        switch (tier) {
            case FULL_HOUSE -> pokerHandString += getPlural(kickers.get(0)) + " over " + getPlural(kickers.get(1));
            case TWO_PAIR -> pokerHandString += getPlural(kickers.get(0)) + " and " + getPlural(kickers.get(1));
            case THREE_OF_A_KIND, FOUR_OF_A_KIND, PAIR -> pokerHandString += getPlural(kickers.get(0));
            default -> pokerHandString += kickers.get(0).getRank() + " High";
        }

        return pokerHandString;
    }

    public boolean betterThan(PokerHand otherHand) {
        return otherHand == null || compareTo(otherHand) > 0;
    }

    public boolean sameAs(PokerHand otherHand) {
        return otherHand != null && compareTo(otherHand) == 0;
    }

    private String getPlural(CardRank rank) {
        return rank.getRank() + (rank == CardRank.SIX ? "es" : "s");
    }
}
