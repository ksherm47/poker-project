package rules.game;

import game.PlayerHandInfo;
import game.PokerDealer;
import rules.hand.PokerHandRuleSet;

import java.util.Set;

public abstract class PokerGameFlow {

    private final PokerDealer dealer;

    public PokerGameFlow(PokerDealer dealer) {
        this.dealer = dealer;
    }

    abstract boolean gameEnded();
    abstract boolean handEnded();
    abstract void nextHand();
    abstract void nextHandStage();
}
