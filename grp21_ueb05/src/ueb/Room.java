package ueb;

/**
 * Ein Raum bietet in einer Konstanten (also public static) ein Kürzel (SHORTCUT) in Form eines Strings an
 * und eine Methode, die das Kürzel für diesen Raum zurückgibt. Für einen normalen Raum ist dies "RO".
 * Ein Raum soll durch Angabe seiner Nutzung (RoomUsage), der Position seiner linken oberen Ecke und der
 * Position seiner rechten unteren Ecke erzeugt werden können.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 * @version 1.0
 */
public class Room {

    /**
     * Konstante mit Kürzel in Form eines Strings zur Repräsentation eines Raums
     */

    public static final String SHORTCUT = "RO";
    //TODO DONE: Attribute beschreiben
    private final RoomUsage roomUsage; //die Raumbenutzung
    private final Position linkeEcke; // die linke obere Ecke
    private final Position rechteEcke; // die rechte untere Ecke

    /**
     * Ein Raum (Room) soll erzeugt werden können mit Angabe seiner Nutzung (RoomUsage), der Position seiner
     * linken oberen Ecke und der Position seiner rechten unteren Ecke.
     * Eine IllegalArgumentException mit einer aussagekräftigen Fehlermeldung wird ausgelöst,
     * wenn die rechte untere Ecke nicht weiter rechts und weiter unten ist als die linke obere Ecke.
     *
     * @param roomUsage  Raumfunktion
     * @param linkeEcke  linke obere Ecke
     * @param rechteEcke rechte untere Ecke
     */
    public Room(RoomUsage roomUsage, Position linkeEcke, Position rechteEcke) {
        if (!(rechteEcke.getX() > linkeEcke.getX() && rechteEcke.getY() > linkeEcke.getY())) {
            throw new IllegalArgumentException("Ein Raum mit diesen Koordinaten kann nicht existieren!");
        }
        this.roomUsage = roomUsage;
        this.linkeEcke = linkeEcke;
        this.rechteEcke = rechteEcke;
    }

    /**
     * Konstruktor für einen Raum über einen gegebenen String. Der String enthält dafür die Parameterangaben in
     * Kurzform durch Leerzeichen getrennt (Bsp.: "BA 0,0 3,4"). Eine IllegalArgumentException mit einer
     * aussagekräftigen Fehlermeldung wird ausgelöst, wenn null übergeben wird oder wenn nicht genau 3
     * Parameter enthalten sind oder wenn die rechte untere Ecke nicht weiter rechts und weiter unten
     * ist als die linke obere Ecke.
     *
     * @param s Stringdarstellung des zu erstellenden Raumes
     */
    Room(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String ist NULL");
        }
        if (s.length() - s.replace(" ", "").length() != 2) {
            throw new IllegalArgumentException("Es sind nicht genau 3 Parameter enthalten");
        }
        String copyString;
        copyString = s.substring(3);
        if (s.length() == s.replaceAll("[^0-9],", "").length()) {
            int x1 = Integer.parseInt(copyString.substring(0, copyString.indexOf(",")));
            int y1 = Integer.parseInt(copyString.substring(copyString.indexOf(",") + 1, copyString.indexOf(" ")));
            copyString = copyString.substring(copyString.indexOf(" "));
            int x2 = Integer.parseInt(copyString.substring(copyString.indexOf(" ") + 1, copyString.indexOf(",")));
            int y2 = Integer.parseInt(copyString.substring(copyString.indexOf(",") + 1));
            if (!(x2 > x1 && y2 > y1)) {
                throw new IllegalArgumentException("Ein Raum mit diesen Koordinaten kann nicht existieren!");
            }
            this.roomUsage = RoomUsage.toRoomUsage(s.substring(0, 2));
            this.linkeEcke = new Position(x1, y1);
            this.rechteEcke = new Position(x2, y2);
        } else {
            throw new IllegalArgumentException("Die Parameter sind nicht korrekt.");
        }
    }


    /**
     * Getter Methode für die RoomUsage
     *
     * @return gibt die Roomusage zurück
     */
    //TODO DONE: Hier nicht den Umweg über toString gehen, direkt auf die Attribute zugreifen.
    public RoomUsage getRoomUsage() {
        return this.roomUsage;
    }

    /**
     * Getter Methode für die obere Linke Ecke
     *
     * @return gibt die Position für die Obere linke Ecke wieder
     */
    //TODO DONE : Hier nicht den Umweg über toString gehen, direkt auf die Attribute zugreifen.
    public Position getPosTL() {
        return this.linkeEcke;
    }

    /**
     * Getter Methode für die untere rechte Ecke
     *
     * @return gibt die Position für die untere rechte Ecke wieder
     */
    //TODO DONE: Hier nicht den Umweg über toString gehen, direkt auf die Attribute zugreifen.
    public Position getPosBR() {
        return this.rechteEcke;
    }

    /**
     * Getter für den Shortcut
     *
     * @return Shortcut als String
     */
    public String getShortcut() {
        return SHORTCUT;
    }

    /**
     * Berechnet die Grundfläche eines Raumes
     *
     * @return gibt die Grundfläche als int-Wert zurück
     */
    public int calcBaseArea() {
        return ((getPosBR().getX() - getPosTL().getX()) * (getPosBR().getY() - getPosTL().getY()));
    }

    /**
     * Berechnet die Nutzfläche eines Raumes
     *
     * @return gibt die Nutzfläche als int-Wert zurück
     */
    public int calcEffectiveArea() {
        return calcBaseArea();
    }

    /**
     * Berechnet die Wohnfläche eines Raumes
     *
     * @return gibt die Wohnfläche als int-Wert zurück
     */
    public int calcLivingArea() {
        return calcBaseArea();
    }

    /**
     * Gibt die Darstellung als String wieder
     *
     * @return Stringdarstellung vom Konstruktor mit Kürzel vorne dran
     */
    public String toString() {
        return getShortcut() + " " + this.roomUsage.getShortcut() + " " + this.linkeEcke + " " + this.rechteEcke;
    }

    /**
     * Jeder Raum überschreibt die Methode boolean equals(Object obj). Sofern das übergebene Objekt ein Raum ist, gilt
     * dieser als gleich, wenn er die gleiche Nutzung hat und die gleiche Wohnfläche oder die gleiche Nutzfläche.
     *
     * @param obj das Objekt, mit dem verglichen werden soll
     * @return true wenn das Objekt gleich dem Raum ist, false wenn nicht
     */
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room room = (Room) obj;
            return (this.roomUsage.getShortcut().equals(room.roomUsage.getShortcut()) && (
                    this.calcLivingArea() == room.calcLivingArea() ||
                            this.calcEffectiveArea() == room.calcEffectiveArea()));
        }
        return false;
    }
}
