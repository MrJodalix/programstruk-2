package ueb;

import java.util.Arrays;

import static ueb.Room.*;

/**
 * Beschreibt eine Wohnung mit variabler Anzahl an Räumen
 *
 * @author Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 * @version 1.0
 */
public class Flat {

    /**
     * Attribut-Array
     */
    Room[] rooms;

    /**
     * Default-Konstruktor
     */
    public Flat() {
        rooms = new Room[]{};
    }

    /**
     * Var-args-Konstruktor der über eine beliebige Anzahl an Räumen eine Wohnung erstellt.
     *
     * @param room die übergebenen Räume für die Wohnung
     */
    public Flat(Room... room) {
        if (room == null) {
            throw new IllegalArgumentException("Kein Raum gegeben.");
        }
        for (Room value : room) {
            add(value);
        }
    }

    /**
     * Konstruktor auf String-Basis
     *
     * @param s der String mit mehreren Räumen
     */
    Flat(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        int laenge1 = s.length();
        int laenge2 = s.replace("\n", "").length();
        int anzahl = laenge1 - laenge2 + 1;
        String copy1 = s;
        String copy2 = s;
        rooms = new Room[anzahl];
        for (int i = 0; i < anzahl; i++) {
            if (copy2.contains("\n")) {
                copy1 = copy2.substring(0, copy2.indexOf("\n"));
                copy2 = copy2.substring(copy2.indexOf("\n") + 1);
            } else {
                copy1 = copy2;
            }
            String bezeichner = copy1.substring(0, 2);
            //TODO DONE: Keine magic Strings nutzen. ShortCut der Räume nutzen.
            switch (bezeichner) {
                case Room.SHORTCUT:
                    rooms[i] = new Room(copy1.substring(3));
                    break;
                case CrawlSpace.SHORTCUT:
                    rooms[i] = new CrawlSpace(copy1.substring(3));
                    break;
                case FunctionalSpace.SHORTCUT:
                    rooms[i] = new FunctionalSpace(copy1.substring(3));
                    break;
                case RoofRoom.SHORTCUT:
                    rooms[i] = new RoofRoom(copy1.substring(3));
                    break;
                default:
                    throw new IllegalArgumentException("Raumart existiert nicht.");
            }
        }
    }

    /**
     * gibt die Anzahl der Räume in der Wohnung wieder.
     *
     * @return anzahl der Räume
     */
    public int getCountOfRooms() {
        if (rooms == null) {
            return 0;
        }
        return rooms.length;
    }

    /**
     * fügt einen Raum der Wohnung hinzu
     *
     * @param room der Raum der hinzugefügt werden soll
     */
    public void add(Room room) {
        if (room == null) {
            throw new IllegalArgumentException();
        }
        if (this.rooms == null) {
            rooms = new Room[]{room};
        } else {
            Room[] copyArray = Arrays.copyOf(rooms, rooms.length + 1);
            copyArray[rooms.length] = room;
            rooms = Arrays.copyOf(copyArray, copyArray.length);
        }
    }

    /**
     * Grundflächenberechnung der Wohnung
     *
     * @return die Grundfläche der Wohnung
     */
    public int calcBaseArea() {
        int grundflaeche = 0;
        for (Room room : rooms) {
            grundflaeche += room.calcBaseArea();
        }
        return grundflaeche;
    }

    /**
     * Nutzflächenberechnung der Wohnung
     *
     * @return die Nutzfläche der Wohnung
     */
    public int calcEffectiveArea() {
        int nutzflaeche = 0;
        for (Room room : rooms) {
            nutzflaeche += room.calcEffectiveArea();
        }
        return nutzflaeche;
    }

    /**
     * Wohnflächenberechnung der Wohnung
     *
     * @return die Wohnfläche der Wohnung
     */
    public int calcLivingArea() {
        int wohnflaeche = 0;
        for (Room room : rooms) {
            wohnflaeche += room.calcLivingArea();
        }
        return wohnflaeche;
    }

    /**
     * gibt die Stringdarstellung der Wohnung mit deren Grund-/Nutz- und Wohnfläche und deren Räume wieder
     *
     * @return gibt die Wohnung mit deren Grund-/Nutz- und Wohnfläche und deren Räume wieder
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(rooms.length + " Räume mit Grundfläche " + calcBaseArea() +
                ", Nutzfläche " + calcEffectiveArea() + " und Wohnfläche " + calcLivingArea() + "\n");
        for (Room room : rooms) {
            sb.append(room).append("\n");
        }
        return sb.toString();
    }

    /**
     * überprüft, ob die aktuelle Wohnung mit dem übergebenen Objekt übereinstimmt
     *
     * @param obj die zu überprüfende Objekt
     * @return true wenn das Objekt gleich der aktuellen Wohnung ist.
     */
    public boolean equals(Object obj) {
        boolean gleich = true;
        if (obj instanceof Flat) {
            Flat flat = (Flat) obj;
            if (rooms.length == flat.rooms.length) {
                for (int i = 0; i < rooms.length && gleich; i++) {
                    if (!(rooms[i].equals(flat.rooms[i]))) {
                        gleich = false;
                    }
                }
            } else {
                gleich = false;
            }
        } else {
            gleich = false;
        }
        return gleich;
    }
}
