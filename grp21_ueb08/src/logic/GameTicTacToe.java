package logic;

/**
 * Stellt Spielelogik von TicTacToe dar. Hier werden z.B. WinningCondition und Spielzüge festgelegt
 *
 * @author cei, Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public class GameTicTacToe {
    /**
     * Name of the players in an array. Length must be 2.
     */
    private final String[] players;

    /**
     * Index of the player. Must be either 0 or 1.
     */
    private int idxCurrPlayer;
    /**
     * Connection to the gui.
     */
    private final GUIConnector gui;

    /**
     * Enum for the symbols used by the players. The ordinal value of the
     * respective symbol of a player should correspond with the index of this
     * player in the player array. The additional value "EMPTY" (marking an
     * empty cell) thus has the highest ordinal value. Visibility should be as
     * restricted as possible.
     */
    public enum Symbol {X, O, EMPTY}

    /**
     * The 2-dimensional field on which the player play.
     */
    Symbol[][] field;

    /**
     * Constructor for a game of tic tac toe. Initializes the field.
     *
     * @param p1   name of the first player
     * @param p2   name of the second player
     * @param size size of the game field
     * @param gui  connection to the gui
     */
    public GameTicTacToe(String p1, String p2, int size, GUIConnector gui) {
        this.players = new String[]{p1, p2};
        this.idxCurrPlayer = 0;
        this.field = new Symbol[size][size];
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                this.field[y][x] = Symbol.EMPTY;
            }
        }
        this.gui = gui;
    }

    /**
     * TestKonstruktor
     *
     * @param strings Die Spieler
     * @param symbols Die Symbole
     * @param fakeGUI FakeGui
     */
    GameTicTacToe(String[] strings, Symbol[][] symbols, GUIConnector fakeGUI) {
        this.players = strings;
        this.field = symbols;
        gui = fakeGUI;
    }

    /**
     * bestimmt, ob es noch leere Zellen auf dem Feld gibt.
     * Es dürfen nicht mehr Zellen überprüft werden als notwendig.
     *
     * @return true wenn es noch leere Felder gibt
     */
    public boolean emptyCellsLeft() {
        boolean empty = false;
        for (int x = 0; x < field.length && !empty; x++) {
            for (int y = 0; y < field[x].length && !empty; y++) {
                empty = field[y][x] == Symbol.EMPTY;
            }
        }
        return empty;
    }

    /**
     * bestimmt, ob es einen Gewinner gibt und initiiert eine entsprechende Ausgabe auf der Oberfläche.
     * Ist das Spielfeld voll, ohne dass es einen Gewinner gibt, meldet die Oberfläche ein Unentschieden.
     */
    protected void handleEndOfGame() {
        if ((!emptyCellsLeft()) && getWinnerName() == null) {
            gui.onGameEnd("Remis");
        } else {
            gui.onGameEnd("Winner\n is\n " + players[idxCurrPlayer]);
        }
    }

    /**
     * bestimmt, ob es einen Gewinner gibt und liefert dessen Namen.
     *
     * @return Gibt es (noch) keinen Sieger, wird null zurückgegeben.
     */
    public String getWinnerName() {
        boolean winner = false;
        for (int x = 0; x < field.length && !winner; x++) {
            int countHorizontal = 0, countVertical = 0, countDiagonal1 = 0, countDiagonal2 = 0;
            for (int y = 0; y < field[x].length && !winner; y++) {
                if (field[y][x] == Symbol.values()[idxCurrPlayer]) {//links nach rechts
                    countHorizontal++;
                }
                if (field[x][y] == Symbol.values()[idxCurrPlayer]) {//oben nach unten
                    countVertical++;
                }
                if (field[y][y] == Symbol.values()[idxCurrPlayer] && x == 0) {//Diagonal oben links unten rechts
                    countDiagonal1++;
                }
                //Diagonal unten links oben rechts
                if (field[field.length - 1 - y][y] == Symbol.values()[idxCurrPlayer] && x == 0) {
                    countDiagonal2++;
                }
                if (countHorizontal == field.length || countVertical == field.length ||
                        countDiagonal1 == field.length || countDiagonal2 == field.length) {
                    winner = true;
                }
            }
        }
        if (winner) {
            return players[idxCurrPlayer];
        }
        return null;
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
            field[coord[0]][coord[1]] = Symbol.values()[idxCurrPlayer];
            gui.displaySymbol(coord, Symbol.values()[idxCurrPlayer]);
            if (emptyCellsLeft()) {
                if (getWinnerName() == null) {
                    idxCurrPlayer++;
                    idxCurrPlayer = idxCurrPlayer % players.length;
                    gui.setCurrentPlayer(players[idxCurrPlayer], Symbol.values()[idxCurrPlayer]);
                } else {
                    handleEndOfGame();
                }
            } else {
                handleEndOfGame();
            }
        }
    }

    /**
     * Nur für Tests(nur zum Testen/Debuggen verwendet) liefert eine Stringdarstellung des Feldes. Spalten werden
     * durch Leerzeichen getrennt, Zeilen durch einen Zeilenvorschub (\n).
     *
     * @return Stringvariante des Feldes
     */
    public String fieldToString() {
        StringBuilder stringField = new StringBuilder();
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                stringField.append(field[y][x]).append(" ");
            }
            stringField.append("\n");
        }
        return stringField.toString();
    }
}
