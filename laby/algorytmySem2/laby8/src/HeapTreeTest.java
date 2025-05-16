import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class HeapTreeTest {

    private HeapTree<Integer> heap;

    @BeforeEach
    public void setUp() {
        heap = new HeapTree<>();
    }

    @Test
    public void testAddSingleElement() {
        assertTrue(heap.add(10));
        assertThrows(IllegalArgumentException.class, () -> heap.add(null));
    }

    @Test
    public void testAddMultipleElementsAndMinimumOrder() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);
        heap.add(15);

        assertEquals(3, heap.minimum());
        assertEquals(5, heap.minimum());
        assertEquals(10, heap.minimum());
        assertEquals(15, heap.minimum());
        assertEquals(20, heap.minimum());
    }

    @Test
    public void testMinimumThrowsWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> heap.minimum());
    }

    @Test
    public void testClear() {
        heap.add(1);
        heap.add(2);
        heap.clear();
        assertThrows(NoSuchElementException.class, () -> heap.minimum());
        assertTrue(heap.add(5));
        assertEquals(5, heap.minimum());
    }

    @Test
    public void testHeapPropertyAfterAdditions() {
        heap.add(8);
        heap.add(4);
        heap.add(6);
        heap.add(2);
        heap.add(10);
        heap.add(1);

        assertEquals(1, heap.minimum());
        assertEquals(2, heap.minimum());
        assertEquals(4, heap.minimum());
        assertEquals(6, heap.minimum());
        assertEquals(8, heap.minimum());
        assertEquals(10, heap.minimum());
    }
}