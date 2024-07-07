package ueb;

/**
 * TODO DONE Beschreibung der Klasse fehlt
 * Klasse für ein Element einer Liste. In dieser Klasse wird ausschließlich rekursiv gearbeitet.
 *
 * @author Anton Schmitter, Joshua-Scott Schoettke Gruppe 21
 */

public class Element {

//<editor-fold defaultstate="collapsed" desc="Methoden aus der Vorlesung">


    /**
     * Attribute für das Element
     * <p>
     * value der Startwert
     * next  der Rest der Liste
     */

    private int value;
    private Element next;


    /**
     * Konstruktor
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

    public Element appendElement(int value) {
        if (this.next == null)
            this.next = new Element(value, null);
        else
            this.next.appendElement(value);
        return this;
    }

    /**
     * Element insertElement (int value), die eine Zahl in die Liste sortiert einfügt.
     *
     * @param value Die neue Zahl, die eingefügt werden soll
     * @return die neue Liste, "Element", die, die neuen Zahl enthält
     */
    public Element insertElement(int value) {
        if (this.value > value)
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
     * Element deleteElement (int value), die eine Zahl aus der Liste löscht.
     *
     * @param value Die Zahl, die entfernt werden soll
     * @return die neue Liste, "Element", die, die Zahl nicht enthält
     */
    public Element deleteElement(int value) {
        if (this.value == value) {
            return this.next;
        } else if (this.next != null)
            this.next = this.next.deleteElement(value);
        return this;
    }


//</editor-fold>

    /**
     * int size() gibt die Anzahl der Listenelemente aus
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

    /**
     * int sum() berechnet die Summe der Listenelemente
     *
     * @return sum  die Summe der Werte dieses und aller folgenden Elemente. Value wenn next gleich null.
     */
    public int sum() {
        //TODO DONE verändert die Liste
        if (next == null) {
            return value;
        } else {
            return value + this.next.sum();
        }
    }

    /**
     * boolean isSorted() gibt zurück, ob die Liste sortiert aufsteigend ist.
     *
     * @return true    wenn kein Element folgt oder die folgenden Elemente jeweils keinen kleineren Wert enthalten als
     * ihr Vorgänger.
     */
    public boolean isSorted() {
        if (next == null) {
            return true;
        } else if (value <= next.value) {
            return this.next.isSorted();
        } else {
            return false;
        }

    }

    /**
     * boolean existsElement(int value) gibt zurück, ob ein gesuchtes Element in der Liste vorkommt
     *
     * @return true    wenn dieses oder eines der folgenden Elemente den übergebenen Wert enthält.
     */
    public boolean existsElement(int value) {
        if (this.value == value) {
            return true;
        } else {
            if (next == null) {
                return false;
            }
            return this.next.existsElement(value);
        }
    }

    /**
     * String toString() gibt die Liste aus.
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
     * int getValueAt(int index) gibt den value an der Stelle index aus
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
     * Element insertElementAt(int value, int index) fügt an der Stelle {index} den Wert {value} ein
     *
     * @param value Der einzufügende Wert
     * @param index Die Stelle in der Liste an der eingefügt werden soll
     * @return newElement  Wird als Index die Listenlänge übergeben, so wird das neue Element angehängt. Wurde ein
     * falscher Index übergeben, so wird die Liste unverändert zurückgegeben.
     */

    public Element insertElementAt(int value, int index) {
        Element newElement = new Element(this.value, next);
        if (index >= 0) {
            if (index == 0) {
                newElement = prependElement(value);
            } else if (this.next != null) {
                newElement.next = next.insertElementAt(value, index - 1);
            } else {
                if (index == 1) {
                    newElement.appendElement(value);
                }
            }
        }
        return newElement;
    }


    /**
     * Element prependElement(int value) fügt ein neues Element mit dem übergebenen Wert vor das aktuelle Element ein.
     *
     * @param value Der einzufügende Wert
     * @return newElement  Die neue Liste mit dem einzusetzenden Wert
     */

    public Element prependElement(int value) {
        return new Element(value, this);
    }

    /**
     * Element deleteAll(int value) löscht alle Elemente, die den übergebenen Wert enthalten.
     *
     * @param value Der zu löschende Wert
     * @return newElement  Die neue Liste ohne den Wert
     */

    public Element deleteAll(int value) {
        Element newElement = new Element(this.value, next);

        if (existsElement(value)) {
            if (this.value == value) {
                if (next == null) {
                    return deleteElement(value);
                } else {
                    newElement = newElement.deleteElement(value);
                }
            } else {
                if (next != null) {
                    newElement.next = next.deleteAll(value);
                }
            }
        }
        if (existsElement(value)) {
            return newElement.deleteAll(value);
        }
        return newElement;
    }
}
