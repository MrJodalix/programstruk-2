package ueb;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Tests the calendar-methods.
 *
 * @author Joshua-Scott Schoettke, Anton Schmitter, Gruppe 21
 */
public class LinkedListCalendarTest {


    @Test
    public void testCalendarSeize_FiveElementsOverlappsFirstToThird() throws ParseException {
        Calendar calendar = new LinkedListCalendar("test", "01.01.2020", "01.02.2020", "01.03.2020", "01.04.2020", "01.05.2020", "01.06.2020", "01.07.2020", "01.08.2020", "01.09.2020", "01.10.2020", "01.11.2020", "01.12.2020");
        SingleTimeInterval t1 = new SingleTimeInterval("15.03.2020", "15.07.2020");
        Calendar compareCalendar = new LinkedListCalendar("test", "01.01.2020", "01.02.2020", "01.03.2020", "01.08.2020", "01.09.2020", "01.10.2020", "01.11.2020", "01.12.2020");
        assertEquals(compareCalendar, calendar.seize(t1));
    }

    @Test
    public void testLinkedListCalendarSeize_WorkTest1() throws ParseException {
        Calendar calendarTest = new LinkedListCalendar("test", "03.07.2021", "04.07.2021");
        assertEquals(calendarTest.getIntervalCount(), 1);

        SingleTimeInterval t1 = new SingleTimeInterval("10.07.2021", "11.07.2021");
        Calendar calendar1 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "11.07.2021");
        assertEquals(calendar1, calendarTest.seize(t1));
        assertEquals(calendarTest.getIntervalCount(), 2);

        SingleTimeInterval t2 = new SingleTimeInterval("17.07.2021", "18.07.2021");
        Calendar calendar2 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "11.07.2021", "17.07.2021", "18.07.2021");
        assertEquals(calendar2, calendarTest.seize(t2));
        assertEquals(calendarTest.getIntervalCount(), 3);

        SingleTimeInterval t3 = new SingleTimeInterval("24.07.2021", "25.07.2021");
        Calendar calendar3 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "11.07.2021", "17.07.2021", "18.07.2021", "24.07.2021", "25.07.2021");
        assertEquals(calendar3, calendarTest.seize(t3));
        assertEquals(calendarTest.getIntervalCount(), 4);

        SingleTimeInterval t4 = new SingleTimeInterval("11.07.2021", "17.07.2021");
        Calendar calendar4 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "18.07.2021", "24.07.2021", "25.07.2021");
        assertEquals(calendar4, calendarTest.seize(t4));
        assertEquals(calendarTest.getIntervalCount(), 3);

        SingleTimeInterval t5 = new SingleTimeInterval("04.07.2021", "24.07.2021");
        Calendar calendar5 = new LinkedListCalendar("test", "03.07.2021", "25.07.2021");
        assertEquals(calendar5, calendarTest.seize(t5));
        assertEquals(calendarTest.getIntervalCount(), 1);
    }

    @Test
    public void testLinkedListCalendarSeize_WorkTest2() throws ParseException {
        Calendar calendarTest = new LinkedListCalendar("test", "03.07.2021", "04.07.2021");
        assertEquals(calendarTest.getIntervalCount(), 1);

        SingleTimeInterval t1 = new SingleTimeInterval("10.07.2021", "11.07.2021");
        Calendar calendar1 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "11.07.2021");
        assertEquals(calendar1, calendarTest.seize(t1));
        assertEquals(calendarTest.getIntervalCount(), 2);

        SingleTimeInterval t2 = new SingleTimeInterval("17.07.2021", "18.07.2021");
        Calendar calendar2 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "11.07.2021", "17.07.2021", "18.07.2021");
        assertEquals(calendar2, calendarTest.seize(t2));
        assertEquals(calendarTest.getIntervalCount(), 3);

        SingleTimeInterval t3 = new SingleTimeInterval("24.07.2021", "25.07.2021");
        Calendar calendar3 = new LinkedListCalendar("test", "03.07.2021", "04.07.2021", "10.07.2021", "11.07.2021", "17.07.2021", "18.07.2021", "24.07.2021", "25.07.2021");
        assertEquals(calendar3, calendarTest.seize(t3));
        assertEquals(calendarTest.getIntervalCount(), 4);

        SingleTimeInterval t5 = new SingleTimeInterval("01.07.2021", "30.07.2021");
        Calendar calendar5 = new LinkedListCalendar("test");
        assertEquals(calendar5, calendarTest.release(t5));
        assertEquals(calendarTest.getIntervalCount(), 0);
    }

    @Test
    public void testLinkedListCalendarRelease_WorkTest1() throws ParseException {
        Calendar calendarTest = new LinkedListCalendar("test", "03.07.2021", "25.07.2021");
        assertEquals(calendarTest.getIntervalCount(), 1);

        SingleTimeInterval t1 = new SingleTimeInterval("10.07.2021", "11.07.2021");
        Calendar calendar1 = new LinkedListCalendar("test", "03.07.2021", "10.07.2021", "11.07.2021", "25.07.2021");
        assertEquals(calendar1, calendarTest.release(t1));
        assertEquals(calendarTest.getIntervalCount(), 2);

        SingleTimeInterval t2 = new SingleTimeInterval("17.07.2021", "18.07.2021");
        Calendar calendar2 = new LinkedListCalendar("test", "03.07.2021", "10.07.2021", "11.07.2021", "17.07.2021", "18.07.2021", "25.07.2021");
        assertEquals(calendar2, calendarTest.release(t2));
        assertEquals(calendarTest.getIntervalCount(), 3);

        Calendar calendar5 = new LinkedListCalendar("");
        calendarTest.clear();
        assertEquals(calendarTest.getIntervalCount(), 0);



    }
    @Test
    public void testBothCalenderAreEqualButSecret_WorkTest1() throws ParseException {
        Calendar c1 = new ArrayCalendar("Weihnachtsurlaub 20", "24.12.2020", "28.12.2020");
        Calendar c2 = new LinkedListCalendar("Weihnachtsurlaub 20", "24.12.2020", "28.12.2020");
        c1.seize(new SingleTimeInterval("31.12.2020", "04.01.2021"));
        c2.seize(new SingleTimeInterval("31.12.2020", "04.01.2021"));
        assertEquals(c1.getInterval(0), c2.getInterval(0));
        assertEquals(c1.getInterval(1), c2.getInterval(1));
        assertEquals(c1, c2);
        assertEquals(c2, c1);


    }
}
