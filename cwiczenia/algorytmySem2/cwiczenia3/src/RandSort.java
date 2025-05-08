import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RandSort<T extends Comparable<? super T>> {

    private boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    public void sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        while(!isSorted(list)) {
            Collections.shuffle(list);
        }

    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(76, 71, 5, 57, 12, 18, 50, 
        82, 94, 20, 93, 20, 55, 62, 3, 1, 2, 4, 6, 7, 8));
        RandSort<Integer> sorter = new RandSort<>();
        sorter.sort(list); 
        System.out.println(list);
    }
}
