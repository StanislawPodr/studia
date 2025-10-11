import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tasowanie implements Iterator<Integer> {
    private Iterator<Integer> first;
    private Iterator<Integer> second;
    private boolean isFirst = true;

    public Tasowanie(Iterator<Integer> first, Iterator<Integer> second) {
        this.first = first;
        this.second = second;
        if(!first.hasNext()) {
            isFirst = false;
        }
    }

    @Override
    public boolean hasNext() {
       return first.hasNext() || second.hasNext();
    }

    @Override
    public Integer next() {
        if (isFirst) {
            if (first.hasNext()) {
                isFirst = !second.hasNext();
                return first.next();
            }
        } else {
            if (second.hasNext()) {
                isFirst = first.hasNext();
                return second.next();
            }
        }
        throw new NoSuchElementException();
    }

}
