package aisd.queue;

import aisd.stack.ArrayStack;
import aisd.stack.IStack;

public class StackQueue<T> implements IQueue<T> {

    IStack<T> stackEnqueue;
    IStack<T> stackDequeue;
    private static final int DEFAULT_CAPACITY = 16;

    public StackQueue(int capacity) {
        stackEnqueue = new ArrayStack<>(capacity);
        stackDequeue = new ArrayStack<>(capacity);
    }

    public StackQueue() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return stackDequeue.isEmpty() && stackEnqueue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return stackDequeue.isFull() || stackEnqueue.isFull();
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        try {
            while (!stackEnqueue.isEmpty()) {
                stackDequeue.push(stackEnqueue.pop());
            }
            return stackDequeue.pop();
        } catch (Exception e) {
            throw new EmptyQueueException();
        }
    }

    @Override
    public void enqueue(T elem) throws FullQueueException {
        try {
            while (!stackDequeue.isEmpty()) {
                stackEnqueue.push(stackDequeue.pop());
            }
            stackEnqueue.push(elem);
        } catch (Exception e) {
            throw new FullQueueException();
        }
    }

    @Override
    public int size() {
        return Math.max(stackEnqueue.size(), stackDequeue.size());
    }

    @Override
    public T first() throws EmptyQueueException {
        try {
            while (!stackEnqueue.isEmpty()) {
                stackDequeue.push(stackEnqueue.pop());
            }
            return stackDequeue.top();
        } catch (Exception e) {
            throw new EmptyQueueException();
        }
    }

}
