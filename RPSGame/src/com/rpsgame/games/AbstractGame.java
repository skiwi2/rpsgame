
package com.rpsgame.games;

import com.rpsgame.actions.Action;
import com.rpsgame.players.Player;
import com.rpsgame.results.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An abstract class that provides some basic implementations of the Game.
 *
 * @author Frank van Heeswijk
 * @param <A> The type of action in the game
 * @param <R> The type of result in the game
 * @param <P> The type of player in the game
 */
abstract public class AbstractGame<A extends Action, R extends Result, P extends Player<A, R>> implements Game<A, R, P> {
    /** A list holding all players. */
    protected final List<P> players = new ArrayList<>();

    @Override
    public void addPlayer(final P player) {
        Objects.requireNonNull(player);
        players.add(player);
    }
}
