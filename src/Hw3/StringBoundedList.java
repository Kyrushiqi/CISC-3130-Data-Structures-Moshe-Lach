package Hw3;
/*
Default method: A non-abstract instance method in an interface. For example:
interface I {
    void m1(); // abstract instance method
    default void m2(){   // non-abstract instance method
        // statements
    }
}

A default method can invoke an abstract method. For example:
interface I {
    void m1();
    default void m2(){
        m1();
    }
}
 */

/**
 * A capacity-bounded list of String elements.
 * Each list has a capacity, which is the maximum number of elements that the list can hold at one time.
 * Each list also has a size, which is the number of elements currently held by the list.
 * The elements of a list have indexes: the first element is at index 0, the second element is at index 1, and so on.
 * Null elements are allowed.
 */
public interface StringBoundedList {
    /**
     * Returns the number maximum number of elements that this list can hold at the same time.
     * @return the capacity
     */
    int capacity();

    /**
     * Returns the number of elements currently held in this list.
     * @return the size, which is less than or equal to the capacity
     * Starts counting with 1, not 0.
     */
    int size();

    /**
     * Adds the specified element to the end of this list, if the list isn't already full. 
     * For example, if {@code list} is a StringBoundedList with capacity 10 that represents {@code [a, b, c]}, 
     * then saying {@code list.add("d")} makes {@code list} represent {@code [a, b, c, d]}.
     * @param s the String to add
     * @throws IllegalStateException if this list is already full
     */
    void add(String s);

    /**
     * Returns the element at the specified index in this list.
     * @param index the position of the desired element.
     * @return the element (possibly null) at the specified position
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    String get(int index);

    /**
     * Replaces the old element at the specified index with the new element, and returns the old element.
     * @param index the index where an element should be replaced
     * @param element the new element
     * @return the old element
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    String set(int index, String element);

    /**
     * Return the index of the first occurrence of the specified String in this list, or -1 if it doesn't occur in this list.
     * @param s the String to search for (possibly null)
     * @return the index of the first occurrence of s, or -1 if not found
     */
    int indexOf(String s);

    /**
     * Return the index of the last occurrence of the specified String in this list, or -1 if it doesn't occur in this list.
     * @param s the String to search for (possibly null)
     * @return the index of the last occurrence of s, or -1 if not found
     */
    int lastIndexOf(String s);

    /**
     * Makes the list empty.
     */
    void clear();

    /**
     * Checks whether the List is empty or not.
     * @return true (List is empty) or false (List is not empty)
     */
    default boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Checks if the list is full or not.
     * Is size equal to the capacity?
     * @return true (size == capacity) or false (size != capacity)
     */
    default boolean isFull(){
        return size() == capacity();
    }

    /**
     * @return first element of this list.
     * @throws IndexOutOfBoundsException if this list is empty.
     * Note: get(int index) method can throw this exception for you.
     */
    default String getFirst(){
        return get(0);
    }

    /**
     * @return last element of this list.
     * @throws IndexOutOfBoundsException if this list is empty.
     */
    default String getLast(){
        return get(size() - 1); // size() - 1 b/c size() starts counting from 1 and not 0.
    }

    /**
     * Replaces the first element of this list with the specified element.
     * @param element new element that replaces first element.
     * @return String of new elements...?
     * @throws IndexOutOfBoundsException if this list is empty.
     * Note: set(int index, String element) method can throw the exception for you.
     */
    default String setFirst(String element){
        return set(0, element);
    }

    /**
     * Replaces the last element of this list with the specified element.
     * @param element new element that replaces last element
     * @return String
     * @throws IndexOutOfBoundsException if this list is empty.
     */
    default String setLast(String element){
        return set(size() - 1, element);
    }

    /**
     * Determines whether this list contains the specified String (possibly null).
     * @param s String to be found in the list.
     * @return true (String s has been found in list) or false (String s has NOT been found in list)
     * Ex:
     * If s is found in the list at index 5, then indexOf(s) will return that index (5).
     * Now we compare, is 5 > -1? Yes, so the list contains s. Returns true.
     *
     * If s isn't found in the list, then indexOf(s) will return -1.
     * Now we compare, is -1 > -1? No, the list doesn't contain s. Returns false.
     */
    default boolean contains(String s){
        return indexOf(s) > -1;
    }

    /*
     * Homework 3 part 3:
     * In StringBoundedList interface...
     * Implement 3 abstract methods -- add(int index, String s), remove(int index), and remove(String s)
     *
     * In ArrayStringBoundedList class...
     * Override the abstract methods from the interface to make them concrete. (Concrete = have a body/behavior)
     *
     * Implementation requirements:
     * - All fields must be private
     * - There must not be more than two fields
     * - One field must be String[] and the other field must be an int
     * - Do not use anything from the java.util.package, except for methods of the java.util.Objects class.
     * - Do not make a copy of the array.
     */

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right by one position.
     *
     * Ex: list has a capacity of 10 and represents [a, b, c, d].
     * list.add(2, "x") should make list represent [a, b, x, c, d].
     *
     * @param index, insert element at this index
     * @param s, element to be inserted into list
     * @throws IllegalStateException if the array is already full.
     * @throws IndexOutOfBoundsException if the provided index is negative, or if it is greater than the size.
     * Note: it is possible to add an element at index size, as long as the list isn't full.
     */
    public void add(int index, String s);

    /**
     * Removes the element at the specified position in this list.
     * Shifts all subsequent elements to the left by one position to fill in the gap.
     * Returns the element that was removed.
     *
     * Ex: list = [a, b, c, d]
     * list.remove(1) should make the list represent [a, c, d] not [a, null, c, d]
     *
     * @param index element at this index to be removed.
     * @return element that was removed.
     * @throws IndexOutOfBoundsException if the provided index is negative, or greater than or equal to the size.
     */
    public String remove(int index);


    /**
     * Removes the first occurrence of the specified String from this list.
     * Shifts all subsequent elements to the left by one position to fill in the gap.
     * Returns true if an element was removed, false otherwise.
     * @param s first occurrence of this String will be removed from list.
     * @return true (if element was removed) or false (if element was NOT removed)
     */
    default boolean remove(String s){
        // Find first occurrence of s -> indexOf(s)
        // Removes first occurrence of s and shifts elements to the left-> remove(indexOf(s)),
        // returns a string of the removed element.
        if(s != null && indexOf(s) > -1){ // if String s is found, continue
            if(remove(indexOf(s)).equals(s)){
                return true;
            }
        }
        if(s == null && indexOf(s) > -1){
            remove(indexOf(s));
            return true;
        }
        return false;
    }
}