import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class SelectionSort<T> extends AbstractSwappingSortingAlgorithm<T>{
    public SelectionSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        for(int i = list.size() - 1; i >= 0; i--) {
            int maxIndex = i;
            for(int j = 0; j < i; j++) {
                if(compare(list.get(j), list.get(maxIndex)) > 0) {
                    maxIndex = j;
                }
            }
            swap(list, maxIndex, i);
        }
        return list;
    }

}
