package game;

import cards.PlayingCard;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PokerPlayer {

    @Getter
    @EqualsAndHashCode.Include
    private String name;

    @Setter
    @Getter
    private int money;

    @Builder.Default
    private final Set<PlayingCard> handCards = new HashSet<>();

    public void dealCard(PlayingCard card) {
        handCards.add(card);
    }

    public Set<PlayingCard> getHandCards() {
        return new HashSet<>(handCards);
    }
}
