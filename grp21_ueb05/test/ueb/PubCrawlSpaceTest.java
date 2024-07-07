package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubCrawlSpaceTest {

    @Test
    public void constructByParam() {
        CrawlSpace room = new CrawlSpace(RoomUsage.STAIRWAY, new Position(0, 0), new Position(4, 4));
        assertEquals(RoomUsage.STAIRWAY, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }


    //------------------------------------------------------

    @Test
    public void constructByString() {
        CrawlSpace room = new CrawlSpace("SL 0,0 4,4");
        assertEquals(RoomUsage.SLEEP, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }

    //------------------------------------------------------

    @Test
    public void getShortcut() {
        CrawlSpace room = new CrawlSpace(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertEquals("CS", room.getShortcut());
    }


    @Test
    public void calcLivingArea() {
        CrawlSpace room = new CrawlSpace(RoomUsage.BATH, new Position(2, 3), new Position(4, 5));
        assertEquals(0, room.calcLivingArea());
    }

    //------------------------------------------------------

    @Test
    public void testToString() {
        CrawlSpace room = new CrawlSpace(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertEquals("CS BA 0,0 4,4", room.toString());
    }

    //------------------------------------------------------

    @Test
    public void testEquals_SameRoom() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertTrue(roomA.toString() + " sollte sich selbst gleich sein", roomA.equals(roomA));
    }

    @Test
    public void testEquals_SameRooms() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        CrawlSpace roomB = new CrawlSpace(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_SameAreaSizes() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.BATH, new Position(0, 0), new Position(4, 4));
        CrawlSpace roomB = new CrawlSpace(RoomUsage.BATH, new Position(2, 4), new Position(4, 12));
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_Null() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        assertFalse("`null` ist kein Raum", roomA.equals(null));
    }

    @Test
    public void testEquals_NoRoom() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        assertFalse("\"roomB\" ist kein Raum", roomA.equals("roomB"));
    }

    @Test
    public void testEquals_DifferentUsage() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        CrawlSpace roomB = new CrawlSpace(RoomUsage.STORE, new Position(0, 0), new Position(4, 4));
        assertFalse(roomA.toString() + " hat andere Usage als " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_DifferentAreaSizes() {
        CrawlSpace roomA = new CrawlSpace(RoomUsage.SLEEP, new Position(0, 0), new Position(4, 4));
        CrawlSpace roomB = new CrawlSpace(RoomUsage.SLEEP, new Position(1, 0), new Position(4, 4));
        assertTrue("zwei CS sind immer gleich, da Wohnfläche immer 0", roomA.equals(roomB));
    }

    //------------------------------------------------------

}