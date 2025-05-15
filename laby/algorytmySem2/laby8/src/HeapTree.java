import java.util.NoSuchElementException;
import java.util.Queue;

public class HeapTree<T extends Comparable<? super T>> implements Heap<T> {
    private T value;
    private HeapTree<T> left;
    private HeapTree<T> right;
    private HeapTree<T> parent;
    private HeapTree<T> lastNodeAdded;

    public HeapTree(T value) {
        this.value = value;
    }

    public HeapTree(T value, HeapTree<T> parent) {
        this(value);
        this.parent = parent;
    }

    public HeapTree() {
    }

    public boolean add(T value) {
        if (this.value == null) {
            this.value = value;
            return true;
        } else if (value == null) {
            throw new IllegalArgumentException("Cannot add null value to heap");
        }

        Queue<HeapTree<T>> queue = new java.util.LinkedList<>();
        boolean added = false;
        queue.add(this);
        HeapTree<T> current = queue.poll();
        while (!added) {
            if (current.left == null) {
                current.left = new HeapTree<>(value, current);
                added = true;
            } else if (current.right == null) {
                current.right = new HeapTree<>(value, current);
                added = true;
            } else {
                queue.add(current.left);
                queue.add(current.right);
            }
        }

        swim(current);
        lastNodeAdded = current;
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
        if (node.value.compareTo(node.parent.value) < 0) {
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
        if (this.parent == null) {
            return min;
        }
        this.value = lastNodeAdded.value;
        if (lastNodeAdded.parent.right == null) {
            lastNodeAdded.parent.left = null;
            lastNodeAdded = lastNodeAdded.parent;
            if (lastNodeAdded.parent != null) {
                lastNodeAdded = lastNodeAdded.parent.left.right;
            }
           
        } else {
            lastNodeAdded.parent.right = null;
            lastNodeAdded = lastNodeAdded.parent.left;
        }
        sink(this);
        return min;
    }

    private void removeAndSwimSmaller(HeapTree<T> node) {
        if (node.left != null && node.right != null) {
            if (node.left.value.compareTo(node.right.value) < 0) {
                node.value = node.left.value;
                removeAndSwimSmaller(node.left);
            } else {
                node.value = node.right.value;
                removeAndSwimSmaller(node.right);
            }
        } else if (node.left != null) {
            node.value = node.left.value;
            removeAndSwimSmaller(node.left);
        } else if (node.right != null) {
            node.value = node.right.value;
            removeAndSwimSmaller(node.right);
        } else {
            node.clear();
        }
    }

    private void sink(HeapTree<T> node) {
        if (node == null || node.parent == null) {
            return;
        }
        if (node.value.compareTo(node.parent.value) < 0) {
            T temp = node.value;
            node.value = node.parent.value;
            node.parent.value = temp;
            sink(node.parent);
        } else {
            removeAndSwimSmaller(node);
        }
    }
}
