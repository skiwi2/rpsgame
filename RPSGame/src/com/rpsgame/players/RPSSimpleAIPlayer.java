
package com.rpsgame.players;

import com.rpsgame.actions.RPSGesture;
import com.rpsgame.view.RPSView;

/**
 * An AI player that can play RPS.
 *
 * @author Frank van Heeswijk
 */
public class RPSSimpleAIPlayer extends RPSPlayer {
    /**
     * Constructs the RPSSimpleAIPlayer.
     * 
     * @param rpsView   The RPS view
     */
    public RPSSimpleAIPlayer(final RPSView rpsView) {
        super(rpsView);
    }

    @Override
    public RPSGesture doAction() {
        return getRandomAction();
    }
}
