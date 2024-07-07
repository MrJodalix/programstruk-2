package ueb;

/**
 * Ein spezieller Raum ist ein Kriechkeller (CrawlSpace). Auch dieser hat wie jeder Raum eine Nutzungsangabe und die
 * beiden Positionsangaben, seine Wohnfläche ist allerdings immer 0.
 * <p>
 * Das Kürzel für einen Kriechkeller ist "CS".
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 * @version 1.0
 */
//TODO DONE: @Override bei allen Methoden nutzen, bei denen dies sinnvoll ist.
public class CrawlSpace extends Room {
    /**
     * Konstante mit Kürzel in Form eines Strings zur Repräsentation des Kriechkellers
     */
    public static final String SHORTCUT = "CS";

    /**
     * Konstruktor zur Erstellung eines Kriechkellers.
     *
     * @param roomUsage  die Raumfunktion
     * @param linkeEcke  die linke obere Ecke
     * @param rechteEcke die rechte untere Ecke
     */
    public CrawlSpace(RoomUsage roomUsage, Position linkeEcke, Position rechteEcke) {
        super(roomUsage, linkeEcke, rechteEcke);
    }

    /**
     * Kontruktor zur Erstellung eines Kriechkellers durch einen gegebenen String.
     *
     * @param s String zur Erstellung eines Kriechkellers
     */
    public CrawlSpace(String s) {
        super(s);
    }

    /**
     * Getter Methode für den Shortcut
     *
     * @return Shortcut ist immer "CS"
     */
    //TODO DONE: Hier eine Konstante für den ShortCut einführen und zurückgeben.
    @Override
    public String getShortcut() {
        return SHORTCUT;
    }

    /**
     * Berechnet die Wohnfläche eines Raumes
     *
     * @return gibt die Wohnfläche als int-Wert zurück
     */
    @Override
    public int calcLivingArea() {
        return 0;
    }

}
