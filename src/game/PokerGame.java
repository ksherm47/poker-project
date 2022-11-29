package game;

import cards.CardDeck;
import cards.PlayingCard;

import java.util.*;

public class PokerGame {

    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        deck.shuffle();

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

        PokerDealer dealer = new PokerDealer(deck, Set.of(kenny, brenna, david, alice));
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
        System.out.println("Kenny: " + kenny.getHandCards() + " (" + dealer.getPlayerPokerHand(kenny) + ")");
        System.out.println("Brenna: " + brenna.getHandCards() + " (" + dealer.getPlayerPokerHand(brenna) + ")");
        System.out.println("David: " + david.getHandCards() + " (" + dealer.getPlayerPokerHand(david) + ")");
        System.out.println("Alice: " + alice.getHandCards() + " (" + dealer.getPlayerPokerHand(alice) + ")");
        System.out.println();

        for (PokerPlayer player : dealer.getBestPlayers()) {
            System.out.println("Best Hand: " + player.getName() + " " + dealer.getPlayerPokerHand(player).getCards());
        }
    }
}
