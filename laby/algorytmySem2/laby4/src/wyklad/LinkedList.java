package wyklad;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last = head;
    private int size = 0;
    private Node<T> current = head;

    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        Node<T> element = current;
        current = head;
        return element.get();
    }

    public boolean add(T element) {
        if (size == 0) {
            head = new Node<>(element);
            last = head;
        }

        last.setNext(element);
        last = last.getNext();
        size++;
        return true;
    }

    @Override
    public int indexOf(T data) {
        int pos = 0;
        Node<T> actElem = head;
        while (actElem != null) {
            if (actElem.getValue().equals(data))
                return pos;
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public T getLast() {
        return last.get();
    }

    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(head, size);
    }

    public boolean contains(T element) {
        for (T i : this) {
            if (Objects.equals(i, element)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

   

    private class LinkedListIterator implements Iterator<T> {

        Node<T> current;
        Node<T> next;
        int howManyLeft;

        public LinkedListIterator(Node<T> first, int howManyLeft) {
            this.next = first;
            this.howManyLeft = howManyLeft;
        }

        @Override
        public boolean hasNext() {
            return howManyLeft > 0;
        }

        @Override
        public T next() {
            if (howManyLeft <= 0) {
                throw new NoSuchElementException();
            }
            current = next;
            howManyLeft--;
            if (howManyLeft != 0) {
                next = next.getNext();
            }
            return current.get();
        }

    }
}
