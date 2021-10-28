/*
 * TCSS 342
 */

package structures;

/**
 * Defines operations for a FIFO queue.
 * 
 * @author Alan Fowler - An adaptation of code from several textbooks
 * @version 1.1
 * 
 * @param <T>
 */
public interface QueueADT<T> {

    /**
     * Adds the specified element to the 'rear' or 'tail' of the queue.
     * 
     * @param theElement the element to add to the queue
     */
    void enqueue(T theElement);

    /**
     * Removes and returns the 'front' or 'head' element from the queue.
     * 
     * @throws EmptyCollectionException if the queue is empty.
     * @return the front element from the queue
     */
    T dequeue();

    /**
     * Returns the 'front' or 'head' element from the queue (without removing the
     * element).
     * 
     * @throws EmptyCollectionException if the queue is empty.
     * @return the front element from the queue
     */
    T first();

    /**
     * How many elements are in the queue?
     * 
     * @return the count of elements currently in the queue
     */
    int size();

    /**
     * Is the queue empty?
     * 
     * @return True if the queue contains no elements; False otherwise
     */
    boolean isEmpty();

}
