package ueb;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Interface für ein Zeitintervall.
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
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public interface TimeInterval {

//<editor-fold defaultstate="expanded" desc="Definitionen von Datumsangaben für Unendlich">
    /**
     * ein Datum in fernster Vergangenheit
     */
    Date PAST_INFINITY = new Date(Long.MIN_VALUE);
    /**
     * ein Datum in fernster Zukunft
     */
    Date FUTURE_INFINITY = new Date(Long.MAX_VALUE);

    /**
     * Formatierungsvorschrift für Datumsangaben in einem String
     */
    SimpleDateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
//</editor-fold>

//<editor-fold defaultstate="expanded" desc="Vergleiche von Datumsangaben unter Berücksichtigung von Unendlich">

    /**
     * Vergleicht zwei Datumsangaben: kleiner als
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum kleiner ist als das zweite, sonst false
     */
    static boolean lt(Date l, Date r) {
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
    static boolean gt(Date l, Date r) {
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
    static boolean eq(Date l, Date r) {
        return !lt(l, r) && !gt(l, r);
    }

    /**
     * Vergleicht zwei Datumsangaben: Ungleichheit
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn beide Datumsangaben ungleich sind
     */
    static boolean ne(Date l, Date r) {
        return !eq(l, r);
    }

    /**
     * Vergleicht zwei Datumsangaben: kleiner gleich
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum kleiner oder gleich dem zweiten ist
     */
    static boolean le(Date l, Date r) {
        return !gt(l, r);
    }

    /**
     * Vergleicht zwei Datumsangaben: größer gleich
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return true, wenn das erste Datum größer oder gleich dem zweiten ist
     */
    static boolean ge(Date l, Date r) {
        return !lt(l, r);
    }

    /**
     * Liefert die kleinere der beiden Datumsangaben.
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return die kleinere der beiden Datumsangaben
     */
    static Date min(Date l, Date r) {
        return lt(l, r) ? l : r;
    }

    /**
     * Liefert die größere der beiden Datumsangaben.
     *
     * @param l linkes Datum
     * @param r rechtes Datum
     * @return die größere der beiden Datumsangaben
     */
    static Date max(Date l, Date r) {
        return gt(l, r) ? l : r;
    }

    /**
     * Prüft, ob das frühestmögliche Datum angegeben wurde.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn das frühestmögliche Datum angegeben wurde
     */
    static boolean isPastInfinity(Date d) {
        return d == PAST_INFINITY;
    }

    /**
     * Prüft, ob das spätestmögliche Datum angegeben wurde.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn das spätestmögliche Datum angegeben wurde
     */
    static boolean isFutureInfinity(Date d) {
        return d == FUTURE_INFINITY;
    }
//</editor-fold>

    //--------------------------------------------------------------------------


//<editor-fold defaultstate="expanded" desc="Methoden von TimeInterval unter Verwendung obiger Vergleichsmethoden">


    /**
     * Liefert den Start des Intervalls.
     * rechtsoffenes Intervall: Start-Zeitpunkt inklusive
     *
     * @return der Start des Intervalls
     */
    Date getStart();

    /**
     * Liefert das Ende des Intervalls.
     * rechtsoffenes Intervall: End-Zeitpunkt exklusive
     *
     * @return das Ende des Intervalls
     */
    Date getEnd();

    /**
     * Liefert die Bezeichnung des Intervalls.
     *
     * @return die Bezeichnung des Intervalls
     */
    String getCaption();

    /**
     * Liefert die Dauer des Intervalls in Millisekunden.
     *
     * @return die Dauer des Intervalls in Millisekunden,
     * null, wenn eine der Grenzen im Unendlichen (INFINITY) liegt
     */
    Long getDuration();

    /**
     * Prüft, ob this hinter other liegt.
     * Dafür muss this beginnen, nachdem other beendet ist.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this hinter other liegt
     */
    boolean succeeds(TimeInterval other);

    /**
     * Prüft, ob this vor other liegt.
     * Dafür muss this enden bevor other beginnt.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this vor other liegt
     */
    boolean precedes(TimeInterval other);

    /**
     * Prüft, ob other direkt an this angrenzt.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn other direkt an this angrenzt
     */
    boolean touches(TimeInterval other);

    /**
     * Prüft, ob this das Datum d enthält.
     *
     * @param d zu prüfendes Datum
     * @return true, wenn this das Datum d enthält, sonst false
     */
    boolean contains(Date d);

    /**
     * Prüft, ob this other komplett enthält oder gleich ist.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this other komplett enthält oder gleiche Grenzen hat, sonst false
     */
    boolean contains(TimeInterval other);

    /**
     * Prüft, ob this und other überlappen.
     * Dafür muss this beginnen, bevor other endet und enden, nachdem other startet.
     *
     * @param other zweites Zeitintervall
     * @return true, wenn this und other überlappen
     */
    boolean overlaps(TimeInterval other);

    /**
     * Liefert ein neues Zeitintervall, das der Überschneidung entspricht.
     * Die Benennung des neuen Intervalls entspricht
     * "(" + this.caption + ") intersect (" + other.caption + ")"
     *
     * @param other zweites Zeitintervall
     * @return ein neues Zeitintervall, das der Überschneidung entspricht;
     * {@code null}, wenn keine Überschneidung existiert
     */
    TimeInterval intersect(TimeInterval other);

    /**
     * Liefert ein neues Zeitintervall, welches this und other vereinigt.
     * Die Benennung des neuen Intervalls entspricht
     * "("+ this.caption + ") union (" + other.caption + ")"
     *
     * @param other zu vereinigendes Zeitintervall
     * @return ein neues Zeitintervall, welches this und other vereinigt;
     * {@code null}, wenn this und other weder überlappen noch aneinander grenzen
     */
    TimeInterval union(TimeInterval other);

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
    TimeInterval[] sub(TimeInterval other);

    /**
     * Liefert ein neues Intervall zwischen this und other.
     * Die Benennung des neuen Intervalls entspricht
     * "("+ this.caption + ") between (" + other.caption + ")"
     *
     * @param other zweites Zeitintervall
     * @return ein neues Intervall zwischen this und other
     * {@code null}, wenn die Intervalle überlappen oder angrenzen
     */
    TimeInterval between(TimeInterval other);

    /**
     * Vergleich der Startpunkte der beiden Intervalle.
     *
     * @param other zu vergleichendes Zeitintervall
     * @return -1, wenn this vor dem übergebenen Intervall beginnt
     * 0, wenn this und other zum gleichen Zeitpunkt starten
     * +1, wenn this nach other beginnt
     */
    int compareTo(TimeInterval other);
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
    boolean equals(Object obj);

    /**
     * Liefert dies Intervall in Stringdarstellung.
     *
     * @return dies Intervall in Stringdarstellung;
     * für INFINITY wird "oo" als Repräsentation von unendlich genommen
     */
    @Override
    String toString();

}
