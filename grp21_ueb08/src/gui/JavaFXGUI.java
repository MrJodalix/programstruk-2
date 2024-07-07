package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.GUIConnector;
import logic.GameTicTacToe.Symbol;

/**
 * This class is responsible for changing the gui when the logic deems it
 * necessary. Created by the gui and then passed as a parameter into the logic.
 * <br>
 * Addtional private or protected methods may be added to this.
 *
 * @author cei, Anton Schmitter, Joshua-Scott Schoettke Gruppe 21
 */
public class JavaFXGUI implements GUIConnector {

    /**
     * The buttons of the game field stored in an array (position in the array =
     * position on the surface).
     */
    private final Button[][] btnsField;

    private final Label lblCurrentPlayer;

    private final Label lblWinner;

    /**
     * The constructor. Gets passed all components of the gui that may change
     * due to actions in the logic.
     *
     * @param btns the buttons of the game field (can change their text to the
     * symbols of the players)
     * <br>
     */
    public JavaFXGUI(Button[][] btns, Label lblCurrentPlayer, Label lblWinner) {
        this.btnsField = btns;
        for (Button[] buttons : btnsField) {
            for (Button button : buttons) {
                button.setText("");
            }
        }
        this.lblCurrentPlayer = lblCurrentPlayer;
        this.lblWinner = lblWinner;
    }

    @Override
    public void displaySymbol(int[] coord, Symbol symbol) {
        btnsField[coord[0]][coord[1]].setText(symbol.toString());

    }

    @Override
    public void setCurrentPlayer(String name, Symbol symbol) {
        lblCurrentPlayer.setText(name + " (" + symbol.toString() + ")");
    }

    @Override
    public void onGameEnd(String winnerName) {
        lblWinner.setText(winnerName);
    }

}
