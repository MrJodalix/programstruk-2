package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubRoomTest {

    @Test
    public void constructByParam() {
        Room room = new Room(RoomUsage.STAIRWAY, new Position(0, 0), new Position(4, 4));
        assertEquals(RoomUsage.STAIRWAY, room.getRoomUsage());
        assertEquals(new Position(0,0), room.getPosTL());
        assertEquals(new Position(4,4), room.getPosBR());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructByParam_SamePositions() {
        Room room = new Room(RoomUsage.SLEEP, new Position(0, 0), new Position(0, 0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructByParam_PosBRIsMoreLeft() {
        Room room = new Room(RoomUsage.SLEEP, new Position(4, 4), new Position(3, 5));
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructByParam_PosBRIsMoreTop() {
        Room room = new Room(RoomUsage.SLEEP, new Position(4, 4), new Position(5, 3));
    }

    //------------------------------------------------------

    @Test
    public void constructByString() {
        Room room = new Room("SL 0,0 4,4");
        assertEquals(RoomUsage.SLEEP, room.getRoomUsage());
        assertEquals(new Position(0,0), room.getPosTL());
        assertEquals(new Position(4,4), room.getPosBR());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructByString_SamePositions() {
        Room room = new Room("SL 0,0 0,0");
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructByString_PosBRIsMoreLeft() {
        Room room = new Room("SL 4,4 3,5");
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructByString_PosBRIsMoreTop() {
        Room room = new Room("SL 4,4 5,3");
    }

    //------------------------------------------------------

    @Test
    public void getShortcut() {
        Room room = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertEquals("RO", room.getShortcut());
    }

    @Test
    public void calcBaseArea() {
        Room room = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertEquals(16, room.calcBaseArea());
    }

    @Test
    public void calcEffectiveArea() {
        Room room = new Room(RoomUsage.BATH, new Position(0, 0), new Position(3, 4));
        assertEquals(12, room.calcEffectiveArea());
    }

    @Test
    public void calcLivingArea() {
        Room room = new Room(RoomUsage.BATH, new Position(2, 3), new Position(4, 5));
        assertEquals(4, room.calcLivingArea());
    }

    //------------------------------------------------------

    @Test
    public void testToString() {
        Room room = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertEquals("RO BA 0,0 4,4", room.toString());
    }

    //------------------------------------------------------

    /*
     assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
     */
    @Test
    public void testEquals_SameRoom() {
        Room roomA = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertTrue(roomA.toString() + " sollte sich selbst gleich sein", roomA.equals(roomA));
    }

    @Test
    public void testEquals_SameRooms() {
        Room roomA = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        Room roomB = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_SameAreaSizes() {
        Room roomA = new Room(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        Room roomB = new Room(RoomUsage.BATH, new Position(2, 4), new Position(4, 12));
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_Null() {
        Room roomA = new Room(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        assertFalse("`null` ist kein Raum", roomA.equals(null));
    }

    @Test
    public void testEquals_NoRoom() {
        Room roomA = new Room(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        assertFalse("\"roomB\" ist kein Raum", roomA.equals("roomB"));
    }

    @Test
    public void testEquals_DifferentUsage() {
        Room roomA = new Room(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        Room roomB = new Room(RoomUsage.STORE, new Position(0, 0), new Position(4, 4));
        assertFalse(roomA.toString() + " hat andere Usage als " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_DifferentAreaSizes() {
        Room roomA = new Room(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        Room roomB = new Room(RoomUsage.SLEEP, new Position(1, 0), new Position(4, 4));
        assertFalse(roomA.toString() + " hat andere Area als " + roomB.toString(), roomA.equals(roomB));
    }
}