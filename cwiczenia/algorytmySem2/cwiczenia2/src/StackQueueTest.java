

import org.junit.jupiter.api.Test;

import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.queue.StackQueue;

import static org.junit.jupiter.api.Assertions.*;


class StackQueueTest {

    @Test
    void testIsEmpty() throws FullQueueException {
        StackQueue<Integer> queue = new StackQueue<>();
        assertTrue(queue.isEmpty(), "Queue should be empty initially");

        queue.enqueue(1);
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
    }

    @Test
    void testIsFull() throws FullQueueException {
        StackQueue<Integer> queue = new StackQueue<>(2);
        assertFalse(queue.isFull(), "Queue should not be full initially");

        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.isFull(), "Queue should be full after reaching capacity");
    }

    @Test
    void testEnqueueAndDequeue() throws EmptyQueueException, FullQueueException {
        StackQueue<Integer> queue = new StackQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue(), "First dequeued element should be 1");
        assertEquals(2, queue.dequeue(), "Second dequeued element should be 2");
        assertEquals(3, queue.dequeue(), "Third dequeued element should be 3");
    }

    @Test
    void testDequeueEmptyQueue() {
        StackQueue<Integer> queue = new StackQueue<>();
        assertThrows(EmptyQueueException.class, queue::dequeue, "Dequeueing an empty queue should throw EmptyQueueException");
    }

    @Test
    void testEnqueueFullQueue() throws FullQueueException {
        StackQueue<Integer> queue = new StackQueue<>(2);
        queue.enqueue(1);
        queue.enqueue(2);
        assertThrows(FullQueueException.class, () -> queue.enqueue(3), "Enqueueing a full queue should throw FullQueueException");
    }

    @Test
    void testSize() throws EmptyQueueException, FullQueueException {
        StackQueue<Integer> queue = new StackQueue<>();
        assertEquals(0, queue.size(), "Initial size should be 0");

        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size(), "Size should be 2 after enqueueing two elements");

        queue.dequeue();
        assertEquals(1, queue.size(), "Size should be 1 after dequeuing one element");
    }

    @Test
    void testFirst() throws FullQueueException, EmptyQueueException {
        StackQueue<Integer> queue = new StackQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.first(), "First element should be 1");
        assertEquals(1, queue.first(), "First element should still be 1 after calling first()");
    }

    @Test
    void testFirstEmptyQueue() {
        StackQueue<Integer> queue = new StackQueue<>();
        assertThrows(EmptyQueueException.class, queue::first, "Calling first() on an empty queue should throw EmptyQueueException");
    }
}