package ueb;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;
import static ueb.TimeInterval.DF;

/**
 * Tests the calendar-methods.
 * @author klk
 */
public class PubLinkedListCalendarTest {

    /**
     * Test of constructor
     */
    @Test
    public void testLinkedListCalendarConstructor_InvalidCountOfDates() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                "01.03.2020");
        assertEquals(1, c.getIntervalCount());
        assertEquals(new SingleTimeInterval("01.01.2020", "01.02.2020"), c.getInterval(0));
    }

    /**
     * Test of getIntervalCount method, of class LinkedListCalendar.
     */
    @Test
    public void testGetIntervalCount_EmptyLinkedListCalendar() {
        Calendar c = new LinkedListCalendar("test");
        assertEquals(0, c.getIntervalCount());
    }

    @Test
    public void testGetIntervalCount_TwoElements() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                  "01.03.2020", "01.04.2020");
        assertEquals(2, c.getIntervalCount());
    }

    /**
     * Test of getInterval method, of class LinkedListCalendar.
     */
    @Test
    public void testGetInterval_First() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                  "01.03.2020", "01.04.2020");
        assertEquals(new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), ""),
                     c.getInterval(0));
    }
	
    @Test
    public void testGetInterval_Snd() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                  "01.03.2020", "01.04.2020",
                                  "01.05.2020", "01.06.2020");
        assertEquals(new SingleTimeInterval("01.03.2020", "01.04.2020"), c.getInterval(1));
    }

    @Test
    public void testGetInterval_Last() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                  "01.03.2020", "01.04.2020",
                                  "01.05.2020", "01.06.2020");
        assertEquals(new SingleTimeInterval("01.05.2020", "01.06.2020"), c.getInterval(2));
									   
    }

    @Test
    public void testGetInterval_IndexTooBig() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                  "01.03.2020", "01.04.2020");
        assertNull(c.getInterval(2));
    }

    @Test
    public void testGetInterval_IndexNegative() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                  "01.03.2020", "01.04.2020");
        assertNull(c.getInterval(-1));
    }


    /**
     * Test of equals method, of class LinkedListCalendar.
     */
    @Test
    public void testEquals_FourSameElements() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020",
                                     "01.07.2020", "01.08.2020");
        Calendar c2 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020",
                                     "01.07.2020", "01.08.2020");
        assertTrue(c1.equals(c2));
    }

    @Test
    public void testEquals_FirstOfThreeDiffers() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        Calendar c2 = new LinkedListCalendar("test", "02.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEquals_MidOfThreeDiffers() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        Calendar c2 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "02.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEquals_LastOfThreeDiffers() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        Calendar c2 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "02.05.2020", "01.06.2020");
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEquals_FirstHasMoreElements() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        Calendar c2 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020");
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEquals_SecondHasMoreElements() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020");
        Calendar c2 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020",
                                     "01.05.2020", "01.06.2020");
        assertFalse(c1.equals(c2));
    }

    
    @Test
    public void testEquals_WithNull() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020");
        assertFalse(c1.equals(null));
    }
    
    @Test
    public void testEquals_WithString() throws ParseException {
        Calendar c1 = new LinkedListCalendar("test", "01.01.2020", "01.02.2020",
                                     "01.03.2020", "01.04.2020");
        assertFalse(c1.equals(" "));
    } 
    

    /**
     * Test of toString method, of class LinkedListCalendar.
     */
    @Test
    public void testToString_IsSurroundedByCurlyBracketAndBlank() throws ParseException {
        Calendar c = new LinkedListCalendar("test", "01.03.2020", "01.04.2020");
        String cs = c.toString();
        assertEquals('{', cs.charAt(0));
        assertEquals(' ', cs.charAt(1));
        assertEquals(' ', cs.charAt(cs.length()-2));
        assertEquals('}', cs.charAt(cs.length()-1));
    }
    
}
