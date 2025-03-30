import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OneWaySquareListTest {

    @Test
    public void testAddAndGet() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    public void testAddAtIndex() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(1, 3);

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    public void testRemoveByIndex() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //list.add(4);

        assertEquals(2, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    public void testRemoveByElement() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove((Integer) 2));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    public void testContains() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    public void testClear() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSet() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(1, 5);
        assertEquals(5, list.get(1));
    }

    @Test
    public void testIndexOf() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testIsEmpty() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        assertTrue(list.isEmpty());

        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testSize() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        assertEquals(0, list.size());

        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    public void testPrint() {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        // This test is for manual verification of the print output
        list.print();
    }
}