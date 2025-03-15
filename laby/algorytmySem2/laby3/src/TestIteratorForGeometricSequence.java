import java.util.Iterator;

public class TestIteratorForGeometricSequence implements Iterator<Integer> {
    private int current;
    private int ratio;
    private int limit;

    public TestIteratorForGeometricSequence(int start, int ratio, int limit) {
        this.current = start;
        this.ratio = ratio;
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return current <= limit;
    }

    @Override
    public Integer next() {
        int result = current;
        current *= ratio;
        if (result > limit) {
            throw new IllegalStateException("No more elements");
        }
        return result;
    }

}
