import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TwoWayLinkedList<E> implements IList<E> {
    private Node<E> headSentinel;
    private Node<E> tailSentinel;
    private int size = 0;

    private record NodeWithIndex<E>(Node<E> node, int index) {
    };

    public TwoWayLinkedList() {
        headSentinel = new Node<E>(null);
        tailSentinel = new Node<E>(null, headSentinel);
    }

    @Override
    public Iterator<E> iterator() {
        return new TwoWayLinkedListIterator();
    }

    @Override
    public boolean add(E e) {
        size++;
        Node<E> added = new Node<>(e);
        if (size > 1) {
            tailSentinel.getPrevious().getPrevious().setNext(added);
        }
        tailSentinel.getPrevious().setNext(tailSentinel);
        added.setPrevious(tailSentinel.getPrevious());
        tailSentinel.setPrevious(added);
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index == size) {
            this.add(element);
            return;
        }
        Node<E> currentNode = getNode(index);
        size++;
        Node<E> previousNode = currentNode.getPrevious();
        Node<E> previousPreviousNode = previousNode.getPrevious();
        Node<E> nextNode = previousNode.getNext();
        Node<E> newNode = new Node<>(element, previousNode, nextNode);
        currentNode.setPrevious(newNode);
        previousNode.setNext(currentNode);
        if (previousPreviousNode != null) {
            previousPreviousNode.setNext(newNode);
        }
    }

    @Override
    public void clear() {
        size = 0;
        headSentinel.setNext(null);
        tailSentinel.setPrevious(null);
    }

    @Override
    public boolean contains(E element) {
        return findNode(element) != null;
    }

    @Override
    public E get(int index) {
        return getNode(index).get();
    }

    private Node<E> getNode(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int roadFromStartForward = (index + 1 + (index + 1) % 2) / 2;
        int roadFromStartBackwords = (index + 1) % 2;
        int roadFromEnd = size - index;
        Node<E> result;
        if (roadFromStartForward + roadFromStartBackwords >= roadFromEnd) {
            result = tailSentinel;
            while (roadFromEnd > 0) {
                result = result.getPrevious();
                roadFromEnd--;
            }
            return result;
        }

        result = headSentinel;
        while (roadFromStartForward > 0) {
            result = result.getNext();
            roadFromStartForward--;
        }

        while (roadFromStartBackwords > 0) {
            result = result.getPrevious();
            roadFromStartBackwords--;
        }

        return result;
    }

    @Override
    public E set(int index, E element) {
        getNode(index).setElement(element);
        return element;
    }

    private NodeWithIndex<E> findNode(E element) {
        if (size < 1) {
            return null;
        }

        Node<E> node = tailSentinel.getPrevious();
        int index = size - 1;
        while (node != null && !Objects.equals(element, node.getElement())) {
            node = node.getPrevious();
            index--;
        }

        if (index < 0) {
            return null;
        }

        return new NodeWithIndex<>(node, index);
    }

    @Override
    public int indexOf(E element) {
        NodeWithIndex<E> nodeWithIndex = findNode(element);
        return nodeWithIndex == null ? -1 : nodeWithIndex.index();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove(int index) {
        Node<E> node = getNode(index);
        removeNode(node);
        return node.get();
    }

    @Override
    public boolean remove(E element) {
        NodeWithIndex<E> node = findNode(element);
        if (node == null) {
            return false;
        }
        removeNode(node.node());
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayLinkedListIterator();
    }

    private void removeNode(Node<E> currentNode) {
        size--;
        Node<E> nextNextNode = currentNode.getNext();
        Node<E> previousNode = currentNode.getPrevious();
        Node<E> previousPreviousNode = previousNode.getPrevious();
        Node<E> nextNode = previousNode.getNext();
        nextNode.setPrevious(previousNode);
        if (previousPreviousNode != null) {
            previousPreviousNode.setNext(nextNode);
        }
        previousNode.setNext(nextNextNode);
    }

    private class TwoWayLinkedListIterator implements ListIterator<E> {
        Node<E> current;
        Node<E> next;
        Node<E> previous;
        int index = -1;
        boolean currentIsRemoved = true;

        public TwoWayLinkedListIterator() {
            if (size == 0) {
                return;
            }
            current = headSentinel;
            next = headSentinel.getNext().getPrevious();
        }

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            index++;
            if(!currentIsRemoved) {
                previous = current;
            }
            current = next;
            if (index == size - 1) {
                next = null;
            } else {
                next = next.getNext().getPrevious();
            }
            currentIsRemoved = false;
            return current.get();
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            index--;
            if (!currentIsRemoved) {
                next = current;
            }

            current = previous;
            previous = previous.getPrevious();

            currentIsRemoved = false;
            return current.get();
        }

        @Override
        public int nextIndex() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            return index + 1;
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) {
                throw new IndexOutOfBoundsException();
            }
            return index - 1;
        }

        @Override
        public void remove() {
            if (currentIsRemoved) {
                throw new IllegalStateException();
            }
            removeNode(current);
            currentIsRemoved = true;
        }

        @Override
        public void set(E e) {
            current.setElement(e);
        }

        @Override
        public void add(E e) {
            TwoWayLinkedList.this.add(e);
        }

    }

}
