package ueb;

import java.text.ParseException;

/**
 * Hält die Zeitintervalle in einer Liste vom Typ TimeIntervalList statt in einem Array vor.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public class LinkedListCalendar extends Calendar {

    /**
     * Liste mit TimeIntervallen
     */
    private TimeIntervalList list;

    /**
     * Konstruktor für einen leeren Listenkalender mit einer Caption
     *
     * @param caption Beschriftung des Kalenders
     */
    public LinkedListCalendar(String caption) {
        super(caption);
        list = new TimeIntervalListEmpty();
    }

    /**
     * Konstruktor für einen ListenKalender
     *
     * @param caption Beschriftung
     * @param dates   Daten
     * @throws ParseException bei falschen Strings
     */
     LinkedListCalendar(String caption, String... dates) throws ParseException {
        super(caption);
        SingleTimeInterval[] intervals = new SingleTimeInterval[dates.length / 2];
        list = new TimeIntervalListEmpty();
        int j = 0;
        for (int i = 0; i < intervals.length; i++) {
            seize(new SingleTimeInterval(dates[j], dates[j + 1]));
            j = j + 2;
        }
    }

    /**
     * gibt die Intervalanzahl zurück
     *
     * @return gibt Anzahl der Intervalle zurück
     */
    @Override
    public int getIntervalCount() {
        return list.length();
    }

    /**
     * Gibt ein Interval an einem bestimmten Index zurück
     *
     * @param idx Index des Intervals
     * @return gibt Interval an dem Index zurück
     */
    @Override
    public TimeInterval getInterval(int idx) {
        int i = 0;
        if (idx >= 0 && idx < list.length()) {
            TimeIntervalList newList = list;
            while (i < idx && i <= newList.length()) {
                newList = newList.getNext();
                i++;
            }
            if (i == idx) {
                return newList.getPayload();
            }
        }
        return null;
    }

    /**
     * Belegt ein Zeitfenster im Kalender
     *
     * @param ti Zeitfenster
     * @return gibt den Listenzeiger zurück
     */
    @Override
    public Calendar seize(TimeInterval ti) {
        list = list.insert(ti);
        return this;
    }

    /**
     * Gibt ein Zeitfenster im Kalender frei
     *
     * @param ti Zeitfenster
     * @return Zeiger auf die Liste
     */
    @Override
    public Calendar release(TimeInterval ti) {
        list = list.remove(ti);
        return this;
    }

    /**
     * Löscht alle Kalendereinträge
     *
     * @return Zeiger auf die Liste
     */
    @Override
    public Calendar clear() {
        list = new TimeIntervalListEmpty();
        return this;
    }

    /**
     * Stringdarstellung des Kalenders
     *
     * @return Stringdarstellung des Kalenders. Der Kalender beginnt und endet mit "{" bzw. "}"
     */
    @Override
    public String toString() {
        return "{ [" + list + "] }";
    }


}