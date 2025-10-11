import java.util.Iterator;
import java.util.NoSuchElementException;

public class CountValuesIterator<T> implements Iterator<T> {

    int index;
    T[] table;
    T value;
    boolean hasNext;

    public CountValuesIterator(int index, T[] table) {
        this.index = index;
        this.table = table;
        value = table[index];
        hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }

        boolean foundNext = false;
        while (!foundNext && index + 1 != table.length) {
            index++;
            if (table[index].equals(value)) {
                foundNext = true;
            }
        }
        if (!foundNext) {
            hasNext = false;
        }

        return value;
    }

}
