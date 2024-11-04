package Hw5;

import java.util.NoSuchElementException;

/*
A DoublyLinkedList is a linked list in which each node has 3 fields:
- the node's data (the element),
- a pointer to the previous node in the list,
- a pointer to the next node in the list.

The only exceptions are the first and last nodes:
- the first node has no previous node, so the 'previous' field should be null;
- the last node has no next node, so the 'next' field should be null.

A DoublyLinkedList should have a 'head' pointer to the list's first node and a 'tail' pointer to the list's
last node. When the list is empty, these pointers should be null.

Homework 5, part 2 Objective:
Add 8 methods to this class.
- The running time of the first 6 methods should be O(1).
- The running time of the last 2 methods should be O(n).

Notes: 10/29/24 in NB
- The assignment operator (=) performs the operations on the right first,
then it proceeds to assign that operation to the datatype on the left.
EX: String s (2) = new String("hi"); (1)
A string object with a reference to the value "hi" is instantiated first. It is then assigned to the String variable s.

Lesson learnt:
Because this is a DoublyLinkedList, you mustn't forget that there should be 2 references between 2 nodes.
In the particular scenario where you create a new node, you must link the new node with the nodes that are already present.
Examples:
- addFirst() creates a new node, that new node must be linked to head.previous before head references the new node.
    Translated in code: head = head.previous = new Node<E>(element, null, head);
    if we only did head = new Node<E>(element, null, head);
    then the new node.next would reference head, but head.previous will still be referencing null.

- addLast() creates a new node, that new node must be linked to tail.next before tail references the new node.
    Translated in code: tail = tail.next = new Node<E> (element, tail, null);
    if we only did tail = new Node<E> (element, tail, null);
    then the new node.previous would reference tail, but tail.next will still be referencing null.
 */
public class DoublyLinkedList<E> { // DoublyLinkedList is a larger data structure compared to Node.
    private static class Node<E> { // Node is a data structure, not an object. Node serves as a way to structure and organize data in a particular format.
        private E data;
        private Node<E> previous; // pointer to previous node
        private Node<E> next;     // pointer to next node

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    private Node<E> head;   // pointer to the first node of this list
    private Node<E> tail;   // pointer to the last node of this list
    private int size;       // the number of nodes currently in this list

    public DoublyLinkedList() {
        tail = head = null;
        size = 0;
    }

    // 8 Methods start here:
    /**
     * Adds the specified element at the beginning of the list.
     * @param element the data of a node
     */
    public void addFirst(E element){ // O(1)
        if(isEmpty()){ // Case 1: if there are no nodes present
            tail = head = new Node<>(element);
        } else {
            // Case 2: if there's one or more nodes present
            head = head.previous = new Node<E>(element, null, head);
            // Above works b/c the assignment operator makes the Node object first then assigns it to the head.previous.
            // Head.previous then gets assigned to head. (Head.previous refers to the reference, not the "box" itself)
        }
        size++;
    }

    /**
     * Adds the specified element at the end of the list.
     * @param element the data of a node
     */
    public void addLast(E element){ // O(1)
        if(isEmpty()){ // Case 1: if there are no nodes present
            addFirst(element); // if the list is empty it doesn't matter if we invoke the addFirst() or addLast() methods.
                               // Both do the same thing -- adds one element/node to an empty list.
        } else {
            // Case 2: if there's one or more nodes present
            tail = tail.next = new Node<E> (element, tail, null);
            size++;
        }
    }

    /**
     * @return the first element of the list;
     * @throws java.util.NoSuchElementException if the list is empty.
     */
    public E getFirst(){ // O(1)
        if(head == null || tail == null || size == 0){
            throw new NoSuchElementException();
        }
        return head.data;
    }

    /**
     * @return the last element of the list;
     * @throws java.util.NoSuchElementException if the list is empty.
     */
    public E getLast(){ // O(1)
        if(head == null || tail == null || size == 0){
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    /**
     * @return the number of elements (= the number of nodes in the list)
     */
    public int size(){ // O(1)
        return size;
    }

    /**
     * Determines whether the list is empty.
     */
    public boolean isEmpty(){ // O(1)
        return size == 0;
    }

    /**
     * @return String representation of the list.
     * The string representation should consist of all the elements, in order, enclosed in brackets.
     * Adjacent elements should be separated by a comma and space.
     *
     * Ex:
     * [] -> empty list
     * [a, b, c] -> list of size 3 containing the Strings "a", "b", "c".
     */
    @Override
    public String toString(){ // O(n)
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head;

        while(current != null){
            sb.append(current.data);

            if(current != tail){
                sb.append(", ");
            }

            current = current.next;
        }
        return sb.append("]").toString();
    }

    /**
     * @return String representation of the list in reverse order.
     *
     * EX:
     * toString() returns [a, b, c]
     * toReverseString() returns [c, b, a]
     *
     * Hint: Traverse through the list backwards, that is, from tail to head.
     */
    public String toReverseString(){ // O(n)
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = tail;

        while(current != null){
            sb.append(current.data);

            if(current != head){
                sb.append(", ");
            }
            current = current.previous;
        }

        return sb.append("]").toString();
    }

    /*
    Homework 5, part 3 (00521)
    Objective:
    Add 2 methods to the DoublyLinkedList class:
    - public E removeFirst()
    - public E removeLast()

    The methods should throw a NoSuchElementException if the list is empty.

    The running time of each method should be O(1). Note that in SinglyLinkedList, the running time of removeLast() was
    O(n).

    Refer to NB 10/31/24 for notes and visuals on this.
    - Current doesn't exist outside the methods, so the reference of current to the removed node will be disregarded
    and garbage collector will still collect that removed node.
     */

    /**
     * Removes and returns the first element of the list.
     * @return first element of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public E removeFirst(){ // O(1)
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> current = head;

        if(size <= 1){ // Case 1: 1 node present
            head = null;
            tail = null;
        } else { // Case 2: 2 or more nodes present
            head = head.next;
            head.previous = null;
            current.next = null;
        }
        size--;

        return current.data;
    }

    /**
     * Removes and returns the last element of the list.
     * @return last element of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public E removeLast(){ // O(1)
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> current = tail;
        if(size <= 1){ // Case 1: 1 node present
            removeFirst();
        } else { // Case 2: 2 or more nodes present
            tail = tail.previous;
            tail.next = null;
            current.previous = null;
            size--;
        }
        return current.data;
    }

    /*
    Homework 5, part 4 (00522):
    Objective:
    Add 4 more methods to the DoublyLinkedList class...
    - clear()
    - contains(Object o)
    - add(E e)
    - remove(Object o)

    Look at NB 11/3/24 remove(Object o)'s Debug part for breakdown and visual.
     */

    /**
     * Removes all the elements from the list.
     */
    public void clear(){
        // Case 1: 1 node present
        if(size <= 1){
            head = null;
            tail = null;
        } else { // Case 2: 2 or more nodes present
            Node<E> current = head;
            while(current != null){
                removeFirst();
                current = head;
            }
        }
        size = 0;
    }

    /**
     * Determines whether the list contains the specified element.
     * @param o looking for specified element o
     * @return true (contains o) OR false (doesn't contain o)
     */
    public boolean contains(Object o){
        Node<E> current = head;
        while(current != null){
            if(current.data.equals(o)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Adds the specified element to the end of the list and returns true.
     * @param e element to be added at the end of the list
     * @return true
     */
    public boolean add(E e){
        addLast(e);
        return true;
    }

    /**
     * Removes the FIRST occurrence of the specified element from the list. Returns true if an element was
     * removed; otherwise, returns false.
     * @param o element o's first occurrence to be removed from list
     * @return true (if element o was removed) OR false (if element o was NOT removed)
     */
    public boolean remove(Object o){
        Node<E> current = head;
        while(current != null){
            if(current.data.equals(o)){
                // Case 1: 1 node present
                if(size <= 1){
                    removeFirst();
                } else { // Case 2: 2 or more nodes present
                    if(head == current){ // Case 2.1: The first element is the one you want to remove.
                        removeFirst();
                        return true; // Immediately end method when this is executed.
                    }
                    if(tail == current){ // Case 2.2: The last element is the one you want to remove.
                        removeLast();
                        return true;
                    }
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    current.previous = null;
                    current.next = null;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /*
    Homework 5, part 5 (00523):
    Objective:
    Add 2 more methods to the DoublyLinkedList class...
    - indexOf(Object o)
    - lastIndexOf(Object o)
     */

    /**
     * @param o
     * @return index of the first occurrence of the specified element in the list.
     * If the specified element doesn't occur in the list, the method returns -1.
     */
    public int indexOf(Object o){
        int index = 0; // if index = -1, then index++ should be the first statement in the while loop. This is correct too.
        Node<E> current = head;

        while(current != null){
            if(current.data.equals(o)){
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    /**
     * @param o
     * @return the index of the last occurrence of the specified element in the list.
     * If the specified element doesn't occur in the list, the method returns -1.
     */
    public int lastIndexOf(Object o){
        int indexOfLast = -1;
        int index = 0;

        Node<E> current = head;
        while(current != null){
            if(current.data.equals(o)){
                indexOfLast = index;
            }
            index++;
            current = current.next;
        }
        return indexOfLast;
    }

    /*
    Homework 5, part 6 (00524):

    Objective:
    Add 4 more methods to the DoublyLinkedList class...
    - get(int index)
    - set(int index, E element)
    - add(int index, E element)
    - remove(int index)

    The running time of all these methods should be O(n).

    For each of these methods, minimize the number of required "link jumps."
    Link jump occurs when we run a statement of the form current = current.next or current = current.previous.
    Each call to one of these methods should perform at most n/2 link jumps, where n is the list's size.
    In other words, if the desired index is within the first half of the list, start the current pointer at the
    list's head; otherwise, start the current pointer at the list's tail.

    Recall that in SinglyLinkedList, the running time of these methods was O(n), and we had to perform up to n
    link jumps. Here, in DoublyLinkedList, the running time of these methods is still O(n), since n/2 is O(n),
    but we've cut the running time down by half (approximately).
     */

    /**
     * @param index
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if index is negative (index < 0) or
     * index is greater than or equal to size (index >= size).
     */
    public E get(int index){ // O(n)
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        int indexCounter = 0;
        // Optimized version: O(n/2) = O(n)
        // Another way is to use for loops instead of while loops as showcased in set(int index, E element);
        if(index < size/2){ // if the index is in the first half of the linkedList/list
            while(current != null){ // O(n)
                if(index == indexCounter){
                    return current.data;
                }

                indexCounter++;
                current = current.next;
            }
        } else if (index >= size/2){ // if the index is in the second half of the linkedList/list
            indexCounter = size - 1;
            current = tail; // start from the end

            while(current != null){ // O(n)
                if(index == indexCounter){
                    return current.data;
                }

                indexCounter--;
                current = current.previous;
            }
        }
        return current.data; // Not rlly necessary but this method needs a return statement.

        /* Acceptable answer but can be optimized to limit link jumps:
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        int indexCounter = 0;
        Node<E> current = head;

        while(current != null){ // O(n)
            if(index == indexCounter){
                return current.data;
            }

            indexCounter++;
            current = current.next;
        }

        return current.data; // Not rlly necessary but this method needs a return statement. */
    }

    /**
     * Replaces the element at the specified index with the specified new element, and returns the old element.
     * @param index replace element at this index
     * @param element replace element at specified index with this element
     * @return the old element (the element being replaced)
     * @throws IndexOutOfBoundsException if index < 0 || index >= size
     */
    public E set(int index, E element){ // O(n) + O(n) = O(n) OR O(n/2) = O(n)
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        E oldElement = current.data;
        if(index < size/2){ // if index is in first half of list
            for(int i = 0; i < size - 1; i++){ // O(n), runs up to size/2 times, 'i' never goes past the halfway point
                if(index == i){
                    oldElement = current.data;
                    current.data = element;
                    return oldElement;
                }
                current = current.next;
            }
        } else if(index >= size/2){ // if index is in the second half of the list
            current = tail;
            for(int i = size - 1; i >= 0; i--){ // O(n), runs up to size/2 times, 'i' never goes past the halfway point from the opposite direction.
                if(index == i){
                    oldElement = current.data;
                    current.data = element;
                    return oldElement;
                }
                current = current.previous;
            }
        }
        return oldElement;
    }

    /**
     * Adds the specified element to the list at the specified index.
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException if index < 0 || index > size (but note that index == size is allowed)
     */
    public void add(int index, E element){ // O(n)
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> current;

        if(index == 0){
            addFirst(element);
        } else if(index == size){
            addLast(element);
        } else if(index < size/2){
            current = head;
            for(int i = 0; i <= size-1; i++){
                if(index-1 == i){
                    current.next = current.next.previous = new Node<E>(element, current, current.next);
                    size++;
                }
                current = current.next;
            }
        } else {
            current = tail;
            for(int i = size-1; i >= 0; i--){
                if(index == i){
                    current.previous = current.previous.next = new Node<E>(element, current.previous, current);
                    size++;
                }
                current = current.previous;
            }
        }
    }

    /**
     * Removes and returns the element at the specified index.
     * @param index
     * @return element at specified index
     * @throws IndexOutOfBoundsException if index < 0 || index >= size
     */
    public E remove(int index){ // O(n)
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = head;
        E oldElement = current.data;

        if(index == 0){
            oldElement = current.data;
            removeFirst();
            return oldElement;
        } else if(index == size-1){
            current = tail;
            oldElement = current.data;
            removeLast();
            return oldElement;
        } else if(index < size/2){
            for(int i = 0; i < size-1; i++){
                if(index == i){
                    oldElement = current.data;
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    current.previous = null;
                    current.next = null;
                    size--;
                    return oldElement;
                }
                current = current.next;
            }
        } else {
            current = tail;
            for(int i = size-1; i >= 0; i--){
                if(index == i){
                    oldElement = current.data;
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    current.previous = null;
                    current.next = null;
                    size--;
                    return oldElement;
                }
                current = current.previous;
            }
        }
        return oldElement;
    }
}
