package modyfikacja;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TwoWayCycledListWithSentinelTest {
    @Test
    public void testIsPalindrom() {
        TwoWayCycledListWithSentinel<Integer> list = new TwoWayCycledListWithSentinel<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(1);
        assertTrue(list.isPalindrom());
        list.clear();
        list.add(1);
        list.add(2);
        list.add(1);
        assertTrue(list.isPalindrom());
        list.clear();
        list.add(null);
        assertTrue(list.isPalindrom());
        list.clear();
        assertTrue(list.isPalindrom());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertFalse(list.isPalindrom());
        list.clear();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        assertTrue(list.isPalindrom());
        list.clear();
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        assertTrue(list.isPalindrom());
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(1);
        assertFalse(list.isPalindrom());
    }
}