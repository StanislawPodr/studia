class Node<T> {
    protected T value;
    protected Node<T> left;
    protected Node<T> right;
    protected Node<T> parent;
    Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }
}
