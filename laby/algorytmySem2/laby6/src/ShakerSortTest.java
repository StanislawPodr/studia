import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShakerSortTest {

    @Test
    public void testSortAscending() {
        ShakerSort<Integer> shakerSort = new ShakerSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(5, 3, 8, 6, 2, 7, 4, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> result = shakerSort.sort(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSortDescending() {
        ShakerSort<Integer> shakerSort = new ShakerSort<>(Comparator.reverseOrder());
        List<Integer> input = Arrays.asList(5, 3, 8, 6, 2, 7, 4, 1);
        List<Integer> expected = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> result = shakerSort.sort(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSortEmptyList() {
        ShakerSort<Integer> shakerSort = new ShakerSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList();
        List<Integer> expected = Arrays.asList();
        List<Integer> result = shakerSort.sort(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSortSingleElement() {
        ShakerSort<Integer> shakerSort = new ShakerSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(42);
        List<Integer> expected = Arrays.asList(42);
        List<Integer> result = shakerSort.sort(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSortAlreadySorted() {
        ShakerSort<Integer> shakerSort = new ShakerSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = shakerSort.sort(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSortReversedList() {
        ShakerSort<Integer> shakerSort = new ShakerSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = shakerSort.sort(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSortSameValuesRepeated() {
        ShakerSort<Integer> sorter = new ShakerSort<>(Comparator.naturalOrder());
        List<Integer> input = Arrays.asList(7, 7, 7, 7, 6);
        List<Integer> expected = Arrays.asList(6, 7, 7, 7, 7);
        assertEquals(expected, sorter.sort(input));
    }
}