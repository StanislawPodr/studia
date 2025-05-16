import java.util.ArrayList;
import java.util.Comparator;

public class ArrayTreeBinaryHeap<T> implements Heap<T> {
    private ArrayList<HeapTree<T>> heap;
    private int size;
    private int capacity;
    private Comparator<T> comparator;

    public ArrayTreeBinaryHeap(int H, Comparator<T> comparator) {
        if (H < 1) {
            throw new IllegalArgumentException("H must be greater than or equal to 1");
        }
        this.size = 0;
        this.capacity = (int) Math.pow(2, H) - 1;
        this.heap = new ArrayList<>(capacity);
        this.comparator = comparator;
    }

    @Override
    public void clear() {
        size = 0;
        heap.clear();
    }
    @Override
    public boolean add(T element) {
        if(size <= capacity) {
            heap.add(new HeapTree<>(element));
            size++;
            swim(size - 1);
            return true;
        }
    }
    @Override
    public T minimum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'minimum'");
    }

  

}
