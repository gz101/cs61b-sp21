package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


/**
 * Performs some basic array tests.
 */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("lld1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<Double> ad2 = new ArrayDeque<>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", ad1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", ad1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigArrayDequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    public void getArrayDequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            ad1.addFirst(i);
        }

        assertEquals("Expected to be 0", 0, (int) ad1.get(3));
        assertEquals("Expected to be 1", 1, (int) ad1.get(2));
        assertEquals("Expected to be 2", 2, (int) ad1.get(1));
        assertEquals("Expected to be 3", 3, (int) ad1.get(0));

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            ad2.addLast(i);
        }

        assertEquals("Expected to be 0", 0, (int) ad2.get(0));
        assertEquals("Expected to be 1", 1, (int) ad2.get(1));
        assertEquals("Expected to be 2", 2, (int) ad2.get(2));
        assertEquals("Expected to be 3", 3, (int) ad2.get(3));
    }

    @Test
    public void testAddFirstAndRemoveFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertEquals(3, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeFirst());
        assertEquals(1, (int) deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddLastAndRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(3, (int) deque.removeLast());
        assertEquals(2, (int) deque.removeLast());
        assertEquals(1, (int) deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testSize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assertEquals(0, deque.size());

        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(2, deque.size());

        deque.removeFirst();
        assertEquals(1, deque.size());
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(1, (int) deque.get(0));
        assertEquals(2, (int) deque.get(1));
        assertEquals(3, (int) deque.get(2));
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }

        assertEquals(8, deque.size());

        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }

        assertEquals(16, deque.size());

        for (int i = 0; i < 8; i++) {
            deque.removeFirst();
        }

        assertEquals(8, deque.size());

        // Remove additional elements to trigger resizing to 8
        for (int i = 0; i < 8; i++) {
            deque.removeFirst();
        }

        assertEquals(0, deque.size());
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayDeque<Integer> otherDeque = new ArrayDeque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        otherDeque.addLast(1);
        otherDeque.addLast(2);
        otherDeque.addLast(3);

        assertEquals(deque, otherDeque);

        otherDeque.addLast(4);
        assertNotEquals(deque, otherDeque);
    }
}
