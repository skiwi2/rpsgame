
package com.rpsgame.actions;

import java.util.List;

/**
 * An enum providing the RPS gestures.
 *
 * @author Frank van Heeswijk
 */
public enum RPSGesture implements Gesture, Action, GestureRPSRules {
    ROCK,
    PAPER,
    SCISSORS;

    @Override
    public List<RPSGesture> listWinsFrom() {
        return winMapping().get(this);
    }

    @Override
    public List<RPSGesture> listTiesTo() {
        return tieMapping().get(this);
    }

    @Override
    public List<RPSGesture> listLosesFrom() {
        return loseMapping().get(this);
    }
}
