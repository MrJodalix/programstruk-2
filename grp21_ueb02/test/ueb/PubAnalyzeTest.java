package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author klk
 */
public class PubAnalyzeTest {

    @Test
    public void getPrintWidthPerColumn() {
        assertEquals(9, Analyze.getPrintWidthPerColumn(0));
        assertEquals(1, Analyze.getPrintWidthPerColumn(1));
        assertEquals(1, Analyze.getPrintWidthPerColumn(2));
        assertEquals(1, Analyze.getPrintWidthPerColumn(3));
        assertEquals(2, Analyze.getPrintWidthPerColumn(4));
        assertEquals(1, Analyze.getPrintWidthPerColumn(5));
        assertEquals(3, Analyze.getPrintWidthPerColumn(6));
        assertEquals(1, Analyze.getPrintWidthPerColumn(7));
        assertEquals(1, Analyze.getPrintWidthPerColumn(8));
        assertEquals(1, Analyze.getPrintWidthPerColumn(9));
    }

    @Test
    public void calcDistanceBetween_SamePoint() {
        assertEquals(0, Analyze.calcDistanceBetween(new int[]{1,1}, new int[]{1,1}));
    }

    @Test
    public void calcDistanceBetween_Vertical() {
        assertEquals(5, Analyze.calcDistanceBetween(new int[]{1,1}, new int[]{1,6}));
        assertEquals(5, Analyze.calcDistanceBetween(new int[]{1,6}, new int[]{1,1}));
    }

    @Test
    public void calcDistanceBetween_Horizontal() {
        assertEquals(5, Analyze.calcDistanceBetween(new int[]{1,1}, new int[]{6,1}));
        assertEquals(5, Analyze.calcDistanceBetween(new int[]{6,1}, new int[]{1,1}));
    }

    @Test
    public void calcDistanceBetween_Diagonal() {
        assertEquals((int)Math.ceil(Math.sqrt(25+25)), Analyze.calcDistanceBetween(new int[]{1,1}, new int[]{6,6}));
        assertEquals((int)Math.ceil(Math.sqrt(25+25)), Analyze.calcDistanceBetween(new int[]{6,6}, new int[]{1,1}));
        assertEquals((int)Math.ceil(Math.sqrt(25+64)), Analyze.calcDistanceBetween(new int[]{1,1}, new int[]{6,9}));
        assertEquals((int)Math.ceil(Math.sqrt(64+25)), Analyze.calcDistanceBetween(new int[]{9,6}, new int[]{1,1}));
    }

    @Test
    public void isValidPosition() {
        assertTrue(Analyze.isValidPosition(new int[]{1,1}));
        assertTrue(Analyze.isValidPosition(new int[]{0,0}));

    }

    @Test
    public void isValidPosition_Not() {
        assertFalse(Analyze.isValidPosition(null));
        assertFalse(Analyze.isValidPosition(new int[]{}));
        assertFalse(Analyze.isValidPosition(new int[]{1}));
        assertFalse(Analyze.isValidPosition(new int[]{1,2,3}));

        assertFalse(Analyze.isValidPosition(new int[]{-1,-1}));
        assertFalse(Analyze.isValidPosition(new int[]{99,999}));
    }

}