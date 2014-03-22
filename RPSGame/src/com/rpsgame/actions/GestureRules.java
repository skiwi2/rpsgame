
package com.rpsgame.actions;

import java.util.List;
import java.util.Map;

/**
 * Provides the rules of a specific gesture, meaning when one wins, ties or loses.
 *
 * @author Frank van Heeswijk
 * @param <T> The type of gesture
 */
public interface GestureRules<T extends Gesture> {
    /**
     * Returns a mapping from the gestures to which gestures they win from.
     * 
     * @return  A mapping from the gestures to which gestures they win from
     */
    public Map<T, List<T>> winMapping();

    /**
     * Returns a mapping from the gestures to which gestures they tie to.
     * 
     * @return  A mapping from the gestures to which gestures they tie to
     */
    public Map<T, List<T>> tieMapping();

    /**
     * Returns a mapping from the gestures to which gestures they lose from.
     * 
     * @return  A mapping from the gestures to which gestures they lose from
     */
    public Map<T, List<T>> loseMapping();
}
