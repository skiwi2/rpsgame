
package com.rpsgame.players;

import com.rpsgame.actions.Action;
import com.rpsgame.results.Result;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * An interface holding a player that can play any type of game.
 *
 * @author Frank van Heeswijk
 * @param <A> The type of the action
 * @param <R> The type of the result
 */
public interface Player<A extends Action, R extends Result> {
    /**
     * Processes when some player has done some action and obtained some result.
     * 
     * @param target    The player that performed the action
     * @param action    The action the player performed
     * @param result    The result of the action the player has performed
     */
    default public void onPostAction(final Player<A, R> target, final A action, final R result) { }

    /**
     * Does the action of the player.
     * 
     * @return The action the player has done
     */
    public A doAction();

    /**
     * Does the backup action of the player.
     * 
     * This will get called when the normal action has timed out.
     * 
     * @return The backup action the player has done
     */
    public A doActionBackup();

    /**
     * Performs the action of the player with a timeout on an Executor.
     * 
     * This will execute the normal action of the player waiting for a maximum specified time, if a timeout occurs then it will default to the backup action.
     * 
     * @param executor  The Executor on which the action needs to be executed
     * @param period    The time period it may take until the action gets a timeout
     * @param unit      The time unit of the period
     * @return  The action performed by the player
     */
    default public A doActionWithTimeout(final Executor executor, final int period, final TimeUnit unit) {
        FutureTask<A> futureTask = new FutureTask<>(this::doAction);
        executor.execute(futureTask);
        try {
            return futureTask.get(period, unit);
        } catch (InterruptedException ex) {
            futureTask.cancel(true);
            Thread.currentThread().interrupt();
            return doActionBackup();
        } catch (ExecutionException ex) {
            throw new IllegalStateException(ex);
        } catch (TimeoutException ex) {
            timeout();
            futureTask.cancel(true);
            return doActionBackup();
        }
    }

    /**
     * Does something when a timeout has occured.
     */
    default public void timeout() { }
}
