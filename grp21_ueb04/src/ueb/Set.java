package ueb;

/**
 * Die Klasse Set stellt eine Menge und die mit dieser Menge möglichen Operationen dar.
 * Um die Elemente der Menge zu fassen, wird ein Attribut vom Typ Element angelegt.
 *
 * @author Anton Schmitter, Joshua-Scott Schoettke Gruppe 21
 */

public class Set {

    /**
     * Attribute aus Element
     */

    public Element elements;

    /**
     * Default-Konstruktor
     */

    public Set() {

    }

    /**
     * Kopier-Konstruktor, der eine neue Menge mit den gleichen Werten wie in der übergebenen Menge erstellt.
     *
     * @param set das Attribut aus der Klasse Element
     */

    public Set (Set set) {
        //TODO DONE null abfangen
        //TODO DONE kopieren, nicht einfach Referenz setzen
        //TODO 2 DONE Sie setzen noch immer einfach die Referenz für diese Elemente auf die Elemente der übergebenen Menge.
        //  Eine Änderung der Elemente in `set` würde so eine Änderung der Elemente in `this` bewirken.
        //  Das ist keine Kopie!

//        if (set != null) {
//            elements = set.elements;
//        }
//        if (set != null) {
//            Set copySet = new Set();
//            copySet.addAll(set.elements);
//        }
//         Set clone = new Set();
//         clone = (Set)set.clone();

        if (set != null && !set.isEmpty()) {
            for (int i = 0; i < set.elements.size(); i++) {
                this.addElement(set.elements.getValueAt(i));
            }
        }
    }
            //TODO 2 DONE was bewirkt diese Anweisung? Nichts! Weg damit.


    /**
     * Variabler-Konstruktor
     *
     * @param elements das Attribut aus der Klasse Element
     */

    Set(int... elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        // this.elements = new Element(elements[0], null);
        for (int i = 0; i < elements.length; i++) {
            addElement(elements[i]);
        }
    }

    /**
     * prüft, ob Elemente enthalten sind
     */

    public boolean isEmpty() {
        return elements == null;
    }

    /**
     * prüft, ob der Wert schon in der Menge existiert.
     *
     * @param value der Wert der geprüft werden soll
     * @return true wenn der Wert existiert, false wenn nicht
     */

    public boolean existsElement(int value) {
        //TODO DONE leere Menge beachten
        if (isEmpty()) {
            return false;
        }
        return this.elements.existsElement(value);

    }

    /**
     * fügt ein neues Element mit übergebenem Wert zu, wenn der Wert noch nicht existiert.
     * Ein Wert kann also nur ein Mal in der Menge vorkommen.
     *
     * @param value der einzufügende Wert
     */
    public void addElement(int value) {
        if (isEmpty()) {
            elements = new Element(value, null);
        } else if (!existsElement(value)) {
            elements = this.elements.insertElement(value);
        }
    }

    /**
     * löscht ein Element dieses Wertes; falls es nicht vorhanden ist, passiert nichts.
     *
     * @param value der zu löschende Wert
     */

    public void deleteElement(int value) {
        if (existsElement(value)) {
            elements = this.elements.deleteElement(value);
        }
    }

    /**
     * prüft, ob die übergebene Menge gleich ist, also genau die gleichen Elemente und nur diese enthält.
     *
     * @param other die zu überprüfende Liste
     * @return true wenn die Liste gleich der übergebenen Liste ist
     */

    public boolean isEqual(Set other) {
        if (other == null) {
            return false;
        }
        if (this.elements == null && other.elements == null) {
            return true;
        }
        if (other.elements == null || this.elements == null) {
            return false;
        }
        return this.elements.isEqual(other.elements);
    }

    /**
     * liefert eine Stringdarstellung des Mengeninhalts mit umschließenden geschweiften Klammern.
     */

    public String toString() {
        if (isEmpty()) {
            return "{}";
        } else {
            return "{" + elements + "}";
        }
    }

    //____________________________________________________________________________

    /**
     * liefert ein Array mit allen enthaltenen Werten.
     * Diese Methode darf ausschließlich in Tests verwendet werden!
     */

    int[] toArray() {
        int[] arrayList = new int[]{};
        if (!isEmpty()) {
            arrayList = new int[this.elements.size()];
            for (int i = 0; i < arrayList.length; i++) {
                arrayList[i] = this.elements.getValueAt(i);
            }
        }
        return arrayList;
    }

    /**
     * fügt alle noch nicht enthaltenen Werte einer übergebenen Liste, als Elemente hinzu. Die übergebene Liste darf
     * nicht verändert werden.
     *
     * @param list Liste mit werten die übertragen werden sollen.
     */


    void addAll(Element list) {
        //TODO DONE kopieren, nicht einfach Referenz setzen
        for (int i = 0; i < list.size(); i++) {
            addElement(list.getValueAt(i));
        }
    }

    /**
     * erstellt eine Kopie dieser Menge.
     */

    Set copy() {
        return new Set(this);
//        Set copyList = new Set();
//        if (!isEmpty()) {
//            int size = this.elements.size();
//            for (int i = 0; i < size; i++) {
//                copyList.addElement(this.elements.getValueAt(i));
//            }
//        }
//        return copyList;
    }

    //____________________________________________________________________________________

    /**
     * liefert eine neue Menge, die die Vereinigung der aktuellen mit der übergebenen abbildet (A ∪ B).
     *
     * @param other die Menge deren Unionsmenge mit dieser überprüft werden soll.
     * @return die Vereinigunsmenge. Wenn other = NULL wird die einfache Liste zurück gegeben.
     */

    public Set union(Set other) {
        Set unionSet = new Set(this);
        if (other == null) {
            //TODO DONE Kopie rausgeben
            return unionSet;
        }

        if (!other.isEmpty()) {
            unionSet.addAll(other.elements);
        }
        return unionSet;
    }

    /**
     * liefert eine neue Menge, die die Schnittmenge der aktuellen mit der übergebenen abbildet (A ∩ B).
     *
     * @param other die Liste deren Schnittmenge mit dieser Menge überprüft werden soll
     * @return die Schnittmenge beider Mengen. Wenn other = NULL wird NULL zurück gegeben.
     */

    public Set intersection(Set other) {
        Set interSet = new Set();
        if (other == null) {
            return interSet;
        }
        if (!other.isEmpty() && !isEmpty()) {
            int size = this.elements.size();
            for (int i = 0; i < size; i++) {
                if (existsElement(other.elements.getValueAt(i)))
                    interSet.addElement(other.elements.getValueAt(i));
            }
        }
        return interSet;
    }


    /**
     * liefert eine neue Menge, die die Differenzmenge der aktuellen mit der übergebenen abbildet (A \ B).
     *
     * @param other die Liste die in der Menge nicht vorhanden sein soll
     * @return die Differenzmenge. Wenn other = NULL wird die einfache Liste zurück gegeben.
     */

    public Set diff(Set other) {
        Set diffSet = this.copy();
        if (other == null) {
            //TODO DONE Kopie rausgeben
            return diffSet;
        }

        if (!other.isEmpty() && !isEmpty()) {
            int size = other.elements.size();
            for (int i = 0; i < size; i++) {
                if (existsElement(other.elements.getValueAt(i)))
                    diffSet.deleteElement(other.elements.getValueAt(i));
            }
        }
        return diffSet;
    }

    /**
     * liefert eine neue Menge, die die symmetrische Differenz von der aktuellen und der
     * gegebenen Menge bildet (A △ B).
     *
     * @param other die Differenzmenge B
     * @return Die symmetrische Menge, die die Werte enthält, die A und B nicht gemeinsam haben. Wenn other = NULL
     * wird die einfache Liste zurück gegeben.
     */

    public Set symmDiff(Set other) {
        Set symSet = this.copy();
        if (other == null) {
            //TODO DONE Kopie rausgeben
            return symSet;
        }

        if (!other.isEmpty() && !isEmpty()) {
            int size = other.elements.size();
            for (int i = 0; i < size; i++) {
                if (!existsElement(other.elements.getValueAt(i))) {
                    symSet.addElement(other.elements.getValueAt(i));
                } else {
                    symSet.deleteElement(other.elements.getValueAt(i));
                }
            }
        } else if (!other.isEmpty()) {
            symSet = other.copy();
        }
//        return this.diff(other).union(other.diff(this));
//        return this.union(other).diff(intersection(other));
        return symSet;
    }

    /**
     * prüft, ob diese Menge eine echte Teilmenge der übergebenen ist (A ⊂ B).
     *
     * @param other die Hauptmenge B
     * @return gibt True zurück wenn die Liste eine echte Teilmenge von der übergebenen Liste ist.
     * False wenn nicht oder other = NULL.
     */

    public boolean isProperSubSet(Set other) {
        boolean isProper = true;
        if (other == null || other.isEmpty()) {
            return false;
        }

        //TODO DONE testen: echte Teilmenge, wenn diese leer ist
        if (isEmpty() && !isEqual(other)) {
            return true;
        }

        if (elements.size() < other.elements.size()) {
            for (int i = 0; i < this.elements.size() && isProper; i++) {
                //FIXME DONE Schleifen dürfen in PS2 nicht mit return verlassen werden
                isProper = other.existsElement(this.elements.getValueAt(i));
            }
        }
        if (elements.size() >= other.elements.size()){
            isProper = false;
        }
        return isProper;
    }
}