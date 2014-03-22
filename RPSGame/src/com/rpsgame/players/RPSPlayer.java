
package com.rpsgame.players;

import com.rpsgame.actions.RPSGesture;
import com.rpsgame.results.RPSResult;
import com.rpsgame.view.RPSView;
import java.util.Arrays;

/**
 * A player that is able to play RPS.
 *
 * @author Frank van Heeswijk
 */
abstract public class RPSPlayer extends GesturePlayer<RPSGesture, RPSResult> {
    /** The RPSView interface. */
    protected final RPSView rpsView;

    /**
     * Constructs the RPSPlayer with the RPSView interface as argument.
     * 
     * @param rpsView   The RPS view
     */
    public RPSPlayer(final RPSView rpsView) {
        super(Arrays.asList(RPSGesture.values()));
        this.rpsView = rpsView;
    }

    @Override
    public RPSGesture doActionBackup() {
        return getRandomAction();
    }

    @Override
    public void timeout() {
        rpsView.enterGestureTimeout(this);
    }
}
