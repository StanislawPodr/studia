import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BSTTest {

    @Test
    void testInsertAndSearch() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        assertTrue(bst.search(5));
        assertTrue(bst.search(3));
        assertTrue(bst.search(7));
        assertFalse(bst.search(10));
    }

    @Test
    void testFindMinimum() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);

        assertEquals(1, bst.findMinimum());
    }

    @Test
    void testFindMaximum() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(2);
        bst.insert(10);
        bst.insert(5);
        bst.insert(13);

        assertEquals(13, bst.findMaximum());
    }

    @Test
    void testRemoveElement() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        Integer previous = bst.removeElement(5);
        assertEquals(3, previous); 
        assertFalse(bst.search(5));
    }


    @Test
    void testRemoveElementLargerBST() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(18);

        Integer previous = bst.removeElement(10);
        assertEquals(7, previous); 
        assertFalse(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
    }


    @Test
    void testPrevious() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(6);

        assertEquals(5, bst.previous(6));
        assertNull(bst.previous(3)); 
    }

    @Test
    void testPreviousLargerTree() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(18);

        assertEquals(12, bst.previous(15));
        assertNull(bst.previous(3));
    }

    @Test
    void testInOrderTraversal() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(4);
        bst.insert(2);
        bst.insert(5);
        bst.insert(1);
        bst.insert(3);

        List<Integer> list = new ArrayList<>();
        Executor<Integer, List<Integer>> executor = new ListExecutor<>(list);
        bst.inOrderTraversal(executor);
        assertEquals(List.of(1, 2, 3, 4, 5), executor.getResult());
    }

    @Test
    void testEmptyTree() {
        BST<Integer> bst = new BST<>(Integer::compare);
        assertThrows(IllegalStateException.class, bst::findMinimum);
        assertThrows(IllegalStateException.class, bst::findMaximum);
        assertThrows(IllegalStateException.class, () -> bst.removeElement(5));
        assertThrows(IllegalStateException.class, () -> bst.previous(5));
        assertThrows(IllegalStateException.class, () -> bst.inOrderTraversal(new ListExecutor<>(new ArrayList<>())));
    }

    @Test
    void testInsertNull() {
        BST<Integer> bst = new BST<>(Integer::compare);
        assertThrows(IllegalArgumentException.class, () -> bst.insert(null));
    }

    @Test
    void testSearchNull() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(5);
        assertThrows(IllegalArgumentException.class, () -> bst.search(null));
    }

    @Test
    void testRemoveNull() {
        BST<Integer> bst = new BST<>(Integer::compare);
        bst.insert(5);
        assertThrows(IllegalArgumentException.class, () -> bst.removeElement(null));
    }
}