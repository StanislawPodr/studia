import java.util.ArrayList;
import java.util.List;

public class BucketSort<T extends Comparable<? super T>> {
    public List<T> sort(List<T> list, List<List<T>> bucketsPartitionList) {
        if (list == null || list.size() < 2) {
            return new ArrayList<>();
        }

        List<ArrayList<T>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketsPartitionList.size(); i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i = 0; i < list.size(); i++) {
            boolean found = false;
            for (int j = 0; j < bucketsPartitionList.size() && !found; j++) {
                if (list.get(i).compareTo(bucketsPartitionList.get(j).get(0)) >= 0
                        && list.get(i).compareTo(bucketsPartitionList.get(j).get(1)) <= 0) {
                    buckets.get(j).add(list.get(i));
                    found = true;
                }
            }
        }

        List<T> sortedList = new ArrayList<>();
        HeapSort<T> heapSort = new HeapSort<>();
        for (int i = 0; i < buckets.size(); i++) {
            heapSort.sort(buckets.get(i));
            sortedList.addAll(buckets.get(i));
        }

        return sortedList;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(4, 2, 2, 8, 3, 3, 1));
        BucketSort<Integer> bucketSort = new BucketSort<>();
        list = bucketSort.sort(list, List.of(
                List.of(0, 2),
                List.of(3, 4),
                List.of(5, 8)));
        System.out.println(list);
    }
}
