import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    private QuickSort<Integer> quickSort;
    private PivotStrategy<Integer> pivotStrategy;

    @BeforeEach
    void setUp() {
        pivotStrategy = new FirstIndexPivot<>();
        quickSort = new QuickSort<>(pivotStrategy, Comparator.naturalOrder());
    }

    @Test
    void testSortEmptyList() {
        List<Integer> list = new ArrayList<>();
        List<Integer> sortedList = quickSort.sort(list);
        assertTrue(sortedList.isEmpty());
    }

    @Test
    void testSortSingleElementList() {
        List<Integer> list = new ArrayList<>(List.of(1));
        List<Integer> sortedList = quickSort.sort(list);
        assertEquals(List.of(1), sortedList);
    }

    @Test
    void testSortAlreadySortedList() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> sortedList = quickSort.sort(list);
        assertEquals(List.of(1, 2, 3, 4, 5), sortedList);
    }

    @Test
    void testSortUnsortedList() {
        List<Integer> list = new ArrayList<>(List.of(5, 3, 1, 4, 2));
        List<Integer> sortedList = quickSort.sort(list);
        assertEquals(List.of(1, 2, 3, 4, 5), sortedList);
    }

    @Test
    void testSortListWithDuplicates() {
        List<Integer> list = new ArrayList<>(List.of(4, 2, 4, 3, 1, 2));
        List<Integer> sortedList = quickSort.sort(list);
        assertEquals(List.of(1, 2, 2, 3, 4, 4), sortedList);
    }

    @Test
    void testSortDescendingOrderList() {
        List<Integer> list = new ArrayList<>(List.of(5, 4, 3, 2, 1));
        List<Integer> sortedList = quickSort.sort(list);
        assertEquals(List.of(1, 2, 3, 4, 5), sortedList);
    }
}