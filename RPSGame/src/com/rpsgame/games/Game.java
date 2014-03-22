
package com.rpsgame.games;

import com.rpsgame.actions.Action;
import com.rpsgame.players.Player;
import com.rpsgame.results.Result;
import java.util.Map;

/**
 * An interface that can be used for playing any type of game. 
 *
 * @author Frank van Heeswijk
 * @param <A> The type of action in the game
 * @param <R> The type of result in the game
 * @param <P> The type of player in the game
 */
public interface Game<A extends Action, R extends Result, P extends Player<A, R>> {
    /**
     * Adds a player to this game.
     * 
     * @param player    The new player
     */
    public void addPlayer(final P player);

    /**
     * Plays one round of the game. 
     */
    public void playRound();

    /**
     * Default implementation of playing a game.
     * 
     * This will play a game that lasts forever.
     */
    default public void playGame() {
        boolean playing = true;
        while (playing) {
            playRound();
        }
    }

    /**
     * Returns the result of a player after having played the current round.
     * 
     * @param playerActions A mapping from the players to their actions
     * @param forPlayer The player for which to determine the result
     * @param forAction The action that belongs to the player
     * @return  The result of the player for this round
     */
    public R determinePlayerResult(final Map<P, A> playerActions, final P forPlayer, final A forAction);
}
