package game;

import cards.CardDeck;
import cards.PlayingCard;
import com.google.common.collect.Sets;
import evaluator.PokerHand;
import evaluator.PokerHandEvaluator;
import exceptions.PokerDealerException;

import java.util.*;
import java.util.stream.IntStream;

public class PokerDealer {

    private final CardDeck deck;
    private final Set<PokerPlayer> players;
    private final Map<PokerPlayer, PokerHand> playerPokerHands;

    private final Set<PlayingCard> board;

    public PokerDealer(CardDeck deck, Set<PokerPlayer> players) {
        this.deck = deck;
        this.players = players;

        board = new HashSet<>();
        playerPokerHands = new HashMap<>();

        updateAllPlayerHands();
    }

    public void addPlayer(PokerPlayer player) {
        players.add(player);
        updatePlayerHand(player);
    }

    public void removePlayer(PokerPlayer player) {
        players.remove(player);
        playerPokerHands.remove(player);
    }

    public void dealPlayers(int numberOfCards) {
        IntStream.range(0, numberOfCards).forEach(
            n -> {
                for (PokerPlayer player : players) {
                    player.dealCard(deck.draw());
                }
            }
        );
        updateAllPlayerHands();
    }

    public void dealToBoard(int numberOfCards) {
        board.addAll(deck.draw(numberOfCards));
        updateAllPlayerHands();
    }

    public void burnCard() {
        deck.draw();
    }

    public Set<PlayingCard> getBoardCards() {
        return new HashSet<>(board);
    }

    public PokerHand getPlayerPokerHand(PokerPlayer player) {
        if (!players.contains(player)) {
            throw new PokerDealerException("Player " + player.getName() + " not added to dealer.");
        }

        return playerPokerHands.get(player);
    }

    public Set<PokerPlayer> getBestPlayers() {
        PokerHand bestHand = null;
        Set<PokerPlayer> bestPlayers = new HashSet<>();
        for (Map.Entry<PokerPlayer, PokerHand> playerHandPair : playerPokerHands.entrySet()) {
            PokerPlayer player = playerHandPair.getKey();
            PokerHand hand = playerHandPair.getValue();

            if (hand.betterThan(bestHand)) {
                bestHand = hand;
                bestPlayers.clear();
                bestPlayers.add(player);
            } else if (hand.sameAs(bestHand)) {
                bestPlayers.add(player);
            }
        }
        return bestPlayers;
    }

    private void updatePlayerHand(PokerPlayer player) {
        if (!players.contains(player)) {
            throw new PokerDealerException("Player " + player.getName() + " not added to dealer.");
        }

        PokerHand pokerHand = null;
        if (!player.getHandCards().isEmpty()) {
            pokerHand = PokerHandEvaluator.evaluatePokerHand(Sets.union(player.getHandCards(), board));
        }
        playerPokerHands.put(player, pokerHand);
    }

    private void updateAllPlayerHands() {
        for (PokerPlayer player : players) {
            updatePlayerHand(player);
        }
    }
}
