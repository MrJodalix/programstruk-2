package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import logic.GUIConnector;
import logic.Game;
import logic.Game.Symbol;

import javafx.scene.image.ImageView;
import logic.GameConnectFour;
import logic.GameTicTacToe;

/**
 * This class is responsible for changing the gui when the logic deems it
 * necessary. Created by the gui and then passed as a parameter into the logic.
 *
 * @author cei, Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public class JavaFXGUI implements GUIConnector {

    /**
     * Images used to represent the symbols of the player.
     */
    private final ImageView[][] imgsField;

    private final Label lblCurrentPlayer;

    private final Label lblstcCurrentPlayer;

    private final Label lblWinner;

    private final ImageView imgCurrentPlayer;

    private final GridPane grdPn;

    /**
     * The constructor. Gets passed all components of the gui that may change
     * due to actions in the logic.
     *
     * @param imgs                  array of images that will be displayed for the symbols of the
     *                              players. The length of the array equals to the number of players. First
     *                              image is for the first player etc.
     * @param lblstcCurrentPlayer   staticlabel "Currentplayer"
     * @param lblCurrentPlayer      label of current player
     * @param lblWinner             label of winner
     * @param currentPlayer         imageView of current player
     * @param gridPane              gridPane of the Game
     */
    public JavaFXGUI(ImageView[][] imgs, Label lblCurrentPlayer, Label lblstcCurrentPlayer, Label lblWinner,
                     ImageView currentPlayer, GridPane gridPane) {
        imgsField = imgs;
        Image img = new Image("gui/img/greysolid.png");
        for (ImageView[] images : imgsField) {
            for (ImageView image : images) {
                image.setImage(img);
            }
        }
        this.lblCurrentPlayer = lblCurrentPlayer;
        this.lblWinner = lblWinner;
        this.imgCurrentPlayer = currentPlayer;
        this.grdPn = gridPane;
        this.lblstcCurrentPlayer = lblstcCurrentPlayer;
    }

    /**
     * displayed the symbols of the players
     *
     * @param coord the coordinates at which in the field the given symbol
     * should be displayed
     * @param symbol the symbol to be displayed
     */
    @Override
    public void displaySymbol(int[] coord, Game.Symbol symbol) {
        Image firstPlayerCF = new Image("gui/img/red.png");
        Image secondPlayerCF = new Image("gui/img/yellow.png");
        Image firstPlayerTTT = new Image("gui/img/x.png");
        Image secondPlayerTTT = new Image("gui/img/o.png");
        if (imgsField.length == GameConnectFour.SIZE) {
            if (symbol == Symbol.X) {
                imgCurrentPlayer.setImage(firstPlayerCF);
            }
            if (symbol == Symbol.O) {
                imgCurrentPlayer.setImage(secondPlayerCF);
            }
        } else if (imgsField.length == GameTicTacToe.SIZE) {
            if (symbol == Symbol.X) {
                imgCurrentPlayer.setImage(firstPlayerTTT);
            }
            if (symbol == Symbol.O) {
                imgCurrentPlayer.setImage(secondPlayerTTT);
            }
        }
        if (coord != null) {
            imgsField[coord[0]][coord[1]].setImage(imgCurrentPlayer.getImage());
        }
    }

    /**
     * displayed the name of the current player
     *
     * @param name name of the current player
     * @param symbol symbol of the current player
     */
    @Override
    public void setCurrentPlayer(String name, Symbol symbol) {
        lblCurrentPlayer.setText(name + " (" + symbol.toString() + ") ");
    }

    /**
     * displayed the end of the game and rules the last steps of the game
     *
     * @param winnerName name of the player than won, null if there is no winner
     * @param winnerCoords array of the coordinates that were involved in the
     * win (length 1st dimension equals to the number of same symbol cells
     */
    @Override
    public void onGameEnd(String winnerName, int[][] winnerCoords) {
        if (winnerCoords != null) {
            for (int[] winnerCoord : winnerCoords) {
                ImageView winStar = new ImageView(new Image("gui/img/star.png"));
                grdPn.add(winStar, winnerCoord[0], winnerCoord[1]);
                winStar.fitWidthProperty().bind(grdPn.widthProperty().
                        divide(imgsField.length).subtract(grdPn.getHgap()));
                winStar.fitHeightProperty().bind(grdPn.heightProperty().
                        divide(imgsField.length).subtract(grdPn.getVgap()));
            }
        }
        grdPn.setDisable(true);
        lblCurrentPlayer.setDisable(true);
        lblstcCurrentPlayer.setDisable(true);
        lblWinner.setVisible(true);
        lblWinner.setText(winnerName);
    }
}
