import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class QuickSort<T> extends AbstractSwappingSortingAlgorithm<T>{
    PivotStrategy<T> strategy;
    public QuickSort(PivotStrategy<T> strategy, Comparator<? super T> comparator) {
        super(comparator);
        this.strategy = strategy;
    }

    public List<T> sort(List<T> list) {
        if(list.size() < 2) {
            return list;
        }

        sort(list, 0, list.size() - 1);
        return list;
    }

    public void sort(List<T> list, int startIndex, int endIndex) {
        if(startIndex >= endIndex) {
            return;
        }

        int pivot = strategy.setPivot(list, startIndex, endIndex);
        swap(list, pivot, startIndex);
        int pivotIndex = defaultPivotManager(list, startIndex, endIndex);
        sort(list, startIndex, pivotIndex - 1);
        sort(list, pivotIndex + 1, endIndex);
    }

    private int defaultPivotManager(List<T> list, int start, int end) {
        int beggining = start;
        T pivotValue = list.get(start);
        while (end > start) {
            while (compare(list.get(start), pivotValue) <= 0 && end > start) {
                start++;
            }

            while (compare(list.get(end), pivotValue) > 0) {
                end--;
            }

            if (end > start) {
                swap(list, start, end);
            }
        }
        swap(list, beggining, end);
        return end;
    }
}
