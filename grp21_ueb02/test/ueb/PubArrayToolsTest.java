package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author marcus, klk
 */
public class PubArrayToolsTest {

    @Test
    public void containsAt() {
        final int[] original = {0, 0, -1, -1, 1, 1};

        assertEquals(0, ArrayTools.containsAt(original, 0));
        assertEquals(2, ArrayTools.containsAt(original, -1));
        assertEquals(4, ArrayTools.containsAt(original, 1));
    }

    @Test
    public void containsAt_Emtpy() {
        final int[] original = {};

        assertEquals(-1, ArrayTools.containsAt(original, 2));
        assertEquals(-1, ArrayTools.containsAt(original, -2));
    }

    @Test
    public void containsAt_Missing() {
        final int[] original = {0, 0, -1, -1, 1, 1};

        assertEquals(-1, ArrayTools.containsAt(original, 2));
        assertEquals(-1, ArrayTools.containsAt(original, -2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void containsAt_Null() {
        int unused = ArrayTools.containsAt(null, 2);
    }


    @Test
    public void deleteElementAt_Valid() {
        final int[] original = {0, 1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 2, 3, 4}, ArrayTools.deleteElementAt(original, 0));
        assertArrayEquals(new int[]{0, 2, 3, 4}, ArrayTools.deleteElementAt(original, 1));
        assertArrayEquals(new int[]{0, 1, 3, 4}, ArrayTools.deleteElementAt(original, 2));
        assertArrayEquals(new int[]{0, 1, 2, 4}, ArrayTools.deleteElementAt(original, 3));
        assertArrayEquals(new int[]{0, 1, 2, 3}, ArrayTools.deleteElementAt(original, 4));
    }


    @Test
    public void insertElementAt_Valid() {
        final int[] original = {1, 2, 3, 4};
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, ArrayTools.insertElementAt(original, 0, 0));
        assertArrayEquals(new int[]{1, 0, 2, 3, 4}, ArrayTools.insertElementAt(original, 1, 0));
        assertArrayEquals(new int[]{1, 2, 0, 3, 4}, ArrayTools.insertElementAt(original, 2, 0));
        assertArrayEquals(new int[]{1, 2, 3, 0, 4}, ArrayTools.insertElementAt(original, 3, 0));
        assertArrayEquals(new int[]{1, 2, 3, 4, 0}, ArrayTools.insertElementAt(original, 4, 0));
    }

    @Test
    public void insertElementAt_EmptyArray_ValidIdx() {
        final int[] original = {};
        assertArrayEquals(new int[]{0}, ArrayTools.insertElementAt(original, 0, 0));
    }


    @Test
    public void getLengthOfLongestArray_ContainsNull() {
        assertEquals("{ null } => -1", -1, ArrayTools.getLengthOfLongestArray(new int[][]{null}));
        assertEquals("{ null, null } => -1", -1, ArrayTools.getLengthOfLongestArray(new int[][]{null, null}));
    }

    @Test
    public void getLengthOfLongestArray_ContainsEmptyArray() {
        assertEquals("{ {} } => 0", 0, ArrayTools.getLengthOfLongestArray(new int[][]{{}}));
        assertEquals("{ null, {}, null } => 0", 0, ArrayTools.getLengthOfLongestArray(new int[][]{null, {}, null}));
    }

    @Test
    public void getLengthOfLongestArray_filledArrays() {
        assertEquals("{ { }, { 1 } } => 1", 1, ArrayTools.getLengthOfLongestArray(new int[][]{{}, {1}}));
        assertEquals("{ { }, { 1 } } => 1", 1, ArrayTools.getLengthOfLongestArray(new int[][]{{1}, {}}));
        assertEquals("{ { 1, 1 }, { } } => 2", 2, ArrayTools.getLengthOfLongestArray(new int[][]{{1, 1}, {}}));
        assertEquals("{ { }, { 1, 1 } } => 2", 2, ArrayTools.getLengthOfLongestArray(new int[][]{{}, {1, 1}}));
    }

}
