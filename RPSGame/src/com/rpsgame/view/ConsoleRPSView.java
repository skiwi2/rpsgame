
package com.rpsgame.view;

import com.rpsgame.actions.RPSGesture;
import com.rpsgame.console.ConsoleReader;
import com.rpsgame.players.RPSHumanPlayer;
import com.rpsgame.players.RPSPlayer;
import com.rpsgame.players.RPSSimpleAIPlayer;
import com.rpsgame.results.RPSResult;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class providing the console implementation of the RPSView.
 *
 * @author Frank van Heeswijk
 */
public class ConsoleRPSView implements RPSView {
    @Override
    public void processPlayerAction(final RPSPlayer player, final RPSGesture gesture, final RPSResult result) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(gesture);
        Objects.requireNonNull(result);
        if (player instanceof RPSHumanPlayer) {
            System.out.println("I played " + gesture + " and the result was a " + result);
        }
        else if (player instanceof RPSSimpleAIPlayer) {
            System.out.println("The AI played " + gesture + " and the result was a " + result);
        }
    }

    @Override
    public void gestureNotRecognized(final List<RPSGesture> gestures) {
        System.out.println("Your gesture was nog recognized or ambigious, please choose from: " + 
                String.join(", ", gestures.stream().map(RPSGesture::toString).collect(Collectors.toList())));
    }

    @Override
    public void pleaseEnterGesture() {
        System.out.println("Please enter your gesture: ");
    }

    @Override
    public String readGestureInput() throws InterruptedException {
        return ConsoleReader.nextLine(this);
    }

    @Override
    public void enterGestureTimeout(final RPSPlayer player) {
        if (player instanceof RPSHumanPlayer) {
            System.out.println("You have not entered your gesture in time.");
        } else if (player instanceof RPSSimpleAIPlayer) {
            System.out.println("The AI has not entered his gesture in time.");
        }
    }
}
