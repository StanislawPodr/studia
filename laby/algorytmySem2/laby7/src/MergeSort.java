import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import core.AbstractSortingAlgorithm;

public class MergeSort<T> extends AbstractSortingAlgorithm<T>{

    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    private void mergeOnOneList(List<T> list, List<T> result, int startFirst, int endFirst, int startSecond,
            int endSecond) {
        while (endFirst >= startFirst && endSecond >= startSecond) {
            if (compare(list.get(startFirst), list.get(startSecond)) <= 0) {
                result.add(list.get(startFirst));
                startFirst++;
            } else {
                result.add(list.get(startSecond));
                startSecond++;
            }
        }

        while (endSecond >= startSecond) {
            result.add(list.get(startSecond));
            startSecond++;
        }

        while (endFirst >= startFirst) {
            result.add(list.get(startFirst));
            startFirst++;
        }

    }

    public List<T> sort(List<T> list) {
        int pass = 1;
        while (pass < list.size()) {
            List<T> result = new ArrayList<>(list.size());
            int numberOfPairsToMerge = list.size() / pass / 2;
            for (int i = 0; i < numberOfPairsToMerge; i++) {
                int startIndex = i * pass * 2;
                mergeOnOneList(list, result, startIndex, startIndex + pass - 1, startIndex + pass,
                       startIndex + 2 * pass - 1);
            }

            int startIndex = numberOfPairsToMerge * pass * 2;
            if(startIndex + pass < list.size()) {}
            mergeOnOneList(list, result, startIndex, Math.min(startIndex + pass - 1, list.size() - 1), startIndex + pass, list.size() - 1);
            pass *= 2;
            list = result;
        }
        return list;
    }
}
