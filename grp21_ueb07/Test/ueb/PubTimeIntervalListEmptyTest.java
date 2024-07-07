package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author klk
 */
public class PubTimeIntervalListEmptyTest {
    
    /**
     * Test of getPayload method, of class TimeIntervalList.
     */
    @Test(expected = RuntimeException.class)
    public void testGetPayload() {
        TimeInterval result = new TimeIntervalListEmpty().getPayload();
    }

    /**
     * Test of getNext method, of class TimeIntervalList.
     */
    @Test(expected = RuntimeException.class)
    public void testGetNext() {
        TimeIntervalList result = new TimeIntervalListEmpty().getNext();
    }

    /**
     * Test of isEmpty method, of class TimeIntervalList.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(new TimeIntervalListEmpty().isEmpty());
    }

    /**
     * Test of length method, of class TimeIntervalList.
     */
    @Test
    public void testLength() {
        assertEquals(0, new TimeIntervalListEmpty().length());
    }


    /**
     * Test of equals method, of class TimeIntervalList.
     */
    @Test
    public void testEquals() {
        TimeIntervalList til1 = new TimeIntervalListEmpty();
        TimeIntervalList til2 = new TimeIntervalListEmpty();
        assertEquals(til2, til1);
        assertEquals(til1, til2);
    }
    
    @Test
    public void testEquals_OtherObjects() {
        TimeIntervalList til = new TimeIntervalListEmpty();
        assertNotEquals(til, "String");
        assertNotEquals(til, null);
    }
    
    

    
}
