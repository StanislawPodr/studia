import java.util.Iterator;

public class KolejneLiczby implements Iterator<Integer>{

    private int current;


    public KolejneLiczby(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        this.current = k;
    }


    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
       return current++;
    }

}
