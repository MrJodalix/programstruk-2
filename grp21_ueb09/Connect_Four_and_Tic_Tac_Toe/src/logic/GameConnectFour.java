package logic;

/**
 * Die Klasse repräsentiert das Spiel ConnectFour, bei dem mind. 4 Spielsteine in eine Reihe gebracht werden müssen
 * um zu gewinnen. Der eingesetzte Spielstein fällt in die unterste freie Reihe
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke Gruppe21
 */

public class GameConnectFour extends Game{

    //Größe eines ConnectFour-Spielfeldes als öffentliche Konstante
    public static final int SIZE = 7;
    //Die Anzahl der zu aneinander reihenden Steine
    private static final int numWin = 4;

    /**
     * Constructor for a game of connect four. Initializes the field.
     *
     * @param p1     name of the first player
     * @param p2     name of the second player
     * @param size   size of the game field
     * @param gui    connection to the gui
     */
    public GameConnectFour(String p1, String p2, int size, GUIConnector gui) {
        super(p1, p2, size,numWin, gui);

    }

    /**
     * Konstruktor für Tests
     * @param strings die Spieler
     * @param symbols die Symbole der Spieler
     * @param fakeGUI fakegui
     */
    public GameConnectFour(String[] strings, Symbol[][] symbols, GUIConnector fakeGUI) {
        super(strings,symbols,fakeGUI);
    }

    /**
     * liefert die Position des Klicks, wo das Symbol des aktuellen Spielers platziert werden muss.
     * Im Spiel ConnectFour wird das die Position der untersten freien Zelle in der angeklickten Spalte sein.
     *
     * @param clickCoord ursprünglich geklickte Zelle
     * @return nächst höhere freie Zellenposition der gewählten Spalte
     */
    @Override
    int[] determineCoordinates(int[] clickCoord) {
        boolean done = false;
        int[] Coord = new int[2];
        for (int i = getSize()-1; i >= 0 && !done; i--) {
            if (this.field[clickCoord[0]][i] == Symbol.EMPTY){
                Coord = new int[]{clickCoord[0], i};
                done = true;
            }
        }
        return Coord;
    }
}
