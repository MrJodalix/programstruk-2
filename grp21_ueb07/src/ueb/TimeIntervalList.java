package ueb;

/**
 * Interface zur Definition einer Liste von Zeitintervallen.
 * Mit der Aufgabenstellung herausgegeben.
 *
 * @author klk
 */
public interface TimeIntervalList {
    /**
     * Liefert die Nutzlast (Getter).
     * Löst eine {@code RuntimeException} aus, wenn auf die leere Liste angewendet.
     *
     * @return die Nutzlast
     */
    TimeInterval getPayload();

    /**
     * Liefert das nächste Element (Getter).
     * Löst eine {@code RuntimeException} aus, wenn auf die leere Liste angewendet.
     *
     * @return das nächste Element
     */
    TimeIntervalList getNext();

    /**
     * Liefert true, wenn ein Listenelement vorhanden, sonst false.
     *
     * @return true, wenn ein Listenelement vorhanden, sonst false
     */
    boolean isEmpty();

    /**
     * Liefert Länge der Liste.
     *
     * @return die Länge der Liste
     */
    int length();

    /**
     * Fügt das Zeitintervall als neues Listenelement zeitlich sortiert ein.
     * Überschneidet (overlaps) oder berührt (touches) das einzufügende
     * Zeitintervall ti ein in der Liste vorhandenes Zeitintervall, so werden
     * diese beiden vereinigt (union) und in die Liste eingefügt, das vormals
     * vorhandene ausgekettet.
     *
     * @param ti Zeitintervall, das als Element einzufügen ist
     * @return die Liste mit eingefügtem Zeitintervall
     */
    TimeIntervalList insert(TimeInterval ti);

    /**
     * Entfernt ein Zeitintervall aus der Liste. Das Zeitintervall muss nicht
     * mit den gleichen Grenzen in der Liste stehen!
     * Enthält (contains) ti ein Zeitintervall der Liste, so wird dieses
     * komplett entfernt.
     * Überschneidet (overlaps) ti ein Zeitintervall der Liste so entsteht
     * stattdessen (mit sub) ein neues Zeitintervall bei einseitiger Überlappung
     * oder zwei Zeitintervalle bei beidseitiger Überlappung.
     *
     * @param ti freizugebendes Zeitintervall
     * @return die Liste mit freigegebenem Zeitintervall
     */
    TimeIntervalList remove(TimeInterval ti);

    /**
     * Liefert eine Auflistung der enthaltenen Intervalle, wobei die
     * Nutzlasten jeweils mit ", " voneinander getrennt sind.
     *
     * @return in einem String die Aufzählung aller enthaltenen Elemente
     */
    @Override
    String toString();

    /**
     * Prüft, ob das übergebene Objekt auch ein Listenelement ist und
     * gleich diesem ist und eventuelle Folgeelemente gleich sind
     * (nicht mehr und nicht weniger).
     *
     * @param obj zu vergleichendes Objekt
     * @return true, wenn das Objekt ein gleiches Listenelement ist
     *   inklusive seiner eventuell vorhandenen Folgeelemente
     */
    @Override
    boolean equals(Object obj);

}
