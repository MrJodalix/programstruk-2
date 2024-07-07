package ueb;

/**
 * Ein weiterer spezieller Raum ist ein Dachraum (RoofRoom). Er kennt zusätzlich auch die Seite (Side), die sich zur
 * Dachrinne neigt und in welcher Distanz zur Dachrinne (Ganzzahlwert) der Raum eine Höhe von einem Meter erreicht.
 * <p>
 * Werden im Konstruktor mit String als Parameter zu wenig oder zu viel Parameter angegeben, soll eine
 * IllegalArgumentException mit aussagekräftiger Fehlermeldung erfolgen.
 * <p>
 * Während die Nutzfläche auch hier der Grundfläche entspricht, wird die Wohnfläche bis zu einer Höhe von einem Meter
 * gar nicht, zwischen 1m und 2m Höhe zur Hälfte und erst ab 2m Höhe voll zur Wohnfläche gezählt. Hilfe zur Berechnung
 * folgt.
 * <p>
 * Das Kürzel für einen Dachraum ist "RR".
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 * @version 1.0
 */
//TODO DONE: JavaDoc für alle Attribute und Methoden schreiben.
//TODO DONE: @Override bei allen Methoden nutzen, bei denen dies sinnvoll ist.
public class RoofRoom extends Room {
    /**
     * Konstante mit Kürzel in Form eines Strings zur Repräsentation des Dachraums
     */
    public static final String SHORTCUT = "RR";

    /**
     * Attribut für die Dachschräge
     */
    private final Side side;

    /**
     * Distanz zu einem Meter Höhe
     */
    //TODO DONE: Sprechenden Bezeichner wählen.
    private final int distToOneMeter;


    /**
     * @param roomUsage      die Raumfunktion
     * @param linkeEcke      linke obere Ecke des Raumes
     * @param rechteEcke     rechte untere Ecke des Raumes
     * @param side           die Seite der Dachschrägen
     * @param distToOneMeter Distanz zu einem Meter Höhe der Dachschrägen
     */
    public RoofRoom(RoomUsage roomUsage, Position linkeEcke, Position rechteEcke, Side side, int distToOneMeter) {
        super(roomUsage, linkeEcke, rechteEcke);
        this.side = side;
        this.distToOneMeter = distToOneMeter;
    }

    /**
     * Konstruktor zur Erstellung eines RoofRooms über einen String
     *
     * @param s String zur Erstellung eines RoofRooms
     */
    public RoofRoom(String s) {
        super(s.substring(0, s.substring(0, s.lastIndexOf(" ")).lastIndexOf(" ")));
        String copy = s.substring(3);
        copy = copy.substring(copy.indexOf(" ") + 1);
        copy = copy.substring(copy.indexOf(" ") + 1);

        this.side = Side.toSide(copy.substring(0, copy.indexOf(" ")));
        this.distToOneMeter = Integer.parseInt(copy.substring(copy.indexOf(" ") + 1));
    }

    /**
     * Getter Methode für den Shortcut
     *
     * @return Shortcut für RoofRoom
     */
    //TODO DONE: Hier eine Konstante für den ShortCut einführen und zurückgeben.
    @Override
    public String getShortcut() {
        return SHORTCUT;
    }

    /**
     * Berechnet die WOhnfläche im RoofRoom
     *
     * @return gibt WOhnfläche des RoofRooms wieder
     */
    //TODO DONE: Hier darf nicht einfach stumpf mit der Distanz zu einem Meter Höhe gerechnet werden. Wird ein Raum
    //      niemals 1 Meter hoch, sollte er eine Wohnfläche von 0 haben, aber nicht negativ werden. Wird ein Raum
    //      einen Meter hoch, aber niemals 2 Meter hoch, so muss die Hälfte der Fläche zwischen 1 und 2 Meter
    //      (Raumende) berechnet werden.
    //TODO DONE: Redundanten Code vermeiden. Seitenlängen nur einmal ausrechnen und ggf. tauschen bzw. auf nicht
    //      seitenorientierte Variablen zuweisen.
    //TODO 2 DONE: Berechnung für Räume, die einen Meter hoch, aber niemals zwei Meter hoch werden ist weiterhin nicht
    //        korrekt. Ist solch ein Raum nicht quadratisch wird als LivingArea 0 berechnet, auch wenn der Raum
    //        eigentlich eine LivingArea > 0 haben müsste.
    //FIXME DONE: Redundanten Code (dies schließt auch sehr ähnlichen Code mit ein) vermeiden. Der Code im
    //       if (this.side.isLeftRight()) ... else... ist nahezu identisch. Hier muss es reichen zunächst nur abhängig
    //       von isLeftRight len und width zu tauschen oder neuen Variablen wie z.B. roofSide und otherSide zuzuweisen.
    //       Die Berechnung kann dann einmal erfolgen und muss nicht für die beiden möglichen Seitenfälle wiederholt
    //       werden.
    @Override
    public int calcLivingArea() {
        int length = getPosBR().getX() - getPosTL().getX();
        int width = getPosBR().getY() - getPosTL().getY();
        int area = calcBaseArea();
        int gutterSide = length;
        int otherSide = width;

        //Wenn die Decke trotzdem überall mind. 2 Meter hoch ist.
        if (distToOneMeter == 0) {
            return area;
        }

        //Wechsel der Eigenschaften
        if (this.side.isLeftRight()) {
            gutterSide = width;
            otherSide = length;
        }

        //Wenn die Distanz zu einem Meter Höhe schon größer als die breite ist
        if (distToOneMeter >= otherSide) {
            return 0;
        } else {

            if (distToOneMeter * 2 >= otherSide){
                area -= (gutterSide * distToOneMeter) + ((gutterSide * (otherSide - distToOneMeter)) / 2);
            } else {
                area -= (gutterSide * distToOneMeter) + ((gutterSide * distToOneMeter) / 2);
            }
        }
        //Sollte eigentlich nicht eintreten.
        //Wenn die Grundfläche wegen der subtraktion kleiner als Null wird.
        if (area < 0){
            area = 0;
        }
        return area;
    }

    /**
     * Gibt die Darstellung als String wieder
     *
     * @return Stringdarstellung vom Konstruktor mit Kürzel vorne dran
     */
    @Override
    //TODO 2 DONE: Auch hier @Override nutzen, da hier die Methode calcLivingArea aus Room überschrieben wird.
    public String toString() {
        return super.toString() + " " + this.side.getShortcut() + " " + this.distToOneMeter;
    }
}
