import java.util.List;
import java.util.Random;

public class RandomPivot<T> implements PivotStrategy<T> {

    private Random random = new Random();

    @Override
    public int setPivot(List<T> list, int start, int end) {
        int pivot = start + random.nextInt(end - start + 1);
        return pivot;
    }

}
