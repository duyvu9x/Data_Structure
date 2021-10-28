/* 
 * TCSS 342
 */

package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.EmptyCollectionException;
import structures.ArrayStack;

/**
 * Unit tests of the ArrayStack class.
 * 
 * @author Alan Fowler
 * @version 1.3
 */
public class ArrayStackTest {
    
    // test fixture
    
    /**
     * A GenericSimpleArrayStack to test.
     */
    private ArrayStack<Integer> myStack;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void init() {
        myStack = new ArrayStack<>();
    }

    /**
     * Test method for {@link structures.ArrayStack#ArrayStack()}.
     */
    @Test
    public void testArrayStack() {
        assertNotNull(myStack, "myStack was not instantiated!");
        assertEquals(0, myStack.size(), "myStack should be size zero!");
        assertTrue(myStack.isEmpty(), "myStack should be empty!");
        assertEquals("Empty Stack", myStack.toString(),
                "incorrect toString() for empty Stack!");
    }
    
    /**
     * Test method for {@link structures.ArrayStack#ArrayStack(int)}.
     */
    @Test
    public void testArrayStackOverloadedConstructor() {
        final ArrayStack<Integer> stack2 = new ArrayStack<>(50);
        assertNotNull(stack2, "stack2 was not instantiated!");
        assertEquals(0, stack2.size(), "stack2 should be size zero!");
        assertTrue(stack2.isEmpty(), "stack2 should be empty!");
        assertEquals("Empty Stack", stack2.toString(),
                "incorrect toString() for empty Stack!");
    }

    /**
     * Test method for {@link structures.ArrayStack#push(java.lang.Object)}.
     */
    @Test
    public void testPush() {
        // using toString() in tests means that the format of toString() is specified
        // OR it means we are doing white box testing
        // The advantage of using toString() is that we can test all contents in one shot
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            sb.insert(0, i + "\n");
            myStack.push(i);
            assertEquals(sb.toString(), myStack.toString(),
                    "push() failed to update the contents of the Stack as expected!");
            assertEquals(i + 1, myStack.size(),
                    "push() failed to update the size of the Stack.");
        }
    }

    /**
     * Test method for {@link structures.ArrayStack#pop()}.
     */
    @Test
    public void testPop() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            sb.insert(0, i + "\n");
            myStack.push(i);
        }
        for (int i = 10; i > 0; i--) {
            assertEquals(i, myStack.pop(), "pop() returned an unexpected result!");
            assertEquals(i, myStack.size(),
                    "pop() did not update the size of the stack as expected");
            sb.delete(0, sb.indexOf("\n") + 1);
            assertEquals(sb.toString(), myStack.toString(),
                    "pop() failed to update the contents of the Stack as expected!");
        }
        assertEquals(0, myStack.pop(), "pop() returned an unexpected result!");
        assertEquals(0, myStack.size(),
                "pop() did not update the size of the stack as expected");
        assertEquals("Empty Stack", myStack.toString(),
                "pop() failed to update the contents of the Stack as expected!");
    }
    
    /**
     * Test method for {@link GenericSimpleArrayStack#pop()}.
     */
    @Test
    public void testPopEmpty() {
        final Exception exception = assertThrows(EmptyCollectionException.class, () -> {
            myStack.pop();
        });
        final String expectedMessage = "The stack is empty.";
        final String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage,
                "pop() on an empty stack did not produce the expected exception message.");
    }

    /**
     * Test method for {@link structures.ArrayStack#peek()}.
     */
    @Test
    public void testPeek() {
        for (int i = 0; i <= 10; i++) {
            myStack.push(i);          
            assertEquals((Integer) i, myStack.peek(), "peek() returned an unexpected result!");
            // OR
            assertEquals(i, (int) myStack.peek(), "peek() returned an unexpected result!");
        }
    }

    /**
     * Test method for {@link GenericSimpleArrayStack#peek()}.
     */
    @Test
    public void testPeekEmpty() {
        final Exception exception = assertThrows(EmptyCollectionException.class, () -> {
            myStack.peek();
        });
        final String expectedMessage = "The stack is empty.";
        final String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage,
                "peek() on an empty stack did not produce the expected exception message.");
        
    }

}
