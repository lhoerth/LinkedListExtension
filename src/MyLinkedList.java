/**
 * MyLinkedList class implements a List ADT as a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
    private int theSize;
    private int modCount = 0;

    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    public void clear() {
        doClear();
    }

    private void doClear() {
        // Since this same code would appear both in the constructor
        // and in the body of the clear method, we created a doClear helper
        // method so we don't have to repeat this code twice in our class.

        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
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
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     *
     * @param x   any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     *
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx   index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        // It is pretty common to search through the list to find the node
        // that corresponds to a given index (idx). Instead of always starting
        // at the front and walking towards the end of the list, we'll check to
        // see if the index is in the front half or the back half of the list.
        // (We're trying to be more efficient and reduce the search time by half.)

        Node<AnyType> p;

        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());
        }

        if (idx < size() / 2) {
            // if the index is in the front half of the list,
            // start p at the front and use a loop to walk forward
            p = beginMarker.next;

            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            // else the index in the back half of the list,
            // start p at the end and use a loop to walk backward
            p = endMarker;

            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }

        return p;
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

    /**
     *  Checks to see if an item exists in the list and return true if it does
     *  and false otherwise.
     *
     *  @return true if list contains item
     */
    public boolean contains(AnyType x) {
        for (int i = 0; i < theSize; i++) {
            // debug: System.out.println("getNode(i).data: " + getNode(i).data);
            if (getNode(i).data == x) {
                return true;
            }
        }
        return false;
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
     * Obtains an Iterator object used to traverse the collection.
     *
     * @return an iterator positioned prior to the first element.
     */
    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.ListIterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        private int idx = 0;
        private Node<AnyType> nextItem;

        /**
         * Inserts the specified element into the list.
         */
        public void add(AnyType x) {

        }
        public int nextIndex(){
            throw new UnsupportedOperationException("nextIndex operation not supported.");
        }
        public void set(AnyType x) {
            throw new UnsupportedOperationException("set operation not supported.");
        }
        public int previousIndex(){throw new UnsupportedOperationException("nextIndex operation not supported.");}
        public boolean hasPrevious(){
            throw new UnsupportedOperationException("nextIndex operation not supported.");
        }
        public AnyType previous(){
            throw new UnsupportedOperationException("nextIndex operation not supported.");
        }

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }
}

