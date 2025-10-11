import java.util.Stack;

public class TrieDictionary<V> {
    private TrieNode<V> root;

    public TrieDictionary() {
        this.root = new TrieNode<>('\0', null, null, null);
    }

    private static class TrieNode<T> {
        char character;
        T value;
        TrieNode<T> next;
        TrieNode<T> child;

        TrieNode(char character, T value, TrieNode<T> child, TrieNode<T> next) {
            this.character = character;
            this.value = value;
            this.next = next;
            this.child = child;
        }

        public void addChild(TrieNode<T> childNode) {
            TrieNode<T> nextChild = this.child;
            this.child = childNode;
            childNode.next = nextChild;
        }

        public void addNext(TrieNode<T> nextNode) {
            TrieNode<T> nextNext = this.next;
            this.next = nextNode;
            nextNode.next = nextNext;
        }

        public static <T> void remove(TrieNode<T> parent, TrieNode<T> toRemove) {
            TrieNode<T> newNext = toRemove.next;
            if (parent.child == toRemove) {
                parent.child = newNext;
            } else {
                TrieNode<T> current = parent.child;
                while (current.next != toRemove) {
                    current = current.next;
                }
                current.next = newNext;
            }
        }

        public boolean hasChild() {
            return this.child != null;
        }

        public boolean hasValue() {
            return this.value != null;
        }

    }

    public V insert(String key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }

        if (key.isEmpty()) {
            V oldValue = root.value;
            root.value = value;
            return oldValue;
        }

        if (!root.hasChild()) {
            insertToEmpty(key, value, 0, root);
            return null;
        }

        TrieNode<V> previous = root;
        int current_char_index = 0;
        TrieNode<V> node = root.child;
        boolean isFirst = true;
        while (current_char_index < key.length()) {
            if (node == null) {
                TrieNode<V> newNode = new TrieNode<>(key.charAt(current_char_index), null, null, null);
                if (isFirst) {
                    previous.addChild(newNode);
                } else {
                    previous.addNext(newNode);
                }
                insertToEmpty(key, value, current_char_index + 1, newNode);
                return null;
            } else if (node.character == key.charAt(current_char_index)) {
                current_char_index++;
                previous = node;
                node = node.child;
                isFirst = true;
            } else if (node.character > key.charAt(current_char_index)) {
                TrieNode<V> newNode = new TrieNode<>(key.charAt(current_char_index), null, null, null);
                if (isFirst) {
                    previous.addChild(newNode);
                } else {
                    previous.addNext(newNode);
                }
                insertToEmpty(key, value, current_char_index + 1, newNode);
                return null;
            } else {
                previous = node;
                node = node.next;
                isFirst = false;
            }
        }
        V oldValue = previous.value;
        previous.value = value;
        return oldValue;
    }

    public V search(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        if (key.isEmpty()) {
            return root.value;
        }

        if (!root.hasChild()) {
            return null;
        }

        TrieNode<V> node = root.child;
        int current_char_index = 0;
        while (node != null) {
            if (node.character == key.charAt(current_char_index)) {
                current_char_index++;
                if (current_char_index == key.length()) {
                    return node.value;
                }
                node = node.child;
            } else if (node.character > key.charAt(current_char_index)) {
                return null;
            } else {
                node = node.next;
            }
        }
        return null;
    }

    public V remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        if (key.isEmpty()) {
            V oldValue = root.value;
            root.value = null;
            return oldValue;
        }

        if (!root.hasChild()) {
            return null;
        }

        Stack<TrieNode<V>> stack = getStackToNode(key);
        if (stack == null) {
            return null;
        }

        TrieNode<V> foundNode = stack.pop();
        V oldValue = foundNode.value;
        if (foundNode.hasChild()) {
            foundNode.value = null;
            return oldValue;
        }

        TrieNode<V> current = foundNode;
        while (!current.hasChild() && !stack.isEmpty()) {
            TrieNode<V> parent = stack.pop();
            TrieNode.remove(parent, current);
            current = parent;
        }
        return oldValue;
    }

    private Stack<TrieNode<V>> getStackToNode(String key) {
        TrieNode<V> node = root.child;
        int current_char_index = 0;
        TrieNode<V> foundNode = null;
        Stack<TrieNode<V>> stack = new Stack<>();
        stack.push(root);
        while (foundNode == null && node != null) {
            if (node.character == key.charAt(current_char_index)) {
                current_char_index++;
                if (current_char_index == key.length()) {
                    foundNode = node;
                } else if (node.hasValue()) {
                    stack.clear();
                }
                stack.push(node);
                node = node.child;
            } else if (node.character > key.charAt(current_char_index)) {
                return null;
            } else {
                node = node.next;
            }
        }
        if (foundNode == null) {
            return null;
        }
        return stack;
    }

    private static <V> void insertToEmpty(String key, V value, int index, TrieNode<V> currentNode) {
        TrieNode<V> newNode = currentNode;
        while (index < key.length()) {
            newNode = new TrieNode<>(key.charAt(index), null, null, null);
            currentNode.child = newNode;
            currentNode = newNode;
            index++;
        }
        newNode.value = value;
    }
}
