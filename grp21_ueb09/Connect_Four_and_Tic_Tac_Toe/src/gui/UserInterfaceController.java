package gui;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import logic.GameConnectFour;
import logic.GameTicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Diese Klasse ist der Kommunikator zwischen dem UserInterface und der Logic.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke Gruppe21
 */

public class UserInterfaceController implements Initializable {
    /**
     * Spielfeld
     */
    @FXML
    private GridPane grdPn;
    @FXML
    private ImageView imgCurrPlayer;
    @FXML
    private VBox VBxLeft;
    /**
     * Buttons
     */
    @FXML
    private Button btnNG;
    @FXML
    private Button btnSG;
    @FXML
    private ToggleGroup RdBtnGameType;
    @FXML
    private RadioButton RdBtnTTT;
    @FXML
    private RadioButton RdBtnCF;
    /**
     * Textfelder
     */
    @FXML
    private TextField txtfldPlayer1;
    @FXML
    private TextField txtfldPlayer2;
    @FXML
    private Label lblstcCurrentPlayer;
    @FXML
    private Label lblCurrentPlayer;
    @FXML
    private Label lblWinner;
    /**
     * Attribut vom Game um die Veränderungen zu setzen
     */
    private GameTicTacToe gameT;

    private GameConnectFour gameC;
    /**
     * Attribut vom Spielfeld zur Initialisierung des Feldes
     */
    private ImageView[][] field;


    /**
     * Creates an array of imageviews corresponding to the gridPane. Each
     * imageView becomes a child of the gridPane and fills a cell. For proper
     * resizing it is binded to the gridPanes width and height. If the GridPane
     * has a hgap or a vgap it is necessary to also consider these when binding.
     * A default image could be added by passing another parameter into this method.
     *
     * @param grdPn the GridPane to which ImageViews should be added
     * @return an array of imageviews added to the gridPane
     */
    private ImageView[][] initImages(GridPane grdPn, Image img) {
        int colcount = grdPn.getColumnConstraints().size();
        int rowcount = grdPn.getRowConstraints().size();
        field = new ImageView[colcount][rowcount];
        for (int x = 0; x < colcount; x++) {
            for (int y = 0; y < rowcount; y++) {
                //creates an empty imageview
                field[x][y] = new ImageView();
                field[x][y].setImage(img);

                //add the imageview to the cell and assign the correct indicees for
                //this imageview, so you later can use GridPane.getColumnIndex(Node)
                grdPn.add(field[x][y], x, y);

                //the image shall resize when the cell resizes
                field[x][y].fitWidthProperty().bind(grdPn.widthProperty().
                        divide(colcount).subtract(grdPn.getHgap()));
                field[x][y].fitHeightProperty().bind(grdPn.heightProperty().
                        divide(rowcount).subtract(grdPn.getVgap()));
            }
        }
        return field;
    }

    /**
     * Initialisierung des Programms
     *
     * @param url            initialisierungs URL
     * @param resourceBundle initialisierung
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image("gui/img/greysolid.png");
        grdPn.setDisable(true);
        lblWinner.setVisible(false);
        btnNG.setDisable(true);
        imgCurrPlayer.setVisible(false);
        lblstcCurrentPlayer.setVisible(false);
        btnNG.setDisable(true);
        field = initImages(grdPn, img);
    }

    /**
     * reagiert auf die Button Benutzung
     *
     * @param actionEvent die Eingabe von außen
     */
    @FXML
    private void handleBtnGamePlay(ActionEvent actionEvent) {
        Image empty = new Image("gui/img/greysolid.png");
        if (actionEvent.getSource() == btnNG) {
            grdPn.setDisable(true);
            btnNG.setDisable(true);
            VBxLeft.setVisible(true);
            lblWinner.setVisible(false);
            imgCurrPlayer.setVisible(false);
            VBxLeft.setVisible(true);
            lblstcCurrentPlayer.setDisable(true);
            lblCurrentPlayer.setDisable(true);
        }
        if (actionEvent.getSource() == btnSG) {
            grdPn.getChildren().clear();
            field = initImages(grdPn, empty);
            if (RdBtnGameType.getSelectedToggle().equals(RdBtnTTT)) {
                startGameTicTacToe();
            } else {
                startGameConnectFour();
            }
        }
    }


    /**
     * reagiert auf das auswählen eines Radiobuttons und erstellt die größe des Spielfelds.
     *
     * @param actionEvent die Eingabe von außen
     */
    @FXML
    private void ChosenGame(ActionEvent actionEvent) {
        Image img = new Image("gui/img/greysolid.png");
        int size = 0;
        if (actionEvent.getSource() == RdBtnCF) {
            size = GameConnectFour.SIZE;
        } else if (actionEvent.getSource() == RdBtnTTT) {
            size = GameTicTacToe.SIZE;
        }
        grdPn.getChildren().clear();
        grdPn.getColumnConstraints().clear();
        grdPn.getRowConstraints().clear();
        for (int i = grdPn.getColumnCount(); i < size; i++) {
            RowConstraints newRConstrains = new RowConstraints();
            newRConstrains.setMinHeight(10.0);
            grdPn.getRowConstraints().add(newRConstrains);
            ColumnConstraints newCConstrains = new ColumnConstraints();
            newCConstrains.setMinWidth(10.0);
            grdPn.getColumnConstraints().add(newCConstrains);
        }
        field = initImages(grdPn, img);
    }

    /**
     * sets up a new ConnectFour Game
     */
    private void startGameConnectFour() {
        Image currentImage = new Image("gui/img/red.png");
        grdPn.setDisable(false);
        btnNG.setDisable(false);
        lblstcCurrentPlayer.setDisable(false);
        lblCurrentPlayer.setDisable(false);
        VBxLeft.setVisible(false);
        lblstcCurrentPlayer.setVisible(true);
        lblCurrentPlayer.setText(txtfldPlayer1.getText() + " (" + GameConnectFour.Symbol.X + ")");
        imgCurrPlayer.setImage(currentImage);
        imgCurrPlayer.setVisible(true);
        this.gameC = new GameConnectFour(txtfldPlayer1.getText(), txtfldPlayer2.getText(), GameConnectFour.SIZE,
                new JavaFXGUI(field, lblCurrentPlayer, lblstcCurrentPlayer, lblWinner, imgCurrPlayer, grdPn));
    }

    /**
     * sets up a new TicTacToe Game
     */
    private void startGameTicTacToe() {
        Image currentImage = new Image("gui/img/x.png");
        grdPn.setDisable(false);
        btnNG.setDisable(false);
        lblstcCurrentPlayer.setDisable(false);
        lblCurrentPlayer.setDisable(false);
        VBxLeft.setVisible(false);
        lblstcCurrentPlayer.setVisible(true);
        lblCurrentPlayer.setText(txtfldPlayer1.getText() + " (" + GameTicTacToe.Symbol.X + ")");
        imgCurrPlayer.setImage(currentImage);
        imgCurrPlayer.setVisible(true);
        this.gameT = new GameTicTacToe(txtfldPlayer1.getText(), txtfldPlayer2.getText(), GameTicTacToe.SIZE,
                new JavaFXGUI(field, lblCurrentPlayer, lblstcCurrentPlayer, lblWinner, imgCurrPlayer, grdPn));
    }


    /**
     * Reaction to the mouse click event on the GridPane.
     *
     * @param event the mouse click event that is fired when the gridpane is clicked
     */
    @FXML
    private void onMouseClickGridPane(MouseEvent event) {
        int x = -1;
        int y = -1;
        boolean leftClicked = event.getButton() == MouseButton.PRIMARY;
        boolean rightClicked = event.getButton() == MouseButton.SECONDARY;

        //determine the imageview of the grid that contains the coordinates of
        //the mouseclick to determine the board-coordinates
        for (Node node : grdPn.getChildren()) {
            if (node instanceof ImageView) {
                if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                    //to use following methods the columnIndex and rowIndex
                    //must have been set when adding the imageview to the grid
                    x = GridPane.getColumnIndex(node);
                    y = GridPane.getRowIndex(node);
                }
            }
        }
        int[] clickedPos = new int[]{x, y};
        assert (x >= 0 && y >= 0) : "dem Klick ist keine Koordinate zuzuordnen";

        if (leftClicked) {
            //react on leftclick
            if (RdBtnGameType.getSelectedToggle().equals(RdBtnCF)) {
                this.gameC.playerTurn(clickedPos);
            }
            if (RdBtnGameType.getSelectedToggle().equals(RdBtnTTT)) {
                this.gameT.playerTurn(clickedPos);
            }
        } else if (rightClicked) {
            //react on rightclick
        }
    }
}