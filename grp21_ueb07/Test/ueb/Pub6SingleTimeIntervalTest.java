package ueb;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;
import static ueb.SingleTimeInterval.DF;

/**
 * Tests of class SingleTimeInterval.
 * In assignment 6 given Tests for TimeInterval now instantiate a SingleTimeInterval.
 * @author klk
 */
public class Pub6SingleTimeIntervalTest {

//<editor-fold defaultstate="expanded" desc="constructor tests">
    /**
     * Test of constructor of class TimeInterval
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_StartIsNull() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(null, DF.parse("01.01.2020"), "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_EndIsNull() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(DF.parse("01.01.2020"), null, "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_StartIsSameAsEnd() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.01.2020"), "");
    }

    /**
     * Test of string-constructor of class TimeInterval
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_String_StartIsNull() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(null, "01.01.2020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_String_EndIsNull() throws ParseException {
        TimeInterval ti = new SingleTimeInterval("01.01.2020", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_String_StartIsSameAsEnd() throws ParseException {
        TimeInterval ti = new SingleTimeInterval("01.01.2020", "01.01.2020");
    }
//</editor-fold>

//<editor-fold defaultstate="expanded" desc="static methods tests">
    /**
     * Test of lt method, of class TimeInterval.
     */
    @Test
    public void testLt_NotTouchingDates() throws ParseException {
        assertTrue(TimeInterval.lt(DF.parse("01.01.2020"), DF.parse("01.04.2020")));
    }
    
    @Test
    public void testLt_sameDate() throws ParseException {
        assertFalse(TimeInterval.lt(DF.parse("01.01.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testLt_PreFirst() throws ParseException {
        assertTrue(TimeInterval.lt(DF.parse("01.01.2020"), DF.parse("02.01.2020")));
    }
    
    @Test
    public void testLt_PostFirst() throws ParseException {
        assertFalse(TimeInterval.lt(DF.parse("02.01.2020"), DF.parse("01.01.2020")));
    }
    
    /**
     * Test of gt method, of class TimeInterval.
     */
    @Test
    public void testGt_NotTouchingDates() throws ParseException {
        assertTrue(TimeInterval.gt(DF.parse("01.02.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testGt_sameDate() throws ParseException {
        assertFalse(TimeInterval.gt(DF.parse("01.01.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testGt_PreFirst() throws ParseException {
        assertFalse(TimeInterval.gt(DF.parse("01.01.2020"), DF.parse("02.01.2020")));
    }
    
    @Test
    public void testGt_PostFirst() throws ParseException {
        assertTrue(TimeInterval.gt(DF.parse("02.01.2020"), DF.parse("01.01.2020")));
    }
    
    /**
     * Test of eq method, of class TimeInterval.
     */
    @Test
    public void testEq_SameDate() throws ParseException {
        assertTrue(TimeInterval.eq(DF.parse("01.01.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testEq_PreFirst() throws ParseException {
        assertFalse(TimeInterval.eq(DF.parse("01.01.2020"), DF.parse("02.01.2020")));
    }
    
    @Test
    public void testEq_PostFirst() throws ParseException {
        assertFalse(TimeInterval.eq(DF.parse("02.01.2020"), DF.parse("01.01.2020")));
    }
    
    /**
     * Test of ne method, of class TimeInterval.
     */
    @Test
    public void testNe_SameDate() throws ParseException {
        assertFalse(TimeInterval.ne(DF.parse("01.01.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testNe_PreFirst() throws ParseException {
        assertTrue(TimeInterval.ne(DF.parse("01.01.2020"), DF.parse("02.01.2020")));
    }
    
    @Test
    public void testNe_PostFirst() throws ParseException {
        assertTrue(TimeInterval.ne(DF.parse("02.01.2020"), DF.parse("01.01.2020")));
    }
    
    /**
     * Test of le method, of class TimeInterval.
     */
    @Test
    public void testLe_SameDate() throws ParseException {
        assertTrue(TimeInterval.le(DF.parse("01.01.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testLe_PreFirst() throws ParseException {
        assertTrue(TimeInterval.le(DF.parse("01.01.2020"), DF.parse("02.01.2020")));
    }
    
    @Test
    public void testLe_PostFirst() throws ParseException {
        assertFalse(TimeInterval.le(DF.parse("02.01.2020"), DF.parse("01.01.2020")));
    }
    
    /**
     * Test of ge method, of class TimeInterval.
     */
    @Test
    public void testGe_SameDate() throws ParseException {
        assertTrue(TimeInterval.ge(DF.parse("01.01.2020"), DF.parse("01.01.2020")));
    }
    
    @Test
    public void testGe_PreFirst() throws ParseException {
        assertFalse(TimeInterval.ge(DF.parse("01.01.2020"), DF.parse("02.01.2020")));
    }
    
    @Test
    public void testGe_PostFirst() throws ParseException {
        assertTrue(TimeInterval.ge(DF.parse("02.01.2020"), DF.parse("01.01.2020")));
    }
    
    /**
     * Test of min method, of class TimeInterval.
     */
    @Test
    public void testMin_SameDates() throws ParseException {
        Date result = TimeInterval.min(DF.parse("01.01.2020"), DF.parse("01.01.2020"));
        assertEquals(DF.parse("01.01.2020"), result);
    }
    
    @Test
    public void testMin_PreFirst() throws ParseException {
        Date result = TimeInterval.min(DF.parse("01.01.2020"), DF.parse("02.01.2020"));
        assertEquals(DF.parse("01.01.2020"), result);
    }
    
    @Test
    public void testMin_PostFirst() throws ParseException {
        Date result = TimeInterval.min(DF.parse("02.01.2020"), DF.parse("01.01.2020"));
        assertEquals(DF.parse("01.01.2020"), result);
    }
    
    /**
     * Test of max method, of class TimeInterval.
     */
    @Test
    public void testMax_SameDates() throws ParseException {
        Date result = TimeInterval.max(DF.parse("01.01.2020"), DF.parse("01.01.2020"));
        assertEquals(DF.parse("01.01.2020"), result);
    }
    
    @Test
    public void testMax_PreFirst() throws ParseException {
        Date result = TimeInterval.max(DF.parse("01.01.2020"), DF.parse("02.01.2020"));
        assertEquals(DF.parse("02.01.2020"), result);
    }
    
    @Test
    public void testMax_PostFirst() throws ParseException {
        Date result = TimeInterval.max(DF.parse("02.01.2020"), DF.parse("01.01.2020"));
        assertEquals(DF.parse("02.01.2020"), result);
    }
    
    /**
     * Test of isPastInfinity method, of class TimeInterval.
     */
    @Test
    public void testIsPastInfinity() {
        assertTrue(TimeInterval.isPastInfinity(TimeInterval.PAST_INFINITY));
    }
    
    @Test
    public void testIsPastInfinity_Not() {
        assertFalse(TimeInterval.isPastInfinity(TimeInterval.FUTURE_INFINITY));
    }
    
    /**
     * Test of isFutureInfinity method, of class TimeInterval.
     */
    @Test
    public void testIsFutureInfinity() {
        assertTrue(TimeInterval.isFutureInfinity(TimeInterval.FUTURE_INFINITY));
    }
    
    @Test
    public void testIsFutureInfinity_Not() {
        assertFalse(TimeInterval.isFutureInfinity(TimeInterval.PAST_INFINITY));
    }
//</editor-fold>

//<editor-fold defaultstate="expanded" desc="dynamic methods tests">
    /**
     * Test of getStart method, of class TimeInterval.
     */
    @Test
    public void testGetStart() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("31.12.2020"), "");
        assertEquals(DF.parse("01.01.2020"), ti.getStart());
    }
    
    /**
     * Test of getEnd method, of class TimeInterval.
     */
    @Test
    public void testGetEnd() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("31.12.2020"), "");
        assertEquals(DF.parse("31.12.2020"), ti.getEnd());
    }
    
    /**
     * Test of getDuration method, of class TimeInterval.
     */
    @Test
    public void testGetDuration_OneDay() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("02.01.2020"), "");
        Long oneDayInMs = (long) 24 * 60 * 60 * 1000;
        assertEquals(oneDayInMs, ti.getDuration());
    }
    
    @Test
    public void testGetDuration_StartsInPastInfinity() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(TimeInterval.PAST_INFINITY, DF.parse("02.01.2020"), "");
        assertNull(ti.getDuration());
    }
    
    @Test
    public void testGetDuration_EndsInFutureInfinity() throws ParseException {
        TimeInterval ti = new SingleTimeInterval(DF.parse("01.01.2020"), TimeInterval.FUTURE_INFINITY, "");
        assertNull(ti.getDuration());
    }
    
    /**
     * Test of succeeds method, of class TimeInterval.
     */
    @Test
    public void testSucceeds_AprilSuccJanuary() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval apr = new SingleTimeInterval(DF.parse("01.04.2020"), DF.parse("01.05.2020"), "");
        assertTrue(apr.succeeds(jan));
    }
    @Test
    public void testSucceeds_JanNotSuccApril() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval apr = new SingleTimeInterval(DF.parse("01.04.2020"), DF.parse("01.05.2020"), "");
        assertFalse(jan.succeeds(apr));
    }
    
    @Test
    public void testSucceeds_TouchingIntervals_FebSuccJan() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(feb.succeeds(jan));
    }
    
    @Test
    public void testSucceeds_TouchingIntervals_JanNotSuccFeb() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(jan.succeeds(feb));
    }
    
    @Test
    public void testSucceeds_SameOrEqualIntervals() throws ParseException {
        TimeInterval jan1 = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval jan2 = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        assertFalse(jan1.succeeds(jan2));
        assertFalse(jan1.succeeds(jan1));
    }
    
    @Test
    public void testSucceeds_OverlappingIntervals() throws ParseException {
        TimeInterval january       = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval midJan2MidFeb = new SingleTimeInterval(DF.parse("15.01.2020"), DF.parse("15.02.2020"), "");
        assertFalse(midJan2MidFeb.succeeds(january));
        assertFalse(january.succeeds(midJan2MidFeb));
    }
    
    /**
     * Test of precedes method, of class TimeInterval.
     */
    @Test
    public void testPrecedes_JanuaryPreApril() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval apr = new SingleTimeInterval(DF.parse("01.04.2020"), DF.parse("01.05.2020"), "");
        assertTrue(jan.precedes(apr));
    }
    
    @Test
    public void testPrecedes_AprilPreJanuary() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval apr = new SingleTimeInterval(DF.parse("01.04.2020"), DF.parse("01.05.2020"), "");
        assertFalse(apr.precedes(jan));
    }
    
    @Test
    public void testPrecedes_TouchingIntervals() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(jan.precedes(feb));
    }
    
    @Test
    public void testPrecedes_TouchingIntervals_WrongOrder() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(feb.precedes(jan));
    }
    
    @Test
    public void testPrecedes_SameOrEqualIntervals() throws ParseException {
        TimeInterval jan1 = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval jan2 = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        assertFalse(jan1.precedes(jan2));
        assertFalse(jan1.precedes(jan1));
    }
    
    @Test
    public void testPrecedes_OverlappingIntervals() throws ParseException {
        TimeInterval january       = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval midJan2MidFeb = new SingleTimeInterval(DF.parse("15.01.2020"), DF.parse("15.02.2020"), "");
        assertFalse(january.precedes(midJan2MidFeb));
        assertFalse(midJan2MidFeb.precedes(january));
    }
    
    /**
     * Test of touches method, of class TimeInterval.
     */
    @Test
    public void testTouches_PreFirst() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(jan.touches(feb));
    }
    
    @Test
    public void testTouches_PostFirst() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(feb.touches(jan));
    }
    
    @Test
    public void testTouches_OneDayBetweenPreFirst() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("31.01.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(jan.touches(feb));
    }
    
    @Test
    public void testTouches_OneDayBetweenPostFirst() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("31.01.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(feb.touches(jan));
    }
    
    @Test
    public void testTouches_Overlapping() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("31.01.2020"), "");
        TimeInterval mid = new SingleTimeInterval(DF.parse("15.01.2020"), DF.parse("15.02.2020"), "");
        assertFalse(jan.touches(mid));
        assertFalse(mid.touches(jan));
    }
    
    /**
     * Test of contains(Date) method, of class TimeInterval.
     */
    @Test
    public void testContains_Date_PreDate() throws ParseException {
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(feb.contains(DF.parse("31.01.2020")));
    }
    
    @Test
    public void testContains_Date_PostDate() throws ParseException {
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(feb.contains(DF.parse("01.03.2020")));
    }
    
    @Test
    public void testContains_Date_AtIntervalStart() throws ParseException {
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(feb.contains(DF.parse("01.02.2020")));
    }
    
    @Test
    public void testContains_Date_InMiddle() throws ParseException {
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(feb.contains(DF.parse("15.02.2020")));
    }
    
    @Test
    public void testContains_Date_AtIntervalEnd() throws ParseException {
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(feb.contains(DF.parse("01.03.2020")));
    }
    
    /**
     * Test of contains method, of class TimeInterval.
     */
    @Test
    public void testContains_TimeInterval_SameInterval() throws ParseException {
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(feb.contains(feb));
    }
    
    @Test
    public void testContains_TimeInterval_AtStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("07.02.2020"), "");
        assertTrue(tiA.contains(tiB));
    }
    
    @Test
    public void testContains_TimeInterval_AtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(tiA.contains(tiB));
    }
    
    @Test
    public void testContains_TimeInterval_OverlappingAtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.02.2020"), DF.parse("10.03.2020"), "");
        assertFalse(tiA.contains(tiB));
    }
    
    @Test
    public void testContains_TimeInterval_OverlappingAtStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.01.2020"), DF.parse("10.02.2020"), "");
        assertFalse(tiA.contains(tiB));
    }
    
    @Test
    public void testContains_TimeInterval_TouchingAtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("10.03.2020"), "");
        assertFalse(tiA.contains(tiB));
    }
    
    @Test
    public void testContains_TimeInterval_TouchingAtStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.01.2020"), DF.parse("01.02.2020"), "");
        assertFalse(tiA.contains(tiB));
    }
    
    /**
     * Test of overlaps method, of class TimeInterval.
     */
    @Test
    public void testOverlaps_EqualOrSame() throws ParseException {
        TimeInterval feb1 = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval feb2 = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(feb1.overlaps(feb2));
        assertTrue(feb1.overlaps(feb1));
    }
    
    @Test
    public void testOverlaps_SameStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("07.02.2020"), "");
        assertTrue(tiA.overlaps(tiB));
    }
    
    @Test
    public void testOverlaps_SameEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.02.2020"), DF.parse("01.03.2020"), "");
        assertTrue(tiA.overlaps(tiB));
    }
    
    @Test
    public void testOverlaps_OverlappingAtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.02.2020"), DF.parse("10.03.2020"), "");
        assertTrue(tiA.overlaps(tiB));
    }
    
    @Test
    public void testOverlaps_OverlappingAtStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.01.2020"), DF.parse("10.02.2020"), "");
        assertTrue(tiA.overlaps(tiB));
    }
    
    @Test
    public void testOverlaps_TouchingAtEnd() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(jan.overlaps(feb));
    }
    
    @Test
    public void testOverlaps_TouchingAtStart() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(feb.overlaps(jan));
    }
    
    @Test
    public void testOverlaps_Containing() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval mid = new SingleTimeInterval(DF.parse("10.01.2020"), DF.parse("20.01.2020"), "");
        assertTrue(jan.overlaps(mid));
        assertTrue(mid.overlaps(jan));
    }
    
    /**
     * Test of intersect method, of class TimeInterval.
     */
    @Test
    public void testIntersect_OverlappingAtStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.01.2020"), DF.parse("10.02.2020"), "b");
        TimeInterval intersect = new SingleTimeInterval(DF.parse("01.02.2020"),
                DF.parse("10.02.2020"),
                "(a).intersect(b)");
        assertEquals(intersect, tiA.intersect(tiB));
    }
    
    @Test
    public void testIntersect_OverlappingAtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("22.02.2020"), DF.parse("10.03.2020"), "b");
        TimeInterval intersect = new SingleTimeInterval(DF.parse("22.02.2020"),
                DF.parse("01.03.2020"),
                "(a).intersect(b)");
        assertEquals(intersect, tiA.intersect(tiB));
    }
    
    @Test
    public void testIntersect_ContainingSecond() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("10.02.2020"), DF.parse("12.02.2020"), "b");
        TimeInterval intersect = new SingleTimeInterval(DF.parse("10.02.2020"),
                DF.parse("12.02.2020"),
                "(a).intersect(b)");
        assertEquals(intersect, tiA.intersect(tiB));
    }
    
    @Test
    public void testIntersect_SndContainingFirst() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("10.02.2020"), DF.parse("12.02.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "b");
        TimeInterval intersect = new SingleTimeInterval(DF.parse("10.02.2020"),
                DF.parse("12.02.2020"),
                "(a).intersect(b)");
        assertEquals(intersect, tiA.intersect(tiB));
    }
    
    @Test
    public void testIntersect_NotTouchingIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.04.2020"), DF.parse("01.05.2020"), "b");
        assertNull(tiA.intersect(tiB));
    }
    
    @Test
    public void testIntersect_TouchingIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "b");
        assertNull(tiA.intersect(tiB));
    }
    
    /**
     * Test of union method, of class TimeInterval.
     */
    @Test
    public void testUnion_SeparateIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "b");
        assertNull(tiA.union(tiB));
    }
    
    @Test
    public void testUnion_TouchingIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "b");
        TimeInterval tiE = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "(a).union(b)");
        assertEquals(tiE, tiA.union(tiB));
    }
    
    @Test
    public void testUnion_OverlappingIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.04.2020"), "b");
        TimeInterval tiE = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.04.2020"), "(a).union(b)");
        assertEquals(tiE, tiA.union(tiB));
    }
    
    /**
     * Test of sub method, of class TimeInterval.
     */
    @Test
    // |---|
    //     |---|
    public void testSub_NotOverlapping() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{
            new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "")};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    // |---|
    //   |---|
    public void testSub_OverlappingAtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("15.01.2020"), DF.parse("01.03.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{
            new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("15.01.2020"), "")};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    // |---|
    // |-|
    public void testSub_OverlappingAtBegin() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("15.01.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{
            new SingleTimeInterval(DF.parse("15.01.2020"), DF.parse("01.02.2020"), "")};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    // |-------|
    //   |---|
    public void testSub_ContainingComplete() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.04.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{
            new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), ""),
            new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "")};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    //    |---|
    //  |-------|
    public void testSub_ContainedComplete() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.04.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    //     |---|
    //     |-|
    public void testSub_ContainingAtStart() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.04.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{
            new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "")};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    // |---|
    //   |-|
    public void testSub_ContainingAtEnd() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.04.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{
            new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "")};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    @Test
    // |---|
    // |---|
    public void testSub_SameInterval() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        TimeInterval[] tiE = new SingleTimeInterval[]{};
        assertArrayEquals(tiE, tiA.sub(tiB));
    }
    
    /**
     * Test of between method, of class TimeInterval.
     */
    @Test
    public void testBetween_FebResultsBetweenJanAndMarch() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval mar = new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertEquals(feb, jan.between(mar));
    }
    
    @Test
    public void testBetween_FebResultsBetweenMarchAndJan() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval mar = new SingleTimeInterval(DF.parse("01.03.2020"), DF.parse("01.04.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "().between()");
        assertEquals(feb, mar.between(jan));
    }
    
    @Test
    public void testBetween_OverlappingIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.04.2020"), "");
        assertNull(tiA.between(tiB));
        assertNull(tiB.between(tiA));
    }
    
    @Test
    public void testBetween_TouchingIntervals() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval feb = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertNull(jan.between(feb));
        assertNull(feb.between(jan));
    }
    
    /**
     * Test of compareTo method, of class TimeInterval.
     */
    @Test
    public void testCompareTo_FirstBeforeSecond() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertEquals(-1, tiA.compareTo(tiB));
    }
    
    @Test
    public void testCompareTo_OverlappingFirstStartsBefore() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("02.01.2020"), DF.parse("01.03.2020"), "");
        assertEquals(-1, tiA.compareTo(tiB));
    }
    
    @Test
    public void testCompareTo_SecondBeforeFirst() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertEquals(1, tiB.compareTo(tiA));
    }
    
    @Test
    public void testCompareTo_OverlappingSecondStartsBefore() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("02.01.2020"), DF.parse("01.03.2020"), "");
        assertEquals(1, tiB.compareTo(tiA));
    }
    
    @Test
    public void testCompareTo_SameStartFirstShorter() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "");
        assertEquals(0, tiA.compareTo(tiB));
    }
    
    @Test
    public void testCompareTo_SameStartFirstLonger() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.03.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        assertEquals(0, tiA.compareTo(tiB));
    }
    
    /**
     * Test of equals method, of class TimeInterval.
     */
    @Test
    public void testEquals_SameIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        assertTrue(tiA.equals(tiB));
    }
    
    @Test
    public void testEquals_DifferentIntervals() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.02.2020"), DF.parse("01.03.2020"), "");
        assertFalse(tiA.equals(tiB));
    }
    
    @Test
    public void testEquals_SameIntervalButDifferentCaption() throws ParseException {
        //captions are not compared in equals
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "a");
        TimeInterval tiB = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "b");
        assertTrue(tiA.equals(tiB));
    }
    
    @Test
    public void testEquals_String() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        assertFalse(jan.equals("String"));
    }
    
    @Test
    public void testEquals_Null() throws ParseException {
        TimeInterval jan = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "");
        assertFalse(jan.equals(null));
    }
    
    /**
     * Test of toString method, of class TimeInterval.
     */
    @Test
    public void testToString_ContainsMinus() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), DF.parse("01.02.2020"), "a");
        assertTrue(tiA.toString().contains(" - "));
    }
    
    @Test
    public void testToString_FutureInfinity() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(DF.parse("01.01.2020"), TimeInterval.FUTURE_INFINITY, "a");
        assertTrue(tiA.toString().contains("oo"));
    }
    
    @Test
    public void testToString_PastInfinity() throws ParseException {
        TimeInterval tiA = new SingleTimeInterval(TimeInterval.PAST_INFINITY, DF.parse("01.01.2020"), "a");
        assertTrue(tiA.toString().contains("oo"));
    }
//</editor-fold>

}
