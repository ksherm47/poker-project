package game;

import cards.CardDeck;
import cards.CardDeckType;
import cards.PlayingCard;
import com.google.common.collect.Sets;
import evaluator.PokerHand;
import evaluator.PokerHandEvaluator;
import rules.PokerHandRules;

import java.util.*;
import java.util.stream.IntStream;

public class PokerDealer {

    private final CardDeck deck;
    private final Set<PokerPlayer> players;
    private final Set<PlayingCard> board;

    public PokerDealer(CardDeckType deckType, Set<PokerPlayer> players) {
        this.players = players;

        deck = new CardDeck(deckType);
        board = new HashSet<>();
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void addPlayer(PokerPlayer player) {
        players.add(player);
    }

    public void removePlayer(PokerPlayer player) {
        players.remove(player);
    }

    public void dealPlayers(int numberOfCards) {
        IntStream.range(0, numberOfCards).forEach(
            n -> {
                for (PokerPlayer player : players) {
                    player.dealCard(deck.draw());
                }
            }
        );
    }

    public void dealToBoard(int numberOfCards) {
        board.addAll(deck.draw(numberOfCards));
    }

    public void burnCard() {
        deck.draw();
    }

    public Set<PlayingCard> getBoardCards() {
        return new HashSet<>(board);
    }

    public Set<PlayerHandInfo> getWinningPlayers(PokerHandRules rules) {
        PokerHand bestHand = null;
        Set<PlayerHandInfo> bestPlayersInfo = new HashSet<>();

        for (PokerPlayer player : players) {
            PokerHand hand = PokerHandEvaluator.evaluatePokerHand(Sets.union(board, player.getHandCards()));
            boolean handIsBetter = rules.handIsBetter(hand, bestHand);
            if (handIsBetter || rules.handIsSame(hand, bestHand)) {
                if (handIsBetter) {
                    bestHand = hand;
                    bestPlayersInfo.clear();
                }
                bestPlayersInfo.add(PlayerHandInfo.builder()
                        .player(player)
                        .hand(hand)
                        .build());
            }
        }
        return bestPlayersInfo;
    }
}
