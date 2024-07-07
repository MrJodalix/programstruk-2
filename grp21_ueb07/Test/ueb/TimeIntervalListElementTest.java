package ueb;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;
import static ueb.TimeInterval.DF;

/**
 * @author Joshua-Scott Schoettke, Gruppe 21
 */

public class TimeIntervalListElementTest {
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
    //  /---/   /   /
    //  /   /   /---/
    //  /---/   /---/
    @Test
    public void testInsert0() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeInterval til2 = new SingleTimeInterval("01.03.2000", "01.04.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.02.2000","01.03.2000", "01.04.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/   /   /
    //  /   /---/   /
    //  /---/---/   /
    @Test
    public void testInsert1() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeInterval til2 = new SingleTimeInterval("01.02.2000", "01.03.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.03.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /   /---/   /
    //  /---/   /   /
    //  /---/---/   /
    @Test
    public void testInsert2() throws ParseException {
        TimeIntervalList til1 = createTestList("01.02.2000", "01.03.2000");
        TimeInterval til2 = new SingleTimeInterval("01.01.2000", "01.02.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.03.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /   /---/   /---/   /
    //  /   /   /---/   /   /
    //  /   /---/---/---/   /
    @Test
    public void testInsert3() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000","01.03.2000", "01.04.2000");
        TimeInterval til2 = new SingleTimeInterval("01.02.2000", "01.03.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.04.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/---/---/---/---/
    //  /   /   /---/   /   /
    //  /---/---/---/---/---/
    @Test
    public void testInsert4() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.05.2000");
        TimeInterval til2 = new SingleTimeInterval("01.02.2000", "01.03.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.05.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/---/---/   /   /
    //  /   /---/---/---/   /
    //  /---/---/---/---/   /
    @Test
    public void testInsert5() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.03.2000");
        TimeInterval til2 = new SingleTimeInterval("01.02.2000", "01.04.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.04.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /   /   /---/   /   /
    //  /---/---/---/---/---/
    //  /---/---/---/---/---/
    @Test
    public void testInsert6() throws ParseException {
        TimeIntervalList til1 = createTestList("01.02.2000", "01.03.2000");
        TimeInterval til2 = new SingleTimeInterval("01.01.2000", "01.05.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.05.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/   /---/   /---/
    //  /  -/---/---/---/-  /
    //  /---/---/---/---/---/
    @Test
    public void testInsert7() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000","01.03.2000", "01.04.2000","01.05.2000", "01.06.2000");
        TimeInterval til2 = new SingleTimeInterval("20.01.2000", "15.05.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.06.2000");
        assertEquals(til3, til1.insert(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/   /---/   /---/   /---/   /---/   /---/
    //  /   /   / --/---/---/---/-- /   /   /   /   /
    //  /---/   /---/---/---/---/---/   /---/   /---/
    @Test
    public void testInsert8() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000","01.02.2000","01.03.2000","01.04.2000","01.05.2000","01.06.2000","01.07.2000","01.08.2000","01.09.2000","01.10.2000","01.11.2000","01.12.2000");
        TimeInterval til2 = new SingleTimeInterval("20.03.2000", "15.07.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.02.2000","01.03.2000","01.08.2000","01.09.2000","01.10.2000","01.11.2000","01.12.2000");
        assertEquals(til3, til1.insert(til2));
    }
    //----------------------------------------------------------------------------------------
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/   /   /   /   /
    //  /   /   /---/   /   /
    //  /---/   /   /   /   /
    @Test
    public void testRemove0() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000");
        TimeInterval ti2 = new SingleTimeInterval("01.03.2000", "01.04.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.02.2000");
        assertEquals(til3, til1.remove(ti2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/   /---/   /   /
    //  /   /   /---/   /   /
    //  /---/   /   /   /   /
    @Test
    public void testRemove1() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000","01.03.2000", "01.04.2000");
        TimeInterval til2 = new SingleTimeInterval("01.03.2000", "01.04.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.02.2000");
        assertEquals(til3, til1.remove(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/---/---/---/   /
    //  /   /   /---/   /   /
    //  /---/---/   /---/   /
    @Test
    public void testRemove2() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.05.2000");
        TimeInterval til2 = new SingleTimeInterval("01.03.2000", "01.04.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "01.03.2000", "01.04.2000","01.05.2000");
        assertEquals(til3, til1.remove(til2));
    }
    /**
     * Test of getPayload method, of class TimeIntervalListElement.
     */
    //  /---/   /---/   /---/
    //  /  -/---/---/---/-  /
    //  /-- /   /   /   / --/
    @Test
    public void testRemove3() throws ParseException {
        TimeIntervalList til1 = createTestList("01.01.2000", "01.02.2000","01.03.2000", "01.04.2000","01.05.2000","01.06.2000");
        TimeInterval til2 = new SingleTimeInterval("20.01.2000", "15.05.2000");
        TimeIntervalList til3 = createTestList("01.01.2000", "20.01.2000", "15.05.2000","01.06.2000");
        assertEquals(til3, til1.remove(til2));
    }
}
