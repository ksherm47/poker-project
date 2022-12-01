package game;

import cards.CardDeck;
import cards.CardDeckType;
import cards.PlayingCard;
import rules.handevaluation.PokerHand;
import rules.PokerHandRuleSet;

import java.util.HashSet;
import java.util.Set;
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

    public Set<PlayerHandInfo> getWinningPlayers(PokerHandRuleSet handRuleSet) {
        PokerHand bestHand = null;
        Set<PlayerHandInfo> bestPlayersInfo = new HashSet<>();

        for (PokerPlayer player : players) {
            PokerHand hand = handRuleSet.getHandEvaluator().evaluatePokerHand(board, player.getHandCards());
            int comparison = handRuleSet.getHandRankingRuleSet().compare(hand, bestHand);

            if (comparison >= 0) {
                if (comparison > 0) {
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

    public PokerHand getPlayerPokerHand(PokerPlayer player, PokerHandRuleSet handRuleSet) {
        return handRuleSet.getHandEvaluator().evaluatePokerHand(board, player.getHandCards());
    }
}
