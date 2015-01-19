/**
 * MyArrayList class implements a List ADT using an array.
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    private AnyType[] theItems;
    private int theSize;

    /**
     * Construct an empty ArrayList.
     */
    public MyArrayList() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    public void clear() {
        doClear();
    }

    private void doClear() {
        // Since this code would appear both in the constructor
        // and in the body of the clear method, we created a doClear helper
        // method so we don't have to repeat this code twice in our class.

        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * Adds an item to this collection, at the end.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * Adds an item to this collection, at the specified index.
     *
     * @param x any object.
     */
    public void add(int idx, AnyType x) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }

        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }

        theItems[idx] = x;
        theSize++;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        AnyType removedItem = theItems[idx];

        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }

        theSize--;

        return removedItem;
    }

    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size " + size());
        }

        return theItems[idx];
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index " + idx + "; size " + size());
        }

        AnyType old = theItems[idx];
        theItems[idx] = newVal;

        return old;
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    /**
     * Returns true if this collection is empty.
     *
     * @return true if this collection is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            // if the desired capacity is less than the current size,
            // we are okay, so do nothing and return
            return;
        }

        // If we reach this point, we will need to increase our capacity.
        // To do this, we need to create a new array with the increased capacity.
        // Then we need to copy all of the items from the existing array into the new array.
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Returns a String representation of this collection.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType x : this) {
            sb.append(x + " ");
        }

        sb.append("]");

        return new String(sb);
    }

    /**
     * This is the implementation of the ArrayListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyArrayList.
     */
    private class ArrayListIterator implements java.util.Iterator<AnyType> {
        private int current = 0;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            okToRemove = true;
            return theItems[current++];
        }

        @Override
        public void remove() {
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyArrayList.this.remove(--current);
            okToRemove = false;
        }
    }
}
