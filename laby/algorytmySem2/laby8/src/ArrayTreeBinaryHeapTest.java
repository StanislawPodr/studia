import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;

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

    @Test
    void testHotLevelReturnsCorrectLevelForSmallHeap() {
        heap.add(10);
        heap.add(20);
        heap.add(30);
        heap.add(40);
        heap.add(50);
        heap.add(60);
        heap.add(70);
        // Heap tree for H=3 (capacity=7): [10,20,30,40,50,60,70]
        // Level sums: [10], [20+30], [40+50+60+70] = [10], [50], [220]
        // So, hotLevel should return [40, 50, 60, 70]
        assertEquals(List.of(40, 50, 60, 70), ArrayTreeBinaryHeap.hotLevel(heap));
    }

    @Test
    void testHotLevelThrowsOnEmptyHeap() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTreeBinaryHeap.hotLevel(heap));
    }

    @Test
    void testHotLevelThrowsOnNullHeap() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTreeBinaryHeap.hotLevel(null));
    }

    @Test
    void testHotLevelWithSingleElement() {
        heap.add(42);
        assertEquals(List.of(42), ArrayTreeBinaryHeap.hotLevel(heap));
    }

    @Test
    void testHotLevelWithMultipleLevelsHavingSameSum() {
        heap.add(5);  // root
        heap.add(3);  // left
        heap.add(7);  // right
        // Levels: [5], [3,7] -> sums: 5, 10
        assertEquals(List.of(5, 7), ArrayTreeBinaryHeap.hotLevel(heap));
    }

    @Test
    void testHotLevelWithMoreThanCapacity() {
        // For H=3, capacity=7, add more than 7 elements
        for (int i = 0; i <= 15; i++) {
            heap.add(i);
        }
        // Just check it does not throw and returns a non-empty list
        List<Integer> result = ArrayTreeBinaryHeap.hotLevel(heap);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result, List.of(7, 8, 9, 10, 11, 12, 13, 14));
    }

    @Test
    void testHotLevelWithNegativeValues() {
        heap.add(-10);  // root
        heap.add(-5);  // left
        heap.add(-7);  // right
        // Levels: [5], [3,7] -> sums: 5, 10
        assertEquals(List.of(-10), ArrayTreeBinaryHeap.hotLevel(heap));
    }

    @Test
    void testHotLevelWithMoreThanCapacityNegativeValues() {
        // For H=3, capacity=7, add more than 7 elements
        for (int i = 1; i <= 15; i++) {
            heap.add(-i);
        }
        // Just check it does not throw and returns a non-empty list
        List<Integer> result = ArrayTreeBinaryHeap.hotLevel(heap);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result, List.of(-15));
    }

}