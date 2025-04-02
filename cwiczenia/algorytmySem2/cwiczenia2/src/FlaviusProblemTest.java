import aisd.queue.IQueue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlaviusProblemTest {

    @Test
    void testGetQueueWithValidInput() throws Exception {
        FlaviusProblem problem = new FlaviusProblem(5, 2);
        IQueue<Integer> resultQueue = problem.getQueue();

        assertNotNull(resultQueue, "Queue should not be null");
        assertEquals(5, resultQueue.size(), "Queue size should match the number of elements");

        int[] expectedOrder = {1, 3, 0, 4, 2}; // Expected elimination order
        for (int expected : expectedOrder) {
            assertEquals(expected, resultQueue.dequeue(), "Order of elements in the queue is incorrect");
        }
    }

    @Test
    void testGetQueueWithSingleElement() throws Exception {
        FlaviusProblem problem = new FlaviusProblem(1, 1);
        IQueue<Integer> resultQueue = problem.getQueue();

        assertNotNull(resultQueue, "Queue should not be null");
        assertEquals(1, resultQueue.size(), "Queue size should be 1");
        assertEquals(0, resultQueue.dequeue(), "The only element should be 0");
    }

    @Test
    void testGetQueueWithInvalidInput() {
        assertThrows(Exception.class, () -> new FlaviusProblem(0, 1).getQueue(), "Should throw exception for n=0");
        assertThrows(Exception.class, () -> new FlaviusProblem(5, 0).getQueue(), "Should throw exception for k=0");
    }
}