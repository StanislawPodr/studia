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

    @Test
    public void testMultipleInsertions() {
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(Comparator.naturalOrder());
        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
        }
        assertEquals("Max should be 100 after inserting 100 elements", Integer.valueOf(100), heap.findMax());
    }

    @Test
    public void testMergeWithSameSizedHeaps() {
        MaxBinomialHeap<Integer> heap1 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap1.insert(10);
        heap1.insert(20);
        heap1.insert(30);
        MaxBinomialHeap<Integer> heap2 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap2.insert(15);
        heap2.insert(25);
        heap2.insert(35);
        heap1.merge(heap2);
        assertEquals("Max should be 35 after merging heaps of same size", Integer.valueOf(35), heap1.extractMax());
        assertEquals("Max should be 30 after extracting max", Integer.valueOf(30), heap1.extractMax());
        assertEquals("Max should be 25 after extracting max", Integer.valueOf(25), heap1.extractMax());
        assertEquals("Max should be 20 after extracting max", Integer.valueOf(20), heap1.extractMax());
        assertEquals("Max should be 15 after extracting max", Integer.valueOf(15), heap1.extractMax());
        assertEquals("Max should be 10 after extracting max", Integer.valueOf(10), heap1.extractMax());
    }

    @Test
    public void testMergingAfterExtractingMax() {
        MaxBinomialHeap<Integer> heap1 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap1.insert(10);
        heap1.insert(20);
        heap1.insert(30);
        heap1.insert(40);
        Integer max1 = heap1.extractMax();
        assertEquals("Max should be 40 before merging", Integer.valueOf(40), max1);
        MaxBinomialHeap<Integer> heap2 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap2.insert(50);
        heap2.insert(60);
        heap1.merge(heap2);
        assertEquals("Max should be 60 after merging", Integer.valueOf(60), heap1.findMax());
    }

    @Test
    public void testFindMaxAfterMultipleInsertions() {
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(Comparator.naturalOrder());
        for (int i = 1; i <= 50; i++) {
            heap.insert(i * 2);
        }
        assertEquals("Max should be 100 after inserting 50 elements", Integer.valueOf(100), heap.findMax());
    }
}
