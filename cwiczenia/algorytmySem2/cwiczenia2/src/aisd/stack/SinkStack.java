package aisd.stack;

import aisd.queue.EmptyQueueException;

public class SinkStack<T> implements IStack<T> {

    private static final int DEFAULT_CAPACITY = 16;
    T array[];
    int beginIndex;
    int endIndex;

    @SuppressWarnings("unchecked")
    public SinkStack(int initialSize) {
        array = (T[]) (new Object[initialSize + 1]);
    }

    public SinkStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return beginIndex == endIndex;
    }

    @Override
    public boolean isFull() {
        return beginIndex == (endIndex + 1) % array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        T retValue = array[--endIndex];
        return retValue;
    }

    @Override
    public void push(T elem) throws FullStackException {
        if (isFull())
            throw new FullStackException();
        array[endIndex++] = elem;
        endIndex %= array.length;
    }

    @Override
    public int size() {
        return (endIndex + array.length - beginIndex) % array.length;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        return array[endIndex - 1];
    }

    public T sink() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        T retValue = array[beginIndex++];
        beginIndex %= array.length;
        return retValue;
    }

}
