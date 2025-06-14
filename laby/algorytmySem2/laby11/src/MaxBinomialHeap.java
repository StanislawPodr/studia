import java.util.Comparator;
import java.util.LinkedList;

public class MaxBinomialHeap<T> {
    Comparator<? super T> comparator;
    BinomialTree firstNode;

    private class BinomialTree {
        T value;
        int degree;
        LinkedList<BinomialTree> children;
        BinomialTree next;

        BinomialTree(T value) {
            this.value = value;
            this.degree = 0;
            this.children = new LinkedList<>();
        }

        BinomialTree mergeTree(BinomialTree other) {
            if (comparator.compare(other.value, this.value) >= 0) {
                other.children.addFirst(this);
                this.next = null;
                other.next = null;
                other.degree++;
                return other;
            } else {
                this.children.addFirst(other);
                this.next = null;
                other.next = null;
                this.degree++;
                return this;
            }
        }

        public boolean hasNext() {
            return next != null;
        }

    }

    public MaxBinomialHeap(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        BinomialTree newTree = new BinomialTree(element);
        BinomialTree prev = newTree;
        while (firstNode != null && firstNode.degree == prev.degree) {
            BinomialTree current = pop();
            current = prev.mergeTree(current);
            prev = current;
        }
        push(prev);
    }

    public T findMax() {
        if (firstNode == null) {
            return null;
        }
        return findMaxTree().value;
    }

    public void merge(MaxBinomialHeap<T> other) {
        if (other.firstNode == null) {
            return;
        }

        if (firstNode == null) {
            firstNode = other.firstNode;
            return;
        }
        joinHeaps(other);
        BinomialTree current = firstNode;
        BinomialTree prev = null;
        while (current.hasNext()) {
            if (current.degree == current.next.degree) {
                if (current.next.hasNext() && current.degree == current.next.next.degree) {
                    prev = current;
                    current = current.next;
                }
                BinomialTree nextCurrent = current.next.next;
                BinomialTree mergedTree = current.mergeTree(current.next);
                if (prev == null) {
                    firstNode = mergedTree;
                } else {
                    prev.next = mergedTree;
                }
                mergedTree.next = nextCurrent;
                current = mergedTree;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    private void joinHeaps(MaxBinomialHeap<T> other) {
        BinomialTree thisIterator = firstNode;
        BinomialTree otherIterator = other.firstNode;
        while (thisIterator.hasNext() && otherIterator != null) {
            while (thisIterator.hasNext() && thisIterator.next.degree < otherIterator.degree) {
                thisIterator = thisIterator.next;
            }
            if (thisIterator.hasNext()) {
                while (otherIterator != null && otherIterator.degree <= thisIterator.next.degree) {
                    BinomialTree nextOther = otherIterator.next;
                    BinomialTree nextThis = thisIterator.next;
                    thisIterator.next = otherIterator;
                    otherIterator.next = nextThis;
                    otherIterator = nextOther;
                    thisIterator = thisIterator.next;
                }
            }
        }
        if (!thisIterator.hasNext()) {
            thisIterator.next = otherIterator;
        }
    }

    public T extractMax() {
        if (firstNode == null) {
            return null;
        }
        PairOfTrees maxTreeWithPrev = findMaxTreeWithPrev();
        BinomialTree maxTree = maxTreeWithPrev.second;
        T maxValue = maxTree.value;
        BinomialTree prev = maxTreeWithPrev.first;
        if (prev != null) {
            prev.next = maxTree.next;
        } else {
            firstNode = maxTree.next;
        }
        MaxBinomialHeap<T> splitHeap = splitTree(maxTree);
        merge(splitHeap);
        return maxValue;
    }

    private BinomialTree findMaxTree() {
        BinomialTree iterator = firstNode;
        BinomialTree max = firstNode;
        while (iterator.hasNext()) {
            BinomialTree tree = iterator.next;
            if (comparator.compare(tree.value, max.value) > 0) {
                max = tree;
            }
            iterator = tree;
        }
        return max;
    }

    private class PairOfTrees {
        final BinomialTree first;
        final BinomialTree second;

        PairOfTrees(BinomialTree first, BinomialTree second) {
            this.first = first;
            this.second = second;
        }
    }

    private PairOfTrees findMaxTreeWithPrev() {
        BinomialTree iterator = firstNode;
        BinomialTree max = firstNode;
        BinomialTree prev = null;
        while (iterator.hasNext()) {
            BinomialTree tree = iterator.next;
            if (comparator.compare(tree.value, max.value) > 0) {
                prev = iterator;
                max = tree;
            }
            iterator = tree;
        }
        return new PairOfTrees(prev, max);
    }

    private void push(BinomialTree tree) {
        tree.next = firstNode;
        firstNode = tree;
    }

    private BinomialTree pop() {
        BinomialTree poppedNode = firstNode;
        firstNode = firstNode.next;
        poppedNode.next = null;
        return poppedNode;
    }

    private MaxBinomialHeap<T> splitTree(BinomialTree tree) {
        MaxBinomialHeap<T> heap = new MaxBinomialHeap<>(comparator);
        LinkedList<BinomialTree> children = tree.children;
        for (BinomialTree child : children) {
            heap.push(child);
        }
        return heap;
    }

}
