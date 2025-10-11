import java.util.Iterator;
import java.util.NoSuchElementException;

public class KtyIterator<T> implements Iterator<T> {

    private Iterator<T> original;
    private int k;
    private T next;
    private boolean hasNext;

    public KtyIterator(Iterator<T> original, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        this.original = original;
        this.k = k;
        hasNext = getNext();
    }

    boolean getNext() {
        for(int i = 0; i < k; i++) {
            hasNext = original.hasNext();
            if(hasNext == false) {
                return false;
            }
            next = original.next();
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        if(!hasNext) {
            throw new NoSuchElementException();
        }
        T result = next;
        hasNext = getNext();
        return result;
    }
    
}
