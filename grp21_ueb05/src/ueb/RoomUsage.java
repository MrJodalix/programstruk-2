package ueb;

/**
 * Enum, welches die Nutzungsweisen eines Raumes enthält
 *
 * @author Anton Schmitter, Joshua-Scoot Schöttke, Gruppe 21
 * @version 1.0
 */
public enum RoomUsage {
    HALLWAY("HA"), BATH("BA"), COOK("CO"), LIVE("LI"), WORK("WO"),
    SLEEP("SL"), SAUNA("SA"), STORE("ST"), STAIRWAY("SW");

    /**
     * Setzen Sie passende Modifizierer ein, sodass die Methode ohne eine konkrete
     * Instanz über ihren Klassennamen genutzt werden kann.
     */

    /**
     * Variable für die Abkürzung
     */
    private final String shortcut;

    /**
     * Kontruktor mit der Abkürzungsvariablen
     *
     * @param shortcut Abkürzungsvariable
     */
    RoomUsage(String shortcut) {
        this.shortcut = shortcut;
    }


    /**
     * Getter von der Abkürzung
     *
     * @return gibt die Abkürzung der Raumbezeichner wieder
     */
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Gibt den Enumwert zu der Kurzform Shortcut zurück
     *
     * @param shortcut Abkürzungsvariable
     * @return gibt den Enumwert der Abkürzung zurück
     */
    //TODO DONE: Nur ein Return am Ende.
    //TODO 2 DONE: Das return ist nicht am Ende und wird auch entgegen der Regeln innerhalb einer Schleife genutzt.
    static RoomUsage toRoomUsage(String shortcut) {
        //TODO DONE: Hier kein switch nutzen, das schränkt die Erweiterbarkeit ein. Iterativ arbeiten.
        RoomUsage benutzung = null;
        for (RoomUsage roomUsage : RoomUsage.values()) {
            if (roomUsage.shortcut.equals(shortcut)) {
                benutzung = roomUsage;
            }
        }
        if (benutzung == null) {
            throw new IllegalArgumentException("Das Raumkürzel ist nicht korrekt.");
        }
        return benutzung;
    }
}
