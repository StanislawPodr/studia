import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

public class MaxBinomialHeapTest {
    // writing unit tests for MaxBinomialHeap

    @Test
    public void testInsertAndFindMax() {
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        assertEquals("Max should be 30 after inserting elements", Integer.valueOf(30), heap.findMax());
    }

    @Test
    public void testMergeHeaps() {
        MaxBinomialHeap<Integer> heap1 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap1.insert(10);
        heap1.insert(20);
        heap1.insert(5);
        heap1.insert(30);
        heap1.insert(25);
        heap1.insert(15);
        heap1.insert(40);
        heap1.insert(35);
        heap1.insert(40);

        MaxBinomialHeap<Integer> heap2 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap2.insert(30);
        heap2.insert(40);
        heap2.insert(15);
        heap2.insert(25);
        heap2.insert(5);
        heap2.insert(35);

        heap1.merge(heap2);

        assertEquals("Max should be 40 after merging heaps", Integer.valueOf(40), heap1.findMax());
    }

    @Test
    public void testEmptyHeap() {
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(Comparator.naturalOrder());
        assert heap.findMax() == null : "Max should be null for an empty heap";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNullElement() {
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap.insert(null); 

    }
    @Test
    public void testRemoveMax() {
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);

        assertEquals("Max should be 30 before removing max", Integer.valueOf(30), heap.extractMax());
        assertEquals("Max should be 20 after removing max", Integer.valueOf(20), heap.findMax());
    }
}
