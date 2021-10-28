/*
 * TCSS 342
 */

package structures;

import exceptions.ElementNotFoundException;

/**
 * ArrayUnorderedList represents an array implementation of an unordered list.
 *
 * @author Lewis and Chase
 * @version 4.0
 * @author Alan Fowler - formatted for TCSS 342
 * @version Spring 2021
 * 
 * @param <T> the generic placeholder variable
 */
public class ArrayUnorderedList<T> extends AbstractArrayList<T>
                                   implements UnorderedListADT<T> {
    /**
     * Creates an empty list using the default capacity.
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Creates an empty list using the specified capacity.
     *
     * @param theInitialCapacity the initial size of the list
     */
    public ArrayUnorderedList(final int theInitialCapacity) {
        super(theInitialCapacity);
    }

    @Override
    public void addToFront(final T theElement) {
        if (size() == myList.length) {
            expandCapacity();
        }
        // shift elements up one
        System.arraycopy(myList, 0, myList, 1, myRear + 1);
        myList[0] = theElement;
        myRear++;
        myModCount++;
    }

    @Override
    public void addToRear(final T theElement) {
        if (size() == myList.length) {
            expandCapacity();
        }

        myList[myRear] = theElement;
        myRear++;
        myModCount++;
    }

    @Override
    public void addAfter(final T theElement, final T theTarget) {
        if (size() == myList.length) {
            expandCapacity();
        }

        int scan = 0;

        // find the insertion point
        while (scan < myRear && !theTarget.equals(myList[scan])) {
            scan++;
        }

        if (scan == myRear) {
            throw new ElementNotFoundException("UnorderedList");
        }

        // shift all elements after the insertion point up by one to make room
        System.arraycopy(myList, scan + 1, myList, scan + 2, myList.length - scan - 2);

        // insert element
        myList[scan + 1] = theElement;
        myRear++;
        myModCount++;
    }
}
