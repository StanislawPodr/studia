import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class HeapTree<T> extends Node<T> implements Heap<T> {
    private Node<T> lastNodeAdded;
    private ComparatorReference<T> comparator;

    public HeapTree(T value, Comparator<T> comparator) {
        super(value, null);
        this.comparator = new ComparatorReference<>(comparator);
    }

    public HeapTree(Comparator<T> comparator) {
        super(null, null);
        this.comparator = new ComparatorReference<>(comparator);
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

        Queue<Node<T>> queue = new java.util.LinkedList<>();
        boolean added = false;
        queue.add(this);
        Node<T> current = null;
        while (!added) {
            current = queue.poll();
            if (current.left == null) {
                current.left = new Node<>(value, current);
                lastNodeAdded = current.left;
                added = true;
            } else if (current.right == null) {
                current.right = new Node<>(value, current);
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

    private void swim(Node<T> node) {
        if (node.parent != null && comparator.compare(node.value, node.parent.value) < 0) {
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

        this.value = deleteLastNode();
        sinkThis();
        return min;
    }

    private void sink(Node<T> node) {
        if (node == null) {
            return;
        }
        Node<T> smallest = node;
        if (node.left != null && comparator.compare(node.left.value, smallest.value) < 0) {
            smallest = node.left;
        }

        if (node.right != null && comparator.compare(node.right.value, smallest.value) < 0) {
            smallest = node.right;
        }

        if (smallest != node) {
            T temp = node.value;
            node.value = smallest.value;
            smallest.value = temp;
            sink(smallest);
        }
    }

    T deleteLastNode() {
        Node<T> forDeletion = lastNodeAdded;
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
        return forDeletion.value;
    }

    void sinkThis() {
        sink(this);
    }

    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator.setComparator(comparator);
        List<T> array = new ArrayList<>();
        while(this.value != null) {
            array.add(minimum());
        }

        for (T val : array) {
            add(val);
        }
    }

}
