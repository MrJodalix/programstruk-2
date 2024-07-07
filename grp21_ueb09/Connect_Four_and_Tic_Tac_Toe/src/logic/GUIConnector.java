package logic;

import logic.Game.Symbol;

/**
 * Interface used for the logic of the game connect four and tic tac toe to
 * communicate with the gui.
 *
 * @author cei
 */
public interface GUIConnector {

    /**
     * Displays a given symbol at a specified cell of the field.
     *
     * @param coord the coordinates at which in the field the given symbol
     * should be displayed
     * @param symbol the symbol to be displayed
     */
    public void displaySymbol(int[] coord, Symbol symbol);

    /**
     * Sets the name and symbol of the current player on the gui.
     *
     * @param name name of the current player
     * @param symbol symbol of the current player
     */
    public void setCurrentPlayer(String name, Symbol symbol);

    /**
     * Called when the game is won by a player. Needs to display the name of the
     * winner on the gui and has to ensure that the user cannot continue playing
     * (e.g. by disabling components). If there is no winner, but there are also
     * no empty fields left, no player has won (but in a way the computer). Then
     * this method can be used to display that "no one" is the winner on the
     * gui.
     *
     * @param winnerName name of the player than won, null if there is no winner
     * @param winnerCoords array of the coordinates that were involved in the
     * win (length 1st dimension equals to the number of same symbol cells
     * required for a win), lengths of the 2nd dimension should always be 2
     */
    public void onGameEnd(String winnerName, int[][] winnerCoords);

}
