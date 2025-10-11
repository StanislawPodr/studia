import org.junit.jupiter.api.Test;

import wyklad.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;





public class LinkedListTest {

    @Test
    public void testAddAndGet() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(30);
        list.add(1, 20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testSet() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.set(1, 30);

        assertEquals(30, list.get(1));
    }

    @Test
    public void testRemoveByIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(20, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @Test
    public void testRemoveByElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.remove((Integer) 20));
        assertFalse(list.contains(20));
        assertEquals(2, list.size());
    }

    @Test
    public void testAddFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.addFirst(5);

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
    }

    @Test
    public void testRemoveFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);

        assertEquals(10, list.removeFirst());
        assertEquals(1, list.size());
        assertEquals(20, list.get(0));
    }

    @Test
    public void testRemoveLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);

        assertEquals(20, list.removeLast());
        assertEquals(1, list.size());
        assertEquals(10, list.get(0));
    }

    @Test
    public void testIndexOf() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(1, list.indexOf(20));
        assertEquals(-1, list.indexOf(40));
    }

    @Test
    public void testContains() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);

        assertTrue(list.contains(10));
        assertFalse(list.contains(30));
    }

    @Test
    public void testIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.isEmpty());

        list.add(10);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);

        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testIterator() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        int sum = 0;
        for (int value : list) {
            sum += value;
        }

        assertEquals(60, sum);
    }

    @Test
    public void testGetOutOfBounds() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void testRemoveFromEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();

        assertThrows(NoSuchElementException.class, list::removeFirst);
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    public void testAddNull() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(null);
        list.add(10);

        assertEquals(null, list.get(0));
        assertEquals(10, list.get(1));
        assertTrue(list.contains(null));
    }

    @Test
    public void testRemoveNull() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(null);
        list.add(10);

        assertTrue(list.remove(null));
        assertFalse(list.contains(null));
        assertEquals(1, list.size());
    }

    @Test
    public void testSetNull() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.set(0, null);

        assertEquals(null, list.get(0));
        assertTrue(list.contains(null));
    }

    @Test
    public void testRemoveInvalidIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    public void testSetInvalidIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 20));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 20));
    }

    @Test
    public void testGetInvalidIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void testAddAtIndexInvalid() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 20));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 20));
    }

    @Test
    public void testClearWithNull() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(null);
        list.add(10);

        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
}