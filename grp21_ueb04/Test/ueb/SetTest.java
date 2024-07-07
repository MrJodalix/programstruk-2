package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Eigene Tests für die Mengenoperationen
 *
 * @author Joshua-Scott Schoettke, Anton Schmitter Gruppe21
 */

public class SetTest {
    /**
     * Erzeugt ein Element mit übergebenem Wert, bei mehreren Werten werden
     * weitere Elemente angehängt.
     *
     * @param value Wert(e) des Elements/der Elemente
     * @return Element mit Wert, bei mehreren Werten mehrere aneinandergehängte
     * Elemente
     */
    private Element createElements(int... value) {
        if (value.length == 0) {
            return null;
        }
        // ein Element anlegen
        Element el = new Element(value[0]);
        Element firstEl = el; //erstes Element merken

        // weitere Elemente anlegen
        for (int i = 1; i < value.length; i++) {
            el.appendElement(value[i]);
            el = el.getNext();
        }
        return firstEl;
    }

    //---------------------------------------------------------------------------
    @Test
    public void testAddAll_EmptyList() {
        Set set1 = new Set();
        Element el = createElements(0, 1, 2);

        set1.addAll(el);
        assertArrayEquals(new int[]{0, 1, 2}, set1.toArray());
        assertNotSame(set1, el);
    }

    @Test
    public void testAddAll_OneElement() {
        Set set1 = new Set(1);
        Element el = createElements(0, 1, 2);

        set1.addAll(el);
        assertArrayEquals(new int[]{0, 1, 2}, set1.toArray());
        assertNotSame(set1, el);
    }

    @Test
    public void testCopy_OneElement() {
        Set set1 = new Set(1);
        Set set2 = set1.copy();
        assertArrayEquals(new int[]{1}, set2.toArray());
    }

    @Test
    public void testCopy_Empty() {
        Set set1 = new Set();
        Set set2 = set1.copy();
        assertArrayEquals(new int[]{}, set2.toArray());
    }
    //------------------------------------------------------------------------

    @Test
    public void testUnion_TwoElements() {
        Set set1 = new Set(1);
        Set set2 = new Set(2);
        Set set3 = set1.union(set2);
        assertArrayEquals(new int[]{1, 2}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testUnion_TwoElementsSame() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(1, 2);
        Set set3 = set1.union(set2);
        assertArrayEquals(new int[]{1, 2, 3}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testUnion_TwoSetsSame() {
        Set set1 = new Set(1, 2);
        Set set2 = new Set(1, 2);
        Set set3 = set1.union(set2);
        assertArrayEquals(new int[]{1, 2}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testUnion_OneElementFirst() {
        Set set1 = new Set(1);
        Set set2 = new Set();
        Set set3 = set1.union(set2);
        assertArrayEquals(new int[]{1}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testUnion_OneElementSecond() {
        Set set1 = new Set();
        Set set2 = new Set(1);
        Set set3 = set1.union(set2);
        assertArrayEquals(new int[]{1}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testUnion_otherNull() {
        Set set1 = new Set(1);
        Set set2 = null;
        Set set3 = set1.union(set2);
        assertArrayEquals(new int[]{1}, set3.toArray());
        assertNotSame(set1, set2);
    }
    //------------------------------------------------------------------------

    @Test
    public void testIntersection_ThreeElements() {
        Set set1 = new Set(1, 2);
        Set set2 = new Set(2, 3);
        Set set3 = set1.intersection(set2);
        assertArrayEquals(new int[]{2}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testIntersection_FourElements() {
        Set set1 = new Set(1, 3, 2);
        Set set2 = new Set(2, 3, 4);
        Set set3 = set1.intersection(set2);
        assertArrayEquals(new int[]{2, 3}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testIntersection_EmptyFirst() {
        Set set1 = new Set();
        Set set2 = new Set(2, 3, 4);
        Set set3 = set1.intersection(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testIntersection_EmptySecond() {
        Set set1 = new Set(1, 3, 2);
        Set set2 = new Set();
        Set set3 = set1.intersection(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testIntersection_NotInSet() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.intersection(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testIntersection_otherNull() {
        Set set1 = new Set(1);
        Set set2 = new Set();
        Set set3 = set1.intersection(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    //------------------------------------------------------------------------

    @Test
    public void testDiff_NotInSet() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.diff(set2);
        assertArrayEquals(new int[]{1, 2, 3}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testDiff_OneElementSame() {
        Set set1 = new Set(1, 2, 3, 4);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.diff(set2);
        assertArrayEquals(new int[]{1, 2, 3}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testDiff_EmptySetFirst() {
        Set set1 = new Set();
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.diff(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testDiff_EmptySetSecond() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set();
        Set set3 = set1.diff(set2);
        assertArrayEquals(new int[]{1, 2, 3}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testDiff_EqualSet() {
        Set set1 = new Set(4, 5, 6);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.diff(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testDiff_otherNull() {
        Set set1 = new Set(1);
        Set set2 = null;
        Set set3 = set1.diff(set2);
        assertArrayEquals(new int[]{1}, set3.toArray());
        assertNotSame(set1, set2);
    }
    //------------------------------------------------------------------------

    @Test
    public void testSymDiff_NotInSet() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.symmDiff(set2);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testSymDiff_OneElementSame() {
        Set set1 = new Set(1, 2, 3, 4);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.symmDiff(set2);
        assertArrayEquals(new int[]{1, 2, 3, 5, 6}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testSymDiff_EmptySetFirst() {
        Set set1 = new Set();
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.symmDiff(set2);
        assertArrayEquals(new int[]{4, 5, 6}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testSymDiff_EmptySetSecond() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set();
        Set set3 = set1.symmDiff(set2);
        assertArrayEquals(new int[]{1, 2, 3}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testSymDiff_EqualSet() {
        Set set1 = new Set(4, 5, 6);
        Set set2 = new Set(4, 5, 6);
        Set set3 = set1.symmDiff(set2);
        assertArrayEquals(new int[]{}, set3.toArray());
        assertNotSame(set1, set2);
    }

    @Test
    public void testSymDiff_otherNull() {
        Set set1 = new Set(1);
        Set set2 = null;
        Set set3 = set1.symmDiff(set2);
        assertArrayEquals(new int[]{1}, set3.toArray());
        assertNotSame(set1, set2);

    }

    //------------------------------------------------------------------------
    @Test
    public void testIsProperSubSet_NotInSet() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(4, 5, 6);
        assertFalse(set1.isProperSubSet(set2));
    }

    @Test
    public void testIsProperSubSet_OneElementSame() {
        Set set1 = new Set(1, 2, 3, 4);
        Set set2 = new Set(4, 5, 6);
        assertFalse(set1.isProperSubSet(set2));
    }

    @Test
    public void testIsProperSubSet_EmptySetFirst() {
        Set set1 = new Set();
        Set set2 = new Set(4, 5, 6);
        assertTrue(set1.isProperSubSet(set2));

    }

    @Test
    public void testIsProperSubSet_EmptySetSecond() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set();
        assertFalse(set1.isProperSubSet(set2));

    }

    @Test
    public void testIsProperSubSet_NotEqualSet() {
        Set set1 = new Set(4, 5, 6);
        Set set2 = new Set(4, 5, 6);
        assertFalse(set1.isProperSubSet(set2));

    }

    @Test
    public void testIsProperSubSet_EqualSet() {
        Set set1 = new Set(4, 5, 6);
        Set set2 = new Set(4, 5, 6, 7);
        assertTrue(set1.isProperSubSet(set2));

    }

    @Test
    public void testIsProperSubSet_otherNull() {
        Set set1 = new Set(4, 5, 6);
        Set set2 = null;
        assertFalse(set1.isProperSubSet(set2));

    }
}
