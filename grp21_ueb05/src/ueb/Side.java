package ueb;

/**
 * Enum, dass Seitenangaben enthält.
 *
 * @author Anton Schmitter, Joshua Scott Schöttke, Gruppe 21
 * @version 1.0
 */
public enum Side {
    TOP("TP"), RIGHT("RT"), BOTTOM("BM"), LEFT("LT");

    /**
     * Variable für die Abkürzung
     */
    private final String shortcut;

    /**
     * Kontruktor mit der Abkürzungsvariablen
     *
     * @param shortcut Abkürzungsvariable
     */
    Side(String shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * Getter von der Abkürzung
     *
     * @return gibt die Abkürzung der Seitenangabe wieder
     */
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Gibt an, ob die Seitenangabe links oder rechts ist
     *
     * @return true wenn sie links oder rechts ist
     */
    public boolean isLeftRight() {
        return this == LEFT || this == RIGHT;
    }

    /**
     * Gibt den Enumwert zu der Kurzform Shortcut zurück
     *
     * @param shortcut die Kurzform der Seitenangabe
     * @return gibt Enumwert der Seitenangabe wieder
     */
    //TODO DONE: Nur ein Return am Ende.
    //TODO 2 DONE: Das return ist nicht am Ende und wird auch entgegen der Regeln innerhalb einer Schleife genutzt.
    static Side toSide(String shortcut) {
        Side seite = null;
        for (Side side : Side.values()) {
            if (side.shortcut.equals(shortcut)){
                seite = side;
            }
        }
        if (seite == null) {
            throw new IllegalArgumentException("Das Seitenkürzel ist nicht korrekt.");
        }
        return seite;
    }
}

