package rules.hand.handevaluation;

import cards.CardRank;
import cards.PlayingCard;
import rules.hand.handranking.handtier.PokerHandTier;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder(access = AccessLevel.PACKAGE)
public class PokerHand {

    private final PokerHandTier tier;
    private final Set<PlayingCard> cards;
    private final List<CardRank> kickers;

    @Override
    public String toString() {
        if (tier == PokerHandTier.ROYAL_FLUSH) {
            return tier.getTier();
        }

        StringBuilder pokerHandString = new StringBuilder(tier.getTier()).append(", ");
        switch (tier) {
            case FULL_HOUSE -> pokerHandString.append(plural(kickers.get(0)))
                    .append(" over ")
                    .append(plural(kickers.get(1)));
            case TWO_PAIR -> pokerHandString.append(plural(kickers.get(0)))
                    .append(" and ")
                    .append(plural(kickers.get(1)));
            case THREE_OF_A_KIND, FOUR_OF_A_KIND, PAIR -> pokerHandString.append(plural(kickers.get(0)));
            default -> pokerHandString.append(kickers.get(0).getRank()).append(" High");
        }

        return pokerHandString.toString();
    }

    private String plural(CardRank rank) {
        return rank.getRank() + (rank == CardRank.SIX ? "es" : "s");
    }
}
