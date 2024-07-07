package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests published with the assignment 4.
 *
 * @author cei
 */
public class PubSetTest {

    //The varargs-constructor, addElement and toArray have to work properly

    /**
     * Add has to work
     */
    @Test
    public void test0AddElement() {
        Set set = new Set();
        set.addElement(5);
        set.addElement(2);
        set.addElement(4);
        assertArrayEquals(new int[]{2, 4, 5}, set.toArray());
    }

    /**
     * Same value may not be added multiple times.
     */
    @Test
    public void test0AddElement_SameValues() {
        Set set = new Set();
        set.addElement(5);
        set.addElement(2);
        set.addElement(2);
        set.addElement(4);
        set.addElement(4);
        assertArrayEquals(new int[]{2, 4, 5}, set.toArray());
    }

    @Test
    public void test0ToArray() {
        Set set = new Set(1, 2, 3);
        assertArrayEquals(new int[]{1, 2, 3}, set.toArray());

        set = new Set();
        assertArrayEquals("empty array", new int[]{}, set.toArray());
    }

    @Test
    public void test0Constructor_VarArgsSingleValue() {
        Set s = new Set(1);

        assertFalse(s.isEmpty());
        assertArrayEquals(new int[]{1}, s.toArray());
    }

    @Test
    public void test0Constructor_VarArgsAscending() {
        Set s = new Set(1, 2, 3);

        assertFalse(s.isEmpty());
        assertArrayEquals(new int[]{1, 2, 3}, s.toArray());
    }

    @Test
    public void testIsEmpty() {
        Set set = new Set();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testIsEqual_EqualSets() {
        Set set1 = new Set(1);
        Set set2 = new Set(1);
        assertTrue(set1.isEqual(set2));
    }

    @Test
    public void testDelete_FirstElement() {
        Set set = new Set(1, 2);
        set.deleteElement(1);
        assertArrayEquals(new int[]{2}, set.toArray());
    }

    @Test
    public void testToString() {
        Set set = new Set(1);
        assertEquals("{1}", set.toString());
    }
}
