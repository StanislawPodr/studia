import java.util.ArrayList;
import java.util.List;

public class ShakerSort<T extends Comparable<T>> {
    public void sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int size = list.size();

        int halfSize = size / 2;

        for (int pass = 1; pass <= halfSize; ++pass) {
            for (int left = pass - 1; left < (size - pass); left++) {
                int right = left + 1;

                if (list.get(left).compareTo(list.get(right)) > 0) {
                    T leftElement = list.get(left);
                    list.set(left, list.get(right));
                    list.set(right, leftElement);
                }
            }

            for (int right = size - pass - 1; right > pass - 1; right--) {
                int left = right - 1;

                if (list.get(left).compareTo(list.get(right)) > 0) {
                    T leftElement = list.get(left);
                    list.set(left, list.get(right));
                    list.set(right, leftElement);
                }
            }

            System.out.println(list);
        }

    }

    public void betterSort(List<T> list) {

        if (list == null || list.size() < 2) {
            return;
        }

        int size = list.size();

        int halfSize = size / 2;

        boolean swapped = true;
        int firstUnsortedIndex = 0;
        int lastUnsortedIndex = size - 1;
        for (int pass = 1; pass <= halfSize && swapped; ++pass) {

            swapped = false;
            int futureLastUnsortedIndex = lastUnsortedIndex;
            for (int left = firstUnsortedIndex; left < lastUnsortedIndex; left++) {
                int right = left + 1;

                if (list.get(left).compareTo(list.get(right)) > 0) {
                    T leftElement = list.get(left);
                    list.set(left, list.get(right));
                    list.set(right, leftElement);
                    futureLastUnsortedIndex = left;
                    swapped = true;
                }
            }

            lastUnsortedIndex = futureLastUnsortedIndex;

            if(swapped) {
                int futureFirstUnsortedIndex = firstUnsortedIndex;
                swapped = false;
                for (int right = lastUnsortedIndex; right > firstUnsortedIndex; right--) {
                    int left = right - 1;
    
                    if (list.get(left).compareTo(list.get(right)) > 0) {
                        T leftElement = list.get(left);
                        list.set(left, list.get(right));
                        list.set(right, leftElement);
                        swapped = true;
                        futureFirstUnsortedIndex = right;
                    }
                }
                firstUnsortedIndex = futureFirstUnsortedIndex;
            }
            
            System.out.println(list);
        }

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3));
        ShakerSort<Integer> sorter = new ShakerSort<>();
        sorter.betterSort(list);
    }

}
