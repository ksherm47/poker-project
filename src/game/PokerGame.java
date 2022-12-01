package game;

import cards.CardDeckType;
import cards.PlayingCard;
import rules.PokerHandRuleSet;
import rules.handevaluation.PokerHand;

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
        PokerPlayer sam = PokerPlayer.builder()
                .name("Sam")
                .build();

        Set<PokerPlayer> players = Set.of(kenny, brenna, david, alice, sam);
        PokerDealer dealer = new PokerDealer(CardDeckType.STANDARD, players);

        dealer.shuffleDeck();
        dealer.dealPlayers(4);

        dealer.dealToBoard(3);
        dealer.burnCard();
        dealer.dealToBoard(1);
        dealer.burnCard();
        dealer.dealToBoard(1);

        Set<PlayingCard> board = dealer.getBoardCards();

        System.out.println();
        System.out.println("Board: " + board);
        System.out.println();
        System.out.println("Kenny: " + kenny.getHandCards() +
                " (" + dealer.getPlayerPokerHand(kenny, PokerHandRuleSet.OMAHA) + ")");
        System.out.println("Brenna: " + brenna.getHandCards() +
                " (" + dealer.getPlayerPokerHand(brenna, PokerHandRuleSet.OMAHA) + ")");
        System.out.println("David: " + david.getHandCards() +
                " (" + dealer.getPlayerPokerHand(david, PokerHandRuleSet.OMAHA) + ")");
        System.out.println("Alice: " + alice.getHandCards() +
                " (" + dealer.getPlayerPokerHand(alice, PokerHandRuleSet.OMAHA) + ")");
        System.out.println("Sam: " + sam.getHandCards() +
                " (" + dealer.getPlayerPokerHand(sam, PokerHandRuleSet.OMAHA) + ")");
        System.out.println();

        for (PlayerHandInfo playerHandInfo : dealer.getWinningPlayers(PokerHandRuleSet.OMAHA)) {
            PokerPlayer player = playerHandInfo.getPlayer();
            PokerHand hand = playerHandInfo.getHand();
            System.out.println("Best Player: " + player.getName());
            System.out.println("Hand: " + hand + " " + hand.getCards());
        }
    }
}
