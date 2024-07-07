package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.GameTicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

// TODO DONE: JavaDoc für die Klasse und alle nicht @Override Methoden, außerdem für nicht @FXML Attribute.

/**
 * Diese Klasse ist der Kommunikator zwischen dem UserInterface und der Logic.
 * @author Anton Schmitter, Joshua-Scott Schoettke, Gruppe 21
 */

public class UserInterfaceController implements Initializable {
    /**
     * Die Buttons auf dem Interface
     */
    @FXML
    private Button btn00;
    @FXML
    private Button btn10;
    @FXML
    private Button btn20;
    @FXML
    private Button btn01;
    @FXML
    private Button btn11;
    @FXML
    private Button btn21;
    @FXML
    private Button btn02;
    @FXML
    private Button btn12;
    @FXML
    private Button btn22;
    @FXML
    private Button btnSG;
    @FXML
    private Button btnNG;
    @FXML
    private GridPane GrdPnButtons;
    /**
     * Text- und Labelfelder für das Spiel
     * Für die Initialisierung eines neuen Spiel.
     */
    @FXML
    private VBox VBxLeft;
    @FXML
    private TextField txtfldPlayer1;
    @FXML
    private TextField txtfldPlayer2;
    @FXML
    private Label lblCurrentPlayer;
    @FXML
    private Label lblWinner;
    @FXML
    private Label lblstcCurrentPlayer;

    /**
     * Attribut vom Game um die Veränderungen zu setzen
     */
    private GameTicTacToe game;
    /**
     * Attribut vom Spielfeld zur Initialisierung des Feldes
     */
    private Button[][] field;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GrdPnButtons.setDisable(true);
        lblWinner.setVisible(false);
        btnNG.setDisable(true);
        lblstcCurrentPlayer.setVisible(false);
        field = new Button[][]{
                {btn00, btn10, btn20},
                {btn01, btn11, btn21},
                {btn02, btn12, btn22}};
    }

    /**
     * Reagiert auf die Button-Action und setzt die logic um.
     * @param actionEvent die Aktivierung eines Buttons
     */
    @FXML
    private void handleBtnGamePlay(ActionEvent actionEvent) {
        if ((actionEvent.getSource()) == btnNG) { //New Game
            btnNG.setDisable(true);
            VBxLeft.setVisible(true);
            lblWinner.setVisible(false);
        } else if ((actionEvent.getSource()) == btnSG) { //Start Game
            this.game = new GameTicTacToe(txtfldPlayer1.getText(), txtfldPlayer2.getText(), field.length, new JavaFXGUI(field,
                    lblCurrentPlayer, lblWinner));
            GrdPnButtons.setDisable(false);
            btnNG.setDisable(false);
            lblstcCurrentPlayer.setDisable(false);
            lblCurrentPlayer.setDisable(false);
            VBxLeft.setVisible(false);
            lblstcCurrentPlayer.setVisible(true);
            lblCurrentPlayer.setText(txtfldPlayer1.getText() + " (" + GameTicTacToe.Symbol.X + ")");
        } else {
            //Im Spielverlauf
            this.game.playerTurn(new int[]{GridPane.getRowIndex((Button) actionEvent.getSource()), GridPane.getColumnIndex((Button) actionEvent.getSource())});
            if (this.game.getWinnerName() != null || (!(this.game.emptyCellsLeft()))) {
                GrdPnButtons.setDisable(true);
                lblCurrentPlayer.setDisable(true);
                lblstcCurrentPlayer.setDisable(true);
                lblWinner.setVisible(true);
                lblWinner.setText(lblWinner.getText());

            }
        }
    }
}
