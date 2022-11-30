package game;

import cards.CardDeckType;
import cards.PlayingCard;
import evaluator.PokerHand;
import rules.PokerHandRules;
import rules.StandardPokerHandRules;

import java.util.*;

public class PokerGame {

    public static void main(String[] args) {
        PokerPlayer kenny = PokerPlayer.builder()
                .name("Kenny")
                .build();
        PokerPlayer brenna = PokerPlayer.builder()
                .name("Brenna")
                .build();
        PokerPlayer david = PokerPlayer.builder()
                .name("David")
                .build();
        PokerPlayer alice = PokerPlayer.builder()
                .name("Alice")
                .build();

        Set<PokerPlayer> players = Set.of(kenny, brenna, david, alice);
        PokerHandRules rules = new StandardPokerHandRules();
        PokerDealer dealer = new PokerDealer(CardDeckType.STANDARD, players);

        dealer.shuffleDeck();
        dealer.dealPlayers(2);

        dealer.dealToBoard(3);
        dealer.burnCard();
        dealer.dealToBoard(1);
        dealer.burnCard();
        dealer.dealToBoard(1);

        Set<PlayingCard> board = dealer.getBoardCards();

        System.out.println();
        System.out.println("Board: " + board);
        System.out.println();
        System.out.println("Kenny: " + kenny.getHandCards());
        System.out.println("Brenna: " + brenna.getHandCards());
        System.out.println("David: " + david.getHandCards());
        System.out.println("Alice: " + alice.getHandCards());
        System.out.println();

        for (PlayerHandInfo playerHandInfo : dealer.getWinningPlayers(rules)) {
            PokerPlayer player = playerHandInfo.getPlayer();
            PokerHand hand = playerHandInfo.getHand();
            System.out.println("Best Player: " + player.getName());
            System.out.println("Hand: " + hand + " " + hand.getCards());
        }
    }
}
