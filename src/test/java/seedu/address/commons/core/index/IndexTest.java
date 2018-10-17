package seedu.address.commons.core.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {
        // invalid index
        assertCreateOneBasedFailure(0);

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {
        // invalid index
        assertCreateZeroBasedFailure(-1);

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    /**
     * Executes {@code Index#fromZeroBased(int)} with {@code invalidZeroBasedIndex}, confirms that an
     * {@code IndexOutOfBoundsException} is thrown.
     */
    private void assertCreateZeroBasedFailure(int invalidZeroBasedIndex) {
        assertCreateFailure(invalidZeroBasedIndex, true);
    }

    /**
     * Executes {@code Index#fromOneBased(int)} with {@code invalidOneBasedIndex}, confirms that an
     * {@code IndexOutOfBoundsException} is thrown.
     */
    private void assertCreateOneBasedFailure(int invalidOneBasedIndex) {
        assertCreateFailure(invalidOneBasedIndex, false);
    }

    /**
     * Executes either {@code Index#fromZeroBased(int)} (if it is zero based), or {@code Index#fromOneBased(int)}
     * (if it is one based), and confirms that an {@code IndexOutOfBoundsException} is thrown.
     */
    private void assertCreateFailure(int invalidIndex, boolean isZeroBased) {
        try {
            if (isZeroBased) {
                Index.fromZeroBased(invalidIndex);
            } else {
                Index.fromOneBased(invalidIndex);
            }
            throw new AssertionError("The expected IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException ie) {
            // expected behaviour
        }
    }

    @Test
    public void toStringTest() {
        final String expected = Index.fromOneBased(5).toString();
        final String actual = "[5]";
        final String actual2 = "[6]";

        // same string (=)
        assertEquals(expected, actual);

        // different string (!=)
        assertNotEquals(expected, actual2);
    }

    @Test
    public void hashCodeTest() {
        final Index fifthPersonIndex = Index.fromOneBased(5);
        final Index sixthPersonIndex = Index.fromOneBased(6);

        Set<Index> expected = new HashSet<>();
        Set<Index> actual = new HashSet<>();
        Set<Index> actual2 = new HashSet<>();

        expected.add(fifthPersonIndex);
        expected.add(sixthPersonIndex);
        actual.add(fifthPersonIndex);
        actual.add(sixthPersonIndex);

        // same set
        assertEquals(expected, actual);

        // different set
        assertNotEquals(expected, actual2);
    }

    @Test
    public void equals() {
        final Index fifthPersonIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertTrue(fifthPersonIndex.equals(Index.fromOneBased(5)));
        assertTrue(fifthPersonIndex.equals(Index.fromZeroBased(4)));

        // same object -> returns true
        assertTrue(fifthPersonIndex.equals(fifthPersonIndex));

        // null -> returns false
        assertFalse(fifthPersonIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthPersonIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthPersonIndex.equals(Index.fromOneBased(1)));
    }
}
