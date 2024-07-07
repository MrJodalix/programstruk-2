package ueb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Die Klasse für ein Zeitintervall.
 * Ein Zeitintervall wird bestimmt durch einen Start- und einen Endzeitpunkt,
 * wobei der Startzeitpunkt zum Intervall gehört, der Endzeitpunkt nicht mehr.
 * <p>
 * Dieser Dezember wird also beschrieben durch 01.12.2020-01.01.2021.
 * <p>
 * Die Benennung des Intervalls ist hier schon ablegbar,
 * wird aber in den bisher zu implementierenden Methoden nicht verwendet.
 * <p>
 * "Die Zukunft beginnt jetzt" bedeutet mit dieser Implementation:
 * Die Vergangenheit wird beschrieben durch PAST_INFINITY bis jetzt,
 * die Zukunft wird beschrieben durch jetzt bis FUTURE_INFINITY.
 *
 * @author cu
 */
public class TimeInterval {

//<editor-fold defaultstate="expanded" desc="Definitionen von Datumsangaben für Unendlich">
    /**
     * ein Datum in fernster Vergangenheit
     */
    final public static Date PAST_INFINITY = new Date(Long.MIN_VALUE);
    /**
     * ein Datum in fernster Zukunft
     */
    final public static Date FUTURE_INFINITY = new Date(Long.MAX_VALUE);

    /**
     * ein Intervall von fernster Vergangenheit bis fernster Zukunft
     */
    final public static TimeInterval ETERNITY = new TimeInterval(PAST_INFINITY, FUTURE_INFINITY, "");

    /**
     * Formatierungsvorschrift für Datumsangaben in einem String
     */
    final public static SimpleDateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
//</editor-fold>

//<editor-fold defaultstate="expanded" desc="Vergleiche von Datumsangaben unter Berücksichtigung von Unendlich">

    /**
     * Vergleicht zwei Datumsangaben: kleiner als
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum kleiner ist als das zweite, sonst false
     */
    public static boolean lt(Date l, Date r) {
        if ((TimeInterval.isPastInfinity(l) && !TimeInterval.isPastInfinity(r))
                || (TimeInterval.isFutureInfinity(r) && !TimeInterval.isFutureInfinity(l))) {
            return true;
        } else if ((TimeInterval.isPastInfinity(r) && !TimeInterval.isPastInfinity(l))
                || (TimeInterval.isFutureInfinity(l) && !TimeInterval.isFutureInfinity(r))) {
            return false;
        } else {
            return l.before(r);
        }
    }

    /**
     * Vergleicht zwei Datumsangaben: größer als
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum größer ist als das zweite, sonst false
     */
    public static boolean gt(Date l, Date r) {
        if ((TimeInterval.isFutureInfinity(l) && !TimeInterval.isFutureInfinity(r))
                || (TimeInterval.isPastInfinity(r) && !TimeInterval.isPastInfinity(l))) {
            return true;
        } else if ((TimeInterval.isFutureInfinity(r) && !TimeInterval.isFutureInfinity(l))
                || (TimeInterval.isPastInfinity(l) && !TimeInterval.isPastInfinity(r))) {
            return false;
        } else {
            return l.after(r);
        }
    }

    /**
     * Vergleicht zwei Datumsangaben: Gleichheit
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn beide Datumsangaben gleich sind
     */
    public static boolean eq(Date l, Date r) {
        return !lt(l, r) && !gt(l, r);
    }

    /**
     * Vergleicht zwei Datumsangaben: Ungleichheit
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn beide Datumsangaben ungleich sind
     */
    public static boolean ne(Date l, Date r) {
        return !eq(l, r);
    }

    /**
     * Vergleicht zwei Datumsangaben: kleiner gleich
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum kleiner oder gleich dem zweiten ist
     */
    public static boolean le(Date l, Date r) {
        return !gt(l, r);
    }

    /**
     * Vergleicht zwei Datumsangaben: größer gleich
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum größer oder gleich dem zweiten ist
     */
    public static boolean ge(Date l, Date r) {
        return !lt(l, r);
    }

    /**
     * Liefert die kleinere der beiden Datumsangaben.
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return die kleinere der beiden Datumsangaben
     */
    public static Date min(Date l, Date r) {
        return lt(l, r) ? l : r;
    }

    /**
     * Liefert die größere der beiden Datumsangaben.
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return die größere der beiden Datumsangaben
     */
    public static Date max(Date l, Date r) {
        return gt(l, r) ? l : r;
    }

    /**
     * Prüft, ob das frühestmögliche Datum angegeben wurde.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn das frühestmögliche Datum angegeben wurde
     */
    public static boolean isPastInfinity(Date d) {
        return d == PAST_INFINITY;
    }

    /**
     * Prüft, ob das spätestmögliche Datum angegeben wurde.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn das spätestmögliche Datum angegeben wurde
     */
    public static boolean isFutureInfinity(Date d) {
        return d == FUTURE_INFINITY;
    }
//</editor-fold>

    //--------------------------------------------------------------------------
    /**
     * Start und Ende Bezeichnung des Zeitintervalls
     */
    private final Date start, end;
    /**
     * Bezeichnung des Zeitintervalls
     */
    private final String caption;

//<editor-fold defaultstate="expanded" desc="Methoden von TimeInterval unter Verwendung obiger Vergleichsmethoden">

    /**
     * Konstruktor für ein nach rechts offenes Intervall
     * (siehe Klassenbeschreibung)
     *
     * @param start   Startdatum
     * @param end     Enddatum
     * @param caption Bezeichnung
     */
    public TimeInterval(Date start, Date end, String caption) {
        if (start == null) throw new IllegalArgumentException("Startdatum darf nicht null sein");
        if (end == null) throw new IllegalArgumentException("Enddatum darf nicht null sein");
        if (ge(start, end)) throw new IllegalArgumentException("Startdatum muss vor dem Enddatum liegen");

        this.start = start;
        this.end = end;
        this.caption = caption;
    }

    /**
     * Konstruktor für ein nach rechts offenes Intervall mit leerer Beschreibung
     * (siehe Klassenbeschreibung)
     *
     * @param start Startdatum
     * @param end   Enddatum
     * @throws IllegalArgumentException wenn einer der Parameter {@code null} ist oder
     *                                  start nicht vor dem Ende liegt
     * @throws ParseException           wenn einer der Strings nicht korrekt formatiert ist gemäß dd.mm.yyyy
     */
    TimeInterval(String start, String end) throws ParseException {
        if (start == null) throw new IllegalArgumentException("Startdatum darf nicht null sein");
        if (end == null) throw new IllegalArgumentException("Enddatum darf nicht null sein");
        Date dStart = DF.parse(start);
        Date dEnd = DF.parse(end);
        if (ge(dStart, dEnd)) throw new IllegalArgumentException("Startdatum muss vor dem Enddatum liegen");
        this.start = dStart;
        this.end = dEnd;
        this.caption = "";
    }

    /**
     * Liefert den Start des Intervalls.
     * rechtsoffenes Intervall: Start-Zeitpunkt inklusive
     *
     * @return der Start des Intervalls
     */
    public Date getStart() {
        return this.start;
    }

    /**
     * Liefert das Ende des Intervalls.
     * rechtsoffenes Intervall: End-Zeitpunkt exklusive
     *
     * @return das Ende des Intervalls
     */
    public Date getEnd() {
        return this.end;
    }

    /**
     * Liefert die Bezeichnung des Intervalls.
     *
     * @return die Bezeichnung des Intervalls
     */
    public String getCaption() {
        return this.caption;
    }

    /**
     * Liefert die Dauer des Intervalls in Millisekunden.
     *
     * @return die Dauer des Intervalls in Millisekunden,
     * null, wenn eine der Grenzen im Unendlichen (INFINITY) liegt
     */
    public Long getDuration() {
        if (end == FUTURE_INFINITY || start == PAST_INFINITY) {
            return null;
        }
        return end.getTime() - start.getTime();
    }

    /**
     * Prüft, ob this hinter other liegt.
     * Dafür muss this beginnen, nachdem other beendet ist.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this hinter other liegt
     */
    public boolean succeeds(TimeInterval other) {
        return (gt(this.start, other.end) || eq(other.end, this.start));
    }

    /**
     * Prüft, ob this vor other liegt.
     * Dafür muss this enden bevor other beginnt.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this vor other liegt
     */
    public boolean precedes(TimeInterval other) {
        return (gt(other.start, this.end) || eq(this.end, other.start));
    }

    /**
     * Prüft, ob other direkt an this angrenzt.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn other direkt an this angrenzt
     */
    public boolean touches(TimeInterval other) {
        return (eq(this.end, other.start) || eq(other.end, this.start));
    }

    /**
     * Prüft, ob this das Datum d enthält.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn this das Datum d enthält, sonst false
     */
    public boolean contains(Date d) {
        if (eq(this.end, d)) {
            return false;
        }
        return (le(this.start, d) && (ge(this.end, d)));
    }

    /**
     * Prüft, ob this other komplett enthält oder gleich ist.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this other komplett enthält oder gleiche Grenzen hat, sonst false
     */
    public boolean contains(TimeInterval other) {
        return (le(start, other.start) && ge(end, other.end));
    }

    /**
     * Prüft, ob this und other überlappen.
     * Dafür muss this beginnen, bevor other endet und enden, nachdem other startet.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this und other überlappen
     */
    public boolean overlaps(TimeInterval other) {
        return (lt(start, other.end) && gt(end, other.start));
    }

    /**
     * Liefert ein neues Zeitintervall, das der Überschneidung entspricht.
     * Die Benennung des neuen Intervalls entspricht
     * "(" + this.caption + ") intersect (" + other.caption + ")"
     *
     * @param other zweites Zeitintervall
     * @return ein neues Zeitintervall, das der Überschneidung entspricht;
     * {@code null}, wenn keine Überschneidung existiert
     */
    public TimeInterval intersect(TimeInterval other) {
        TimeInterval timeInterval = null;
        String caption = "(" + getCaption() + ") intersect (" + other.getCaption() + ")";
        if (overlaps(other)) {
            timeInterval = new TimeInterval(max(this.start, other.start), min(this.end, other.end), caption);
        }
        return timeInterval;
    }

    /**
     * Liefert ein neues Zeitintervall, welches this und other vereinigt.
     * Die Benennung des neuen Intervalls entspricht
     * "("+ this.caption + ") union (" + other.caption + ")"
     *
     * @param other zu vereinigendes Zeitintervall
     * @return ein neues Zeitintervall, welches this und other vereinigt;
     * {@code null}, wenn this und other weder überlappen noch aneinander grenzen
     */
    public TimeInterval union(TimeInterval other) {
        TimeInterval timeInterval = null;
        String caption = "(" + this.caption + ") union (" + other.caption + ")";
        if (overlaps(other) || touches(other)) {
            timeInterval = new TimeInterval(min(this.start, other.start), max(this.end, other.end), caption);
        }
        return timeInterval;
    }

    /**
     * Zieht other aus dem aktuellen Intervall ab.
     * Überlappen die Intervalle nicht, enthält das zurückgegebene Array ein
     * Element, welches dem aktuellen Intervall entspricht.
     * Ist other gleich this, so wird ein leeres Array zurückgegeben.
     * Liegt other mitten in this, entstehen zwei Intervalle, sonst nur eins.
     * Entstehen neue Intervalle, so darf die Benennung leer bleiben.
     *
     * @param other abzuziehendes Zeitintervall
     * @return ein Array mit keinem, ein oder zwei Zeitintervallen, das/die
     * nach Abziehen von other entstehen
     */
    public TimeInterval[] sub(TimeInterval other) {
        //TODO DONE nicht vergessen, dass auch ein leeres Intervall zurückgegeben werden kann
        TimeInterval[] timeIntervals = new TimeInterval[]{};
        if (!equals(other) && !(ge(this.start, other.start) && le(this.end, other.end))) {
            timeIntervals = new TimeInterval[1];
            //TODO DONE overlaps oder contains
            if (overlaps(other) || contains(other)) {
                //TODO DONE Verschachtelte ifs bereinigen, indem als erster Fall der abgehandelt wird, dass zwei Zeitintervalle zurückgegeben werden (-> alle ifs auf einer Ebene)
                if (contains(other) && ne(this.start, other.start) && ne(this.end, other.end)) {
                    timeIntervals = new TimeInterval[2];
                    timeIntervals[0] = new TimeInterval(this.start, other.start, "");
                    timeIntervals[1] = new TimeInterval(other.end, this.end, "");

                } else if (eq(this.start, other.start) && contains(other)) {
                    timeIntervals[0] = new TimeInterval(other.end, this.end, "");
                } else if (eq(this.end, other.end) && contains(other)) {
                    timeIntervals[0] = new TimeInterval(this.start, other.start, "");
                } else if (ge(this.start, other.start)) {
                    timeIntervals[0] = new TimeInterval(other.end, this.end, "");
                } else if (le(this.end, other.end)) {
                    timeIntervals[0] = new TimeInterval(this.start, other.start, "");
                }
                //TODO DONE vermeiden
            } else {
                timeIntervals = new TimeInterval[1];
                timeIntervals[0] = new TimeInterval(this.start, this.end, "");
            }
        }
        //TODO DONE wenn keine Überlappung sollte ein Zeitintervall was gleich zu this ist zurückgegeben werden
        return timeIntervals;
    }

    /**
     * Liefert ein neues Intervall zwischen this und other.
     * Die Benennung des neuen Intervalls entspricht
     * "("+ this.caption + ") between (" + other.caption + ")"
     *
     * @param other zweites Zeitintervall
     * @return ein neues Intervall zwischen this und other
     * {@code null}, wenn die Intervalle überlappen oder angrenzen
     */
    public TimeInterval between(TimeInterval other) {
        TimeInterval timeInterval = null;
        String caption = "(" + this.caption + ") between (" + other.caption + ")";
        if (!overlaps(other) && !touches(other)) {
            timeInterval = new TimeInterval(min(this.end, other.end), max(this.start, other.start), caption);
        }
        return timeInterval;
    }

    /**
     * Vergleich der Startpunkte der beiden Intervalle.
     *
     * @param other zu vergleichendes Zeitintervall
     * @return -1, wenn this vor dem übergebenen Intervall beginnt
     * 0, wenn this und other zum gleichen Zeitpunkt starten
     * +1, wenn this nach other beginnt
     */
    public int compareTo(TimeInterval other) {
        return this.getStart().compareTo(other.getStart());
    }
//</editor-fold>

    /**
     * Liefert true, wenn das übergebene Objekt ein Zeitintervall ist und
     * den gleichen Start- und Endpunkt hat wie this.
     * Damit die allgemeine Methode Object.equals() überschrieben wird,
     * muss der Parameter vom Typ Object sein und in der Methode gecastet werden.
     *
     * @param obj Zeitintervall zum Vergleich
     * @return true, wenn das übergebene Objekt ein Zeitintervall ist und
     * den gleichen Start- und Endpunkt hat wie this
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeInterval) {
            TimeInterval timeInterval = (TimeInterval) obj;
            return this.start.equals(timeInterval.start) && this.end.equals(timeInterval.end);
        }
        return false;
    }

    /**
     * Liefert dies Intervall in Stringdarstellung.
     *
     * @return dies Intervall in Stringdarstellung;
     * für INFINITY wird "oo" als Repräsentation von unendlich genommen
     */
    @Override
    public String toString() {
        final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder result = new StringBuilder()
                .append(TimeInterval.isPastInfinity(this.getStart()) ? "oo" : df.format(this.getStart()))
                .append(" - ")
                .append(TimeInterval.isFutureInfinity(this.getEnd()) ? "oo" : df.format(this.getEnd()));
        return result.toString();
    }

}
