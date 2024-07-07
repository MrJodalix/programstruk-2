package ueb;

/**
 * Abstrakte Klasse, die einen Kalender darstellt, der durch eine übergebene Caption eine Beschriftung erhält.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public abstract class Calendar {

    /**
     * Attribut für den Namen des Kalenders
     */
    private String caption;

    /**
     * Konstruktor für die Caption
     * @param cap die Caption
     */
    public Calendar(String cap){
        caption = cap;
    }
    /**
     * Getter Methode für Caption
     * @return
     */
    public String getCaption(){
        return caption;
    }
    /**
     * Liefert die Anzahl der enthaltenen Zeitintervalle.
     */
    public abstract int getIntervalCount();

    /**
     * Liefert das idx-te Zeitintervall.
     */
    public abstract TimeInterval getInterval(int idx);


    /**
     * Belegt ein Zeitintervall im Kalender.
     */
    public abstract Calendar seize(TimeInterval ti);

    /**
     * Gibt ein bestimmtes Zeitintervall im Kalender frei.
     */
    public abstract Calendar release(TimeInterval ti);

    /**
     * Löscht alle Termine aus dem Kalender
     */
    public abstract Calendar clear();

    /**
     * Liefert die String-Repräsentation des Kalenders.
     */
    @Override
    public abstract String toString();

    /**
     * Prüft auf Gleichheit. Zwei Kalender sind gleich, wenn sie gleiche Zeitintervalle enthalten.
     * @return true, wenn Kalender gleiche payloads haben
     */
    @Override
    public boolean equals(Object o) {
        boolean gleich = true;
        if (o instanceof Calendar) {
            Calendar listCalendar = (Calendar) o;
    //TODO DONE Schleife und getInterval() nutzen
            if (!(this.getIntervalCount() == listCalendar.getIntervalCount())){
                return false;
            }
            for (int i = 0; i < this.getIntervalCount() ; i++) {
                if (!(this.getInterval(i).equals(listCalendar.getInterval(i)))){
                    gleich = false;
                }
            }
        } else {
            gleich = false;
        }
        return gleich;
    }
}
