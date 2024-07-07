package logic;

import logic.Game.Symbol;

/**
 * Fake GUI used for testing the logic of the game Tic Tac Toe. All methods do
 * nothing. To ensure that the logic calls the correct methods for the gui, it
 * could be possibly to add package private boolean attributes, that tell if a
 * certain method has been called.
 *
 * @author cei
 */
public class FakeGUI implements GUIConnector {

    @Override
    public void displaySymbol(int[] coord, Symbol symbol) {
    }

    @Override
    public void setCurrentPlayer(String name, Symbol symbol) {
    }


    @Override
    public void onGameEnd(String winnerName, int[][] winnerCoords) {
    }
}
