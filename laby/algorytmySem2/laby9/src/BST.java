import java.util.Comparator;

public class BST<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    private record NodeWithParentAndLatestLowerAncestor<T>(Node<T> node, Node<T> parent, Node<T> latestLowerAncestor) {
    }

    private record NodeWithParent<T>(Node<T> node, Node<T> parent) {
    }

    public BST(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (root == null) {
            root = new Node<>(data);
            return;
        }

        Node<T> current = root;
        boolean inserted = false;
        while (!inserted) {
            if (comparator.compare(current.getData(), data) > 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node<>(data));
                    inserted = true;
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    current.setRight(new Node<>(data));
                    inserted = true;
                }
                current = current.getRight();
            }
        }
    }

    public boolean search(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return search(root, data);
    }

    private boolean search(Node<T> node, T data) {
        if (node == null) {
            return false;
        }

        int comparisonResult = comparator.compare(node.getData(), data);
        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult > 0) {
            return search(node.getLeft(), data);
        } else {
            return search(node.getRight(), data);
        }
    }

    public T findMinimum() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinimum(root).getData();
    }

    private Node<T> findMinimum(Node<T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findMinimum(node.getLeft());
    }

    public T findMaximum() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaximum(root).getData();
    }

    private Node<T> findMaximum(Node<T> node) {
        if (node.getRight() == null) {
            return node;
        }
        return findMaximum(node.getRight());
    }

    public <R> void inOrderTraversal(Executor<T, R> executor) {
        if (executor == null) {
            throw new IllegalArgumentException("Executor cannot be null");
        }
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        inOrder(root, executor);
    }

    private <R> void inOrder(Node<T> node, Executor<T, R> executor) {
        boolean hasLeft = node.hasLeft();
        boolean hasRight = node.hasRight();

        if (hasLeft) {
            inOrder(node.getLeft(), executor);
        }

        executor.execute(node.getData());

        if (hasRight) {
            inOrder(node.getRight(), executor);
        }
    }

    public T previous(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        NodeWithParentAndLatestLowerAncestor<T> nodeWithParent = findElementIterativeWithLowerAncestor(root, data);
        Node<T> previous = previous(nodeWithParent.node, nodeWithParent.parent, nodeWithParent.latestLowerAncestor);
        return previous != null ? previous.getData() : null;
    }

    private Node<T> previous(Node<T> node, Node<T> parent, Node<T> ancestor) {
        if (node.hasLeft()) {
            return findMaximumIterative(node.getLeft());
        }

        if (parent == null) {
            return null;
        }

        return ancestor;
    }

    private NodeWithParentAndLatestLowerAncestor<T> findElementIterativeWithLowerAncestor(Node<T> node, T data) {
        Node<T> current = root;
        Node<T> parent = null;
        Node<T> latestLowerAncestor = null;

        while (current != null) {
            int comparisonResult = comparator.compare(current.getData(), data);
            if (comparisonResult < 0) {
                parent = current;
                latestLowerAncestor = current;
                current = current.getRight();
            } else if (comparisonResult > 0) {
                parent = current;
                current = current.getLeft();
            } else {
                return new NodeWithParentAndLatestLowerAncestor<>(current, parent, latestLowerAncestor);
            }
        }

        throw new IllegalArgumentException("Element not found in the tree");
    }


    // private NodeWithParent<T> findElementIterative(Node<T> node, T data) {
    //     Node<T> current = root;
    //     Node<T> parent = null;

    //     while (current != null) {
    //         int comparisonResult = comparator.compare(current.getData(), data);
    //         if (comparisonResult < 0) {
    //             parent = current;
    //             current = current.getRight();
    //         } else if (comparisonResult > 0) {
    //             parent = current;
    //             current = current.getLeft();
    //         } else {
    //             return new NodeWithParent<>(current, parent);
    //         }
    //     }

    //     throw new IllegalArgumentException("Element not found in the tree");
    // }

    private Node<T> findMaximumIterative(Node<T> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    private NodeWithParent<T> findMinimumWithParentIterative(Node<T> node, Node<T> parent) {
        while (node.getLeft() != null) {
            parent = node;
            node = node.getLeft();
        }
        return new NodeWithParent<>(node, parent);
    }


    public T removeElement(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        NodeWithParentAndLatestLowerAncestor<T> nodeWithParent = findElementIterativeWithLowerAncestor(root, data);
        Node<T> node = nodeWithParent.node;
        Node<T> parent = nodeWithParent.parent;
        T result = previous(node, parent, nodeWithParent.latestLowerAncestor).getData();
        if (node.hasLeft() && node.hasRight()) {
            NodeWithParent<T> successor = findMinimumWithParentIterative(node.getRight(), node);
            T successorData = successor.node.getData();
            node.setData(successorData);
            node = successor.node;
            parent = successor.parent;
        } 

        removeWithMaxOneChild(node, parent);

        return result;
    }

    private void removeWithMaxOneChild(Node<T> node, Node<T> parent) {
        if (!node.hasLeft() && !node.hasRight()) {
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == node) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (node.hasLeft() && !node.hasRight()) {
            if (parent == null) {
                root = node.getLeft();
            } else if (parent.getLeft() == node) {
                parent.setLeft(node.getLeft());
            } else {
                parent.setRight(node.getLeft());
            }
        } else  {
            if (parent == null) {
                root = node.getRight();
            } else if (parent.getLeft() == node) {
                parent.setLeft(node.getRight());
            } else {
                parent.setRight(node.getRight());
            }
        }
    }

}
