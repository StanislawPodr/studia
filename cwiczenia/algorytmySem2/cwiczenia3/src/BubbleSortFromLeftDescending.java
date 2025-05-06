import java.util.ArrayList;
import java.util.List;

public class BubbleSortFromLeftDescending<T extends Comparable<T>> {
    public void sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                T previousElement = list.get(j - 1);
                if (previousElement.compareTo(list.get(j)) < 0) {
                    list.set(j - 1, list.get(j));
                    list.set(j, previousElement);
                }
            }
            System.out.println(list);
        }

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3));
        BubbleSortFromLeftDescending<Integer> sorter = new BubbleSortFromLeftDescending<>();
        sorter.sort(list);
    }
}
