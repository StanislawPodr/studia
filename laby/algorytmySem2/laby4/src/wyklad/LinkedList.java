package wyklad;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last = head;
    private int size = 0;

    public T get(int index) {
        return this.getNode(index).get();
    }

    public Node<T> getNode(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public boolean add(T element) {
        size++;
        if (size == 1) {
            head = new Node<>(element);
            last = head;
            return true;
        }

        last.setNext(new Node<>(element));
        last = last.getNext();
        return true;
    }

    public int indexOf(T data) {
        Iterator<T> iterator = this.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == size) {
            this.add(element);
            return;
        }

        size++;

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }

    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setElement(element);
        return element;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        size--;
        Node<T> current = head;
        T element = null;
        if (index == 0) {
            element = head.get();
            head = head.getNext();
            return element;
        }

        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }

        if (index == size) {
            element = last.get();
            last = current;
            return element;
        }

        Node<T> removed = current.getNext();
        element = removed.get();
        current.setNext(removed.getNext());
        return element;
    }

    public T addFirst(T element) {
        this.add(0, element);
        return element;
    }

    public int size() {
        return size;
    }

    public Node<T> getLast() {
        return last;
    }

    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
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

    public boolean remove(T element) {
        if (size == 0) {
            return false;
        }

        Node<T> current = head;
        Node<T> previous = null;
        if (Objects.equals(current.get(), element)) {
            head = current.getNext();
            size--;
            return true;
        }

        for (int i = 1; i < size; i++) {
            previous = current;
            current = current.getNext();
            if (Objects.equals(current.get(), element)) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
        }
        return false;
    }

    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        size--;
        Node<T> current = head;
        T element = null;
        if (size == 0) {
            element = head.get();
            head = null;
            return element;
        }

        while (current.getNext() != last) {
            current = current.getNext();
        }

        element = last.get();
        last = current;
        last.setNext(null);
        return element;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        size--;
        T element = head.get();
        head = head.getNext();
        return element;
    }

    private class LinkedListIterator implements Iterator<T> {

        Node<T> current;
        Node<T> previous;
        Node<T> next;
        int howManyLeft;

        public LinkedListIterator() {
            this.next = head;
            this.howManyLeft = size;
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
            previous = current;
            current = next;
            howManyLeft--;
            if (howManyLeft != 0) {
                next = next.getNext();
            }
            return current.get();
        }

        @Override
        public void remove() {
            size--;
            if (size == 0) {
                head.setElement(null);
                last.setElement(null);
                return;
            }

            if (current == head) {
                head = head.getNext();
                return;
            }

            if (current == last) {
                last = previous;
                previous.setNext(null);
                return;
            }

            previous.setNext(next);
        }

    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
