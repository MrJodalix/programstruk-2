package ueb;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;
import static ueb.TimeInterval.DF;

/**
 *
 * @author klk
 */
public class PubTimeIntervalListElementTest {
    
    /**
     * creates a List with timeInterval-elements of the given dates.
     * At least 2 dates must be given. Each 2 dates build one TimeInterval to add.
     * @param dates dates to build the elements of
     * @return a list with the timeIntervals given by dates
     */
    private TimeIntervalList createTestList(String... dates) throws ParseException {
        TimeIntervalList til = new TimeIntervalListEmpty();
        for (int i = dates.length - 2; i >= 0; i = i - 2) {
            til = new TimeIntervalListElement
                         (new SingleTimeInterval(DF.parse(dates[i]),
                                                 DF.parse(dates[i + 1]), ""),
                          til);
        }        
        return til;
    }
    
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    @Test
    public void testGetPayload() {
        TimeIntervalListElement til = new TimeIntervalListElement(SingleTimeInterval.ETERNITY,
                                                                  new TimeIntervalListEmpty());
        assertEquals(SingleTimeInterval.ETERNITY, til.getPayload());
    }

    /**
     * Test of getNext method, of class TimeIntervalListElement.
     */
    @Test
    public void testGetNext() {
        TimeIntervalListElement til = new TimeIntervalListElement(SingleTimeInterval.ETERNITY,
                                                                  new TimeIntervalListEmpty());
        //prüfen der Klasse ohne Nutzung von TimeIntervalListElement.equals()
        assertEquals(new TimeIntervalListEmpty().getClass(), til.getNext().getClass());
    }

    /**
     * Test of isEmpty method, of class TimeIntervalListElement.
     */
    @Test
    public void testIsEmpty() {
        TimeIntervalListElement til = new TimeIntervalListElement(SingleTimeInterval.ETERNITY,
                                                                  new TimeIntervalListEmpty());
        assertFalse(til.isEmpty());
    }

    /**
     * Test of length method, of class TimeIntervalListElement.
     */
    @Test
    public void testLength() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeIntervalList til2 = createTestList("01.01.2000", "01.02.2000", "01.03.2000", "01.04.2000");
        assertEquals(1, til1.length());
        assertEquals(2, til2.length());
    }

    /**
     * Test of equals method, of class TimeIntervalListElement.
     */
    @Test
    public void testEquals() {

        TimeIntervalListElement til1 = new TimeIntervalListElement(SingleTimeInterval.ETERNITY,
                                                                  new TimeIntervalListEmpty());
        TimeIntervalListElement til2 = new TimeIntervalListElement(SingleTimeInterval.ETERNITY,
                                                                  new TimeIntervalListEmpty());
        assertTrue(til1.equals(til1));
        assertTrue(til1.equals(til2));
    }
    
    @Test
    public void testEquals_FirstShorter() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeIntervalList til2 = createTestList("01.01.2000", "01.02.2000", "01.03.2000", "01.04.2000");
        assertFalse(til1.equals(til2));
    }
    
    @Test
    public void testEquals_FirstLonger() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeIntervalList til2 = createTestList("01.01.2000", "01.02.2000", "01.03.2000", "01.04.2000");
        assertFalse(til2.equals(til1));
    }
    
    @Test
    public void testEquals_DiffElements() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeIntervalList til2 = createTestList("01.02.2000", "01.03.2000");
        assertFalse(til1.equals(til2));
        assertFalse(til2.equals(til1));
    }

    
    /**
     * Test of toString method, of class TimeIntervalListElement.
     */
    @Test
    public void testToString() throws ParseException {
        TimeIntervalList til = createTestList("01.03.2000", "01.04.2000",
                "01.05.2000", "01.06.2000");
        assertTrue(til.toString().contains(", "));
        assertTrue(til.toString().endsWith("2000"));
    }

    
}
