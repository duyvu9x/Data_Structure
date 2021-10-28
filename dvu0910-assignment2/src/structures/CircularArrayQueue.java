/*
 * TCSS 342
 */

package structures;

import exceptions.EmptyCollectionException;

/**
 * CircularArrayQueue represents an array implementation of a queue in which the
 * indexes for the front and rear of the queue circle back to 0 when they reach
 * the end of the array.
 * 
 * @author Lewis and Chase
 * @author Alan Fowler - formatted for TCSS 342; added comments
 * @version 1.2
 * 
 * @param <T> the generic data type
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    /** A default capacity for the array backing store. */
    private static final int DEFAULT_CAPACITY = 100;

    /** The index of the front of the queue. */
    private int myFront;

    /** The index of the rear of the queue. */
    private int myRear;

    /** The number of elements in the queue. */
    private int mySize;

    /** The array backing store for the queue. */
    private T[] myElements;

    /**
     * Creates an empty queue using the specified capacity.
     * 
     * @param theInitialCapacity the initial size of the circular array queue
     */
    public CircularArrayQueue(final int theInitialCapacity) {
        myFront = 0;
        myRear = 0;
        mySize = 0;
        myElements = (T[]) (new Object[theInitialCapacity]);
    }

    /**
     * Creates an empty queue using the default capacity.
     */
    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(final T theElement) {
        if (size() == myElements.length) {
            expandCapacity();
        }
        myElements[myRear] = theElement;
        myRear = (myRear + 1) % myElements.length;

        mySize++;
    }

    /**
     * Creates a new array to store the contents of this queue with twice the
     * capacity of the old one.
     */
    private void expandCapacity() {
        final T[] larger = (T[]) (new Object[myElements.length * 2]);

        for (int scan = 0; scan < mySize; scan++) {
            larger[scan] = myElements[myFront];
            myFront = (myFront + 1) % myElements.length;
        }

        myFront = 0;
        myRear = mySize;
        myElements = larger;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }
        final T result = myElements[myFront];
        myElements[myFront] = null;
        myFront = (myFront + 1) % myElements.length;

        mySize--;

        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        // To be completed as a Programming Project
        if (mySize == 0) {
            throw new EmptyCollectionException("The queue is empty.");
        }

        return myElements[myFront];
    }

    @Override
    public boolean isEmpty() {
        // To be completed as a Programming Project
        return mySize == 0;
    }

    @Override
    public int size() {
        // To be completed as a Programming Project

        return mySize;
    }

    /**
     * The returned String includes a comma separated listing of each element in the
     * queue and includes a label for the front of the queue. This method will
     * return an empty String if called on an empty queue.
     * 
     * <p>
     * For a queue that is not empty the format of the returned String is: Front ->
     * 8, 6, 7, 5, 3, 0, 9
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        int scan = 0;

        while (scan < mySize) {
            if (myElements[scan] != null) {
                sb.append(myElements[scan].toString() + "\n");
            }
            scan++;
        }

        return sb.toString();
    }
}
