package Hw4;
import java.lang.StringBuilder;

/*
Homework 4 Part 1:
Generify the interface and class from HW 3. Write:
- an interface BoundedList<E>, and
- a class ArrayBoundedList<E> that implements BoundedList<E>

Methods overridden in ArrayBoundedList interface:
Methods add(both versions), and set should now have a PARAMETER of type E instead of type String.
String -> E Parameter

Methods indexOf and lastIndexOf should now have a PARAMETER
of type Object instead of String. (Using E here instead of Object should be fine too)
String -> Object Parameter

Methods get, set, and remove(the one with an int parameter) should now have E, instead of String, as their RETURN TYPE.
String -> E Return type

Methods that weren't modified here b/c it is already implemented as a default method in BoundedList<E> interface:
setFirst, setLast, contains, remove (the one that returns a boolean), getFirst, and getLast

ArrayBoundedList implementation requirements:
- All fields must be private.
- No more than two fields. One field must be an array, and the other field must be an int.
- Do not use anything from the java.util package, except for methods of the java.util.Objects class.
- Do not make a copy of the array.

Notes...
How to instantiate a Generic instance: (More info in NB, 9/16/24)
1) Create an Object class instance/object. new Object...
2) Cast that Object instance to Generic E. (E)
EX: (E[]) new Object[2];
 */

public class ArrayBoundedList<E> implements BoundedList<E> {
    private E[] arr;
    // ^^ An array of type E. E is a placeholder for the actual class.
    // Actual class can be any reference data type. Generics don't work with primitive types in Java.
    private int currentSize; // tracks how many elements have been added, represents currentSize

    // Constructor, 1 parameter (int capacity)
    @SuppressWarnings("unchecked")
    // ^^ Applied to constructors + methods.
    // Ignores warnings about "unchecked" exceptions. (Used for Generic Arrays)
    public ArrayBoundedList(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException();
        }
        this.arr = (E[]) new Object[capacity];
        this.currentSize = 0;
    }

    // Override Abstract methods of the interface.
    // No need to override default methods as they have already been implemented.
    /**
     * Returns the maximum number of elements that this array can hold at the same time.
     * @return the capacity
     */
    @Override
    public int capacity(){
        return arr.length;
    }

    /**
     * Returns the number of elements currently held in this array.
     * @return the size, which is less than or equal to the capacity
     *
     * # OF ELEMENTS IN ARRAY!!
     */
    @Override
    public int size(){
        return currentSize;
    }

    /**
     * Adds the specified element to the end of this array, if the array isn't already full.
     * For example, if {@code array} is a ArrayBoundedList with capacity 10 that represents {@code [a, b, c]},
     * then saying {@code array.add("d")} makes {@code array} represent {@code [a, b, c, d]}.
     * @param e the data type to add, could be null
     * @throws IllegalStateException if this list is already full
     *
     * currentSize starts counting at 1, so it will be one number above the index.
     * Indices:     0 1 2 3 4
     * elements:    a b c d e
     * currentSize: 1 2 3 4
     *
     * arr[currentSize] = "e";
     * arr[4] = "e"
     * currentSize++; -> 5
     */
    @Override
    public void add(E e){
        if(currentSize >= arr.length){
            throw new IllegalStateException();
        } else {
            arr[currentSize] = e; // currentSize starts counting at 0, so it will be one number above the index.
            currentSize++;
        }
    }

    /**
     * Returns the element at the specified index in this array.
     * @param index the position of the desired element.
     * @return the element (possibly null) at the specified position
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    @Override
    public E get(int index){
        if(index < 0 || index >= currentSize){
            throw new IndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }

    /**
     * Replaces the old element at the specified index with the new element, and returns the old element.
     * @param index the index where an element should be replaced
     * @param e the new element
     * @return the old element
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    @Override
    public E set(int index, E e){
        if(index < 0 || index >= currentSize){
            throw new IndexOutOfBoundsException();
        } else {
            E oldElement = arr[index];
            arr[index] = e;
            return oldElement;
        }
    }

    /**
     * Return the index of the first occurrence of the specified Object in this array, or -1 if it doesn't occur in this array.
     * @param o the Object to search for (possibly null)
     * @return the index of the first occurrence of o, or -1 if not found
     *
     * Logic:
     * If the Object o you are looking for IS null, then compare the element with null.
     * If null == null then return that index.
     *
     * If the Object o you are looking for is NOT null and the element is equal to o then return that index.
     * if "c" = "c" then return index; -> (If the Object is a String.)
     * element = o (target Object) then return element's index.
     */
    @Override
    public int indexOf(Object o){
        for(int i = 0; i < currentSize; i++) {
            if(o == null){
                if(arr[i] == null){
                    return i; // Return index if both o and arr[i] are null
                }
            } else {
                if(arr[i] != null && arr[i].equals(o)){ // Compare only if arr[i] is not null
                    return i;
                }
            }
        }
        return -1;


    }

    /**
     * Return the index of the last occurrence of the specified Object in this array, or -1 if it doesn't occur in this array.
     * @param o the Object to search for (possibly null)
     * @return the index of the last occurrence of o, or -1 if not found
     *
     * Same logic as indexOf(Object o) but backwards.
     * It doesn't start at the capacity of the array (arr.length), b/c I don't want to find the default null values.
     * I want to find the manually added in null elements/elements.
     */
    @Override
    public int lastIndexOf(Object o){
        if(o != null){
            for(int i = currentSize - 1; i > -1; i--){
                try{
                    if(arr[i].equals(o)){ // if the String element (content) in the array is equal to String s's content,
                        return i;         // then return the index of the String element. Comparing contents, not references/addresses.
                    }
                } catch(NullPointerException e) {
                    i--;
                }
            }
        } else {
            for(int i = currentSize - 1; i > -1; i--){
                if(arr[i] == null){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Makes the array empty.
     */
    @Override
    public void clear(){
        for(int i = 0; i < arr.length; i++){
            arr[i] = null;
        }
        currentSize = 0;
    }

    // Override toString() of Object class.

    /**
     * @return list of elements in order, enclosed in square brackets [].
     * Adjacent elements should be separated by comma and a space.
     * EX: [] or [a, b, c] or [a, null, b]
     */
    @Override
    public String toString(){
        StringBuilder fullString = new StringBuilder();
        fullString.append("[");
        for(int i = 0; i < currentSize; i++){
            fullString.append(arr[i]);
            if(i < currentSize - 1){
                fullString.append(", ");
            }
        }
        fullString.append("]");
        return fullString.toString();
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right by one position.
     *
     * Ex: list has a capacity of 10 and represents [a, b, c, d].
     * list.add(2, "x") should make list represent [a, b, x, c, d].
     *
     * @param index, insert element at this index
     * @param e, element to be inserted into list
     * @throws IllegalStateException if the array is already full.
     * @throws IndexOutOfBoundsException if the provided index is negative, or if it is greater than the size.
     * Note: it is possible to add an element at index size, as long as the list isn't full.
     */
    @Override
    public void add(int index, E e){
        // Check if the array is full
        if(currentSize == arr.length){
            throw new IllegalStateException();
        }

        // Check if the index is out of bounds
        if(index < 0 || index > arr.length){
            throw new IndexOutOfBoundsException();
        }

        // Shift elements to the right starting from the last element to the index
        for(int i = currentSize; i > index; i--){
            arr[i] = arr[i-1]; // Move the element one position to the right
        }

        // Insert the new element at the specified index
        arr[index] = e;

        // Increment the size of the list
        currentSize++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts all subsequent elements to the left by one position to fill in the gap.
     * Returns the element that was removed.
     *
     * If E was a String:
     * Ex: list = [a, b, c, d]
     * list.remove(1) should make the list represent [a, c, d] not [a, null, c, d]
     *
     * Trace:
     * oldElement = b
     * hold = b
     * hold2 = b
     *  1  2  3  4 - currentSize
     *  0  1  2  3 - indices
     * [a, c, d, null]
     * i = 0, 0 >= 1
     *
     * @param index element at this index to be removed.
     * @return data type E, element that was removed.
     * @throws IndexOutOfBoundsException if the provided index is negative, or greater than or equal to the size.
     */
    @Override
    public E remove(int index){
        if(index < 0 || index >= currentSize){
            throw new IndexOutOfBoundsException();
        }
        E oldElement = arr[index];

        E hold = arr[currentSize - 1];
        E hold2;

        for(int i = currentSize - 2; i >= index; i--){
            hold2 = arr[i];
            arr[i] = hold;
            hold = hold2;
        }
        arr[currentSize - 1] = null; // replaces last element of the array with null, shortens array by 1
        currentSize--;

        return oldElement;
    }
}

