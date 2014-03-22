
package com.rpsgame.actions;

import static com.rpsgame.actions.RPSGesture.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides an implementation for the GestureRules when considering RPS gestures.
 *
 * @author Frank van Heeswijk
 */
public interface GestureRPSRules extends GestureRules<RPSGesture> {
    @Override
    default public Map<RPSGesture, List<RPSGesture>> winMapping() {
        Map<RPSGesture, List<RPSGesture>> mapping = new HashMap<>();
        mapping.put(ROCK, Arrays.asList(SCISSORS));
        mapping.put(PAPER, Arrays.asList(ROCK));
        mapping.put(SCISSORS, Arrays.asList(PAPER));
        return mapping;
    }

    @Override
    default public Map<RPSGesture, List<RPSGesture>> tieMapping() {
        Map<RPSGesture, List<RPSGesture>> mapping = new HashMap<>();
        mapping.put(ROCK, Arrays.asList(ROCK));
        mapping.put(PAPER, Arrays.asList(PAPER));
        mapping.put(SCISSORS, Arrays.asList(SCISSORS));
        return mapping;
    }

    @Override
    default public Map<RPSGesture, List<RPSGesture>> loseMapping() {
        Map<RPSGesture, List<RPSGesture>> mapping = new HashMap<>();
        mapping.put(ROCK, Arrays.asList(PAPER));
        mapping.put(PAPER, Arrays.asList(SCISSORS));
        mapping.put(SCISSORS, Arrays.asList(ROCK));
        return mapping;
    }
}
