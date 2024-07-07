package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubFunctionalSpaceTest {

    @Test
    public void constructByParam() {
        FunctionalSpace room = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertEquals(RoomUsage.STAIRWAY, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }

    //------------------------------------------------------

    @Test
    public void constructByString() {
        FunctionalSpace room = new FunctionalSpace("0,0 4,4");
        assertEquals(RoomUsage.STAIRWAY, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }

    //------------------------------------------------------

    @Test
    public void getShortcut() {
        FunctionalSpace room = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertEquals("FS", room.getShortcut());
    }

    @Test
    public void calcEffectiveArea() {
        FunctionalSpace room = new FunctionalSpace(new Position(0, 0), new Position(3, 4));
        assertEquals(0, room.calcEffectiveArea());
    }

    @Test
    public void calcLivingArea() {
        FunctionalSpace room = new FunctionalSpace(new Position(2, 3), new Position(4, 5));
        assertEquals(0, room.calcLivingArea());
    }

    //------------------------------------------------------

    @Test
    public void testToString() {
        FunctionalSpace room = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertEquals("FS SW 0,0 4,4", room.toString());
    }

    //------------------------------------------------------
    /*
     assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
     */
    @Test
    public void testEquals_SameRoom() {
        FunctionalSpace roomA = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertTrue(roomA.toString() + " sollte sich selbst gleich sein", roomA.equals(roomA));
    }

    @Test
    public void testEquals_SameRooms() {
        FunctionalSpace roomA = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        FunctionalSpace roomB = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_SameAreaSizes() {
        FunctionalSpace roomA = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        FunctionalSpace roomB = new FunctionalSpace(new Position(2, 4), new Position(4, 12));
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_Null() {
        FunctionalSpace roomA = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertFalse("`null` ist kein Raum", roomA.equals(null));
    }

    @Test
    public void testEquals_NoRoom() {
        FunctionalSpace roomA = new FunctionalSpace(new Position(0, 0), new Position(4, 4));
        assertFalse("\"roomB\" ist kein Raum", roomA.equals("roomB"));
    }
}