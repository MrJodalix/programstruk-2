package logic;


import logic.Game.Symbol;
import static logic.Game.Symbol.X;
import static logic.Game.Symbol.O;
import static logic.Game.Symbol.EMPTY;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the logic of the Game Tic Tac Toe. While these test cases
 * cover a lot, they do not cover all possible scenarios. It is still possible
 * for the logic to malfunction, albeit in special cases. Thinking about own
 * test cases is recommended.
 *
 * @author cei
 */
public class PubTicTacToeTest {

    /**
     * If this test fails, all other tests can be ignored - they might just fail
     * because they require one of the two tested methods.
     */
    @Test
    public void testFieldToStringAndTestConstructor() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, EMPTY, EMPTY},
                    {O, EMPTY, EMPTY},
                    {X, EMPTY, EMPTY}
                }, new FakeGUI());
        //expecting the additional whitespace and linebreak after each symbol
        //or line makes the implementation easier. As this method is just for 
        //testing anyway, this is acceptable behaviour. 
        assertEquals("    X     O     X \n"
                   + "EMPTY EMPTY EMPTY \n"
                   + "EMPTY EMPTY EMPTY \n",
                game.fieldToString());
    }

    /**
     * If this test fails, all other tests can be ignored - they might just fail
     * because they require one of the two tested methods.
     */
    @Test
    public void testFieldToStringAndTestConstructorSize2() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, EMPTY},
                    {O, EMPTY}
                }, new FakeGUI());
        //expecting the additional whitespace and linebreak after each symbol
        //or line makes the implementation easier. As this method is just for 
        //testing anyway, this is acceptable behaviour. 
        assertEquals("    X     O \n"
                   + "EMPTY EMPTY \n", game.fieldToString());
    }

    @Test
    public void testOneTurnEachPlayerSize2() {
        GameTicTacToe game = new GameTicTacToe("Ernie", "Bert", 2, new FakeGUI());
        assertEquals("EMPTY EMPTY \n"
                   + "EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{0, 0});
        assertEquals("    X EMPTY \n"
                   + "EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{1, 1});
        assertEquals("    X EMPTY \n"
                   + "EMPTY     O \n", game.fieldToString());
    }

    @Test
    public void testFillFieldNoWinner() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, X},
                    {O, EMPTY, O}
                }, new FakeGUI());
        game.playerTurn(new int[]{2, 1});
        assertFalse(game.emptyCellsLeft());
        assertNull(game.getWinnerCoords());
    }

    @Test
    public void testFillFieldOneWinner() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, X},
                    {O, X, EMPTY}
                }, new FakeGUI());
        game.playerTurn(new int[]{2, 2});
        assertFalse(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testCellAlreadyOccupiedPlayerCanChooseAgain() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());
        assertEquals("    X     X EMPTY \n"
                   + "    O     O EMPTY \n"
                   + "EMPTY EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{0, 1});
        assertEquals("    X     X EMPTY \n"
                   + "    O     O EMPTY \n"
                   + "EMPTY EMPTY EMPTY \n", game.fieldToString());
        game.playerTurn(new int[]{2, 0});
        assertTrue(game.emptyCellsLeft());
        assertEquals("    X     X     X \n"
                   + "    O     O EMPTY \n"
                   + "EMPTY EMPTY EMPTY \n", game.fieldToString());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerFirstPlayerWTopRowSize2() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Trick", "Track"},
                new Symbol[][]{
                        {X, O},
                        {EMPTY, EMPTY}
                    }, new FakeGUI());
        game.playerTurn(new int[]{1, 1});
        assertEquals("Trick", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerFirstPlayerWTopRow() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());

        assertNull(game.getWinnerCoords());
        game.playerTurn(new int[]{2, 0});
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerFirstPlayerWBottomRow() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, X},
                    {X, O, X},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());

        assertNull(game.getWinnerCoords());
        game.playerTurn(new int[]{2, 2});
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerFirstPlayerWColumn() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, X, EMPTY},
                    {EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());

        assertNull(game.getWinnerCoords());
        game.playerTurn(new int[]{0, 2});
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerFirstPlayerWDiagonalLeftTop() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, EMPTY, EMPTY},
                    {EMPTY, X, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());

        assertNull(game.getWinnerCoords());
        game.playerTurn(new int[]{2, 2});
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerFirstPlayerWDiagonalRightTop() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {EMPTY, EMPTY, X},
                    {EMPTY, X, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());

        assertNull(game.getWinnerCoords());
        game.playerTurn(new int[]{2, 0});
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }

    @Test
    public void testWinnerSecondPlayerWRow() {
        GameTicTacToe game = new GameTicTacToe(new String[]{"Marcus", "Cordula"},
                new Symbol[][]{
                    {X, O, EMPTY},
                    {X, O, EMPTY},
                    {EMPTY, EMPTY, EMPTY}
                }, new FakeGUI());

        game.playerTurn(new int[]{2, 2});
        assertNull(game.getWinnerCoords());
        game.playerTurn(new int[]{2, 1});

        assertTrue(game.emptyCellsLeft());
        assertEquals("Cordula", game.getWinnerName(game.getWinnerCoords()[0]));
    }

}
