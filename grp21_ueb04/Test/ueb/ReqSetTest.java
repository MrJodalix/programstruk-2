package ueb;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReqSetTest {
    /**
     * Legt hierin mindestens 5 Tests mit unterschiedlichen Mengen an, die die Methode Set.isEqual() testen. Einer davon
     * muss emptySet.isEqual(null) und ein anderer emptySet.isEqual(new Set()) prüfen, überlegt 3 weitere bedeutsame
     * Situationen. Diese Tests sind Bestandteil der Aufgabenlösung.
     */
    @Test
    public void testIsEqual_EqualNull() {
        Set set1 = new Set();
        assertFalse(set1.isEqual(null));
    }

    @Test
    public void testIsEqual_EqualEmpty() {
        Set set1 = new Set();
        Set set2 = new Set();
        assertTrue(set1.isEqual(set2));
    }

    @Test
    public void testIsEqual_EqualSets() {
        Set set1 = new Set(1, 3, 5);
        Set set2 = new Set(1, 3, 5);
        assertTrue(set1.isEqual(set2));
    }

    @Test
    public void testIsEqual_NotEqualSets() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(1, 2, 4);
        assertFalse(set1.isEqual(set2));
    }

    @Test
    public void testIsEqual_TooMuchFirst() {
        Set set1 = new Set(1, 2, 3, 4);
        Set set2 = new Set(1, 2, 3);
        assertFalse(set1.isEqual(set2));
    }
    @Test
    public void testIsEqual_TooMuchSec() {
        Set set1 = new Set(1, 2, 3);
        Set set2 = new Set(1, 2, 3, 4);
        assertFalse(set1.isEqual(set2));
    }
}
