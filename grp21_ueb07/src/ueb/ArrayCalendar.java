package ueb;

import java.text.ParseException;
import java.util.Arrays;

/**
 * Kalender, der Zeitintervalle in einem Array vorhält.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public class ArrayCalendar extends Calendar{

    /**
     * Array der Zeitintervalle
     */
    private TimeInterval[] intervals;

    /**
     * Konstruktor für einen leeren Kalender
     * @param caption Beschriftung
     */
    public ArrayCalendar(String caption) {
        super(caption);
        intervals = new TimeInterval[]{};
    }

    /**
     * Konstruktor für einen Kalender mit Zeitintervallen in einem Array gespeichert
     * @param caption Beschriftung
     * @param variant Daten
     * @throws ParseException bei falschen Strings
     */
    public ArrayCalendar(String caption, String... variant) throws ParseException {
        super(caption);
        int j = 0;
        intervals = new SingleTimeInterval[variant.length / 2];
        for (int i = 0; i < intervals.length; i++) {
            SingleTimeInterval newTimeInterval = new SingleTimeInterval(variant[j], variant[j + 1]);
            intervals[i] = newTimeInterval;
            j = j + 2;
        }
    }

    /**
     * gibt die Intervalanzahl zurück
     * @return gibt Anzahl der Intervalle zurück
     */
    @Override
    public int getIntervalCount() {
        return intervals.length;
    }

    /**
     * Gibt ein Interval an einem bestimmten Index zurück
     * @param idx Index des Intervals
     * @return gibt Interval an dem Index zurück
     */
    @Override
    public TimeInterval getInterval(int idx) {
        if (idx < 0 || idx > intervals.length - 1) {
            return null;
        }
        return intervals[idx];
    }

    /**
     * Fügt ein neues Element hinzu und liefert das vergrößerte Array zurück.
     *
     * @param ti anzuhängendes Element
     * @return vergrößertes Array
     */
    private TimeInterval[] insertElementAt(TimeInterval ti, int idx) {
        if (idx >= 0 && idx <= intervals.length) {
            if (idx == 0 && intervals.length == 0) {
                TimeInterval[] arr2 = new TimeInterval[1];
                arr2[0] = ti;
                return arr2;
            }
            TimeInterval[] arr2 = new TimeInterval[intervals.length + 1];
            System.arraycopy(intervals, 0, arr2, 0, idx);
            arr2[idx] = ti;
            if (idx < arr2.length - 1) {
                System.arraycopy(intervals, idx, arr2, idx + 1, intervals.length - idx);
            }
            return arr2;
        }
        return intervals;
    }

    /**
     * Fügt ein Zeitintervall hinzu.
     * Beachtet werden müssen die Fälle:
     * <ul>
     *   <li>keine Überschneidung: ein neues Intervall dazu</li>
     *   <li>Überschneidung: bestehendes Intervall erhält andere Grenzen</li>
     *   <li>Überschneidung mit mehreren bestehenden: Intervalle werden zusammengefasst</li>
     * </ul>
     *
     * @param ti Zeitintervall, das hinzugefügt werden soll
     * @return Array mit zugefügtem Zeitintervall
     */
    private TimeInterval[] insert(TimeInterval ti) {
        boolean eingefuegt = false;
        if (intervals.length == 0) {
            intervals = insertElementAt(ti, 0);
        } else {
            for (int i = 0; i < intervals.length; i++) {
                if (intervals[i].contains(ti) || intervals[i].overlaps(ti) || intervals[i].touches(ti)) {
                    ti = intervals[i].union(ti);
                    intervals = removeElementAt(i);
                    i--;
                }
            }
            for (int i = 0; i < intervals.length && !eingefuegt; i++) {
                if (ti.precedes(intervals[i])) {
                    intervals = insertElementAt(ti, i);
                    i++;
                    eingefuegt = true;
                } else if (ti.succeeds(intervals[i]) && i == intervals.length - 1) {
                    intervals = insertElementAt(ti, i + 1);
                    i++;
                    eingefuegt = true;
                }
            }
            if (intervals.length == 0) {
                intervals = insertElementAt(ti, 0);
            }
        }
        return intervals;
    }

    /**
     * Belegt ein Zeitfenster im Kalender
     * @param ti Zeitfenster
     * @return gibt den Listenzeiger zurück
     */
    @Override
    public Calendar seize(TimeInterval ti) {
        this.insert(ti);
        return this;
    }

    /**
     * Entfernt ein Element aus dem Array und liefert das verkleinerte Array.
     *
     * @param idx welches Element entfernt werden soll
     * @return das um das idx-te Element bereinigte Array,
     * ist idx ungültig, wird das unveränderte Array zurückgeliefert
     */
    private TimeInterval[] removeElementAt(int idx) {
        if (idx >= 0 && idx < intervals.length) {
            TimeInterval[] arr2 = new TimeInterval[intervals.length - 1];
            System.arraycopy(intervals, 0, arr2, 0, idx);
            System.arraycopy(intervals, idx + 1, arr2, idx, arr2.length - idx);
            return arr2;
        }
        return intervals;
    }

    /**
     * Gibt einen Zeitraum im Kalender frei.
     * Zu beachten sind die Fälle:
     * <ul>
     *   <li>Zeitraum ist nicht belegt</li>
     *   <li>Zeitraum überschneidet ein oder mehrere bestehende Elemente -> ein oder zwei neue Intervalle entstehen</li>
     *   <li>Zeitraum enthält ein oder mehrere bestehende Elemente</li>
     * </ul>
     *
     * @param ti freizugebender Zeitraum
     * @return bereinigter Kalender
     */
    private TimeInterval[] remove(TimeInterval ti) {
        if (intervals.length == 1) {
            intervals = intervals[0].sub(ti);
        }
        int i = 0;
        while (i < intervals.length && ((intervals[i].sub(ti).length == 0) || (intervals[i].sub(ti).length == 1))) {
            if (intervals[i].contains(ti) || intervals[i].overlaps(ti)) {
                if (intervals[i].sub(ti).length == 0) {
                    intervals = removeElementAt(i);

                } else if (intervals[i].sub(ti).length == 1) {
                    intervals[i] = intervals[i].sub(ti)[0];
                    i++;
                }
            } else {
                i++;
            }
        }
        if (i < intervals.length && intervals[i].sub(ti).length == 2) {
            intervals[i] = intervals[i].sub(ti)[0];
        }
        return intervals;
    }

    /**
     * Gibt ein Zeitfenster im Kalender frei
     * @param ti Zeitfenster
     * @return Zeiger auf die Liste
     */
    @Override
    public Calendar release(TimeInterval ti) {
        this.remove(ti);
        return this;
    }

    /**
     * Löscht alle Kalendereinträge
     * @return Zeiger auf die Liste
     */
    @Override
    public Calendar clear() {
        while (intervals.length != 0) {
            intervals = removeElementAt(0);
        }
        return this;
    }

    /**
     * Stringdarstellung des Kalenders
     * @return Stringdarstellung des Kalenders. Der Kalender beginnt und endet mit "{" bzw. "}"
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("{ ")
                .append(Arrays.toString(intervals))
                .append(" }");
        return result.toString();
    }

}
