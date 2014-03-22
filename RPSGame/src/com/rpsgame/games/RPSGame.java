
package com.rpsgame.games;

import com.rpsgame.actions.RPSGesture;
import com.rpsgame.players.RPSPlayer;
import com.rpsgame.results.RPSResult;
import com.rpsgame.view.RPSView;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class providing an implementation for a RPS game.
 *
 * @author Frank van Heeswijk
 */
public class RPSGame extends AbstractGame<RPSGesture, RPSResult, RPSPlayer> {
    /** An executor service to run the player calculations on, initialized to two times the available cores. */
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    /** The RPSView interface. */
    private final RPSView rpsView;

    /**
     * Construcs the RPSGame with the RPSView interface as argument.
     * 
     * @param rpsView   The RPS view
     */
    public RPSGame(final RPSView rpsView) {
        this.rpsView = rpsView;
    }

    @Override
    public void playRound() {
        Map<RPSPlayer, RPSGesture> playerGestures = players.stream()
                .collect(Collectors.toMap(
                        player -> player, 
                        player -> player.doActionWithTimeout(executorService, 10, TimeUnit.SECONDS))
                );
        playerGestures.forEach((player, gesture) -> {
            RPSResult result = determinePlayerResult(playerGestures, player, gesture);
            players.forEach(p -> p.onPostAction(player, gesture, result));
            rpsView.processPlayerAction(player, gesture, result);
        });
    }

    @Override
    public RPSResult determinePlayerResult(final Map<RPSPlayer, RPSGesture> playerActions, final RPSPlayer forPlayer, final RPSGesture forGesture) {
        int wins = 0;
        int ties = 0;
        int losses = 0;
        for (Map.Entry<RPSPlayer, RPSGesture> entry : playerActions.entrySet()) {
            RPSPlayer player = entry.getKey();
            RPSGesture gesture = entry.getValue();
            if (player.equals(forPlayer)) {
                continue;
            }
            if (forGesture.winsFrom(gesture)) {
                wins++;
            }
            else if (forGesture.tiesTo(gesture)) {
                ties++;
            }
            else if (forGesture.losesFrom(gesture)) {
                losses++;
            }
        }
        int max = IntStream.of(wins, ties, losses).max().getAsInt();
        if (max == wins) {
            return RPSResult.WIN;
        }
        else if (max == ties) {
            return RPSResult.TIE;
        }
        else {
            return RPSResult.LOSS;
        }
    }
}
