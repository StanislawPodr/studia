import java.util.List;

public class FirstIndexPivot<T> implements PivotStrategy<T> {

    @Override
    public int setPivot(List<T> list, int start, int end) {
        return start;
    }

}
