import java.util.Iterator;

public class Fibbonaci implements Iterator<Integer> {
    int current = 1;
    int previous = 0;

    @Override
    public boolean hasNext() {
       return true;
    }

    @Override
    public Integer next() {
        int temp = current;
        current += previous;
        previous = temp;
        return temp;
    }

}
