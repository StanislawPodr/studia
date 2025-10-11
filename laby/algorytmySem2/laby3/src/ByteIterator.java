import java.util.Iterator;


public class ByteIterator implements Iterator<Integer> {

    Iterator<Integer> numbers;
    int number;
    boolean hasNext;

    public ByteIterator(Iterator<Integer> numbers) {
        if(numbers == null) {
            throw new IllegalArgumentException();
        }
        this.numbers = numbers;
        if (!getNextNumber()) {
            throw new IllegalArgumentException("No numbers in the Iterator");
        }
        this.hasNext = true;
    }

    public boolean getNextNumber() {
        if (numbers.hasNext()) {
            Integer numberInteger = numbers.next();
            if(numberInteger == null) {
                throw new IllegalArgumentException();
            }
            number = numberInteger;
            if (number < 0) {
                throw new IllegalArgumentException("Number out of range");
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    private int minIterations;

    @Override
    public Integer next() {
        if(!hasNext) {
            throw new IllegalStateException("No more elements");
        }
        int temp = number;
        int value = 0;
        int i = -1;
        while (temp > 0) {
            value = temp % 256;
            temp = temp / 256;
            i++;
            minIterations = i;
        }

        number -= value * Math.pow(256, i);
        if (number == 0 && minIterations <= 0) {
            minIterations = -1;
            if (!getNextNumber()) {
                hasNext = false;
            }
        }

        minIterations--;

        return value;
    }

}
