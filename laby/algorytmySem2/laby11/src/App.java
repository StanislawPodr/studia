import java.util.Comparator;

public class App {
    public static void main(String[] args) throws Exception {
        MaxBinomialHeap<Integer> heap1 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap1.insert(10);
        heap1.insert(20);
        heap1.insert(5);
        heap1.insert(30);
        heap1.insert(25);
        heap1.insert(15);
        heap1.insert(40);
        heap1.insert(35);
        heap1.insert(40);

        MaxBinomialHeap<Integer> heap2 = new MaxBinomialHeap<>(Comparator.naturalOrder());
        heap2.insert(30);
        heap2.insert(40);
        heap2.insert(15);
        heap2.insert(25);
        heap2.insert(5);
        heap2.insert(35);

        heap1.merge(heap2);
        System.out.println("Max after merging heaps: " + heap1.findMax());
        return;
    }
}
