public interface Executor<T, R> {
    void execute(T input);
    R getResult();
}
