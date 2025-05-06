import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {

    @Test
    public void testSortIntegers() {
        List<Integer> input = Arrays.asList(5, 2, 9, 1, 5, 6);
        List<Integer> expected = Arrays.asList(1, 2, 5, 5, 6, 9);

        MergeSort<Integer> mergeSort = new MergeSort<Integer>(Comparator.naturalOrder());
        List<Integer> result = mergeSort.sort(new ArrayList<>(input));

        assertEquals(expected, result);
    }

    @Test
    public void testSortStrings() {
        List<String> input = Arrays.asList("banana", "apple", "cherry", "date");
        List<String> expected = Arrays.asList("apple", "banana", "cherry", "date");

        MergeSort<String> mergeSort = new MergeSort<String>(Comparator.naturalOrder());
        List<String> result = mergeSort.sort(new ArrayList<>(input));

        assertEquals(expected, result);
    }

    @Test
    public void testSortEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        MergeSort<Integer> mergeSort = new MergeSort<Integer>(Comparator.naturalOrder());
        List<Integer> result = mergeSort.sort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testSortSingleElementList() {
        List<Integer> input = Arrays.asList(42);
        List<Integer> expected = Arrays.asList(42);

        MergeSort<Integer> mergeSort = new MergeSort<Integer>(Comparator.naturalOrder());
        List<Integer> result = mergeSort.sort(new ArrayList<>(input));

        assertEquals(expected, result);
    }

    @Test
    public void testSortDescendingOrder() {
        List<Integer> input = Arrays.asList(9, 7, 5, 3, 1);
        List<Integer> expected = Arrays.asList(1, 3, 5, 7, 9);

        MergeSort<Integer> mergeSort = new MergeSort<Integer>(Comparator.naturalOrder());
        List<Integer> result = mergeSort.sort(new ArrayList<>(input));

        assertEquals(expected, result);
    }

    @Test
    public void testSortCustomComparator() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);

        MergeSort<Integer> mergeSort = new MergeSort<Integer>(Comparator.reverseOrder());
        List<Integer> result = mergeSort.sort(new ArrayList<>(input));

        assertEquals(expected, result);
    }
}