package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubFlatTest {

    @Test
    public void constructEmptyFlat() {
        Flat flat = new Flat();
        assertEquals(0, flat.getCountOfRooms());
    }

    @Test
    public void constructFlatWithVarArgs() {
        Flat flat = new Flat(new Room("BA 0,0 2,2"),
                            new Room("CO 3,0 6,6"),
                            new Room("SL 0,3 2,6")) ;
        assertEquals(3, flat.getCountOfRooms());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructFlatWithVarArgs_Null() {
        Flat flat = new Flat(new Room("BA 0,0 2,2"),
                null,
                new Room("SL 0,3 2,6")) ;
    }

    @Test
    public void constructFlatWithString() {
        Flat flat = new Flat("RO BA 0,0 2,2\n" +
                                    "RO CO 3,0 6,6\n" +
                                    "RO SL 0,3 2,6") ;
        assertEquals(3, flat.getCountOfRooms());
    }

    //--------------------------------------------------------

    @Test
    public void add_ToEmpty() {
        Flat flat = new Flat();
        flat.add(new Room("LI 0,0 6,6"));
        assertEquals(1, flat.getCountOfRooms());
    }
    @Test
    public void add_ToOneRoom() {
        Flat flat = new Flat(new Room("LI 0,0 6,6"));
        flat.add(new Room("LI 0,0 6,6"));
        assertEquals(2, flat.getCountOfRooms());
    }

    //--------------------------------------------------------

    @Test
    public void calcBaseArea() {
        Flat flat = new Flat("RO BA 0,0 2,2\n" +
                "RO CO 3,0 6,6\n" +
                "RO SL 0,3 2,6") ;
        assertEquals(28, flat.calcBaseArea());
    }

    @Test
    public void calcEffectiveArea() {
        Flat flat = new Flat("RO BA 0,0 2,2\n" +
                "RO CO 3,0 6,6\n" +
                "RO SL 0,3 2,6") ;
        assertEquals(28, flat.calcEffectiveArea());
    }

    @Test
    public void calcLivingArea() {
        Flat flat = new Flat("RO BA 0,0 2,2\n" +
                "RO CO 3,0 6,6\n" +
                "RO SL 0,3 2,6") ;
        assertEquals(28, flat.calcLivingArea());
    }

    //--------------------------------------------------------

    @Test
    public void testEquals() {
        Flat flatA = new Flat("FS 2,2 4,4\n" +
                "CS SW 2,2 4,4\n" +
                "RR BA 20,50 65,70 RT 15");
        Flat flatB = new Flat("CS SW 2,2 4,4\n" +
                "FS 2,2 4,4\n" +
                "RO BA 20,50 35,80");
        assertEquals(flatA.getCountOfRooms(), flatB.getCountOfRooms());
        assertEquals(new FunctionalSpace("2,2 4,4"),       new CrawlSpace("SW 2,2 4,4"));
        assertEquals(new CrawlSpace("SW 2,2 4,4"),         new FunctionalSpace("2,2 4,4"));
        assertEquals(new RoofRoom("BA 20,50 65,70 RT 15"), new Room("BA 20,50 35,80"));
        //assertTrue() wird im Folgenden genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
        assertTrue(flatA.equals(flatB));    // assertTrue() wird hier genutzt, um deutlich zu machen, dass die Methode `equals()` getestet werden soll.
    }

    //--------------------------------------------------------

    @Test
    public void testToString() {
        //Zeilenendezeichen soll auf jeden Fall `\n` (LF) sein  und nicht `\r\n` (CRLF)
        Flat flat = new Flat("FS 2,2 4,4\n" +
                "CS ST 2,2 4,4\n" +
                "RR BA 20,50 65,70 RT 10");
        String expected = "3 Räume mit Grundfläche 908, Nutzfläche 904 und Wohnfläche 600\n" +
                "FS SW 2,2 4,4\n" +
                "CS ST 2,2 4,4\n" +
                "RR BA 20,50 65,70 RT 10\n" ;
        assertEquals(expected, flat.toString());
    }
}