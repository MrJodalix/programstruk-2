package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubRoofRoomTest {

    @Test
    public void constructByParam() {
        RoofRoom room = new RoofRoom(RoomUsage.STAIRWAY, new Position(0, 0), new Position(4, 4),
                Side.BOTTOM, 1);
        assertEquals(RoomUsage.STAIRWAY, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }
    //------------------------------------------------------

    @Test
    public void constructByString() {
        RoofRoom room = new RoofRoom("SL 0,0 4,4 BM 1");
        assertEquals(RoomUsage.SLEEP, room.getRoomUsage());
        assertEquals(new Position(0, 0), room.getPosTL());
        assertEquals(new Position(4, 4), room.getPosBR());
    }

    //------------------------------------------------------

    @Test
    public void getShortcut() {
        RoofRoom room = new RoofRoom("BA 0,0 4,4 BM 1");
        assertEquals("RR", room.getShortcut());
    }

    @Test
    public void calcLivingAreaLeft() {
        RoofRoom room = new RoofRoom("BA 20,50 65,70 LT 15");
        assertEquals(450, room.calcLivingArea());
    }
    @Test
    public void calcLivingAreaWidth0() {
        RoofRoom room = new RoofRoom("BA 20,50 65,70 BM 45");
        assertEquals(0, room.calcLivingArea());
    }
    @Test
    public void calcLivingAreaLength0() {
        RoofRoom room = new RoofRoom("BA 20,50 65,70 RT 45");
        assertEquals(0, room.calcLivingArea());
    }
    @Test
    public void calcLivingAreaHalfHalfBottom() {
        RoofRoom room = new RoofRoom("BA 0,0 50,70 TP 40");
        assertEquals(750, room.calcLivingArea());
    }
    @Test
    public void calcLivingAreaHalfHalfLeft() {
        RoofRoom room = new RoofRoom("BA 0,0 50,70 LT 40");
        assertEquals(350, room.calcLivingArea());
    }
    @Test
    public void calcLivingAreaHalfHalfBottom2() {
        RoofRoom room = new RoofRoom("BA 20,30 70,100 TP 40");
        assertEquals(750, room.calcLivingArea());
    }
    @Test
    public void calcLivingAreaHalfHalfLeft2() {
        RoofRoom room = new RoofRoom("BA 20,30 70,100 LT 40");
        assertEquals(350, room.calcLivingArea());
    }

    //------------------------------------------------------

    @Test
    public void testToString() {
        RoofRoom room = new RoofRoom("BA 0,0 4,4 BM 1");
        assertEquals("RR BA 0,0 4,4 BM 1", room.toString());
    }

    //------------------------------------------------------
    /*
     assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
     */
    @Test
    public void testEquals_SameRoom() {
        RoofRoom roomA = new RoofRoom("BA 0,0 4,4 BM 1");
        assertTrue(roomA.toString() + " sollte sich selbst gleich sein", roomA.equals(roomA));
    }

    @Test
    public void testEquals_SameRooms() {
        RoofRoom roomA = new RoofRoom("BA 0,0 4,4 BM 1");
        RoofRoom roomB = new RoofRoom("BA 0,0 4,4 BM 1");
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_SameEffectiveAreaSizes_SquareToRect() {
        RoofRoom roomA = new RoofRoom("BA 20,30 40,50 LT 15");
        RoofRoom roomB = new RoofRoom("BA 20,40 30,80 RT 15");
        assertTrue(roomA.toString() + " sollte gleich sein mit " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_Null() {
        RoofRoom roomA = new RoofRoom("BA 20,30 40,50 LT 15");
        assertFalse("`null` ist kein Raum", roomA.equals(null));
    }

    @Test
    public void testEquals_NoRoom() {
        RoofRoom roomA = new RoofRoom("BA 20,30 40,50 LT 15");
        assertFalse("\"roomB\" ist kein Raum", roomA.equals("roomB"));
    }

    @Test
    public void testEquals_DifferentUsage() {
        RoofRoom roomA = new RoofRoom("BA 20,30 40,50 LT 15");
        RoofRoom roomB = new RoofRoom("SL 20,30 40,50 LT 15");
        assertFalse(roomA.toString() + " hat andere Usage als " + roomB.toString(), roomA.equals(roomB));
    }

    @Test
    public void testEquals_DifferentAreaSizes() {
        RoofRoom roomA = new RoofRoom("BA 20,30 40,50 LT 15");
        RoofRoom roomB = new RoofRoom("BA 10,30 40,50 LT 15");
        assertFalse(roomA.toString() + " hat andere Area als " + roomB.toString(), roomA.equals(roomB));
    }

}