
package com.rpsgame.view;

import com.rpsgame.actions.RPSGesture;
import com.rpsgame.players.RPSPlayer;
import com.rpsgame.results.RPSResult;
import java.util.List;

/**
 * Interface having all input/output related methods for a RPSGame.
 *
 * @author Frank van Heeswijk
 */
public interface RPSView {
    /**
     * Processes a player action.
     * 
     * @param player    The player on which the action occured
     * @param gesture   The gesture the player has used
     * @param result    The result of using that gesture in that specific round
     */
    public void processPlayerAction(final RPSPlayer player, final RPSGesture gesture, final RPSResult result);

    /**
     * Processes what happens when the gesture has not been recognized.
     * 
     * @param gestures  All available gestures
     */
    public void gestureNotRecognized(final List<RPSGesture> gestures);

    /**
     * Processes what happens when the player should provide his/her gesture.
     */
    public void pleaseEnterGesture();

    /**
     * Processes what happens when the player has a timeout.
     * 
     * @param player    The player that had received a timeout
     */
    public void enterGestureTimeout(final RPSPlayer player);

    /**
     * Processes what happens when the player needs to enter his gesture.
     * 
     * @return  The entered gesture in string format
     * @throws InterruptedException     If waiting for the gesture to be entered has been interrupted
     */
    public String readGestureInput() throws InterruptedException;
}
