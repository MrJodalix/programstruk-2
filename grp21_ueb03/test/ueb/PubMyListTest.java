package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests, die mit der Aufgabenstellung herausgegeben wurden.
 * Ist hiervon einer nicht erfolgreich, können keine Punkte für die Aufgabe erzielt werden.
 *
 * @author klk
 */
public class PubMyListTest {

    /**
     * Erzeugt eine Liste mit den übergebenen Werten.
     *
     * @param value Werte, die in die Liste eingefügt werden sollen.
     * @return Liste mit den Werten.
     */
    private MyList createList(int... value) {
        MyList list = new MyList();
        for (int i : value) {
            list.appendElement(i);
        }
        return list;
    }

    //-----------------------------------------------

    @Test
    public void testIsEmpty() {
        MyList list = new MyList();
        assertTrue(list.isEmpty());
        list = createList(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void test0AppendElement() {
        MyList list = new MyList();
        assertEquals(0, list.size());
        list.appendElement(1);
        assertEquals(1, list.size());
    }

    //-----------------------------------------------

    @Test
    public void testGetValues() {
        MyList list = createList(0, 1, 2);
        assertArrayEquals(new int[]{0, 1, 2}, list.toArray());
    }

    @Test
    public void testGetValues_NotChangesList() {
        MyList list = createList(0, 1, 2);
        assertArrayEquals(new int[]{0, 1, 2}, list.toArray());
        assertArrayEquals(new int[]{0, 1, 2}, list.toArray());
    }

    @Test
    public void testGetValues_EmptyList() {
        MyList list = createList();
        assertArrayEquals(new int[]{}, list.toArray());
    }

    //-----------------------------------------------

    @Test
    public void testInsertElement_InEmptyList() {
        MyList list = createList();
        list.insertElement(0);
        assertEquals(1, list.size());
    }

    @Test
    public void testInsertElement_AtFront() {
        MyList list = createList(1, 2, 3);
        list.insertElement(0);
        assertEquals(4, list.size());
        assertArrayEquals(new int[]{0, 1, 2, 3}, list.toArray());
    }

    @Test
    public void testInsertElement_InMiddle() {
        MyList list = createList(0, 2, 3);
        list.insertElement(1);
        assertEquals(4, list.size());
        assertArrayEquals(new int[]{0, 1, 2, 3}, list.toArray());
    }

    @Test
    public void testInsertElement_AtEnd() {
        MyList list = createList(0, 1, 2);
        list.insertElement(3);
        assertEquals(4, list.size());
        assertArrayEquals(new int[]{0, 1, 2, 3}, list.toArray());
    }

    //-----------------------------------------------

    @Test
    public void testDeleteElement_AtFront() {
        MyList list = createList(0, 1, 2);
        list.deleteElement(0);
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{1, 2}, list.toArray());
    }

    @Test
    public void testDeleteElement_InMiddle() {
        MyList list = createList(0, 1, 2);
        list.deleteElement(1);
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{0, 2}, list.toArray());
    }

    @Test
    public void testDeleteElement_AtEnd() {
        MyList list = createList(0, 1, 2);
        list.deleteElement(2);
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{0, 1}, list.toArray());
    }

    @Test
    public void testDeleteElement_NotExisting() {
        MyList list = createList(0, 1, 2);
        list.deleteElement(3);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{0, 1, 2}, list.toArray());
    }

    @Test
    public void testDeleteElement_EmptyList() {
        MyList list = createList();
        list.deleteElement(3);
        assertEquals(0, list.size());
        assertArrayEquals(new int[]{}, list.toArray());
    }

}
