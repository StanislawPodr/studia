import java.util.ArrayList;
import java.util.List;

public class InsertionSortFromRightDescending<T extends Comparable<? super T>> {
    public void sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        for (int i = list.size() - 2; i >= 0; i--) {
            T current = list.get(i);
            int j = i + 1;
            while (j < list.size() && current.compareTo(list.get(j)) < 0) {
                list.set(j - 1, list.get(j));
                j++;
            }
            list.set(j - 1, current);
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(76,71, 5, 57,12,50,20,93,20,55,62,3));
        InsertionSortFromRightDescending<Integer> sorter = new InsertionSortFromRightDescending<>();
        sorter.sort(list);
    }
}
