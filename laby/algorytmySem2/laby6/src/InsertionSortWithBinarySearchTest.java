import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertionSortWithBinarySearchTest {

    @Test
    public void testSortIntegers() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 8, 6, 2, 7, 4, 1));
        InsertionSortWithBinarySearch<Integer> sorter = new InsertionSortWithBinarySearch<Integer>(Comparator.naturalOrder());

        List<Integer> sortedList = sorter.sort(list);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), sortedList);
    }

    @Test
    public void testSortStrings() {
        List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));
        InsertionSortWithBinarySearch<String> sorter = new InsertionSortWithBinarySearch<String>(Comparator.naturalOrder());

        List<String> sortedList = sorter.sort(list);

        assertEquals(Arrays.asList("apple", "banana", "cherry", "date"), sortedList);
    }

    @Test
    public void testSortEmptyList() {
        List<Integer> list = new ArrayList<>();
        InsertionSortWithBinarySearch<Integer> sorter = new InsertionSortWithBinarySearch<Integer>(Comparator.naturalOrder());

        List<Integer> sortedList = sorter.sort(list);

        assertEquals(new ArrayList<>(), sortedList);
    }

    @Test
    public void testSortSingleElementList() {
        List<Integer> list = new ArrayList<>(List.of(42));
        InsertionSortWithBinarySearch<Integer> sorter = new InsertionSortWithBinarySearch<Integer>(Comparator.naturalOrder());

        List<Integer> sortedList = sorter.sort(list);

        assertEquals(List.of(42), sortedList);
    }

    @Test
    public void testSortAlreadySorted() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        InsertionSortWithBinarySearch<Integer> sorter = new InsertionSortWithBinarySearch<Integer>(Comparator.naturalOrder());

        List<Integer> sortedList = sorter.sort(list);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), sortedList);
    }

    @Test
    public void testSortReverseOrder() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        InsertionSortWithBinarySearch<Integer> sorter = new InsertionSortWithBinarySearch<Integer>(Comparator.naturalOrder());

        List<Integer> sortedList = sorter.sort(list);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), sortedList);
    }
}