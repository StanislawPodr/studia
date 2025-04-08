import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class InsertionSortWithBinarySearch<T> extends AbstractSwappingSortingAlgorithm<T> {
    public InsertionSortWithBinarySearch(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        for (int i = list.size() - 2; i >= 0; i--) {
            T key = list.get(i);

            int position = binarySearch(list, key, i + 1, list.size() - 1);

            int j = i;

            while (j < position) {
                list.set(j, list.get(j + 1));
                j++;
            }
            list.set(position, key);
        }
        return list;
    }

    private int binarySearch(List<T> list, T key, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (compare(list.get(mid), key) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            } 
        }
        return high;
    }

}
