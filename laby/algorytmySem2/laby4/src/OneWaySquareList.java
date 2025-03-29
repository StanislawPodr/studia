import wyklad.LinkedList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OneWaySquareList<T> implements wyklad.IList<T> {

    private int size;
    private int expectedSideSize;

    public OneWaySquareList() {
        list = new LinkedList<>();
        size = 0;
        expectedSideSize = 2;
    }

    private LinkedList<LinkedList<T>> list;

    @Override
    public boolean add(T e) {
        size++;
        if (size == expectedSideSize * expectedSideSize) {
            Iterator<LinkedList<T>> it = list.iterator();
            LinkedList<T> current = it.next();
            fixForNotEnoughInRow(it, current, expectedSideSize);
            list.getLast().add(e);
            expectedSideSize++;
        } else if ((size - 1) % (expectedSideSize - 1) != 0) {
            list.getLast().add(e);
        } else {
            LinkedList<T> newList = new LinkedList<>();
            newList.add(e);
            list.add(newList);
        }
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == size) {
            this.add(element);
            return;
        }

        size++;

        Iterator<LinkedList<T>> it = list.iterator();
        int indexOfSquareHeight = index / (expectedSideSize - 1);
        int indexOfSquareWidth = index % (expectedSideSize - 1);
        LinkedList<T> indexList = it.next();
        for (int i = 0; i < indexOfSquareHeight; i++) {
            indexList = it.next();
        }

        indexList.add(indexOfSquareWidth, element);

        if (size != expectedSideSize * expectedSideSize) {
            fixForTooManyInRow(it, indexList, expectedSideSize - 1);
        } else {
            ListIterator<LinkedList<T>> iteratorForFixingAfterExpand = list.listIterator();
            LinkedList<T> first = iteratorForFixingAfterExpand.next();
            fixForNotEnoughInRow(iteratorForFixingAfterExpand, first, expectedSideSize);
            expectedSideSize++;
        }
    }

    @Override
    public void clear() {
        size = 0;
        expectedSideSize = 2;
        list.clear();
    }

    @Override
    public boolean contains(T element) {
        if (size == 0) {
            return false;
        }

        Iterator<LinkedList<T>> it = list.iterator();
        LinkedList<T> indexList = null;
        boolean found = false;
        while (it.hasNext() && !found) {
            indexList = it.next();
            found = indexList.contains(element);
        }
        return found;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return list.get(index / (expectedSideSize - 1)).get(index % (expectedSideSize - 1));
    }

    @Override
    public T set(int index, T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return list.get(index / (expectedSideSize - 1)).set(index % (expectedSideSize - 1), element);
    }

    @Override
    public int indexOf(T element) {
        if (size == 0) {
            return -1;
        }

        Iterator<LinkedList<T>> it = list.iterator();
        LinkedList<T> indexList;
        int index = 0;
        boolean found = false;
        while (it.hasNext() && !found) {
            indexList = it.next();
            int indexFound = indexList.indexOf(element);
            found = (indexFound != -1);
            if (!found) {
                index += indexList.size();
            } else {
                index += indexFound;
            }
        }
        return found ? index : -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Iterator<LinkedList<T>> it = list.iterator();
        int indexOfSquareHeight = index / (expectedSideSize - 1);
        int indexOfSquareWidth = index % (expectedSideSize - 1);
        LinkedList<T> indexList = it.next();
        for (int i = 0; i < indexOfSquareHeight; i++) {
            indexList = it.next();
        }

        T removed = indexList.remove(indexOfSquareWidth);
        size--;

        if (size == 0) {
            return removed;
        }

        if (size < (expectedSideSize - 1) * (expectedSideSize - 1)) {
            expectedSideSize--;
            Iterator<LinkedList<T>> iteratorForFixingAfterShrink = list.iterator();
            List<T> first = iteratorForFixingAfterShrink.next();
            fixForTooManyInRow(iteratorForFixingAfterShrink, first, expectedSideSize - 1);
        } else {
            fixForNotEnoughInRow(it, indexList, expectedSideSize - 1);
        }

        return removed;
    }

    @Override
    public boolean remove(T element) {
        if (size == 0) {
            return false;
        }

        Iterator<LinkedList<T>> it = list.iterator();
        LinkedList<T> indexList = null;
        boolean found = false;
        while (it.hasNext() && !found) {
            indexList = it.next();
            found = indexList.remove(element);
        }
        size--;

        if (size == 0) {
            return found;
        }

        if (size < (expectedSideSize - 1) * (expectedSideSize - 1)) {
            expectedSideSize--;
            Iterator<LinkedList<T>> iteratorForFixingAfterShrink = list.iterator();
            List<T> first = iteratorForFixingAfterShrink.next();
            fixForTooManyInRow(iteratorForFixingAfterShrink, first, expectedSideSize - 1);
        } else {
            fixForNotEnoughInRow(it, indexList, expectedSideSize - 1);
        }

        return found;
    }

    @Override
    public int size() {
        return size;
    }

    public void print() {
        for (LinkedList<T> l : list) {
            for (T e : l) {
                System.out.print(e + " --");
            }
            System.out.println();
            System.out.print("|\n");
        }
    }

    private void fixForTooManyInRow(Iterator<LinkedList<T>> it, LinkedList<T> current, int maxWidth) {
        LinkedList<T> next = current;
        while (it.hasNext()) {
            next = it.next();
            while (current.size() > maxWidth) {
                next.addFirst(current.removeLast());
            }
            current = next;
        }

        final LinkedList<T> last = current;

        if (current.size() > maxWidth) {
            LinkedList<T> newLastList = new LinkedList<T>();
            newLastList.addAll(last.subList(maxWidth, last.size()));
            it.add(newLastList);
            last.subList(maxWidth, last.size()).clear();
        }
    }

    private void fixForNotEnoughInRow(Iterator<LinkedList<T>> it, LinkedList<T> current, int maxWidth) {
        LinkedList<T> next;
        while (it.hasNext()) {
            next = it.next();
            while (current.size() < maxWidth && !next.isEmpty()) {
                current.add(next.removeFirst());
            }
            current = next;
        }

        final LinkedList<T> last = current;

        if (last.isEmpty()) {
            it.remove();
        }

    }

}
