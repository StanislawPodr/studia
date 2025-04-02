package aisd.stack;

public class VTS<T> extends ArrayStack<T> {

    int cursor;

    private void initialize() {
        cursor = super.topIndex;
    }

    public VTS(int initialSize) {
        super(initialSize);
        initialize();
    }

    public VTS() {
        initialize();
    }

    @Override
    public void push(T element) throws FullStackException {
        super.push(element);
        cursor = super.topIndex;
    }

    @Override
    public T pop() throws EmptyStackException {
        T returned = super.pop();
        cursor = super.topIndex;
        return returned;
    }

    public T top() throws EmptyStackException {
        cursor = super.topIndex;
        return super.top();
    }

    public boolean down() throws CursorOnBottomOfTheStackException {
        if (cursor == 1) {
            throw new CursorOnBottomOfTheStackException();
        }
        return (--cursor) == 1;
    }

    public T peek() {
        return super.array[cursor - 1];
    }
}
