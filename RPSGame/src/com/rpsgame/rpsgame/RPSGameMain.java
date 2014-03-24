
package com.rpsgame.rpsgame;

import com.rpsgame.console.ConsoleReader;
import com.rpsgame.games.RPSGame;
import com.rpsgame.players.RPSHumanPlayer;
import com.rpsgame.players.RPSSimpleAIPlayer;
import com.rpsgame.view.ConsoleRPSView;
import com.rpsgame.view.RPSView;

/**
 * The main class
 *
 * @author Frank van Heeswijk
 */
public class RPSGameMain {
    private void init() {
    	System.out.println("Simon was here again.");
        new Thread(new ConsoleReader()).start();
        RPSView consoleRPSView = new ConsoleRPSView();
        RPSGame rpsGame = new RPSGame(consoleRPSView);
        rpsGame.addPlayer(new RPSHumanPlayer(consoleRPSView));
        rpsGame.addPlayer(new RPSSimpleAIPlayer(consoleRPSView));
        rpsGame.playGame();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new RPSGameMain().init();
    }
}
