
public class Node<T> {
    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
    T value;
    BinaryTree<T> left;
    BinaryTree<T> right;
}
