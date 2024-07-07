package ueb;

/**
 * Klasse für ein Element einer Liste. In dieser Klasse wird ausschließlich rekursiv gearbeitet.
 *
 * @author Anton Schmitter, Joshua-Scott Schoettke Gruppe 21
 */

public class Element {

    //<editor-fold desc="Element aus Ueb03.">

//<editor-fold defaultstate="collapsed" desc="Methoden aus der Vorlesung">

    /**
     * Konstruktor
     */

    private final int value;
    private Element next;

    /**
     * Attribute für das Element
     *
     * @param value der Startwert
     * @param next  der Rest der Liste
     */

    public Element(int value, Element next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Attribut für das Element
     *
     * @param value der Startwert
     */
    public Element(int value) {
        this.value = value;
    }

    /**
     * Eine Getter-Methode für value int getValue().
     *
     * @return gibt den übergebenen value-Wert aus.
     */
    public int getValue() {
        return value;
    }

    /**
     * Eine Getter-Methode für next Element getNext().
     *
     * @return gibt den übergegebenen next-Wert aus.
     */

    public Element getNext() {
        return next;
    }


    /**
     * Element append (int value), die eine Zahl am Ende der Liste anfügt.
     *
     * @param value Die neue Zahl, die eingefügt werden soll
     * @return die neue Liste "Element", welche die neue Zahl enthält
     */

    Element appendElement(int value) {
        if (this.next == null)
            this.next = new Element(value, null);
        else
            this.next.appendElement(value);
        return this;
    }

    /**
     * Element deleteElement (int value), die eine Zahl aus der Liste löscht.
     * Sucht in einer sortierten Liste.
     *
     * @param value Die Zahl, die entfernt werden soll
     * @return die neue Liste, "Element", die, die Zahl nicht enthält
     */
    public Element deleteElement(int value) {
        //TODO DONE isPredecessor nutzen
        //FIXME DONE mit der Anpassung von isPredecessor() wird das so nicht funktionieren
        if (this.value == value) {
            return this.next;
        } else if (this.next != null && isPredecessor(value)) {
            this.next = this.next.deleteElement(value);

        }
        return this;
    }


//</editor-fold>

    /**
     * gibt die Anzahl der Listenelemente aus
     *
     * @return size    liefert die Anzahl der Elemente. 1 Wenn next gleich null ist.
     */

    public int size() {
        if (next == null) {
            return 1;
        } else {
            return 1 + this.next.size();
        }
    }

//    /**
//     * gibt zurück, ob die Liste sortiert aufsteigend ist.
//     *
//     * @return true    wenn kein Element folgt oder die folgenden Elemente jeweils keinen kleineren Wert enthalten als
//     * ihr Vorgänger.
//     */
//    public boolean isSorted() {
//        if (next == null) {
//            return true;
//        } else if (value <= next.value) {
//            return this.next.isSorted();
//        } else {
//            return false;
//        }
//
//    }

    /**
     * gibt zurück, ob ein gesuchtes Element in der sortierten Liste vorkommt
     *
     * @return true    wenn dieses oder eines der folgenden Elemente den übergebenen Wert enthält.
     */
    public boolean existsElement(int value) {
        //TODO DONE isPredecessor nutzen
        //FIXME DONE mit der Anpassung von isPredecessor() wird das so nicht funktionieren
        if (this.value == value) {
            return true;
        } else if (this.next != null && isPredecessor(value)) {
            return this.next.existsElement(value);
        }
        return false;
    }

    /**
     * gibt die Liste aus
     *
     * @return String  von value und jeweils durch ein Leerzeichen getrennt alle folgenden Werte.
     */
    public String toString() {
        if (next == null) {
            return "" + value;
        }
        return this.value + " " + this.next;
    }

    /**
     * gibt den value an der Stelle index aus
     *
     * @param index ist die gesuchte Stelle in der Liste
     * @return value   des Elements an der Stelle index. Wurde ein falscher Index angegeben, wird Integer.MAX_VALUE
     * zurückgegeben.
     */

    public int getValueAt(int index) {
        if (index == 0) {
            return value;
        }
        if (this.next != null && index > 0) {
            return this.next.getValueAt(index - 1);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Element prependElement(int value) fügt ein neues Element mit dem übergebenen Wert vor das aktuelle Element ein.
     *
     * @param value Der einzufügende Wert
     * @return newElement  Die neue Liste mit dem einzusetzenden Wert
     */

    Element prependElement(int value) {
        return new Element(value, this);
    }

//    /**
//     * Element deleteAll(int value) löscht alle Elemente, aus der sortierten Liste.
//     *
//     * @param value Der zu löschende Wert
//     * @return newElement  Die neue Liste ohne den Wert
//     */
//
//    public Element deleteAll(int value) {
//        Element newElement = new Element(this.value, next);
//
//        if (existsElement(value)) {
//            if (this.value == value) {
//                if (next == null) {
//                    return deleteElement(value);
//                } else {
//                    newElement = newElement.deleteElement(value);
//                }
//            } else {
//                if (next != null) {
//                    newElement.next = next.deleteAll(value);
//                }
//            }
//        }
//        if (existsElement(value)) {
//            return newElement.deleteAll(value);
//        }
//        return newElement;
//    }

    //</editor-fold>


    /**
     * legt die Sortierreihenfolge fest, indem ein uebergebener Wert getestet wird, ob er größer ist als der bereits
     * vorhandene Wert
     *
     * @param value der Übergebene Wert
     * @return true wenn this.value ein Vorgänger vom uebergebenen Wert ist.
     */
    boolean isPredecessor(int value) {
        //FIXME DONE laut Aufgabenstellung und @return-Kommentar muss dieser ein Vorgänger sein und darf somit nicht gleich sein
        return this.value < value;
    }

    /**
     * fügt einen neuen Wert in die Liste sortiert ein, wenn der Wert noch nicht vorhanden ist.
     * Dabei wird isPredecessor verwendet
     *
     * @param value der Wert der Eingefuegt werden soll
     * @return die neue Liste mit dem Eingefuegten Wert.
     */
    public Element insertElement(int value) {
        if (!(isPredecessor(value)))
            return new Element(value, this);
        else if (this.next == null) {
            this.next = new Element(value, null);
            return this;
        } else {
            this.next = this.next.insertElement(value);
            return this;
        }
    }

    /**
     * ueberprueft, ob dieses und das uebergebene Element denselben Wert enthalten und auch alle jeweiligen Folgeelemente
     * jeweils denselben Wertenthalten und nicht mehr oder weniger Elemente folgen.
     *
     * @param other die uebergebene Liste
     * @return true wenn die Liste gleich der übergebenen Liste ist.
     */
    public boolean isEqual(Element other) {
        if (other != null) {
            if (this.value == other.value && this.next == null && other.next == null) {
                return true;
            } else if (this.value == other.value && this.next != null && other.next != null) {
                return this.next.isEqual(other.next);
            }
        }
        return false;
    }
}
