package ueb;

//TODO DONE JavaDoc

/**
 * Klasse für eine leere Liste. Die meisten Methoden nutzen Methoden aus Element, nach der Prüfung, ob sie ein Element
 * hat.
 *
 * @author Anton Schmitter, Joshua-Scott Schoettke, Grp21
 */
public class MyList {


//<editor-fold defaultstate="collapsed" desc="Methoden aus der Vorlesung">
    /**
     * Attribut für die Liste
     */
    private Element elements;

    /**
     * boolean isEmpty() überprüft ob die Liste leer ist.
     *
     * @return true    wenn elements == null ist
     */
    public boolean isEmpty() {
        return elements == null;
    }

    /**
     * void appendElement(int value) bearbeitet die Liste "elements", in dem {value} als letztes Element anhängt wird.
     * sollte die Liste leer sein, wird eine neue erstellt und mit dem Wert initialisiert.
     *
     * @param value Der einzufügende Wert
     */
    public void appendElement(int value) {
        if (this.isEmpty())
            elements = new Element(value, null);
        else
            elements = elements.appendElement(value);
    }

    /**
     * void insertElement(int value) bearbeitet die Liste "elements", in dem {value} als Element sortiert eingefügt
     * wird. Sollte die Liste leer sein, wird eine neue erstellt und mit dem Wert initialisiert.
     *
     * @param value Der einzufügende Wert
     */
    public void insertElement(int value) {
        if (this.isEmpty())
            elements = new Element(value, null);
        else
            elements = elements.insertElement(value);
    }

    /**
     * void deleteElement(int value) bearbeitet die nicht leere Liste "elements", in dem {value} als Element gelöscht
     * wird.
     *
     * @param value Der einzufügende Wert
     */
    public void deleteElement(int value) {
        if (!isEmpty())
            elements = elements.deleteElement(value);
    }

//</editor-fold>

    /**
     * int size() liefert die Anzahl der Listenelemente.
     *
     * @return size    die Anzahl aller Elemente. 0 bei leerer Liste
     */

    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            return this.elements.size();
        }
    }

    /**
     * int sum() liefert die Summe aller Listenelemente.
     *
     * @return sum     die Summe aller Elemente. 0 bei leerer Liste
     */
    public int sum() {
        if (isEmpty()) {
            return 0;
        } else {
            return this.elements.sum();
        }
    }

    /**
     * boolean isSorted() überprüft die Liste "elements" ob diese sortiert ist.
     *
     * @return true    wenn die Liste aufsteigend sortiert ist oder leer.
     */

    public boolean isSorted() {
        if (isEmpty()) {
            return true;
        } else {
            return this.elements.isSorted();
        }
    }

    /**
     * boolean existsElement(int value) bestimmt ob {value} schon in der Liste "elements" vorhanden ist.
     *
     * @param value Der zu überprüfende Wert
     * @return true    wenn bereits ein Element mit {value} in der Liste enthalten ist.
     */
    public boolean existsElement(int value) {
        if (isEmpty()) {
            return false;
        } else {
            return this.elements.existsElement(value);
        }
    }

    /**
     * String toString() bildet einen String mit den Werten aus der Liste "elements"
     *
     * @return string  der die in der Liste enthaltenen Werte darstellt. Jeweils durch ein Leerzeichen getrennt und
     * umschlossen von geschweiften Klammern
     */

    public String toString() {
        if (isEmpty()){
            return "{}";
        }
        return "{" + elements + "}";
    }

    /**
     * int[] toArray() bildet ein Array auf der Basis und Reihenfolge der Liste.
     *
     * @return arrayList   Das erstellte Array.
     */
    public int[] toArray() {
        int[] arrayList = new int[size()];
        for (int i = 0; i < arrayList.length; i++) {
            arrayList[i] = this.elements.getValueAt(i);

        }
        return arrayList;
    }

    /**
     * int getValueAt(int index) gibt den Wert {value} an dem Index zurück.
     *
     * @param index Die Stelle in der Liste
     * @return value   Der Werte der zurückgegeben wird. Wenn der index ungültig ist, wird Integer.MAX_VALUE
     * zurückgegeben.
     */
    public int getValueAt(int index) {
        if (isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return this.elements.getValueAt(index);
        }
    }

    /**
     * void insertElementAt(int value, int index) veränder die Liste "elements" indem ein Wert {value} an der Stelle
     * {index} eingefügt wird. Wenn die Liste leer und der Index = 0 ist, wird eine neue Liste erstellt.
     *
     * @param value Der Wert der eingefügt werden soll
     * @param index Die Stelle an der {value} eingefügt werden soll.
     */
    public void insertElementAt(int value, int index) {
        if (isEmpty() && index == 0) {
            elements = new Element(value, null);
        } else if (!isEmpty()) {
            elements = elements.insertElementAt(value, index);
        }

    }

    /**
     * void prependElement(int value) veränder die Liste "elements" indem ein Wert {value} an den Anfang der Liste
     * eingefügt wird. Wenn die Liste leer ist, wird eine neue Liste erstellt.
     *
     * @param value Der einzufügende Wert
     */
    public void prependElement(int value) {
        if (isEmpty()) {
            elements = new Element(value, null);
        } else {
            elements = elements.prependElement(value);
        }
    }

    /**
     * void insertSortedIfUnique(int value) verändert die Liste "elements", indem ein Wert {value} vor das erste größere
     * Element in der Liste eingefügt wird. Wenn die Liste leer ist, wird eine neue Liste erstellt und mit dem Wert
     * initialisiert.
     *
     * @param value Der einzufügende Wert
     */
    public void insertSortedIfUnique(int value) {
        if (isEmpty()) {
            elements = new Element(value, null);
        } else {
            if (!existsElement(value)) {
                insertElement(value);
            }
        }
    }

    /**
     * void deleteAll(int value) löschte alle Elemente mit dem Wert {value}.
     *
     * @param value Der zu löschende Wert
     */
    public void deleteAll(int value) {
        if (!isEmpty()) {
            elements = elements.deleteAll(value);
        }
    }
}
