import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationSort<T extends Comparable<? super T>> {
    private boolean nextPermutation(List<T> list) {
        int size = list.size();
        
        int j = size - 2;
        while (j >= 0 && list.get(j).compareTo(list.get(j + 1)) >= 0) {
            j--;
        }
        
        if (j < 0) return false; 
        
        int k = size - 1;
        while (list.get(k).compareTo(list.get(j)) <= 0) {
            k--;
        }
        
        Collections.swap(list, j, k);
        
        Collections.reverse(list.subList(j + 1, size));
        
        return true;
    }

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
             
        while (!isSorted(list)) {
           nextPermutation(list);
        }
    }


     public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(76, 71));
        PermutationSort<Integer> sorter = new PermutationSort<>();
        sorter.sort(list); 
        System.out.println(list);
    }
}
