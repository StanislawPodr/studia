public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree() {
        this.value = null;
        this.left = null;
        this.right = null;
    }

     public BinaryTree(T value) {
        this.value = null;
        this.left = null;
        this.right = null;
    }

    public void add(T value) {
        root = addRecursive(root, value);
    }

    public void clear() {
        value = null;
        left = null;
        right = null;
    }

    private void addRecursive(BinaryTree<T> current, T value) {
        if (current == null) {
            return new BinaryTree<>(value);
        }

        if (value.hashCode() < current.value.hashCode()) {
            current.left = addRecursive(current.left, value);
        } else if (value.hashCode() > current.value.hashCode()) {
            current.right = addRecursive(current.right, value);
        } else {
            // Value already exists
            return current;
        }

        return current;
    }

}
