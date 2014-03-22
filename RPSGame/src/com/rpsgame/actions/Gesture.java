
package com.rpsgame.actions;

import java.util.List;

/**
 * Interface that all types of gestures should extend.
 *
 * @author Frank van Heeswijk
 */
public interface Gesture {
    /**
     * Lists from which gestures this gesture wins.
     * 
     * @return  A list describing from which gestures this gesture wins
     */
    public List<? extends Gesture> listWinsFrom();

    /**
     * Lists to which gestures this gesture ties.
     * 
     * @return  A list describing to which gestures this gesture ties
     */
    public List<? extends Gesture> listTiesTo();

    /**
     * Lists from which gestures this gesture loses.
     * 
     * @return  A list describing from which gestures this gesture loses
     */
    public List<? extends Gesture> listLosesFrom();

    /**
     * Returns whether this gesture wins from the other gesture
     * 
     * @param <G>   The concrete type of the gesture
     * @param gesture   The other gesture
     * @return  Whether this gesture wins from the other gesture
     */
    default public <G extends Gesture> boolean winsFrom(final G gesture) {
        return listWinsFrom().contains(gesture);
    }

    /**
     * Returns whether this gesture ties to the other gesture
     * 
     * @param <G>   The concrete type of the gesture
     * @param gesture   The other gesture
     * @return  Whether this gesture ties to the other gesture
     */
    default public <G extends Gesture> boolean tiesTo(final G gesture) {
        return listTiesTo().contains(gesture);
    }

    /**
     * Returns whether this gesture loses from the other gesture
     * 
     * @param <G>   The concrete type of the gesture
     * @param gesture   The other gesture
     * @return  Whether this gesture loses from the other gesture
     */
    default public <G extends Gesture> boolean losesFrom(final G gesture) {
        return listLosesFrom().contains(gesture);
    }
}
