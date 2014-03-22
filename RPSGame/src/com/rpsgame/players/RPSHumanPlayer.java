
package com.rpsgame.players;

import com.rpsgame.actions.RPSGesture;
import com.rpsgame.view.RPSView;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A human player that can play RPS.
 *
 * @author Frank van Heeswijk
 */
public class RPSHumanPlayer extends RPSPlayer {  
    public RPSHumanPlayer(final RPSView rpsView) {
        super(rpsView);
    }

    @Override
    public RPSGesture doAction() {
        Optional<RPSGesture> gesture;
        try {
            gesture = askInput();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return doActionBackup();
        }
        while (!gesture.isPresent()) {
            rpsView.gestureNotRecognized(actions);
            try {
                gesture = askInput();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return doActionBackup();
            }
        }
        return gesture.get();
    }

    /**
     * Asks for user input.
     * 
     * @return  The user input, converted to a RPS gesture.
     * @throws InterruptedException     If the method gets interrupted.
     */
    private Optional<RPSGesture> askInput() throws InterruptedException {
        rpsView.pleaseEnterGesture();
        return parseRaw(rpsView.readGestureInput());
    }

    private Optional<RPSGesture> parseRaw(final String raw) {
        List<RPSGesture> gestures = actions.stream()
                .filter(action -> action.toString().toLowerCase().startsWith(raw.toLowerCase()))
                .collect(Collectors.toList());
        return ((gestures.size() == 1) ? Optional.of(gestures.get(0)) : Optional.empty());
    }
}
