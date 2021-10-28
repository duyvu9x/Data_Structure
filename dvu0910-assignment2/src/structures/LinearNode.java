/* 
 * TCSS 342
 */

package structures;

/**
 * Represents a node in a linked structure.
 * 
 * @author Lewis and Chase
 * @author Alan Fowler - formatted for TCSS 342; modified some code; added comments
 * @version 1.2
 * 
 * @param <T> the generic data type
 */
public class LinearNode<T> {
    
    /** A reference to the next node in the linked structure. */
    private LinearNode<T> myNext;
    
    /** The data element in this node. */
    private T myElement;

    /**
     * Creates an empty node.
     */
    public LinearNode() {
        myNext = null;
        myElement = null;
    }

    /**
     * Creates a node storing the specified element.
     * 
     * @param theElement the element to be stored
     */
    public LinearNode(final T theElement) {
        myNext = null;
        myElement = theElement;
    }
    
    /**
     * Initialize the node using the specified data element and
     * the specified next node.
     * 
     * @param theElement the data element held in this node
     * @param theNext the next node in the linked structure
     */
    public LinearNode(final T theElement, final LinearNode<T> theNext) {
        myElement = theElement;
        myNext = theNext;
    }

    /**
     * Returns the node that follows this one.
     * 
     * @return a reference to the next node
     */
    public LinearNode<T> getNext() {
        return myNext;
    }

    /**
     * Sets the node that follows this one.
     * 
     * @param theNode node to follow this one
     */
    public void setNext(final LinearNode<T> theNode) {
        myNext = theNode;
    }

    /**
     * Returns the data element in this node.
     * 
     * @return the data element in this node
     */
    public T getElement() {
        return myElement;
    }

    /**
     * Sets the element stored in this node.
     * 
     * @param theElement the element to be stored at this node
     */
    public void setElement(final T theElement) {
        myElement = theElement;
    }
}
