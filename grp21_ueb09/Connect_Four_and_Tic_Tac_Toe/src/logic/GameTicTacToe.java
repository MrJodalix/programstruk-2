package logic;

/**
 * Die Klasse repräsentiert das Spiel Tic Tac Toe, bei dem 3 Spielsteine in eine Reihe gebracht werden müssen
 * um zu gewinnen.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke Gruppe21
 */

public class GameTicTacToe extends Game {

    //Größe eines TicTacToe-Spielfeldes als öffentliche Konstante
    public static final int SIZE = 3;

    /**
     * Constructor for a game of tic tac toe. Initializes the field.
     *
     * @param p1   name of the first player
     * @param p2   name of the second player
     * @param size size of the game field
     * @param gui  connection to the gui
     */
    public GameTicTacToe(String p1, String p2, int size, GUIConnector gui) {
        super(p1, p2, size, SIZE, gui);
    }

    /**
     * TestKonstruktor
     *
     * @param strings spieler
     * @param symbols Spielfeld
     * @param fakeGUI fakegui
     */
    public GameTicTacToe(String[] strings, Symbol[][] symbols, GUIConnector fakeGUI) {
        super(strings, symbols, fakeGUI);
    }


    /**
     * liefert die Position des Klicks, wo das Symbol des aktuellen Spielers platziert werden muss.
     *
     * @param clickCoord die geklickte Koordinate
     * @return im Spiel TicTacToe entspricht geklickte Koordinate der zu platzierenden Koordinate und gibt diese
     *          zurück.
     */
    @Override
    int[] determineCoordinates(int[] clickCoord) {
        return clickCoord;
    }

}
