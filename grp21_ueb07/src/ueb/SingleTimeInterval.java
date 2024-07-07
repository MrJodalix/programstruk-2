package ueb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingleTimeInterval implements TimeInterval {

    public static SingleTimeInterval ETERNITY = new SingleTimeInterval(PAST_INFINITY, FUTURE_INFINITY, "ewig");
    private final Date start;
    private final Date end;
    private final String caption;

    public SingleTimeInterval(Date start, Date end, String caption) {
        if (start == null) throw new IllegalArgumentException("Startdatum darf nicht null sein");
        if (end == null) throw new IllegalArgumentException("Enddatum darf nicht null sein");
        if (TimeInterval.ge(start, end)) throw new IllegalArgumentException("Startdatum muss vor dem Enddatum liegen");

        this.start = start;
        this.end = end;
        this.caption = caption;
    }

    SingleTimeInterval(String start, String end) throws ParseException {
        if (start == null) throw new IllegalArgumentException("Startdatum darf nicht null sein");
        if (end == null) throw new IllegalArgumentException("Enddatum darf nicht null sein");
        Date dStart = DF.parse(start);
        Date dEnd = DF.parse(end);
        if (TimeInterval.ge(dStart, dEnd)) throw new IllegalArgumentException("Startdatum muss vor dem Enddatum liegen");
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
    @Override
    public Date getStart() {
        return this.start;
    }

    /**
     * Liefert das Ende des Intervalls.
     * rechtsoffenes Intervall: End-Zeitpunkt exklusive
     *
     * @return das Ende des Intervalls
     */
    @Override
    public Date getEnd() {
        return this.end;
    }

    /**
     * Liefert die Bezeichnung des Intervalls.
     *
     * @return die Bezeichnung des Intervalls
     */
    @Override
    public String getCaption() {
        return this.caption;
    }

    /**
     * Liefert die Dauer des Intervalls in Millisekunden.
     *
     * @return die Dauer des Intervalls in Millisekunden,
     * null, wenn eine der Grenzen im Unendlichen (INFINITY) liegt
     */
    @Override
    public Long getDuration() {
        if (end == TimeInterval.FUTURE_INFINITY || start == TimeInterval.PAST_INFINITY) {
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
    @Override
    public boolean succeeds(TimeInterval other) {
        return (TimeInterval.gt(this.start, other.getEnd()) || TimeInterval.eq(other.getEnd(), this.start));
    }

    /**
     * Prüft, ob this vor other liegt.
     * Dafür muss this enden bevor other beginnt.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this vor other liegt
     */
    @Override
    public boolean precedes(TimeInterval other) {
        return (TimeInterval.gt(other.getStart(), this.end) || TimeInterval.eq(this.end, other.getStart()));
    }

    /**
     * Prüft, ob other direkt an this angrenzt.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn other direkt an this angrenzt
     */
    @Override
    public boolean touches(TimeInterval other) {
        return (TimeInterval.eq(this.end, other.getStart()) || TimeInterval.eq(other.getEnd(), this.start));
    }

    /**
     * Prüft, ob this das Datum d enthält.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn this das Datum d enthält, sonst false
     */
    @Override
    public boolean contains(Date d) {
        if (TimeInterval.eq(this.end, d)) {
            return false;
        }
        return (TimeInterval.le(this.start, d) && (TimeInterval.ge(this.end, d)));
    }

    /**
     * Prüft, ob this other komplett enthält oder gleich ist.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this other komplett enthält oder gleiche Grenzen hat, sonst false
     */
    @Override
    public boolean contains(TimeInterval other) {
        return (TimeInterval.le(start, other.getStart()) && TimeInterval.ge(end, other.getEnd()));
    }

    /**
     * Prüft, ob this und other überlappen.
     * Dafür muss this beginnen, bevor other endet und enden, nachdem other startet.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this und other überlappen
     */
    @Override
    public boolean overlaps(TimeInterval other) {
        return (TimeInterval.lt(start, other.getEnd()) && TimeInterval.gt(end, other.getStart()));
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
    @Override
    public TimeInterval intersect(TimeInterval other) {
        SingleTimeInterval singleTimeInterval = null;
        if (overlaps(other)) {
            String caption = "(" + getCaption() + ") intersect (" + other.getCaption() + ")";
            singleTimeInterval = new SingleTimeInterval(TimeInterval.max(this.start, other.getStart()),
                                                        TimeInterval.min(this.end, other.getEnd()), caption);
        }
        return singleTimeInterval;
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
    @Override
    public TimeInterval union(TimeInterval other) {
        SingleTimeInterval singleTimeInterval = null;
        if (overlaps(other) || touches(other)) {
            String caption = "(" + this.caption + ") union (" + other.getCaption() + ")";
            singleTimeInterval = new SingleTimeInterval(TimeInterval.min(this.start, other.getStart()),
                                                        TimeInterval.max(this.end, other.getEnd()), caption);
        }
        return singleTimeInterval;
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
    @Override
    public TimeInterval[] sub(TimeInterval other) {
        TimeInterval[] TimeIntervals = new TimeInterval[]{};
        if (!equals(other) && !(TimeInterval.ge(this.start, other.getStart()) && TimeInterval.le(this.end, other.getEnd()))) {
            TimeIntervals = new SingleTimeInterval[1];
            if (overlaps(other) || contains(other)) {
                if (contains(other) && TimeInterval.ne(this.start, other.getStart()) && TimeInterval.ne(this.end, other.getEnd())) {
                    TimeIntervals = new SingleTimeInterval[2];
                    TimeIntervals[0] = new SingleTimeInterval(this.start, other.getStart(), "");
                    TimeIntervals[1] = new SingleTimeInterval(other.getEnd(), this.end, "");

                } else if (TimeInterval.eq(this.start, other.getStart()) && contains(other) || (TimeInterval.ge(this.start, other.getStart()))) {
                    TimeIntervals[0] = new SingleTimeInterval(other.getEnd(), this.end, "");
                } else if (TimeInterval.eq(this.end, other.getEnd()) && contains(other) || (TimeInterval.le(this.end, other.getEnd()))) {
                    TimeIntervals[0] = new SingleTimeInterval(this.start, other.getStart(), "");
                }
            } else {
                TimeIntervals = new SingleTimeInterval[1];
                TimeIntervals[0] = new SingleTimeInterval(this.start, this.end, "");
            }
        }
        return TimeIntervals;
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
    @Override
    public TimeInterval between(TimeInterval other) {
        SingleTimeInterval singleTimeInterval = null;
        if (!overlaps(other) && !touches(other)) {
            String caption = "(" + this.caption + ") between (" + other.getCaption() + ")";
            singleTimeInterval = new SingleTimeInterval(TimeInterval.min(this.end, other.getEnd()),
                                                        TimeInterval.max(this.start, other.getStart()), caption);
        }
        return singleTimeInterval;
    }

    /**
     * Vergleich der Startpunkte der beiden Intervalle.
     *
     * @param other zu vergleichendes Zeitintervall
     * @return -1, wenn this vor dem übergebenen Intervall beginnt
     * 0, wenn this und other zum gleichen Zeitpunkt starten
     * +1, wenn this nach other beginnt
     */
    @Override
    public int compareTo(TimeInterval other) {
        return this.getStart().compareTo(other.getStart());
    }

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
            return this.start.equals(timeInterval.getStart()) && this.end.equals(timeInterval.getEnd());
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
        return (TimeInterval.isPastInfinity(this.getStart()) ? "oo" : df.format(this.getStart())) +
                " - " +
                (TimeInterval.isFutureInfinity(this.getEnd()) ? "oo" : df.format(this.getEnd()));
    }
}
