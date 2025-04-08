import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectionSortTest {

    @Test
    public void testSortSameValuesRepeated() {
        SelectionSort<Integer> selectionSort = new SelectionSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(7, 7, 7, 7, 6);
        List<Integer> expected = Arrays.asList(6, 7, 7, 7, 7);
        assertEquals(expected, selectionSort.sort(input));
    }

    @Test
    public void testSortIntegers() {
        SelectionSort<Integer> selectionSort = new SelectionSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(5, 3, 8, 6, 2);
        List<Integer> expected = Arrays.asList(2, 3, 5, 6, 8);
        assertEquals(expected, selectionSort.sort(input));
    }

    @Test
    public void testSortStrings() {
        SelectionSort<String> selectionSort = new SelectionSort<>(Comparator.naturalOrder());
        List<String> input = Arrays.asList("banana", "apple", "cherry", "date");
        List<String> expected = Arrays.asList("apple", "banana", "cherry", "date");
        assertEquals(expected, selectionSort.sort(input));
    }

    @Test
    public void testSortEmptyList() {
        SelectionSort<Integer> selectionSort = new SelectionSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList();
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, selectionSort.sort(input));
    }

    @Test
    public void testSortSingleElement() {
        SelectionSort<Integer> selectionSort = new SelectionSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(42);
        List<Integer> expected = Arrays.asList(42);
        assertEquals(expected, selectionSort.sort(input));
    }

    @Test
    public void testSortDescendingOrder() {
        SelectionSort<Integer> selectionSort = new SelectionSort<>(Comparator.reverseOrder());
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(expected, selectionSort.sort(input));
    }
}