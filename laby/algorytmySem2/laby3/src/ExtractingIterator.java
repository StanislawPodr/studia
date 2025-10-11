import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExtractingIterator<T> implements Iterator<CountValuesIterator<T>> {

    T[] table;
    boolean hasNext;
    int index = 0;

    public ExtractingIterator(T[] table) {
        if(table==null) {
            throw new IllegalArgumentException();
        }
        for(T item : table) {
            if(item==null) {
                throw new IllegalArgumentException();
            }
        }
        this.table = table;
        hasNext = true;
        if (table.length == 0) {
            hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public CountValuesIterator<T> next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        int previousIndex = index;
        boolean foundNext = false;
        while (!foundNext && index + 1 != table.length) {
            index++;
            boolean nextTest = true;
            for(int i = index - 1; i >= 0; i--) {
                if(table[i].equals(table[index])) {
                    nextTest = false;
                }
            }
            if(nextTest) {
                foundNext = true;
            }
        }

        if(!foundNext) {
            hasNext = false;
        }
        return new CountValuesIterator<>(previousIndex, table);
    }

}
