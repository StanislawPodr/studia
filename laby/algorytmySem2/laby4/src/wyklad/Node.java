package wyklad;
public class Node<T> {
    private T element;
    private Node<T> next;

    public Node(T element) {
        this.element = element;
        next = null;
    }

    public Node<T> getNext() {
        return next;
    }

    public T get() {
        return element;
    }

    public void setNext(T next) {
        this.next = new Node<>(next);
    }

    public void setElement(T element) {
        this.element = element;
    }
}
