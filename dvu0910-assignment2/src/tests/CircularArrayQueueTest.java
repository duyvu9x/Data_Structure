/* 
 * TCSS 342
 */

package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.EmptyCollectionException;
import structures.CircularArrayQueue;

/**
 * Unit tests of the CircularArrayQueue class.
 * 
 * @author Alan Fowler
 * @version 1.3
 */
class CircularArrayQueueTest {
    
    // test fixture
    
    /**
     * A CircularArrayQueue to test.
     */
    private CircularArrayQueue<Integer> myQueue;
    
    @BeforeEach
    public void init() {
        myQueue = new CircularArrayQueue<>();
    }

    /**
     * Test method for {@link structures.CircularArrayQueue#CircularArrayQueue()}.
     */
    @Test
    final void testCircularArrayQueue() {
        assertNotNull(myQueue, "myQueue was not instantiated!");
        assertEquals(0, myQueue.size(), "myQueue should be size zero!");
        assertTrue(myQueue.isEmpty(), "myQueue should be empty!");
        assertEquals("", myQueue.toString(),
                "incorrect toString() for empty Queue!");
    }

    /**
     * Test method for {@link structures.LinkedQueue#enqueue(java.lang.Object)}.
     */
    @Test
    final void testEnqueue() {
        // using toString() in tests means that the format of toString() is specified
        // OR it means we are doing white box testing
        // The advantage of using toString() is that we can test all contents in one shot
        final StringBuilder sb = new StringBuilder();
        sb.append("Front -> ");
        for (int i = 0; i <= 10; i++) {
            myQueue.enqueue(i);
            sb.append(i);
            assertEquals(sb.toString(), myQueue.toString(),
                    "toString() returns an incorrect result after enqueue()");
            sb.append(", ");
            assertEquals(i + 1, myQueue.size(),
                    "enqueue() failed to update the size of the Queue.");
            assertFalse(myQueue.isEmpty(), "myQueue should not be empty!");
        }
    }

    /**
     * Test method for {@link structures.LinkedQueue#dequeue()}.
     */
    @Test
    final void testDequeue() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            myQueue.enqueue(i);
        }
        sb.append(myQueue.toString());

        for (int i = 0; i < 9; i++) {
            assertEquals(i, myQueue.dequeue(), "dequeue() returned an unexpected result!");
            assertEquals(9 - i, myQueue.size(),
                    "dequeue() did not update the size of the queue as expected.");
            sb.delete(9, 12); //Front -> (and 3 characters - the number, comma, and space)
            assertEquals(sb.toString(), myQueue.toString(),
                    "dequeue() failed to update the contents of the Queue as expected!");
        }
        // check the queue when empty
        assertEquals(9, myQueue.dequeue(), "dequeue() returned an unexpected result!");
        assertEquals(0, myQueue.size(),
                "dequeue() did not update the size of the queue as expected.");
        assertEquals("", myQueue.toString(),
                "dequeue() failed to update the contents of the Queue as expected!");
    }
    
    /**
     * Test method for {@link structures.LinkedQueue#dequeue()}.
     */
    @Test
    public void testDequeueEmpty() {
        final Exception exception = assertThrows(EmptyCollectionException.class, () -> {
            myQueue.dequeue();
        });
        final String expectedMessage = "The queue is empty.";
        final String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage,
                "dequeue() on an empty queue did not produce the expected exception message.");
    }

    /**
     * Test method for {@link structures.LinkedQueue#first()}.
     */
    @Test
    final void testFirst() {
        myQueue.enqueue(1);
        assertEquals(1, myQueue.first(), "first() returned an unexpected result!");
        myQueue.enqueue(2);
        assertEquals(1, myQueue.first(), "first() returned an unexpected result!");
    }
    
    /**
     * Test method for {@link structures.LinkedQueue#first()}.
     */
    @Test
    public void testFirstEmpty() {
        final Exception exception = assertThrows(EmptyCollectionException.class, () -> {
            myQueue.first();
        });
        final String expectedMessage = "The queue is empty.";
        final String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage,
                "first() on an empty queue did not produce the expected exception message.");
    }

    // Tests for size(), isEmpty(), and toString() not included because these methods
    // are covered because they are used in the other tests shown above.

}
