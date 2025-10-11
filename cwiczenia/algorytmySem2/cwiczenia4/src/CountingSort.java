import java.util.ArrayList;
import java.util.List;

public class CountingSort {
    void sort(List<Integer> list, int k) {
        if (list == null || list.size() < 2) {
            return;
        }

        int[] counter = new int[k + 1];
        for (int i = 0; i < list.size(); i++) {
            counter[list.get(i)]++;
        }

        int j = 0;
        for (int i = 0; i < counter.length; i++) {
            while(counter[i] > 0) {
                counter[i]--;
                list.set(j, i);
                j++;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(4, 2, 2, 8, 3, 3, 1));
        CountingSort countingSort = new CountingSort();
        countingSort.sort(list, 8);
        System.out.println(list);
    }
}
