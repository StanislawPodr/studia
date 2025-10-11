import org.junit.jupiter.api.Test;

import aisd.stack.CursorOnBottomOfTheStackException;
import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;
import aisd.stack.VTS;

import static org.junit.jupiter.api.Assertions.*;




class VTSTest {

    @Test
    void testPushAndTop() throws FullStackException, EmptyStackException {
        VTS<Integer> stack = new VTS<>(5);
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.top());
        stack.push(30);
        assertEquals(30, stack.top());
    }

    @Test
    void testPop() throws FullStackException, EmptyStackException {
        VTS<Integer> stack = new VTS<>(5);
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void testPeek() throws FullStackException, EmptyStackException {
        VTS<Integer> stack = new VTS<>(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.peek());
        stack.pop();
        assertEquals(20, stack.peek());
    }

    @Test
    void testDown() throws FullStackException, CursorOnBottomOfTheStackException {
        VTS<Integer> stack = new VTS<>(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(stack.size(), 3);
        assertFalse(stack.down());
        assertTrue(stack.down());
        assertThrows(CursorOnBottomOfTheStackException.class, stack::down);
    }

    @Test
    void testFullStackException() {
        VTS<Integer> stack = new VTS<>(2);
        assertDoesNotThrow(() -> {
            stack.push(10);
            stack.push(20);
        });
        assertThrows(FullStackException.class, () -> stack.push(30));
    }

    @Test
    void testEmptyStackException() {
        VTS<Integer> stack = new VTS<>(5);
        assertThrows(EmptyStackException.class, stack::top);
        assertThrows(EmptyStackException.class, stack::pop);
    }
}