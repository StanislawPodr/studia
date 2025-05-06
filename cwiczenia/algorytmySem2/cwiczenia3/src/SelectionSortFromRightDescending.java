import java.util.ArrayList;
import java.util.List;

public class SelectionSortFromRightDescending<T extends Comparable<T>> {
    public void sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        for (int i = list.size() - 1; i > 0; i--) {
            T min = list.get(i);
            int minIndex = i;
           for(int j = i; j >= 0; j--) {
            T currentToComapre = list.get(j);
                if(min.compareTo(currentToComapre) > 0) {
                    minIndex = j;
                    min = currentToComapre;
                }
           }
           list.set(minIndex, list.get(i));
           list.set(i, min);
           System.out.println(list);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(76,71, 5, 57,12,50,20,93,20,55,62,3));
        SelectionSortFromRightDescending<Integer> sorter = new SelectionSortFromRightDescending<>();
        sorter.sort(list);
    }

}
