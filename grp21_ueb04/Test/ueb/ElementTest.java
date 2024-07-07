package ueb;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElementTest {
    /**
     * Erzeugt ein Element mit übergebenem Wert, bei mehreren Werten werden
     * weitere Elemente angehängt.
     *
     * @param value Wert(e) des Elements/der Elemente
     * @return Element mit Wert, bei mehreren Werten mehrere aneinandergehängte
     * Elemente
     */
    private Element createElements(int... value) {
        if (value.length == 0) {
            return null;
        }
        // ein Element anlegen
        Element el = new Element(value[0]);
        Element firstEl = el; //erstes Element merken

        // weitere Elemente anlegen
        for (int i = 1; i < value.length; i++) {
            el.appendElement(value[i]);
            el = el.getNext();
        }
        return firstEl;
    }

    //----------------------------------------------------------------------------------

    @Test
    public void testIsEqual_Two() {
        Element el = new Element(5);
        Element elOther = new Element(5);
        assertTrue(el.isEqual(elOther));
    }

    @Test
    public void testIsEqual_NOT() {
        Element el = new Element(5);
        Element elOther = new Element(6);
        assertFalse(el.isEqual(elOther));
    }

}
