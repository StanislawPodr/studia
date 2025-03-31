import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ListIterator;
import static org.junit.jupiter.api.Assertions.*;

class TwoWayLinkedListTest {

    private TwoWayLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new TwoWayLinkedList<>();
    }

    @Test
    void testAdd() {
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertTrue(list.add(null)); // Adding null
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertNull(list.get(2)); // Checking null
    }

    @Test
    void testAddAtIndex() {
        list.add(0, 1);
        list.add(1, 2);
        list.add(1, 3);
        list.add(0, null); // Adding null at the start
        assertEquals(4, list.size());
        assertNull(list.get(0)); // Checking null
        assertEquals(1, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(2, list.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 4)); // Invalid index
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testContains() {
        list.add(1);
        list.add(2);
        list.add(null); // Adding null
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(null)); // Checking null
        assertFalse(list.contains(3));
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);
        list.add(null); // Adding null
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertNull(list.get(2)); // Checking null

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3)); // Invalid index
    }

    @Test
    void testSet() {
        list.add(1);
        list.add(2);
        list.set(0, 3);
        list.set(1, null); // Setting null
        assertEquals(3, list.get(0));
        assertNull(list.get(1)); // Checking null

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, 4)); // Invalid index
    }

    @Test
    void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(null); // Adding null
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(null)); // Checking null
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null); // Adding null
        assertEquals(2, list.remove(1));
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertNull(list.get(2)); // Checking null

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3)); // Invalid index
    }

    @Test
    void testRemoveByElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null); // Adding null
        assertTrue(list.remove((Integer) 2));
        assertTrue(list.remove(null)); // Removing null
        assertFalse(list.contains(2));
        assertFalse(list.contains(null)); // Checking null removal
        assertEquals(2, list.size());
        assertFalse(list.remove((Integer) 4)); // Non-existent element
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null); // Adding null
        ListIterator<Integer> iterator = list.listIterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertNull(iterator.next()); // Checking null
        assertTrue(iterator.hasPrevious());
        assertEquals(3, iterator.previous());
        iterator.remove();
        iterator.previous();
    }


    @Test
    void testCombinedOperations() {
        // Adding elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null); // Adding null
        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertNull(list.get(3)); // Checking null

        // Adding at specific indices
        list.add(0, 0); // Add at the start
        list.add(2, 5); // Add in the middle
        list.add(6, 6); // Add at the end
        assertEquals(7, list.size());
        assertEquals(0, list.get(0));
        assertEquals(5, list.get(2));
        assertEquals(6, list.get(6));

        // Removing by index
        assertEquals(5, list.remove(2)); // Remove middle element
        assertEquals(6, list.size());
        assertEquals(6, list.remove(5)); // Remove last element
        assertEquals(5, list.size());
        assertEquals(0, list.remove(0)); // Remove first element
        assertEquals(4, list.size());

        // Removing by element
        assertTrue(list.remove((Integer) 2)); // Remove existing element
        assertFalse(list.remove((Integer) 10)); // Try to remove non-existent element
        assertTrue(list.remove(null)); // Remove null
        assertEquals(2, list.size());

        // Setting elements
        list.set(0, 7);
        list.set(1, 8);
        assertEquals(7, list.get(0));
        assertEquals(8, list.get(1));

        // Checking contains
        assertTrue(list.contains(7));
        assertTrue(list.contains(8));
        assertFalse(list.contains(1));

        // Using iterator
        ListIterator<Integer> iterator = list.listIterator();
        assertTrue(iterator.hasNext());
        assertEquals(7, iterator.next());
        assertEquals(8, iterator.next());
        assertFalse(iterator.hasNext());

        // Clearing the list
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testIteratorRemove() {
        list.add(1);
        list.add(2);
        ListIterator<Integer> iterator = list.listIterator();
        iterator.next();
        iterator.remove();
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));

        assertThrows(IllegalStateException.class, iterator::remove); // Removing without next/previous
    }

    @Test
    void testIteratorSet() {
        list.add(1);
        list.add(2);
        ListIterator<Integer> iterator = list.listIterator();
        iterator.next();
        iterator.set(3);
        assertEquals(3, list.get(0));

        iterator.next();
        iterator.set(null); // Setting null
        assertNull(list.get(1)); // Checking null
    }

    @Test
    void testAddEdgeCases() {
        list.add(0, 1); // Add at start
        list.add(1, 2); // Add at end
        list.add(1, 3); // Add in the middle
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
    }
}