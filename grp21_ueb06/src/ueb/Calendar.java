package ueb;


import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import static ueb.TimeInterval.DF;

/**
 * Ein Kalender kann aufgebaut werden, in dem Zeitintervalle
 * von -Unendlich (PAST_INFINITY) bis +Unendlich (FUTURE_INFINITY) belegt werden können.
 * Die Zeitintervalle sind rechts offen, d.h. der angegebene Endpunkt ist exklusiv.
 * <p>
 * Es gibt keine überlappenden oder angrenzenden Intervalle,
 * d.h. besteht z.B. ein Intervall vom 1.1. bis zum 10.1. und
 * wird ein Intervall 5.1.-15.1. zugefügt, so
 * entsteht statt zwei überlappender Intervalle ein Intervall 1.1.-15.1..
 * <p>
 * Die Zeitintervalle werden in einem Array vorgehalten.
 *
 * @author klk
 */
public class Calendar {

    /**
     * Array zur Aufnahme der Zeitintervalle
     */
    private TimeInterval[] intervals;


    /**
     * Konstruktor initialisiert die Instanzvariablen
     */
    public Calendar() {
        intervals = new TimeInterval[]{};
    }

    /**
     * Fügt Zeitintervalle aus den angegebenen Daten zu.
     * Bei einer ungeraden Anzahl von Datumsangaben bleibt
     * das letzte unberücksichtigt.
     * Da dieser Konstruktor nur für Tests genutzt wird, darf davon
     * ausgegangen werden, dass die Daten in korrekter
     * Reihenfolge angegeben werden (Januar vor Februar).
     *
     * @param dates Daten, jeweils zwei bilden ein Zeitintervall
     * @throws ParseException bei fehlerhafter Datumsangabe
     */
    Calendar(String... dates) throws ParseException {
        int j = 0;
        intervals = new TimeInterval[dates.length / 2];
        for (int i = 0; i < intervals.length; i++) {
            TimeInterval newTimeInterval = new TimeInterval(dates[j], dates[j + 1]);
            intervals[i] = newTimeInterval;
            j = j + 2;
        }
    }

    /**
     * Liefert die Anzahl der enthaltenen Zeitintervalle.
     *
     * @return die Anzahl der enthaltenen Zeitintervalle
     */
    public int getIntervalCount() {
        return intervals.length;
    }

    /**
     * Liefert das idx-te Zeitintervall.
     *
     * @param idx Index beginnend bei Null
     * @return das idx-te Zeitintervall; {@code null}, wenn idx ungültig ist
     */
    public TimeInterval getInterval(int idx) {
        if (idx < 0 || idx > intervals.length - 1) {
            return null;
        }
        return intervals[idx];
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
        //TODO DONE wenn sich Zeitintervalle berühren -> auch verschmelzen
        boolean eingefuegt = false;
        if (intervals.length == 0) {
            intervals = insertElementAt(ti, 0);
        } else {
            for (int i = 0; i < intervals.length; i++) {
                //TODO DONE erst raussuchen, ob gegebenes Zeitintervall verschmolzen werden muss -> verschmelzen und dran denken, dass das Ergebnis dann noch mit weiteren, späteren
                // Elementen verschmolzen werden kann (ti verändern)
                // dann Position bestimmen, an der eingefügt werden muss
                // Schleife kann abgebrochen werden, wenn das einzufügende vor dem aktuellen liegt (precedes)
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
    //TODO DONE Index muss verändert werden, wenn etwas gelöscht wird -> vllt while-Schleife
//        for (int i = 0; i < intervals.length; i++) {
    //TODO DONE hat Probleme, weil Ergebnis von sub nicht direkt auf intervals zugewiesen werden darf. TIPP: Erst sub ausführen und dann schauen was das Ergebnis von sub ist
//            if (intervals[i].contains(ti)) {
//                intervals = intervals[i].sub(ti);
//            }
//            if (!intervals[i].contains(ti) && intervals[i].overlaps(ti)) {
//                intervals = intervals[i].sub(ti);
//            }
//            if (intervals[i].contains(ti) && (!(intervals[i].overlaps(ti)))) {
//                intervals = removeElementAt(i);
//            }
//        }
//        return intervals;
//    }

    /**
     * Belegt ein Zeitintervall im Kalender.
     * Obwohl die Instanz nicht verändert wird, sondern nur das enthaltene Array,
     * wird diese Instanz wieder zurückgegeben.
     *
     * @param ti zu belegendes Zeitintervall
     * @return der Kalender mit dem Zeitintervall
     */
    public Calendar seize(TimeInterval ti) {
        this.insert(ti);
        return this;
    }

    /**
     * Gibt ein bestimmtes Zeitintervall im Kalender frei.
     * Obwohl die Instanz nicht verändert wird, sondern nur das enthaltene Array,
     * wird diese Instanz wieder zurückgegeben.
     *
     * @param ti zu entfernendes Zeitintervall
     * @return Kalender ohne das übergebene Zeitintervall
     */
    public Calendar release(TimeInterval ti) {
        this.remove(ti);
        return this;
    }

    /**
     * Löscht alle Termine aus dem Kalender
     * Obwohl die Instanz nicht verändert wird, sondern nur das enthaltene Array,
     * wird diese Instanz wieder zurückgegeben.
     *
     * @return der Kalender ohne Zeitintervalle
     */
    public Calendar clear() {
        while (intervals.length != 0) {
            intervals = removeElementAt(0);
        }
        return this;
    }

    /**
     * Liefert die String-Repräsentation des Kalenders.
     *
     * @return in einem String die Aufzählung aller Kalendereinträge, am Ende das erste freie Intervall
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("{ ")
                .append(Arrays.toString(intervals))
                .append(" }");
        return result.toString();
    }

    /**
     * Prüft auf Gleichheit. Zwei Kalender sind gleich, wenn sie die gleichen
     * Zeitintervalle in der gleichen Reihenfolge enthalten - nicht mehr und nicht weniger.
     *
     * @return true, wenn Kalender gleiche Zeitintervalle enthalten
     */
    @Override
    public boolean equals(Object o) {
        boolean gleich = true;
        if (o instanceof Calendar) {
            Calendar calender = (Calendar) o;
            if (intervals.length == calender.intervals.length) {
                for (int i = 0; i < intervals.length && gleich; i++) {
                    if (!(intervals[i].equals(calender.intervals[i]))) {
                        gleich = false;
                    }
                }
            } else {
                gleich = false;
            }
        } else {
            gleich = false;
        }
        return gleich;
    }
}
