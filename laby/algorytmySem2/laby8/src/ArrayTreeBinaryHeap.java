import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayTreeBinaryHeap<T> implements Heap<T> {
    private ArrayList<HeapTree<T>> heap;
    private int size;
    private int capacity;
    private ComparatorReference<T> comparator;

    public ArrayTreeBinaryHeap(int H, Comparator<T> comparator) {
        if (H < 1) {
            throw new IllegalArgumentException("H must be greater than or equal to 1");
        }
        this.size = 0;
        this.capacity = (int) Math.pow(2, H) - 1;
        this.heap = new ArrayList<>(capacity);
        this.comparator = new ComparatorReference<>(comparator);
    }

    @Override
    public void clear() {
        size = 0;
        heap.clear();
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null value to heap");
        }

        size++;
        if (size <= capacity) {
            heap.add(new HeapTree<>(element, comparator));
            swim(size - 1);
            return true;
        } else {
            int index = size - 1;
            while (index >= capacity) {
                index = getParentIndex(index);
            }
            heap.get(index).add(element);
            swim(index);
            return true;
        }
    }

    @Override
    public T minimum() {
        if (size == 0) {
            throw new UnsupportedOperationException("Heap is empty");
        }
        T min = heap.get(0).getValue();
        if (size <= capacity) {
            heap.get(0).setValue(heap.get(size - 1).getValue());
            heap.removeLast();
        } else {
            int index = size - 1;
            while (index >= capacity) {
                index = getParentIndex(index);
            }
            heap.get(0).setValue(heap.get(index).deleteLastNode());
        }
        size--;
        sink(0);
        return min;
    }

    private void swim(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && comparator.compare(heap.get(index).getValue(), heap.get(parentIndex).getValue()) < 0) {
            T temp = heap.get(index).getValue();
            heap.get(index).setValue(heap.get(parentIndex).getValue());
            heap.get(parentIndex).setValue(temp);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private void sink(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;
        if (rightChildIndex < capacity) {
            if (leftChildIndex < size
                    && comparator.compare(heap.get(leftChildIndex).getValue(), heap.get(smallest).getValue()) < 0) {
                smallest = leftChildIndex;
            }

            if (rightChildIndex < size
                    && comparator.compare(heap.get(rightChildIndex).getValue(), heap.get(smallest).getValue()) < 0) {
                smallest = rightChildIndex;
            }
        } else {
            heap.get(index).sinkThis();
        }

        if (smallest != index) {
            T temp = heap.get(index).getValue();
            heap.get(index).setValue(heap.get(smallest).getValue());
            heap.get(smallest).setValue(temp);
            sink(smallest);
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator.setComparator(comparator);
        List<T> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            array.add(minimum());
        }
        
        for (T element : array) {
            add(element);
        }
    }

}
