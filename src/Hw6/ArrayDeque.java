package Hw6;

import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A deque is a collection that allows the user to insert, remove, and examine at both the head (front, first element)
 * and the tail (back, last element).
 * An ArrayDeque is a deque that uses an internal array to store its elements.
 * The capacity expands to accommodate as many elements as the user adds.
 *
 * Two preliminary notes:
 * - ArrayDeque is similar to ArrayQueue from class, but has more methods.
 * - The JCF has an ArrayDeque class, but here we are writing our own.
 */
public class ArrayDeque<E> implements Iterable<E>{
    private E[] arr;
    private int indexOfFirst;
    private int indexOfLast;
    private static int DEFAULT_INITIAL_CAPACITY = 10;

    public ArrayDeque(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        this.arr = (E[]) new Object[initialCapacity];
        indexOfFirst = indexOfLast = -1;
    }

    /**
     * @return The number of elements currently in deque
     * Notes in NB: 11/21/24 w/ Stars
     */

    public int size(){ // O(1)
        // Case 0: There are no elements in deque, empty ArrayDeque
        if(indexOfFirst == -1 && indexOfLast == -1){
            return 0;
        } else if(indexOfFirst <= indexOfLast){ // Case 1: First is before Last, meaning the deque's elements didn't loop back around to the front of deque.
            return indexOfLast - indexOfFirst + 1;
        } else { // Case 2: First is after Last, meaning the deque's elements loop back around to the front of the deque.
            return (indexOfLast + 1) + (arr.length - indexOfFirst);
        }
    }

    public boolean isEmpty(){ // O(1)
        return size() == 0;
    }

    public boolean isFull(){
        return size() == arr.length;
    }

    /**
     * Method creates a new array with the desired capacity and copies the elements from the old array into the new array.
     * @param desiredCapacity
     */
    public void ensureCapacity(int desiredCapacity){ // O(n), copies old elements into new array.
        if(arr.length < desiredCapacity){
            E[] newArray = (E[]) new Object[desiredCapacity];

            for(int i = 0; i < size(); i++){
                newArray[i] = arr[(indexOfFirst + i) % arr.length];
            }

            arr = newArray;
            indexOfFirst = 0;
            indexOfLast = size() - 1;
        }
    }

    /**
     * Method that adds an element to the front of a deque. In this case, the front is always before the current indexOfFirst.
     * If there's no room before the indexOfFirst, use a ring-buffer/wrap around the array to an empty space.
     * If there's no more room to add elements into the array, create a new array that is 2*current capacity + 1.
     * IOF = indexOfFirst
     * IOL = indexOfLast
     * @param element the element to be added before indexOfFirst
     *
     * Case 0: Empty ArrayDeque
     * [] -- IOF: -1, IOL: -1
     * addFirst(Z);
     * [Z} -- IOF: 0, IOL: 0
     *
     * Case 1.1: Non-empty ArrayDeque, There's space before IOF
     * [c, d, e, null, null, a, b] -- IOF: 5, IOL: 2
     * addFirst(Z);
     * [c, d, e, null, Z, a, b] -- IOF: 4, IOL: 2
     *
     * Case 1.2: Non-empty ArrayDeque, There's no space before IOF
     * [a, b, c, null, null] -- IOF: 0, IOL: 2
     * addFirst(Z);
     * [a, b, c, null, Z] -- IOF: 4, IOL: 2, since there's no space before a, we wrap around the array
     * and put Z in the next available space in the array.
     *
     * Important note:
     * - There should never be a gap in between indexOfFirst and indexOfLast.
     * (Throughout indexOfFirst and indexOfLast there should never be nulls, it should be only have continuous elements)
     *
     * Notes + Cases w/ visuals in NB: 11/21/24
     */
    public void addFirst(E element){ // Amortized O(1), the only time it is O(n) is when an array needs to be resized.
        if(isFull()){ // If deque is full, expand capacity to 2 * (current capacity) + 1
            ensureCapacity(2 * arr.length + 1);
        }

        if(isEmpty()){ // Case 0: Empty ArrayDeque
            arr[0] = element;
            indexOfFirst = indexOfLast = 0;
        } else { // Case 1: Non-empty ArrayDeque
            indexOfFirst--; // Case 1.1: There's space before IOF
            if(indexOfFirst == -1){ // Case 1.2: There's no space before IOF
                indexOfFirst = arr.length - 1;
            }
            arr[indexOfFirst] = element;
            // OR:
            // indexOfFirst = (indexOfFirst - 1 + arr.length) % arr.length;
            // arr[indexOfFirst] = element;
        }
    }

    /**
     * Method that adds element to the end of a deque. In this case, the end is always after the current indexOfLast.
     * If there's no room after the indexOfLast, use a ring-buffer/wrap around the array to an empty space.
     * If there's no more room to add elements into the array, create a new array that is 2*current capacity + 1.
     * @param element the element to be added after indexOfLast
     *
     * Case 0: Empty ArrayDeque
     * [] -- IOF: -1, IOL: -1
     * addLast(Z);
     * [Z] -- IOF: 0, IOL: 0
     *
     * Case 1.1: Non-empty ArrayDeque, There's space after IOL
     * [a, b, c, null, null] -- IOF: 0, IOL: 2
     * addLast(Z);
     * [a, b, c, Z, null] -- IOF: 0, IOL: 3
     *
     * [c, d, e, null, null, a, b] -- IOF: 5, IOL: 2
     * addLast(Z);
     * [c, d, e, Z, null, a, b] -- IOF: 5, IOL: 3
     *
     * Case 1.2: Non-empty ArrayDeque, There's no space after IOL
     * [null, null, a, b, c] -- IOF: 2, IOL: 4
     * addLast(Z);
     * [Z, null, a, b, c] -- IOF: 2, IOL: 0, since there's no space after c, we wrap around the array
     * and put Z in the next available space in the array.
     *
     * Important note:
     * - There should never be a gap in between indexOfFirst and indexOfLast.
     * (Throughout indexOfFirst and indexOfLast there should never be nulls, it should be only have continuous elements)
     */
    public void addLast(E element){ // Amortized O(1)
        if(isFull()){
            ensureCapacity(2 * arr.length + 1);
        }

        // Case 0: Empty ArrayDeque
        if(isEmpty()){
            arr[0] = element;
            indexOfFirst = indexOfLast = 0;
        } else {
            // Case 1: Non-empty ArrayDeque
            indexOfLast++; // Case 1.1: There's space after IOL
            if(indexOfLast == arr.length){ // Case 1.2: There's no space after IOL
                indexOfLast = 0;
            }
            arr[indexOfLast] = element;
        }
    }

    /**
     * @throws NoSuchElementException if the ArrayDeque is empty
     * @return first element of the deque, not the first element of the array!!
     */
    public E getFirst(){ // O(1)
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return arr[indexOfFirst];
    }

    /**
     * @throws NoSuchElementException if the ArrayDeque is empty
     * @return last element of the deque, not the last element of the array!!
     */
    public E getLast(){ // O(1)
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return arr[indexOfLast];
    }

    /**
     * Retrieves and removes the first element of this deque.
     * @throws NoSuchElementException if the ArrayDeque is empty
     * @return the first element of this deque, the one that is removed.
     *
     * Case 1: There's only one element in ArrayDeque, size() == 1
     * [a] -- IOF: 0, IOL: 0
     * removeFirst();
     * [null] -- IOF: -1, IOL: -1
     *
     * Case 2.1: IOF isn't at the end of the array (IOF != arr.length - 1). After IOF++, IOF should be within arr.length
     * [a, b, c, null, null] -- IOF: 0, IOL: 2
     * removeFirst();
     * [null, b, c, null, null] -- IOF: 1, IOL: 2
     *
     * [c, d, e, null, null, a, b] -- IOF: 5, IOL: 2
     * removeFirst();
     * [c, d, e, null, null, null, b] -- IOF: 6, IOL: 2
     *
     * Case 2.2: IOF is at the end of the array (IOF = arr.length - 1). After IOF++, IOF should be < arr.length. If not, set IOF to 0.
     * [b, c, null, null, a] -- IOF: 4, IOL: 1
     * removeFirst();
     * [b, c, null, null, null] -- IOF: 0, IOL: 1
     */
    public E removeFirst(){ // O(1)
        // Case 0: The ArrayDeque is empty
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        E oldElement = arr[indexOfFirst];
        arr[indexOfFirst] = null;

        if(indexOfFirst == indexOfLast){ // Case 1: There's only one element in ArrayDeque, size() == 1
            indexOfFirst = indexOfLast = -1;
        } else { // Case 2: There's more than one element in ArrayDeque
            indexOfFirst++; // Case 2.1: IOF isn't at the end of the array. After IOF++, IOF should be within arr.length
            if(indexOfFirst == arr.length){ // Case 2.2: IOF is at the end of the array, use ring-buffer and
                indexOfFirst = 0;           // wrap around array so that IOF is 0. After IOF++, IOF should be < arr.length. If not, set IOF to 0.
            }
        }

        return oldElement;
    }

    /**
     * Retrieves and removes the last element of this deque.
     * @throws NoSuchElementException if the ArrayDeque is empty
     * @return the last element of this deque, the one that is removed.
     *
     * Case 1: There's only one element in ArrayDeque, size() == 1
     * [a] -- IOF: 0, IOL: 0
     * removeLast();
     * [null] -- IOF: -1, IOL: -1
     *
     * Case 2.1: IOL isn't at the front of the array (IOL != 0). There is more elements to the left, before IOL
     * [a, b, c, null, null] -- IOF: 0, IOL: 2
     * removeLast();
     * [a, b, null, null, null] -- IOF: 0, IOL: 1
     *
     * [c, d, e, null, a, b] -- IOF: 4, IOL: 2
     * removeLast();
     * [c, d, null, null, a, b] -- IOF: 4, IOL: 1
     *
     * Case 2.2: IOL is at the front of array (IOL = 0). There is no more room to the left, before IOL.
     * [b, null, null, a] -- IOF: 3, IOL: 0
     * removeLast();
     * [null, null, null, a] -- IOF: 3, IOL: 3
     */
    public E removeLast(){ // O(1)
        // Case 0: The ArrayDeque is empty
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        E oldElement = arr[indexOfLast];
        arr[indexOfLast] = null;

        // Case 1: There's only one element in ArrayDeque, size() == 1
        if(indexOfFirst == indexOfLast){
            indexOfFirst = indexOfLast = -1;
        } else { // Case 2: There's more than one element in ArrayDeque
            indexOfLast--; // Case 2.1: IOL isn't at the front of the array (IOL != 0). There is more room to the left, before IOL
            if(indexOfLast == -1){ // Case 2.2: IOL is at index 0 of the array then perform ring-buffer and circle to the end of array.
                indexOfLast = arr.length - 1;
            }
        }
        return oldElement;
    }

    /**
     * @return a String containing all the elements in order from first to last, separated by the standard comma and
     * space; there should be the standard square brackets at the beginning and end.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < size(); i++){
            sb.append(arr[(indexOfFirst + 1) % arr.length]);

            if(i < size() - 1){
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    /* Implementing Iterable interface:
       The Iterator should retrieve the elements in order from first to the last. You may have as many fields as you
       wish in the class that implements Iterator.
    */
    @Override
    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<E>{
        private int currentIndex = indexOfFirst;
        boolean reachedEnd = isEmpty();

        @Override
        public boolean hasNext(){
            return !reachedEnd;
        }

        @Override
        public E next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            if(currentIndex == indexOfLast){
                reachedEnd = true;
            }

            E result = arr[currentIndex];
            currentIndex = (currentIndex + 1) % arr.length;
            return result;
        }
    }
}
