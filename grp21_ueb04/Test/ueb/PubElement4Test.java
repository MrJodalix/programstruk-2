package ueb;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests published with assignment 4. All tests have to be passed to pass the assignment.
 *
 * @author cei
 */
public class PubElement4Test {

    @Test
    public void testIsPredecessor_Is() {
        Element el = new Element(5);
        assertTrue(el.isPredecessor(6));
    }

    @Test
    public void testIsEqual() {
        Element el = new Element(5);
        Element elOther = new Element(5);
        assertTrue(el.isEqual(elOther));
    }
}
