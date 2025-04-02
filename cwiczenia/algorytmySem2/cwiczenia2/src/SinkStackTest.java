import org.junit.jupiter.api.Test;

import aisd.queue.EmptyQueueException;
import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;
import aisd.stack.SinkStack;

import static org.junit.jupiter.api.Assertions.*;




class SinkStackTest {

    @Test
    void testIsEmpty() throws FullStackException {
        SinkStack<Integer> stack = new SinkStack<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testIsFull() throws FullStackException {
        SinkStack<Integer> stack = new SinkStack<>(2);
        assertFalse(stack.isFull());
        stack.push(1);
        stack.push(2);
        assertTrue(stack.isFull());
    }

    @Test
    void testPushAndPop() throws FullStackException, EmptyStackException {
        SinkStack<Integer> stack = new SinkStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testPopEmptyStackException() {
        SinkStack<Integer> stack = new SinkStack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void testPushFullStackException() throws FullStackException {
        SinkStack<Integer> stack = new SinkStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThrows(FullStackException.class, () -> stack.push(3));
    }

    @Test
    void testTop() throws FullStackException, EmptyStackException {
        SinkStack<Integer> stack = new SinkStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.top());
        stack.pop();
        assertEquals(1, stack.top());
    }

    @Test
    void testTopEmptyStackException() {
        SinkStack<Integer> stack = new SinkStack<>();
        assertThrows(EmptyStackException.class, stack::top);
    }

    @Test
    void testSize() throws FullStackException, EmptyStackException {
        SinkStack<Integer> stack = new SinkStack<>();
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void testSink() throws FullStackException, EmptyQueueException {
        SinkStack<Integer> stack = new SinkStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(1, stack.sink());
        assertEquals(2, stack.sink());
    }

    @Test
    void testSinkEmptyQueueException() {
        SinkStack<Integer> stack = new SinkStack<>();
        assertThrows(EmptyQueueException.class, stack::sink);
    }

    @Test
    void testCircularBehavior() throws FullStackException, EmptyQueueException {
        SinkStack<Integer> stack = new SinkStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.sink();
        stack.push(4);
        assertEquals(2, stack.sink());
        assertEquals(3, stack.sink());
        assertEquals(4, stack.sink());
    }
}