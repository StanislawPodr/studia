import java.util.List;

public class ListExecutor<T> implements Executor<T, List<T>> {
    private List<T> inputList;

    public ListExecutor(List<T> inputList) {
        if (inputList == null) {
            throw new IllegalArgumentException("Input list cannot be null.");
        }
        this.inputList = inputList;
    }

    @Override
    public void execute(T input) {
        if (inputList == null) {
            throw new IllegalStateException("Input list is not initialized.");
        }
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        inputList.add(input);
    }

    @Override
    public List<T> getResult() {
        if (inputList == null) {
            throw new IllegalStateException("No result available. Execute method must be called first.");
        }
        return inputList;
    }
}
