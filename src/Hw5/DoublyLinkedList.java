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
            // Head.previous then gets assigned to head.
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
}
