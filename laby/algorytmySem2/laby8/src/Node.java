class Node<T> {
    protected T value;
    protected Node<T> left;
    protected Node<T> right;
    protected Node<T> parent;
    Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }
    public T getValue() {
        return value;
    }
    public Node<T> getLeft() {
        return left;
    }
    public Node<T> getRight() {
        return right;
    }
    public Node<T> getParent() {
        return parent;
    }
    
}
