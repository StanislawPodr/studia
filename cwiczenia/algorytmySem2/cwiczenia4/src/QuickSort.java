import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuickSort<T extends Comparable<? super T>> {

    public List<T> sort(List<T> list) {
        if (list.size() < 2) {
            return list;
        }

        sort(list, 0, list.size() - 1);
        return list;
    }

    public void sort(List<T> list, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int pivot = setPivot(list, startIndex, endIndex);
        Collections.swap(list, pivot, startIndex);
        int pivotIndex = defaultPivotManager(list, startIndex, endIndex);
        sort(list, startIndex, pivotIndex - 1);
        sort(list, pivotIndex + 1, endIndex);
    }

    private int setPivot(List<T> list, int startIndex, int endIndex) {
        Random random = new Random();
        int pivotIndex = random.nextInt(endIndex - startIndex + 1) + startIndex;
        if (list.size() > 100) {
            List<Integer> pivotIndexes = new ArrayList<>();
            pivotIndexes.add(pivotIndex);
            for (int i = 0; i < 2; i++) {
                pivotIndexes.add(random.nextInt(endIndex - startIndex + 1) + startIndex);
            }
            Collections.sort(pivotIndexes);
            return pivotIndexes.get(1);
        }

        return pivotIndex;
    }

    private int defaultPivotManager(List<T> list, int start, int end) {
        int beggining = start;
        T pivotValue = list.get(start);
        while (end > start) {
            while (list.get(start).compareTo(pivotValue) <= 0 && end > start) {
                start++;
            }

            while (list.get(end).compareTo(pivotValue) > 0) {
                end--;
            }

            if (end > start) {
                Collections.swap(list, start, end);
            }
        }
        Collections.swap(list, beggining, end);
        return end;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(4, 2, 2, 8, 3, 3, 1));
        QuickSort<Integer> quickSort = new QuickSort<>();
        list = quickSort.sort(list);
        System.out.println(list);
    }
}
