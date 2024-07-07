package ueb;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TimeIntervalListElement implements TimeIntervalList {

    private TimeInterval payload;
    private TimeIntervalList next;

    public TimeIntervalListElement(TimeInterval ti, TimeIntervalList til) {
        this.payload = ti;
        this.next = til;
    }

    @Override
    public TimeInterval getPayload() {
        return payload;
    }

    @Override
    public TimeIntervalList getNext() {
        return next;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int length() {
        return 1 + this.next.length();
    }

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
    @Override
    public TimeIntervalList insert(TimeInterval ti) {
        if (payload.touches(ti) || payload.overlaps(ti) || payload.contains(ti)) {
            ti = payload.union(ti);
            return this.next.insert(ti);
        } else if (payload.succeeds(ti)){
            TimeIntervalListElement list = new TimeIntervalListElement(ti, this);
            return list;
        }
        this.next = this.next.insert(ti);
        return this;
    }


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

    @Override
    public TimeIntervalList remove(TimeInterval ti) {
        if (payload.contains(ti) || payload.overlaps(ti)) {
            TimeInterval[] til = payload.sub(ti);
            if (til.length == 0) {
                return this.next.remove(ti);
            } else if(til.length == 1){
                this.payload = til[0];
                return this.remove(ti);
            } else if(til.length == 2){
                this.payload = til[0];
                this.next = this.next.insert(til[1]);
                return this.remove(ti);
            }
            return this.next.remove(ti);
        } else {
            this.next = this.next.remove(ti);
            return this;
        }
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
        if (obj instanceof TimeIntervalListElement) {
            TimeIntervalListElement tile = (TimeIntervalListElement) obj;
//            if (payload == null && tile.payload == null) {
//                return next.equals(tile.next);
//            } else if (payload == null || tile.payload == null) {
//                return false;
//            }
            return next.equals(tile.next) &&
                    payload.equals(tile.payload);
        }
        return false;
    }

    /**
     * Liefert eine Auflistung der enthaltenen Intervalle, wobei die
     * Nutzlasten jeweils mit ", " voneinander getrennt sind.
     *
     * @return in einem String die Aufzählung aller enthaltenen Elemente
     */
    @Override
    public String toString() {
        if (next.isEmpty()) {
            return payload.toString();
        }
        return this.payload + ", " + this.next;
    }
}
