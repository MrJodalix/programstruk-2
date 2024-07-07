/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import static logic.Game.Symbol.EMPTY;
import static logic.Game.Symbol.O;
import static logic.Game.Symbol.X;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Published test for assignment 9.
 * @author cei
 */
public class PubConnectFourTest {
    
    @Test
    public void testFieldToStringAndTestConstructor() {
    Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"},
                new Game.Symbol[][]{
                    {    X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {    O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {    X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}, 
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}, 
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}, 
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}, 
                }, new FakeGUI());
        //expecting the additional whitespace and linebreak after each symbol
        //or line makes the implementation easier. As this method is just for 
        //testing anyway, this is acceptable behaviour.
        assertEquals("    X     O     X EMPTY EMPTY EMPTY \n"
                   + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                   + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                   + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                   + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                   + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n", 
                game.fieldToString());
    }
    
    @Test
    public void testWinnerDiagonalTopLeft() {
        Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"},
                new Game.Symbol[][]{
                    {EMPTY, EMPTY, EMPTY,     O,     X,     O},
                    {EMPTY, EMPTY,     O,     X,     O,     X},
                    {EMPTY,     O,     X,     X,     O,     X},
                    {    O,     O,     X,     X,     O,     X},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                }, new FakeGUI());
        
        assertTrue(game.emptyCellsLeft());
        assertEquals("Cordula", game.getWinnerName(game.getWinnerCoords()[0]));
    }
    
    @Test
    public void testWinnerDiagonalBottomLeftToTopRight() {
        Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"},
                new Game.Symbol[][]{
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     O},
                    {EMPTY, EMPTY, EMPTY, EMPTY,     O,     O},
                    {EMPTY, EMPTY, EMPTY,     O,     X,     X},
                    {EMPTY, EMPTY,     O,     X,     X,     X},
                    {EMPTY, EMPTY, EMPTY,     O,     O,     X},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                }, new FakeGUI());
        
        assertTrue(game.emptyCellsLeft());
        assertEquals("Cordula", game.getWinnerName(game.getWinnerCoords()[0]));
    }
    
    @Test
    public void testWinnerDiagonalBottomRight() {
        Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"},
                new Game.Symbol[][]{                  
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     X},
                    {EMPTY, EMPTY, EMPTY, EMPTY,     X,     O},
                    {EMPTY, EMPTY, EMPTY,     X,     O,     X},
                    {EMPTY, EMPTY,     X,     O,     O,     O},
                }, new FakeGUI());
        assertTrue(game.emptyCellsLeft());
        assertEquals("Marcus", game.getWinnerName(game.getWinnerCoords()[0]));
    }
    
    @Test
    public void testWinnerDiagonalTopRight() {
        Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"},
                new Game.Symbol[][]{
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY,     X,     O,     O,     X,     X},
                    {EMPTY, EMPTY,     X,     O,     O,     O},
                    {EMPTY, EMPTY, EMPTY,     X,     X,     O},
                    {EMPTY, EMPTY, EMPTY, EMPTY,     X,     O},
                }, new FakeGUI());
        
        int[][] winnerCoords = game.getWinnerCoords();
        assertNotNull(winnerCoords); 
        assertEquals("Marcus", game.getWinnerName(winnerCoords[0]));
    }
    
    @Test
    public void testWinnerDiagonalBottomLeft2() {
        Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"}, 
                new Game.Symbol[][]{
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     X},
                    {EMPTY, EMPTY, EMPTY,     X,     O,     O,     O},
                    {EMPTY, EMPTY, EMPTY, EMPTY,     X,     X,     O},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     X,     O},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     X},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                }, new FakeGUI()); 
        int[][] winnerCoords = game.getWinnerCoords(); 
        assertNotNull(winnerCoords); 
        assertEquals("Marcus", game.getWinnerName(winnerCoords[0]));
    }
    
    @Test
    public void testWinnerDiagonalBottomLeft() {
        Game game = new GameConnectFour(new String[]{"Marcus", "Cordula"},
                new Game.Symbol[][]{
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     X},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,     O},
                    {EMPTY, EMPTY, EMPTY,     X,     O,     X},
                    {EMPTY, EMPTY, EMPTY,     O,     X,     X},
                    {EMPTY, EMPTY,     O,     O,     O,     X},
                }, new FakeGUI());
        
        assertTrue(game.emptyCellsLeft());
        assertEquals("Cordula", game.getWinnerName(game.getWinnerCoords()[0]));
    }
    
    
    
}
