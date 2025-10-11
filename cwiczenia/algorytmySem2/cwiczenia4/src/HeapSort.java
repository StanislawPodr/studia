import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapSort<T extends Comparable<? super T>> {
    public void createHeap(List<T> list) {
        int n = list.size();
        for (int i = (n - 1) / 2; i >= 0; i--) {
            sink(list, n, i);
        }
    }

    public void sink(List<T> list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && list.get(left).compareTo(list.get(largest)) > 0) {
            largest = left;
        }

        if (right < n && list.get(right).compareTo(list.get(largest)) > 0) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(list, i, largest);
            System.out.println(list);
            sink(list, n, largest);
        }
    }

    public void sort(List<T> list) {
        createHeap(list);
        for (int i = list.size() - 1; i >= 0; i--) {
            Collections.swap(list, 0, i);
            sink(list, i, 0);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(12, 11, 13, 5, 6, 7));
        HeapSort<Integer> heapSort = new HeapSort<>();
        heapSort.sort(list);
        System.out.println(list);
    }
}
