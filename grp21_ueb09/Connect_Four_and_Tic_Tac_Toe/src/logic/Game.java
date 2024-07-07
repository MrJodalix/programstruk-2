package logic;

import java.util.Arrays;

/**
 * Abstract class for a game (implemented by GameTicTacToe and GameConnectFour).
 *
 * @author cei, Anton Schmitter, Joshua-Scott Schöttke Gruppe21
 */
public abstract class Game {

    /**
     * Name of the players in an array. Length must be 2.
     */
    private String[] players;
    /**
     * Index of the player. Must be either 0 or 1.
     */
    private int idxCurrPlayer;
    /**
     * Connection to the gui.
     */
    private GUIConnector gui;
    /**
     * The 2-dimensional field on which the player play.
     */
    Symbol[][] field;
    /**
     * The number of tokens in a line required to win.
     */
    private final int numWin;
    /**
     * The current position of the player
     */
    int[] currentPosition;


    /**
     * Enum for the symbols used by the players. The ordinal value of the
     * respective symbol of a player should correspond with the index of this
     * player in the player array. The additional value "EMPTY" (marking an
     * empty cell) thus has the highest ordinal value.
     */
    public enum Symbol {X, O, EMPTY}

    /**
     * Constructor for a game. Initializes the field.
     *
     * @param p1     name of the first player
     * @param p2     name of the second player
     * @param size   size of the game field
     * @param numWin the number of same-color symbols in one row/column/diagonal
     *               required to win
     * @param gui    connection to the gui
     */
    public Game(String p1, String p2, int size, int numWin, GUIConnector gui) {
        this.players = new String[]{p1, p2};
        this.idxCurrPlayer = 0;
        this.field = new Symbol[size][size];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                this.field[j][i] = Symbol.EMPTY;
            }
        }
        this.numWin = numWin;
        this.gui = gui;
    }

    /**
     * Testkonstruktor
     *
     * @param strings Die Spieler immer zwei
     * @param symbols die Symbole im Feld
     * @param fakeGUI Die Gui
     */
    Game(String[] strings, Symbol[][] symbols, GUIConnector fakeGUI) {
        this.players = strings;
        this.field = symbols;
        gui = fakeGUI;
        if (field.length <= 3) {
            this.numWin = field.length;
        } else {
            this.numWin = 4;
        }
    }

    /**
     * Handles the turn of a player. If the cell chosen by the player is not
     * empty, nothing happens (the player can chose another cell). If it was
     * empty, the symbol of the current player is placed and the update of the
     * gui is initiated. Then the current player is changed, so that it is the
     * turn of the next player. Finally, the method checks if through this turn
     * a player has won the game.
     *
     * @param coord coordinate in the field at which the player wants to place
     *              his/her symbol
     */
    public void playerTurn(int[] coord) {
        if (field[coord[0]][coord[1]] == Symbol.EMPTY) {
            currentPosition = determineCoordinates(coord);
            field[currentPosition[0]][currentPosition[1]] = Symbol.values()[idxCurrPlayer];
            gui.displaySymbol(currentPosition, Symbol.values()[idxCurrPlayer]);
            if (emptyCellsLeft()) {
                if (getWinnerCoords() == null) {
                    idxCurrPlayer++;
                    idxCurrPlayer = idxCurrPlayer % players.length;
                    gui.setCurrentPlayer(players[idxCurrPlayer], Symbol.values()[idxCurrPlayer]);
                    gui.displaySymbol(null, Symbol.values()[idxCurrPlayer]);
                } else {
                    handleEndOfGame();
                }
            } else {
                handleEndOfGame();
            }
        }
    }

    /**
     * Stringbuilder für Testzwecke
     *
     * @return string des Spielfeldes
     */
    String fieldToString() {
        StringBuilder stringField = new StringBuilder();
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                if (field[y][x] == Symbol.EMPTY) {
                    stringField.append(field[y][x]).append(" ");
                } else {
                    stringField.append("    ").append(field[y][x]).append(" ");
                }
            }
            stringField.append("\n");
        }
        return stringField.toString();
    }

    /**
     * behandelt die Ausgabe am Ende des Spiels
     */
    protected void handleEndOfGame() {
        if ((!emptyCellsLeft()) && getWinnerCoords() == null) {
            gui.onGameEnd("Remis", getWinnerCoords());
        } else {
            gui.onGameEnd("Winner\n is\n " + players[idxCurrPlayer], getWinnerCoords());
        }
    }

    /**
     * liefert true, wenn es noch leere Zellen gibt.
     *
     * @return false wenn nicht
     */
    protected boolean emptyCellsLeft() {
        boolean empty = false;
        for (int i = getSize() - 1; i >= 0 && !empty; i--) {
            for (int j = getSize() - 1; j >= 0 && !empty; j--) {
                empty = isCellEmpty(new int[]{j, i});
            }
        }
        return empty;
    }

    /**
     * liefert die Größe des Spielfeldes. Da es immer quadratisch ist, genügt die erste Dimension.
     *
     * @return die größe des Spielfelds
     */
    protected int getSize() {
        return field.length;
    }

    /**
     * liefert true, wenn die übergebene Zelle leer ist
     *
     * @param coord die zu Prüfende Zelle
     * @return false wenn die Zelle belegt ist
     */
    protected boolean isCellEmpty(int[] coord) {
        return field[coord[0]][coord[1]] == Symbol.EMPTY;
    }

    /**
     * liefert die Koordinaten der Zellen der gewinnenden Linie
     *
     * @return die Koordinaten der Zellen der gewinnenden Linie oder null, wenn es (noch) keinen Sieger gibt
     */
    protected int[][] getWinnerCoords() {
        int[][] winningLineHorizontal = new int[numWin][2];
        int[][] winningLineVertical = new int[numWin][2];
        int[][] winningLineDiagonal1 = new int[numWin][2];
        int[][] winningLineDiagonal2 = new int[numWin][2];

        winningLineHorizontal[0] = getCurrentPosition();
        winningLineVertical[0] = currentPosition;
        winningLineDiagonal1[0] = currentPosition;
        winningLineDiagonal2[0] = currentPosition;

        int[] copiedCurrentPosition = currentPosition.clone();
        winningLineHorizontal = getWinningLine(winningLineHorizontal, copiedCurrentPosition, 1, 0);

        copiedCurrentPosition = currentPosition.clone();
        winningLineVertical = getWinningLine(winningLineVertical, copiedCurrentPosition, 0, 1);

        copiedCurrentPosition = currentPosition.clone();
        winningLineDiagonal1 = getWinningLine(winningLineDiagonal1, copiedCurrentPosition, 1, -1);

        copiedCurrentPosition = currentPosition.clone();
        winningLineDiagonal2 = getWinningLine(winningLineDiagonal2, copiedCurrentPosition, 1, 1);

        if (winningLineHorizontal != null) {
            return winningLineHorizontal;
        } else if (winningLineVertical != null) {
            return winningLineVertical;
        } else if (winningLineDiagonal1 != null) {
            return winningLineDiagonal1;
        } else if (winningLineDiagonal2 != null) {
            return winningLineDiagonal2;
        } else {
            return null;
        }
        /* Version 2{
        int[][] winningLine = new int[numWin][2];
        winningLine[0] = getCurrentPosition();
        int[] copiedCurrentPosition = currentPosition.clone();
        winningLine = getWinningLine(winningLine, copiedCurrentPosition, 0, 1);
        if (winningLine != null) {
            return winningLine;
        }
        winningLine = new int[numWin][2];
        winningLine[0] = getCurrentPosition();
        copiedCurrentPosition = currentPosition.clone();
        winningLine = getWinningLine(winningLine, copiedCurrentPosition, 1, 0);
        if (winningLine != null) {
            return winningLine;
        }
        winningLine = new int[numWin][2];
        winningLine[0] = getCurrentPosition();
        copiedCurrentPosition = currentPosition.clone();
        winningLine = getWinningLine(winningLine, copiedCurrentPosition, 1, -1);
        if (winningLine != null) {
            return winningLine;
        }
        winningLine = new int[numWin][2];
        winningLine[0] = getCurrentPosition();
        copiedCurrentPosition = currentPosition.clone();
        winningLine = getWinningLine(winningLine, copiedCurrentPosition, 1, 1);
        if (winningLine != null) {
            return winningLine;
        }
        return null;
    }*/
    }

    /**
     * Hilfsfunktion zur Ablegung und Berechnung der Gewinnenden Koordinaten
     *
     * @param winningLine zweidimensionales Int-Array welches vorgefertigt mit der aktuellen Position übergeben wird
     * @param position    die Position von der wir ausgehen zum vergleichen
     * @param xOffset     die Verschiebung um die X-Achse
     * @param yOffset     die Verschiebung um die Y-Achse
     * @return die winningline wenn diese numWin Elemente enthält, null wenn nicht.
     */

    private int[][] getWinningLine(int[][] winningLine, int[] position, int xOffset, int yOffset) {
        boolean line = true;
        boolean umbruch = false;
        for (int counter = 0; counter < numWin - 1 && line; ) {
            // position[0] = x
            // position[1] = y
            //Veränderung der Position
            if ((Arrays.equals(winningLine[counter], position))) {
                position[0] = position[0] + xOffset;
                position[1] = position[1] + yOffset;
            }
            counter++;
            //Überprüfung
            if (position[0] < field.length && position[1] < field.length && position[0] >= 0 && position[1] >= 0) {
                if (field[position[0]][position[1]] == Symbol.values()[idxCurrPlayer]) {
                    winningLine[counter] = position.clone();
                } else if (!umbruch) {
                    position[0] = position[0] - (numWin * xOffset);
                    position[1] = position[1] - (numWin * yOffset);
                    umbruch = true;
                    counter--;
                } else {
                    line = false;
                }
            } else if (!umbruch) {
                position[0] = position[0] - (numWin * xOffset);
                position[1] = position[1] - (numWin * yOffset);
                umbruch = true;
                counter--;

            } else {
                line = false;
            }
        }
        if (Arrays.equals(winningLine[numWin - 1], position) && line) {
            return winningLine;
        }
        return null;
    }

    /**
     * Hilfsmethode zum erstellen einer aktuellen Position(nur für Tests)
     *
     * @return int[] die aktuelle Position vom Spielstein
     */
    private int[] getCurrentPosition() {
        if (currentPosition == null) {
            boolean actualPlayer = false;
            for (int y = 0; y < field.length && !actualPlayer; y++) {
                for (int x = 0; x < field.length && !actualPlayer; x++) {
                    if (field[x][y] == Symbol.X) {
                        idxCurrPlayer = 0;
                        actualPlayer = true;
                    }
                    if (field[x][y] == Symbol.O) {
                        idxCurrPlayer = 1;
                        actualPlayer = true;
                    }
                    currentPosition = new int[]{x, y};
                }
            }
        }
        return currentPosition;
    }

    /**
     * liefert den Namen des Spielsteinbesitzers von der gegebenen Position
     *
     * @param winningCoord Die position des Spielsteins
     * @return Den String des Gewinners
     */
    protected String getWinnerName(int[] winningCoord) {
        if (field[winningCoord[0]][winningCoord[1]] == Symbol.EMPTY) {
            return "";
        } else {
            return players[idxCurrPlayer];
        }
    }

    /**
     * liefert die Position des Klicks, wo das Symbol des aktuellen Spielers platziert werden muss.
     * Im Spiel ConnectFour wird das die Position der untersten freien Zelle in der angeklickten Spalte sein.
     *
     * @param clickCoord die angeklickte Koordinate
     * @return die Position wo der Spielstein gesetzt werden soll
     */
    abstract int[] determineCoordinates(int[] clickCoord);
}
