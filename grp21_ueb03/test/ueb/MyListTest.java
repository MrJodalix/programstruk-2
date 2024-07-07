package ueb;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests, die neu erstellt wurden.
 * Ist hiervon einer nicht erfolgreich, können keine Punkte für die Aufgabe erzielt werden.
 *
 * @author Joshua-Scott Schoettke,
 */

public class MyListTest {

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

    /**
     * Methoden aus MyList	| zu testende Listen folgenden Inhalts	| Parameter
     * isEmpty()	    | {1, 2, 5}, {1}            , {}	    |
     * isSorted()	    | {1, 2, 5}, {1, 2, 2, 3}   , {2, 1, 3}	|
     * size()	        | {1, 2, 5}, {1}            , {}	    |
     * existsElement()	| {1, 2, 5}, {}	                        | jeder vorhandene und ein nicht vorhandener Wert
     * sum()	        | {1, 2, 5}, {1, 2, 2, 3}   , {}	    |
     * getValueAt()	    | {1, 2, 5}	                            | jeder vorhandene und mindestens ein falscher Index
     */
    //<editor-fold defaultstate="collapsed" desc="Geforderte-eigene-Tests">
    @Test
    public void testIsEmpty() {
        MyList list = new MyList();
        assertTrue(list.isEmpty());
        list = createList(1);
        assertFalse(list.isEmpty());
        list = createList(1, 2, 5);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testIsSorted() {
        MyList list = new MyList();
        assertTrue(list.isSorted());
        list = createList(1);
        assertTrue(list.isSorted());
        list = createList(1, 2, 5);
        assertTrue(list.isSorted());
        list = createList(1, 2, 2, 5);
        assertTrue(list.isSorted());
        list = createList(2, 1, 3);
        assertFalse(list.isSorted());
    }

    @Test
    public void testSize() {
        MyList list = new MyList();
        assertEquals(0, list.size());
        list = createList(1);
        assertEquals(1, list.size());
        list = createList(1, 2, 5);
        assertEquals(3, list.size());
        list = createList(1, 2, 2, 5);
        assertEquals(4, list.size());
    }

    @Test
    public void testIsExist() {
        MyList list = new MyList();
        assertFalse(list.existsElement(1));
        assertFalse(list.existsElement(0));
        list = createList(1, 2, 5);
        assertTrue(list.existsElement(1));
        assertTrue(list.existsElement(2));
        assertTrue(list.existsElement(5));
        assertFalse(list.existsElement(6));
    }

    @Test
    public void testSum() {
        MyList list = new MyList();
        assertEquals(0, list.sum());
        list = createList(1);
        assertEquals(1, list.sum());
        list = createList(1, 2, 5);
      //  System.out.println(Arrays.toString(list.toArray()));
        assertEquals(8, list.sum());
        list = createList(1, 2, 2, 3);
        assertEquals(8, list.sum());
        list = createList(1, 2, 5, 2, 3);
        assertEquals(13, list.sum());
    }

    @Test
    public void testGetValueAt() {
        MyList list = new MyList();
        assertEquals(Integer.MAX_VALUE, list.getValueAt(0));
        assertEquals(Integer.MAX_VALUE, list.getValueAt(-1));
        assertEquals(Integer.MAX_VALUE, list.getValueAt(1));
        list = createList(1, 2, 5);
        assertEquals(1, list.getValueAt(0));
        assertEquals(2, list.getValueAt(1));
        assertEquals(5, list.getValueAt(2));
        assertEquals(Integer.MAX_VALUE, list.getValueAt(3));
        assertEquals(Integer.MAX_VALUE, list.getValueAt(-1));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="InsertElementAt-Tests">
    @Test
    public void testInsertElementAt_EmptyList() {
        MyList list = new MyList();
        list.insertElementAt(2, 0);
        assertEquals(1, list.size());
        assertArrayEquals(new int[]{2}, list.toArray());
    }

    @Test
    public void testInsertElementAt_EmptyListIndexInvalid() {
        MyList list = new MyList();
        list.insertElementAt(2, 1);
        assertEquals(0, list.size());
        assertArrayEquals(new int[]{}, list.toArray());
    }

    @Test
    public void testInsertElementAt_IndexInvalid() {
        MyList list = createList(1, 2, 3, 4);
        list.insertElementAt(5, 5);
        assertEquals(4, list.size());
        assertArrayEquals(new int[]{1, 2, 3, 4}, list.toArray());
    }

    @Test
    public void testInsertElementAt_FirstElement() {
        MyList list = createList(1, 3);
        list.insertElementAt(0, 0);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{0, 1, 3}, list.toArray());
    }

    @Test
    public void testInsertElementAt_MiddelElement() {
        MyList list = createList(1, 3);
        list.insertElementAt(2, 1);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.toArray());
    }

    @Test
    public void testInsertElementAt_LastElement() {
        MyList list = createList(1, 3);
        list.insertElementAt(5, 2);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 3, 5}, list.toArray());
    }

    @Test
    public void testInsertElementAt_Between() {
        MyList list = createList(1, 2, 3);
        list.insertElementAt(4, 2);
        assertEquals(1, list.getValueAt(0));
        assertEquals(2, list.getValueAt(1));
        assertEquals(4, list.getValueAt(2));
        assertEquals(3, list.getValueAt(3));
        assertArrayEquals(new int[]{1, 2, 4, 3}, list.toArray());

    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PrependElement-Tests">
    @Test
    public void testPrependElement_EmptyList() {
        MyList list = new MyList();
        list.prependElement(2);
        assertEquals(1, list.size());
        assertArrayEquals(new int[]{2}, list.toArray());
    }

    @Test
    public void testPrependElement_OneElement() {
        MyList list = createList(1);
        list.prependElement(2);
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{2, 1}, list.toArray());
    }

    @Test
    public void testPrependElement_MoreElements() {
        MyList list = createList(1, 3, 5);
        list.prependElement(2);
        assertEquals(4, list.size());
        assertArrayEquals(new int[]{2, 1, 3, 5}, list.toArray());
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="InsertSortedIfUnique-Tests">
    @Test
    public void testInsertSortedIfUnique_EmptyList() {
        MyList list = new MyList();
        list.insertSortedIfUnique(2);
        assertEquals(2, list.getValueAt(0));
        assertTrue(list.isSorted());
        assertEquals(1, list.size());
        assertArrayEquals(new int[]{2}, list.toArray());
    }

    @Test
    public void testInsertSortedIfUnique_OneElement() {
        MyList list = createList(3);
        list.insertSortedIfUnique(2);
        assertEquals(2, list.getValueAt(0));
        assertEquals(3, list.getValueAt(1));
        assertTrue(list.isSorted());
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{2, 3}, list.toArray());
    }

    @Test
    public void testInsertSortedIfUnique_MoreElements() {
        MyList list = createList(1, 3, 5);
        list.insertSortedIfUnique(2);
        assertEquals(1, list.getValueAt(0));
        assertEquals(2, list.getValueAt(1));
        assertEquals(3, list.getValueAt(2));
        assertEquals(5, list.getValueAt(3));
        assertTrue(list.isSorted());
        assertEquals(4, list.size());
        assertArrayEquals(new int[]{1, 2, 3, 5}, list.toArray());
    }

    @Test
    public void testInsertSortedIfUnique_ExistElements() {
        MyList list = createList(1, 3, 5);
        list.insertSortedIfUnique(3);
        assertEquals(1, list.getValueAt(0));
        assertEquals(3, list.getValueAt(1));
        assertEquals(5, list.getValueAt(2));
        assertTrue(list.isSorted());
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 3, 5}, list.toArray());
    }

    @Test
    public void testInsertSortedIfUnique_between() {
        MyList list = createList(1, 3);
        list.insertSortedIfUnique(2);
        assertEquals(1, list.getValueAt(0));
        assertEquals(2, list.getValueAt(1));
        assertEquals(3, list.getValueAt(2));
        assertTrue(list.isSorted());
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.toArray());

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DeleteAll-Tests">
    @Test
    public void testDeleteAll_EmptyList() {
        MyList list = new MyList();
        list.deleteAll(2);
        assertEquals(0, list.size());
        assertArrayEquals(new int[]{}, list.toArray());
    }

    @Test
    public void testDeleteAll_OneElementFalse() {
        MyList list = createList(3);
        list.deleteAll(2);
        assertEquals(3, list.getValueAt(0));
        assertEquals(1, list.size());
        assertArrayEquals(new int[]{3}, list.toArray());
    }

    @Test
    public void testDeleteAll_OneElementRight() {
        MyList list = createList(3);
        list.deleteAll(3);
        assertEquals(0, list.size());
        assertArrayEquals(new int[]{}, list.toArray());
    }

    @Test
    public void testDeleteAll_deleteAll() {
        MyList list = createList(3, 3, 3, 3, 3);
        list.deleteAll(3);
        assertEquals(0, list.size());
        assertArrayEquals(new int[]{}, list.toArray());
    }

    @Test
    public void testDeleteAll_deleteAllButOne() {
        MyList list = createList(3, 3, 2, 3, 3);
        list.deleteAll(3);
        assertEquals(2, list.getValueAt(0));
        assertEquals(1, list.size());
        assertArrayEquals(new int[]{2}, list.toArray());
    }
    //</editor-fold>
}