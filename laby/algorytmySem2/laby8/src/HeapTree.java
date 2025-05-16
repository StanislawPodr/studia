import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class HeapTree<T extends Comparable<? super T>> implements Heap<T> {
    private T value;
    private HeapTree<T> left;
    private HeapTree<T> right;
    private HeapTree<T> parent;
    private HeapTree<T> lastNodeAdded;
    private Comparator<T> comparator;

    public HeapTree(T value, Comparator<T> comparator) {
        this.value = value;
        this.comparator = comparator;
    }

    public HeapTree(T value, Comparator<T> comparator, HeapTree<T> parent) {
        this(value, comparator);
        this.parent = parent;
    }

    public HeapTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null value to heap");
        }

        if (this.value == null) {
            this.value = value;
            lastNodeAdded = this;
            return true;
        }

        Queue<HeapTree<T>> queue = new java.util.LinkedList<>();
        boolean added = false;
        queue.add(this);
        HeapTree<T> current = null;
        while (!added) {
            current = queue.poll();
            if (current.left == null) {
                current.left = new HeapTree<>(value, comparator, current);
                lastNodeAdded = current.left;
                added = true;
            } else if (current.right == null) {
                current.right = new HeapTree<>(value, comparator, current);
                lastNodeAdded = current.right;
                added = true;
            } else {
                queue.add(current.left);
                queue.add(current.right);
            }
        }

        swim(lastNodeAdded);
        return true;
    }

    public void clear() {
        value = null;
        left = null;
        right = null;
        parent = null;
        lastNodeAdded = null;
    }

    private void swim(HeapTree<T> node) {
        if (node.parent != null && node.value.compareTo(node.parent.value) < 0) {
            T temp = node.value;
            node.value = node.parent.value;
            node.parent.value = temp;
            swim(node.parent);
        }
    }

    @Override
    public T minimum() {
        if (this.value == null) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = this.value;
        if (lastNodeAdded.parent == null) {
            clear();
            return min;
        }

        this.value = lastNodeAdded.value;
        HeapTree<T> forDeletion = lastNodeAdded;
        while (lastNodeAdded.parent != null && lastNodeAdded.parent.right != lastNodeAdded) {
            lastNodeAdded = lastNodeAdded.parent;
        }

        if (lastNodeAdded.parent != null) {
            lastNodeAdded = lastNodeAdded.parent.left;
        }

        while (lastNodeAdded.right != null) {
            lastNodeAdded = lastNodeAdded.right;
        }

        if (forDeletion.parent.left == forDeletion) {
            forDeletion.parent.left = null;
        } else {
            forDeletion.parent.right = null;
        }

        sink(this);
        return min;
    }

    private void sink(HeapTree<T> node) {
        if (node == null) {
            return;
        }
        HeapTree<T> smallest = node;
        if (node.left != null && node.left.value.compareTo(smallest.value) < 0) {
            smallest = node.left;
        } 
        
        if (node.right != null && node.right.value.compareTo(smallest.value) < 0) {
            smallest = node.right;
        }

        if (smallest != node) {
            T temp = node.value;
            node.value = smallest.value;
            smallest.value = temp;
            sink(smallest);
        }
    }
}
