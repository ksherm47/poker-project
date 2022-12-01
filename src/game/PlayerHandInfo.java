package game;

import rules.handevaluation.PokerHand;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerHandInfo {
    private PokerPlayer player;
    private PokerHand hand;
}
