package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubPositionTest {

    @Test
    public void testConstructByParams() {
        Position pos = new Position(1, 2);
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test
    public void testConstructByParams_negativeParams() {
        Position pos = new Position(-1, -2);
        assertEquals(-1, pos.getX());
        assertEquals(-2, pos.getY());
    }

    @Test
    public void testConstructByString() {
        Position pos = new Position("1,2");
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test
    public void testConstructByString_SpaceBehindComma() {
        Position pos = new Position("1, 2");
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructByString_Null() {
        Position pos = new Position(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructByString_NoComma() {
        Position pos = new Position("12");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructByString_TooManyValues() {
        Position pos = new Position("1,2,3");
    }

    //------------------------------------------------------
    /*
     assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
     */
    @Test
    public void testEquals_SamePosition() {
        Position posA = new Position(1,2);
        assertTrue(posA.equals(posA));
    }

    @Test
    public void testEquals_SamePositions() {
        Position posA = new Position(1,2);
        Position posB = new Position(1,2);
        assertTrue(posA.equals(posB));
    }

    @Test
    public void testEquals_DifferentPositions() {
        Position posA = new Position(1,2);
        assertFalse(posA.equals(new Position(1,3)));
        assertFalse(posA.equals(new Position(2,2)));
        assertFalse(posA.equals(new Position(2,1)));
    }

    @Test
    public void testEquals_Null() {
        Position posA = new Position(1,2);
        assertFalse(posA.equals(null));
    }

    @Test
    public void testEquals_String() {
        Position posA = new Position(1,2);
        assertFalse(posA.equals("Hallo"));
    }

    //------------------------------------------------------

    @Test
    public void testToString() {
        Position pos = new Position(1,2);
        assertEquals("1,2", pos.toString());
    }
}