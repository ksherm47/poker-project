package main;

import cards.CardDeckType;
import cards.PlayingCard;
import game.PlayerHandInfo;
import game.PokerDealer;
import game.PokerPlayer;
import rules.hand.PokerHandRuleSet;
import rules.hand.handevaluation.PokerHand;

import java.util.Set;

public class PokerMain {

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
        PokerDealer dealer = new PokerDealer(CardDeckType.SHORT, players);
        PokerHandRuleSet handRuleSet = PokerHandRuleSet.SHORT_DECK;

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
        System.out.println("Kenny: " + kenny.getHandCards() + " (" + dealer.getPlayerPokerHand(kenny, handRuleSet) + ")");
        System.out.println("Brenna: " + brenna.getHandCards() + " (" + dealer.getPlayerPokerHand(brenna, handRuleSet) + ")");
        System.out.println("David: " + david.getHandCards() + " (" + dealer.getPlayerPokerHand(david, handRuleSet) + ")");
        System.out.println("Alice: " + alice.getHandCards() + " (" + dealer.getPlayerPokerHand(alice, handRuleSet) + ")");
        System.out.println("Sam: " + sam.getHandCards() + " (" + dealer.getPlayerPokerHand(sam, handRuleSet) + ")");
        System.out.println();

        for (PlayerHandInfo playerHandInfo : dealer.getWinningPlayers(handRuleSet)) {
            PokerPlayer player = playerHandInfo.getPlayer();
            PokerHand hand = playerHandInfo.getHand();
            System.out.println("Best Player: " + player.getName());
            System.out.println("Hand: " + hand + " " + hand.getCards());
        }
    }
}
