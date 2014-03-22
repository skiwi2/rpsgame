
package com.rpsgame.players;

import com.rpsgame.actions.Action;
import com.rpsgame.results.Result;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A player that is able to play any gesture related game.
 *
 * @author Frank van Heeswijk
 * @param <A> The type of action
 * @param <R> The type of result
 */
abstract public class GesturePlayer<A extends Action, R extends Result> implements Player<A, R> {
    /** List of actions that the player can execute. **/
    protected final List<A> actions;

    /** A Random instance. **/
    private final Random random = new Random();

    /**
     * Constructs the GesturePlayer.
     * 
     * @param actions   The list of actions that can be used
     */
    public GesturePlayer(final List<A> actions) {
        Objects.requireNonNull(actions);
        this.actions = actions;
    }

    /**
     * Returns a random action.
     * 
     * @return  A random action.
     */
    protected A getRandomAction() {
        return actions.get(random.nextInt(actions.size()));
    }
}
