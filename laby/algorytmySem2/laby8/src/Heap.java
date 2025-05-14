public interface Heap<T> {
    void clear();
    boolean add(T element);
    T minimum();
}
