package ueb;

/**
 * Eine Positionsangabe (Position), die jeweils einen ganzzahligen x- und y-Wert enthält.
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public class Position {
    /**
     Eine Position soll erzeugt werden mit Angabe der beiden Werte oder mit Angabe eines Strings. Der String enthält
     die beiden Werte durch Komma getrennt. Leerzeichen an beliebigen Stellen sollen ignoriert werden. Sollte die
     Stringangabe null sein oder kein Komma enthalten oder nicht genau zwei Werte enthalten, soll jeweils eine
     IllegalArgumentException mit einer aussagekräftigen Fehlermeldung ausgelöst werden.
     */


    /**
     * zwei integer Werte x und y als Instanzvariablen, welche nur innerhalb einer Instanz zur Verfügung stehen.
     */

    private final int x;
    private final int y;

    /**
     * Konstruktor auf zwei int Werten
     *
     * @param x der erste Wert
     * @param y der zweite Wert
     */

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Konstruktor auf String-Basis
     *
     * @param s Der übergebene String der die Werte X und Y getrennt von einem Komma enthält.
     */
    //TODO DONE: Hier werden drei Aussagekräftige Fehlermeldungen erwartet für null, kein Komma, Anzahl Argumente falsch.
    public Position(String s) {
        //Nullcheck
        if (s == null) {
            throw new IllegalArgumentException("Position mit null");
        }
        if (!s.contains(",")){
            throw new IllegalArgumentException("Kein Komma enthalten");
        }
        s = s.replace(" ", "");
        String[] params = s.split(",");
        if (params.length != 2){
            throw new IllegalArgumentException("Anzahl de Paramter ist falsch");
        }

            this.x = Integer.parseInt(params[0]);
            this.y = Integer.parseInt(params[1]);
    }

    /**
     * Eine Getter-Methode für x int getX().
     *
     * @return gibt den übergebenen x-Wert aus.
     */
    public int getX() {
        return x;
    }

    /**
     * Eine Getter-Methode für y int getY().
     *
     * @return gibt den übergegebenen y-Wert aus.
     */

    public int getY() {
        return y;
    }

    /**
     * Eine Position überschreibt die Methode boolean equals(Object obj). Sofern das übergebene Objekt eine Position ist,
     * gilt diese als gleich, wenn sie den gleichen x- und den gleichen y-Wert hat.
     *
     * @param obj die zu vergleichende Position
     * @return Sofern das übergebene Objekt eine Position ist,
     * gilt diese als gleich, wenn sie den gleichen x- und den gleichen y-Wert hat.
     */
    public boolean equals(Object obj) {
        //TODO DONE: Hier instance of nutzen, dies schließt null-Check mit ein, niemals toString nutzen. Strings nur
        //      nutzen wenn  explizit ein Text verglichen werden muss.
        if (obj instanceof Position){
            Position pos = (Position) obj;
            return this.x == pos.x && this.y == pos.y;
        } else {
            return false;
        }
    }


    /**
     * Stringdarstellung des x und y wertes
     *
     * @return String arstellung
     */

    public String toString() {
        return this.x + "," + this.y;

    }
}
