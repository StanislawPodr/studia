import java.util.List;

public interface PivotStrategy<T> {
    int setPivot(List<T> list, int start, int end);
}
