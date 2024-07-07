package ueb;

import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Tests the Calendar-methods.
 * @author Anton Schmitter, Joshua-Scott Schöttke Gruppe 21
 */
public class CalendarTest {
    //<editor-fold defaultstate="expanded" desc="TestSeize">
    @Test
    public void testCalendarSeize_OneElements() throws ParseException {
        Calendar calendar = new Calendar();
        TimeInterval t1 = new TimeInterval("01.01.2020", "01.02.2020");
        assertEquals(new Calendar("01.01.2020", "01.02.2020"), calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_TwoElements() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.02.2020");
        TimeInterval t1 = new TimeInterval("01.03.2020", "01.04.2020");
        assertEquals(new Calendar("01.01.2020", "01.02.2020","01.03.2020", "01.04.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_TwoElementsOverlapps() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.02.2020");
        TimeInterval t1 = new TimeInterval("15.01.2020", "01.03.2020");
        assertEquals(new Calendar("01.01.2020", "01.03.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_ThreeElementsOverlapps() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.02.2020","01.03.2020","01.04.2020");
        TimeInterval t1 = new TimeInterval("15.01.2020", "15.03.2020");
        assertEquals(new Calendar("01.01.2020", "01.04.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_ThreeElementsNoOverlapps() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020");
        TimeInterval t1 = new TimeInterval("01.02.2020", "01.03.2020");
        assertEquals(new Calendar("01.01.2020","01.04.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsNoOverlapps() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("01.09.2020", "01.10.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsNoOverlappsFirst() throws ParseException {
        Calendar calendar = new Calendar("01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("01.01.2020","01.02.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsNoOverlappsSecond() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("01.03.2020","01.04.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsOverlappsSecond() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("01.03.2020","15.05.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsOverlappsFirst() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("15.01.2020","01.03.2020");
        assertEquals(new Calendar("01.01.2020","01.03.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020", "01.10.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsOverlappsLast() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("01.10.2020","15.11.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.10.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarSeize_FiveElementsOverlappsLas() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("15.03.2020","15.07.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","01.08.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    @Test
    public void testCalendarRelease_FiveElementsOverlappsForLast() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020","01.10.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("15.03.2020","15.07.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","01.08.2020","01.09.2020","01.10.2020","01.11.2020","01.12.2020")
                , calendar.seize(t1));
    }
    //</editor-fold>

    // <editor-fold defaultstate="expanded" desc="TestRelease">
    @Test
    public void testCalendarRelease_EmptyCalender() throws ParseException {
        Calendar calendar = new Calendar();
        TimeInterval t1 = new TimeInterval("01.01.2020", "01.02.2020");
        assertEquals(new Calendar(), calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_OneElements() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.02.2020");
        TimeInterval t1 = new TimeInterval("01.01.2020", "01.02.2020");
        assertEquals(new Calendar(), calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_OneTimeGetTwo() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.04.2020");
        TimeInterval t1 = new TimeInterval("01.02.2020", "01.03.2020");
        assertEquals(new Calendar("01.01.2020", "01.02.2020","01.03.2020", "01.04.2020")
                , calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_TwoElementsOverlapps() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.04.2020");
        TimeInterval t1 = new TimeInterval("01.01.2020", "01.02.2020");
        assertEquals(new Calendar("01.02.2020", "01.04.2020")
                , calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_ThreeElementsOverlapps() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020", "01.04.2020");
        TimeInterval t1 = new TimeInterval("01.03.2020", "01.04.2020");
        assertEquals(new Calendar("01.01.2020", "01.03.2020")
                , calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_ThreeElementsOneOverlappsLeft() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020");
        TimeInterval t1 = new TimeInterval("01.12.2019", "15.01.2020");
        assertEquals(new Calendar("15.01.2020","01.02.2020")
                , calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_ThreeElementsOneOverlappsRight() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.03.2020");
        TimeInterval t1 = new TimeInterval("15.02.2020", "01.04.2020");
        assertEquals(new Calendar("01.01.2020","15.02.2020")
                , calendar.release(t1));
    }
    @Test
    public void testCalendarRelease_FromToTwoElements() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.04.2020");
        TimeInterval t1 = new TimeInterval("01.02.2020", "01.03.2020");
        assertEquals(new Calendar("01.01.2020", "01.02.2020","01.03.2020","01.04.2020")
                , calendar.release(t1));
    }

    @Test
    public void testCalendarRelease_FiveElementsOverlappsLast() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.03.2020","01.04.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020","01.09.2020","01.10.2020","01.11.2020","01.12.2020");
        TimeInterval t1 = new TimeInterval("15.03.2020","15.09.2020");
        assertEquals(new Calendar("01.01.2020","01.02.2020","01.03.2020","15.03.2020","15.09.2020","01.10.2020","01.11.2020","01.12.2020")
                , calendar.release(t1));
    }
    //</editor-fold>
    //cleanTest

    @Test
    public void testCalendarClean_ThreeElements() throws ParseException {
        Calendar calendar = new Calendar("01.01.2020","01.02.2020","01.05.2020","01.06.2020","01.07.2020","01.08.2020");
        assertEquals(new Calendar(), calendar.clear());
    }
}
