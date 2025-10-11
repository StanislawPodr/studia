public class Node<T> {
    private T element;
    private Node<T> previous;
    private Node<T> next;

    public Node(T element) {
        this.element = element;
    }

    public Node(T element, Node<T> previous) {
        this.element = element;
        this.previous = previous;
    }

    public Node(T element, Node<T> previous, Node<T> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public T get() {
        return element;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
