package Hw4;
/*
Homework 4 Part 1:
Generify the interface and class from HW 3. Write:
- an interface BoundedList<E>, and
- a class ArrayBoundedList<E> that implements BoundedList<E>

Methods changed in BoundedList interface:
Methods add(both versions), set, setFirst, and setLast should now have a PARAMETER of type E instead of type String.
String -> E Parameter

Methods indexOf, lastIndexOf, contains, and remove (the one that returns a boolean) should now have a PARAMETER
of type Object instead of String. (Using E here instead of Object should be fine too)
String -> Object Parameter

Methods get, set, getFirst, getLast, setFirst, setLast, and remove(the one with an int parameter)
should now have E, instead of String, as their RETURN TYPE.
String -> E Return type
 */

/*
Homework 4 Part 3:
Make the BoundedList interface extend the Iterable interface. -> Override iterator() method in concrete class.
 */

/*
 * A capacity-bounded list of specified E elements. E -> Any data type
 * Each list has a capacity, which is the maximum number of elements that the list can hold at one time.
 * Each list also has a size, which is the number of elements currently held by the list.
 * The elements of a list have indexes: the first element is at index 0, the second element is at index 1, and so on.
 * Null elements are allowed.
 */
public interface BoundedList<E> extends Iterable<E>{
    /**
     * Returns the maximum number of elements that this list can hold at the same time.
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
     * For example, if {@code list} is a BoundedList with capacity 10 that represents {@code [a, b, c]},
     * then saying {@code list.add("d")} makes {@code list} represent {@code [a, b, c, d]}.
     * @param e the data type to add
     * @throws IllegalStateException if this list is already full
     */
    void add(E e);

    /**
     * Returns the element at the specified index in this list.
     * @param index the position of the desired element.
     * @return return type E, the element (possibly null) at the specified position
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    E get(int index);

    /**
     * Replaces the old element at the specified index with the new element, and returns the old element.
     * @param index the index where an element should be replaced
     * @param e the new element
     * @return return type E, the old element
     * @throws IndexOutOfBoundsException if index is negative, or greater than or equal to the size
     */
    E set(int index, E e);

    /**
     * Return the index of the first occurrence of the specified Object in this list, or -1 if it doesn't occur in this list.
     * @param o the Object to search for (possibly null)
     * @return the index of the first occurrence of o, or -1 if not found
     *
     * Instead of replacing the parameter with Object, you could also use Generic E.
     */
    int indexOf(Object o);

    /**
     * Return the index of the last occurrence of the specified Object in this list, or -1 if it doesn't occur in this list.
     * @param o the Object to search for (possibly null)
     * @return the index of the last occurrence of o, or -1 if not found
     *
     * Instead of replacing the parameter with Object, you could also use Generic E.
     */
    int lastIndexOf(Object o);

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
     * @return return type E, first element of this list.
     * @throws IndexOutOfBoundsException if this list is empty.
     * Note: get(int index) method can throw this exception for you.
     */
    default E getFirst(){
        return get(0);
    }

    /**
     * @return return type E, last element of this list.
     * @throws IndexOutOfBoundsException if this list is empty.
     */
    default E getLast(){
        return get(size() - 1); // size() - 1 b/c size() starts counting from 1 and not 0.
    }

    /**
     * Replaces the first element of this list with the specified element.
     * @param e new element that replaces first element.
     * @return return type E
     * @throws IndexOutOfBoundsException if this list is empty.
     * Note: set(int index, E e) method can throw the exception for you.
     */
    default E setFirst(E e){
        return set(0, e);
    }

    /**
     * Replaces the last element of this list with the specified element.
     * @param e new element that replaces last element
     * @return return type E
     * @throws IndexOutOfBoundsException if this list is empty.
     */
    default E setLast(E e){
        return set(size() - 1, e);
    }

    /**
     * Determines whether this list contains the specified Object (possibly null).
     * @param o Object to be found in the list.
     * @return true (Object o has been found in list) or false (Object o has NOT been found in list)
     * Ex:
     * If o is found in the list at index 5, then indexOf(o) will return that index (5).
     * Now we compare, is 5 > -1? Yes, so the list contains o. Returns true.
     *
     * If o isn't found in the list, then indexOf(o) will return -1.
     * Now we compare, is -1 > -1? No, the list doesn't contain o. Returns false.
     *
     * Instead of replacing the parameter with Object, you could also use Generic E.
     */
    default boolean contains(Object o){
        return indexOf(o) > -1;
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
    public void add(int index, E e);

    /**
     * Removes the element at the specified position in this list.
     * Shifts all subsequent elements to the left by one position to fill in the gap.
     * Returns the element that was removed.
     *
     * Ex: list = [a, b, c, d]
     * list.remove(1) should make the list represent [a, c, d] not [a, null, c, d]
     *
     * @param index element at this index to be removed.
     * @return return type E, element that was removed.
     * @throws IndexOutOfBoundsException if the provided index is negative, or greater than or equal to the size.
     */
    public E remove(int index);


    /**
     * Removes the first occurrence of the specified Object from this list.
     * Shifts all subsequent elements to the left by one position to fill in the gap.
     * Returns true if an element was removed, false otherwise.
     * @param o first occurrence of this Object will be removed from list.
     * @return true (if element was removed) or false (if element was NOT removed)
     *
     * Instead of replacing the parameter with Object, you could also use Generic E.
     */
    default boolean remove(Object o){
        // Find first occurrence of o -> indexOf(o)
        // Removes first occurrence of s and shifts elements to the left-> remove(indexOf(s)),
        // returns a string of the removed element.
        if(o != null && indexOf(o) > -1){ // if String s is found, continue
            if(remove(indexOf(o)).equals(o)){
                return true;
            }
        }
        if(o == null && indexOf(o) > -1){
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    /*
    Homework 4 Part 2:
        Add two default methods to the BoundedList Interface: addAll(), copyTo()
     */

    /**
     * Combines two BoundedList's elements together. Adds 'other' BoundedList to 'this' BoundedList.
     * Updates 'this' BoundedList. 'other' BoundedList -> 'this' BoundedList
     * 'this' addAll of 'other'
     *
     * EX:
     * if list = BoundedList w/ capacity of 10 => Representing [1, 2]
     * if other = BoundedList => Representing [3, 4]
     * list.addAll(other) should cause list = [1, 2, 3, 4] (Updates) and other = [3, 4] (Stays the same)
     * Other list should not be modified. In example above, other should still represent [3, 4].
     *
     * 10/14/24 notes in NB:
     * BoundedList<C> -> BoundedList<A> -> BoundedList<B> (C is a supertype of A, B is a subtype of A)
     * Valid method calls: C.addAll(C), C.addAll(A), C.addAll(B), A.addAll(A), A.addAll(B), B.addAll(B)
     * As long as the method calls involve 'this' >= 'other' on the hierarchy/tree then it is valid/correct.
     * 'other' must be a subtype or of the same class to 'this' to be valid.
     *
     * Invalid method calls: A.addAll(C), B.addAll(C), B.addAll(A)
     * These are invalid b/c 'this' < 'other' on the hierarchy/tree.
     * 'this' is a subtype of 'other' => Invalid
     * Conclusion:
     * 'this' must be a supertype or of the same class to 'other' for it to be valid.
     * 'other' must be a subtype or of the same class to 'this' to be valid.
     *
     * this.addAll(other):
     * supertype.addAll(subtype) => valid
     * sametype.addAll(sametype) => valid
     * subtype.addAll(supertype) => invalid
     *
     *
     * @param other
     * @throws IllegalStateException when list's capacity isn't sufficient to accept all elements of the other list.
     * And it shouldn't add any elements to the list.
     * EX: if list.capacity() = 10, list.size() = 5, other.size() = 6 => 5+6 = 11 > 10
     * then saying list.addAll(other) should cause an IllegalStateException and list.size() should remain 5.
     */
    default void addAll(BoundedList<? extends E> other){
        // In <> above: other should be E (sametype) or any subtype of E (subtype) only. extends keyword does that for you.
        // supertype.addAll(subtype) => valid
        // sametype.addAll(sametype) => valid
        // <? extends E> means any data type that is E or a subtype of E is a valid input for the method call.

        // if 'this' list is full, true OR adding the sizes of 'this' and 'other' exceeds 'this' capacity then throw Exception.
        if(isFull() || this.size() + other.size() > this.capacity()){
            throw new IllegalStateException();
        }
        // Logic error: The condition, i < this.capacity was wrong.
        // The for loop is used to iterate until the end of other's list.
        // Instead of this.capacity, which iterates until 'this' list capacity is filled up. I already made sure
        // that the combined sizes of 'this' and 'other' would not exceed the 'this' capacity in the if statement above.
        for(int i = 0; i < other.size(); i++){
            this.add(other.get(i)); // Add elements from 'other' to 'this'
        }
    }

    /**
     * Copies all the elements from 'this' BoundedList to the end of the 'other' BoundedList.
     * 'this' BoundedList -> 'other' BoundedList. 'this' copyTo 'other'
     * Basically the opposite of addAll().
     *
     * Ex: if BoundedList list = [1, 2] and BoundedList other = [3, 4] with capacity of 10,
     * then list.copyTo(other) represents other = [3, 4, 1, 2] and list = [1, 2]
     * list should not be modified.
     *
     * @throws IllegalStateException when other's capacity isn't sufficient to accept all the elements of this list.
     * The method should not add any elements to the other list and should throw the Exception.
     * Ex: list.size() = 6, other.capacity() = 10, and other.size() = 5,
     * then list.copyTo(other) should cause exception and other.size() should remain 5.
     *
     * BoundedList<B> -> BoundedList<A> -> BoundedList<C> (B is a supertype of A) (C is a subtype of A)
     * this.addAll(other):
     * subtype.addAll(supertype) => valid
     * sametype.addAll(sametype) => valid
     * supertype.addAll(subtype) => invalid
     *
     * <? super E> means that the parameter accepts any data type that is E or a supertype of E.
     * Look at what is in parameters here. Supertype or same type ^^
     * subtype.addAll(supertype) => valid
     * sametype.addAll(sametype) => valid
     */
    default void copyTo(BoundedList<? super E> other){
        if(isFull() || this.size() + other.size() > other.capacity()){
            throw new IllegalStateException();
        }
        // tracks 'this' list, goes through 'this' list
        for(int i = 0; i < this.size(); i++){
            other.add(get(i));
        }
    }
}
