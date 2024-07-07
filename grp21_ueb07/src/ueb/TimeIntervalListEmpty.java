package ueb;

public class TimeIntervalListEmpty implements TimeIntervalList{

    @Override
    public TimeInterval getPayload() {
        throw new RuntimeException("Leere Liste");
    }

    @Override
    public TimeIntervalList getNext() {
        throw new RuntimeException("Leere Liste");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public TimeIntervalList insert(TimeInterval ti) {
        return new TimeIntervalListElement(ti, this);
    }

    @Override
    public TimeIntervalList remove(TimeInterval ti) {
        return this;
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
    public boolean equals(Object obj){
        return obj instanceof TimeIntervalListEmpty;
    }
    @Override
    public String toString() {
        return "";
    }
}
