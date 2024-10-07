package Hw3;
import java.lang.StringBuilder;

/*
Implementation requirements:
- All fields must be private
- Less than 2 fields only. -> String[] and int
- Do not use anything from the java.util package, except for methods of the java.util.Objects class.

Logical Error Corrections:
Use the int field to track how many elements have been added to the array instead of using it to
track the capacity of the array. The capacity of the array can be tracked by using arr.length.

The currentSize can include added null elements as well. It won't include the default null elements from the array.
Again, it keeps track of ADDED elements, not the default null elements.
 */
public class ArrayStringBoundedList implements StringBoundedList{
    private String[] arr;
    private int currentSize; // tracks how many elements have been added, represents currentSize

    // Constructor, 1 parameter (int capacity)
    public ArrayStringBoundedList(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException();
        }
        this.arr = new String[capacity];
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
     * For example, if {@code array} is a ArrayStringBoundedList with capacity 10 that represents {@code [a, b, c]},
     * then saying {@code array.add("d")} makes {@code array} represent {@code [a, b, c, d]}.
     * @param s the String to add, could be null
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
    public void add(String s){
        if(currentSize >= arr.length){
            throw new IllegalStateException();
        } else {
            arr[currentSize] = s; // currentSize starts counting at 0, so it will be one number above the index.
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
    public String get(int index){
        if(index < 0 || index >= currentSize){
            throw new IndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }

    /**
     * Replaces the old element at the specified index with the new element, and returns the old element.
     * @param index the index where an element should be replaced
     * @param element the new element
     * @return the old element
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    @Override
    public String set(int index, String element){
        if(index < 0 || index >= currentSize){
            throw new IndexOutOfBoundsException();
        } else {
            String oldElement = arr[index];
            arr[index] = element;
            return oldElement;
        }
    }

    /**
     * Return the index of the first occurrence of the specified String in this array, or -1 if it doesn't occur in this array.
     * @param s the String to search for (possibly null)
     * @return the index of the first occurrence of s, or -1 if not found
     *
     * if statement Logic:
     * If the String you are looking for is NOT null, then go through the array and find that index.
     * If the element you are comparing with the String is null, it will produce a NullPointerException error.
     * EX: null = c? -> NullPointerException
     * To deal with this, I use a try and catch statement to catch the NullPointerException and skip this index.
     * Skip the index with the null element and continue looking at the other elements of the array.
     *
     * Tracing example...
     * Indices:  0 1   2   3   4 5 6 7
     * Elements: a b null null b c b d     // null values were manually added in using the add() method
     * Find c
     *
     * Index: 2 -> null
     * Is null = c? No, so it produces a NullPointerException.
     * Skip this index and move on to index 3.
     *
     * Index: 3 -> null
     * Is null = c? No, so it produces a NullPointerException.
     * Skip this index and move on to index 4.
     *
     * Index: 4 -> b
     * Is b = c? No, so move on to index 5.
     *
     * Index: 5 -> c
     * Is c = c? Yes, so return the index.
     *
     * But what if the String we are looking for IS null? (Else statement)
     * Then we search through the array normally until we find null in the array. And return that index.
     *
     * I use currentSize instead of arr.length b/c we only want to look through manually added elements.
     * I don't want to find the index of a default null value.
     */
    @Override
    public int indexOf(String s){
        if(s != null){
            for(int i = 0; i < currentSize; i++){
                try{
                    if(arr[i].equals(s)){ // if the String element (content) in the array is equal to String s's content,
                        return i;         // then return the index of the String element. Comparing contents, not references/addresses.
                    }
                }catch (NullPointerException e){
                    i++;
                }
            }
        } else {
            for(int i = 0; i < currentSize; i++){
                if(arr[i] == null){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Return the index of the last occurrence of the specified String in this array, or -1 if it doesn't occur in this array.
     * @param s the String to search for (possibly null)
     * @return the index of the last occurrence of s, or -1 if not found
     *
     * Same logic as indexOf(String s) but backwards.
     * It doesn't start at the capacity of the array (arr.length), b/c I don't want to find the default null values.
     * I want to find the manually added in null elements/elements.
     */
    @Override
    public int lastIndexOf(String s){
        if(s != null){
            for(int i = currentSize - 1; i > -1; i--){
                try{
                    if(arr[i].equals(s)){ // if the String element (content) in the array is equal to String s's content,
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
}
