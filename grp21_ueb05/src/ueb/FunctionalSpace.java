package ueb;

/**
 * Für einen speziellen Funktionsraum (FunctionalSpace) sind sowohl Wohn- als auch Nutzfläche 0.
 * Als Nutzung wird immer STAIRWAY gesetzt, die Nutzung wird also nicht über die Konstruktoren angegeben. Beispiel für die Konstruktion über einen String: new FunctionalSpace("1,1 2,5").
 * <p>
 * Das Kürzel für einen Funktionsraum ist "FS".
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 * @version 1.0
 */
//TODO DONE: @Override bei allen Methoden nutzen, bei denen dies sinnvoll ist.
public class FunctionalSpace extends Room {
    /**
     * Konstante mit Kürzel in Form eines Strings zur Repräsentation des Funktionionsraums
     */
    public static final String SHORTCUT = "FS";


    /**
     * Konstruktor um einen Funktionsraum zu erstellen. Seine Nutzung ist immer Stairway
     *
     * @param linkeEcke  die linke obere Ecke
     * @param rechteEcke die rechte untere Ecke
     */
    public FunctionalSpace(Position linkeEcke, Position rechteEcke) {
        super(RoomUsage.STAIRWAY, linkeEcke, rechteEcke);
    }

    /**
     * Konstruktor um einen Funktionsraum über einen String zu erstellen.
     *
     * @param s String zur Erstellung eines Funktionsraumes
     */
    public FunctionalSpace(String s) {
        super(RoomUsage.STAIRWAY.getShortcut() + " " + s);
    }

    /**
     * Getter Methode für den Shortcut eines Funktionsraumes
     *
     * @return Shortcut von Functionalspace ist immer "FS"
     */
    @Override
    //TODO DONE: Hier eine Konstante für den ShortCut einführen und zurückgeben.
    public String getShortcut() {
        return SHORTCUT;
    }

    /**
     * Berechnung der Nutzfläche
     *
     * @return die Nutzfläche ist immer 0
     */
    @Override
    public int calcEffectiveArea() {
        return 0;
    }

    /**
     * Berechnung der Wohnfläche
     *
     * @return Wohnfläche ist immer 0
     */
    //TODO 2 DONE: Auch hier @Override nutzen, da hier die Methode calcLivingArea aus Room überschrieben wird.
    @Override
    public int calcLivingArea() {
        return 0;
    }

}
