package game;

import cards.CardDeck;
import cards.CardRank;
import cards.CardSuit;
import cards.PlayingCard;
import com.google.common.collect.Sets;
import evaluator.PokerHand;
import evaluator.PokerHandEvaluator;
import evaluator.PokerHandTier;
import exceptions.EmptyDeckException;
import exceptions.PokerHandEvaluationException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokerGame {

    public static void main(String[] args) {

        //for (int i = 0; i < 10000; i++) {
            CardDeck deck = new CardDeck();
            //Set<PlayingCard> handCards = new HashSet<>();
            deck.shuffle();

            Set<PlayingCard> board = new HashSet<>();
            Set<PlayingCard> hand1 = new HashSet<>();
            Set<PlayingCard> hand2 = new HashSet<>();
            Set<PlayingCard> hand3 = new HashSet<>();

            hand1.add(deck.draw());
            hand2.add(deck.draw());
            hand3.add(deck.draw());
            hand1.add(deck.draw());
            hand2.add(deck.draw());
            hand3.add(deck.draw());

            board.add(deck.draw());
            board.add(deck.draw());
            board.add(deck.draw());
            deck.draw();
            board.add(deck.draw());
            deck.draw();
            board.add(deck.draw());

            System.out.println("Board: " + board);
            System.out.println("Hand 1: " + hand1);
            System.out.println("Hand 2: " + hand2);
            System.out.println("Hand 3: " + hand3);
            System.out.println();

            PokerHand pokerHand1 = PokerHandEvaluator.evaluatePokerHand(Sets.union(board, hand1));
            PokerHand pokerHand2 = PokerHandEvaluator.evaluatePokerHand(Sets.union(board, hand2));
            PokerHand pokerHand3 = PokerHandEvaluator.evaluatePokerHand(Sets.union(board, hand3));

            PokerHand bestHand = Collections.max(List.of(pokerHand1, pokerHand2, pokerHand3));

            System.out.println("Best Hand: " + bestHand + " " + bestHand.getCards());

//            PokerHand pokerHand = PokerHandEvaluator.evaluatePokerHand(handCards);
//            if (pokerHand.getTier().betterThan(PokerHandTier.FULL_HOUSE)) {
//                System.out.println("Cards dealt: " + handCards);
//                System.out.println("Hand Cards: " + pokerHand.getCards());
//                System.out.println("Hand: " + pokerHand);
//                System.out.println();
//            }
        //}
    }
}
