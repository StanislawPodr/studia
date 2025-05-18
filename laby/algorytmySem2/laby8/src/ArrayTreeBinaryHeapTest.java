import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ArrayTreeBinaryHeapTest {

    private ArrayTreeBinaryHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new ArrayTreeBinaryHeap<Integer>(3, Comparator.naturalOrder());
    }

    @Test
    void testAddAndMinimum() {
        assertTrue(heap.add(5));
        assertTrue(heap.add(3));
        assertTrue(heap.add(8));
        assertTrue(heap.add(1));
        assertEquals(1, heap.minimum());
        assertEquals(3, heap.minimum());
        assertEquals(5, heap.minimum());
        assertEquals(8, heap.minimum());
    }

    @Test
    void testAddNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> heap.add(null));
    }

    @Test
    void testMinimumOnEmptyHeapThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> heap.minimum());
    }

    @Test
    void testClear() {
        heap.add(10);
        heap.add(20);
        heap.clear();
        assertThrows(UnsupportedOperationException.class, () -> heap.minimum());
    }

    @Test
    void testAddBeyondCapacity() {
        // For H=3, capacity = 7
        for (int i = 0; i < 10; i++) {
            assertTrue(heap.add(i));
        }
        // Should still return minimum correctly
        assertEquals(0, heap.minimum());
    }

    @Test
    void testConstructorThrowsForInvalidH() {
        assertThrows(IllegalArgumentException.class,
                () -> new ArrayTreeBinaryHeap<Integer>(0, Comparator.naturalOrder()));
    }

    @Test
    void testVeryLongHeap() {
        for (int i = 0; i < 10000; i++) {
            heap.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            assertEquals(i, heap.minimum());
        }
    }

    @Test   
    void testSetComparator() {
        for (int i = 0; i < 10000; i++) {
            heap.add(i);
        }
        heap.setComparator(Comparator.reverseOrder());
        assertEquals(9999, heap.minimum());
    }
}