import java.util.Iterator;

public class DivisorIterable implements Iterable<Integer> {
    public DivisorIterable(int number) {
        this.number = Math.abs(number);
        this.currentDivisor = 1;
    }

    private class DivisorIterator implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            return currentDivisor < number;
        }

        @Override
        public Integer next() {
            int halfOfNumber = number / 2;
            while (currentDivisor <= halfOfNumber) {
                if (number % currentDivisor == 0) {
                    return currentDivisor++;
                }
                currentDivisor++;
            }

            if (currentDivisor == halfOfNumber + 1) {
                currentDivisor = number;
                return number;
            }

            throw new IllegalStateException("No more divisors");
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DivisorIterator();
    }

    private int number;
    private int currentDivisor;
}
