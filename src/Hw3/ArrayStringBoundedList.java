package Hw3;
import java.lang.StringBuilder;

/*
Implementation requirements:
- All fields must be private
- Less than 2 fields only. -> String[] and int
- Do not use anything from the java.util package, except for methods of the java.util.Objects class.
 */
public class ArrayStringBoundedList implements StringBoundedList{
    private String[] arr;
    private int capacity;

    // Constructor, 1 parameter (int capacity)
    public ArrayStringBoundedList(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException();
        }
        this.arr = new String[capacity];
        this.capacity = capacity;
    }

    // Override Abstract methods of the interface.
    // No need to override default methods as they have already been implemented.
    /**
     * Returns the maximum number of elements that this array can hold at the same time.
     * @return the capacity
     */
    @Override
    public int capacity(){
        return capacity;
    }

    /**
     * Returns the number of elements currently held in this array.
     * @return the size, which is less than or equal to the capacity
     * Starts counting with 1, not 0.
     *
     * # OF ELEMENTS IN ARRAY!!
     */
    @Override
    public int size(){
        int currentSize = 0;
        for(int i = 0; i < capacity; i++){
            if(arr[i] != null){
                currentSize++;
            }
        }
        return currentSize;
    }

    /**
     * Adds the specified element to the end of this array, if the array isn't already full.
     * For example, if {@code array} is a ArrayStringBoundedList with capacity 10 that represents {@code [a, b, c]},
     * then saying {@code array.add("d")} makes {@code array} represent {@code [a, b, c, d]}.
     * @param s the String to add, could be null
     * @throws IllegalStateException if this list is already full
     */
    @Override
    public void add(String s){
        if(size() >= capacity){
            throw new IllegalStateException();
        } else {
            arr[size() + 1] = s;
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
        if(index < 0 || index >= arr.length){ // or index >= capacity?
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
        if(index < 0 || index >= arr.length){ // or index >= capacity?
            throw new IndexOutOfBoundsException();
        } else {
            return arr[index] = element;
        }
    }

    /**
     * Return the index of the first occurrence of the specified String in this array, or -1 if it doesn't occur in this array.
     * @param s the String to search for (possibly null)
     * @return the index of the first occurrence of s, or -1 if not found
     */
    @Override
    public int indexOf(String s){
        for(int i = 0; i < capacity; i++){
            if(arr[i].equals(s)){ // if the String element (content) in the array is equal to String s's content,
                return i;         // then return the index of the String element. Comparing contents, not references/addresses.
            }
        }
        return -1;
    }

    /**
     * Return the index of the last occurrence of the specified String in this array, or -1 if it doesn't occur in this array.
     * @param s the String to search for (possibly null)
     * @return the index of the last occurrence of s, or -1 if not found
     */
    @Override
    public int lastIndexOf(String s){
        for(int i = capacity; i > -1; i--){
            if(arr[i].equals(s)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Makes the array empty.
     */
    @Override
    public void clear(){
        for(int i = 0; i < capacity; i++){
            arr[i] = null;
        }
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
        for(int i = 0; i < capacity; i++){
            fullString.append(arr[i]);
            if(i < capacity - 1){
                fullString.append(", ");
            }
        }
        fullString.append("]");
        return fullString.toString();
    }
}
