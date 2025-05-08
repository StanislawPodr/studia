import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MergeSortWithQueue<T extends Comparable<? super T>> {

    public List<T> sort(List<T> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        Queue<LinkedList<T>> queue = new LinkedList<>();
        for (T item : list) {
            queue.add(new LinkedList<T>(List.of(item))); 
        }

        while (queue.size() > 1) {
            LinkedList<T> left = queue.poll();
            LinkedList<T> right = queue.poll();
            LinkedList<T> merged = new LinkedList<>();
            merge(merged, left, right);
            queue.add(merged);
        }
        list = queue.poll();
        return list;
    }

    private void merge(Queue<T> queue, Queue<T> left, Queue<T> right) {
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.peek().compareTo(right.peek()) <= 0) {
                queue.add(left.poll());
            } else {
                queue.add(right.poll());
            }
        }
        while (!left.isEmpty()) {
            queue.add(left.poll());
        }
        while (!right.isEmpty()) {
            queue.add(right.poll());
        }
    }

    public static void main(String[] args) {
        MergeSortWithQueue<Integer> sorter = new MergeSortWithQueue<>();
        List<Integer> list = List.of(5, 3, 8, 1, 2, 7);
        List<Integer> sortedList = sorter.sort(list);
        System.out.println(sortedList); 
    }

}
