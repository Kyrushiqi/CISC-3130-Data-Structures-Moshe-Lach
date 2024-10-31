package Hw5;
/*
This file tests the following methods of DoublyLinkedList:
- size, isEmpty
- toString, toReverseString
- addFirst, addLast
- getFirst, getLast
- removeFirst, removeLast
 */

import java.util.NoSuchElementException;

public class DoublyLinkedListTest2 {
    public static void main(String[] args) {
        testAddFirst();
        testAddLast();
        testAddFirstAndAddLast();
        testGetFirst();
        testGetLast();
        testRemoveFirst();
        testRemoveLast();
    }

    private static void testAddFirst() {
        System.out.println("testing addFirst");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        System.out.println(list.size());            // expected: 0
        System.out.println(list.isEmpty());         // expected: true
        System.out.println(list);                   // expected: []
        System.out.println(list.toReverseString()); // expected: []
        System.out.println();

        list.addFirst("aa");
        System.out.println(list.size());            // expected: 1
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [aa]
        System.out.println(list.toReverseString()); // expected: [aa]
        System.out.println();

        list.addFirst("b");
        System.out.println(list.size());            // expected: 2
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [b, aa]
        System.out.println(list.toReverseString()); // expected: [aa, b]
        System.out.println();

        list.addFirst("c");
        System.out.println(list.size());            // expected: 3
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [c, b, aa]
        System.out.println(list.toReverseString()); // expected: [aa, b, c]
        System.out.println();

        list.addFirst("d");
        System.out.println(list.size());            // expected: 4
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [d, c, b, aa]
        System.out.println(list.toReverseString()); // expected: [aa, b, c, d]
        System.out.println();

        list.addFirst("c");
        System.out.println(list.size());            // expected: 5
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [c, d, c, b, aa]
        System.out.println(list.toReverseString()); // expected: [aa, b, c, d, c]
        System.out.println();
    }

    private static void testAddLast() {
        System.out.println("testing addLast");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        System.out.println(list.size());            // expected: 0
        System.out.println(list.isEmpty());         // expected: true
        System.out.println(list);                   // expected: []
        System.out.println(list.toReverseString()); // expected: []
        System.out.println();

        list.addLast("aa");
        System.out.println(list.size());            // expected: 1
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [aa]
        System.out.println(list.toReverseString()); // expected: [aa]
        System.out.println();

        list.addLast("b");
        System.out.println(list.size());            // expected: 2
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [aa, b]
        System.out.println(list.toReverseString()); // expected: [b, aa]
        System.out.println();

        list.addLast("c");
        System.out.println(list.size());            // expected: 3
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [aa, b, c]
        System.out.println(list.toReverseString()); // expected: [c, b, aa]
        System.out.println();

        list.addLast("d");
        System.out.println(list.size());            // expected: 4
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [aa, b, c, d]
        System.out.println(list.toReverseString()); // expected: [d, c, b, aa]
        System.out.println();

        list.addLast("c");
        System.out.println(list.size());            // expected: 5
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [aa, b, c, d, c]
        System.out.println(list.toReverseString()); // expected: [c, d, c, b, aa]
        System.out.println();
    }

    private static void testAddFirstAndAddLast() {
        System.out.println("testing addFirst and addLast");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("a");
        list.addFirst("b");
        list.addFirst("c");
        list.addLast("d");
        list.addLast("e");

        System.out.println(list);                   // expected: [c, b, a, d, e]
        System.out.println(list.toReverseString()); // expected: [e, d, a, b, c]
        System.out.println();
    }

    private static void testGetFirst() {
        System.out.println("testing getFirst");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        try {
            list.getFirst();
            System.out.println("an exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
        }

        list.addFirst("a");
        System.out.println(list.getFirst()); // expected: a

        list.addFirst("b");
        System.out.println(list.getFirst()); // expected: b

        list.addFirst("c");
        System.out.println(list.getFirst()); // expected: c

        list.addLast("d");
        System.out.println(list.getFirst()); // expected: c

        System.out.println();

        // ------------------------------------------------------------------

        list = new DoublyLinkedList<>();

        list.addLast("a");
        System.out.println(list.getFirst()); // expected: a

        list.addLast("b");
        System.out.println(list.getFirst()); // expected: a

        list.addFirst("c");
        System.out.println(list.getFirst()); // expected: c

        System.out.println();
    }

    private static void testGetLast() {
        System.out.println("testing getLast");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        try {
            list.getLast();
            System.out.println("an exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
        }

        list.addLast("a");
        System.out.println(list.getLast()); // expected: a

        list.addLast("b");
        System.out.println(list.getLast()); // expected: b

        list.addLast("c");
        System.out.println(list.getLast()); // expected: c

        list.addFirst("d");
        System.out.println(list.getLast()); // expected: c

        System.out.println();

        // ------------------------------------------------------------------

        list = new DoublyLinkedList<>();

        list.addFirst("a");
        System.out.println(list.getLast()); // expected: a

        list.addFirst("b");
        System.out.println(list.getLast()); // expected: a

        list.addLast("c");
        System.out.println(list.getLast()); // expected: c

        System.out.println();
    }

    private static void testRemoveFirst() {
        System.out.println("testing removeFirst");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        try {
            list.removeFirst();
            System.out.println("An exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
            System.out.println("Good, Exception has been caught");
        }

        list.addFirst("a");
        String first = list.removeFirst();
        System.out.println(first);                  // expected: a
        System.out.println(list.size());            // expected: 0
        System.out.println(list.isEmpty());         // expected: true
        System.out.println(list);                   // expected: []
        System.out.println(list.toReverseString()); // expected: []
        System.out.println();

        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        System.out.println(list.size());            // expected: 3
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [c, b, a]
        System.out.println(list.toReverseString()); // expected: [a, b, c]
        System.out.println(list.getFirst());        // expected: c
        System.out.println(list.getLast());         // expected: a
        System.out.println();

        first = list.removeFirst();
        System.out.println(first);                  // expected: c
        System.out.println(list.size());            // expected: 2
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [b, a]
        System.out.println(list.toReverseString()); // expected: [a, b]
        System.out.println(list.getFirst());        // expected: b
        System.out.println(list.getLast());         // expected: a
        System.out.println();

        first = list.removeFirst();
        System.out.println(first);                  // expected: b
        System.out.println(list.size());            // expected: 1
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [a]
        System.out.println(list.toReverseString()); // expected: [a]
        System.out.println(list.getFirst());        // expected: a
        System.out.println(list.getLast());         // expected: a
        System.out.println();

        first = list.removeFirst();
        System.out.println(first);                  // expected: a
        System.out.println(list.size());            // expected: 0
        System.out.println(list.isEmpty());         // expected: true
        System.out.println(list);                   // expected: []
        System.out.println(list.toReverseString()); // expected: []
        System.out.println();

        try {
            list.getFirst();
            System.out.println("An exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
        }

        try {
            list.getLast();
            System.out.println("An exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
        }
    }

    private static void testRemoveLast() {
        System.out.println("testing removeLast");

        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        try {
            list.removeLast();
            System.out.println("An exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
            System.out.println("Good, NoSuchElementException is caught");
        }

        list.addFirst("a");
        String last = list.removeLast();
        System.out.println(last);                  // expected: a
        System.out.println(list.size());            // expected: 0
        System.out.println(list.isEmpty());         // expected: true
        System.out.println(list);                   // expected: []
        System.out.println(list.toReverseString()); // expected: []
        System.out.println();

        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        System.out.println(list.size());            // expected: 3
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [c, b, a]
        System.out.println(list.toReverseString()); // expected: [a, b, c]
        System.out.println(list.getFirst());        // expected: c
        System.out.println(list.getLast());         // expected: a
        System.out.println();

        last = list.removeLast();
        System.out.println(last);                  // expected: a
        System.out.println(list.size());            // expected: 2
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [c, b]
        System.out.println(list.toReverseString()); // expected: [b, c]
        System.out.println(list.getFirst());        // expected: c
        System.out.println(list.getLast());         // expected: b
        System.out.println();

        last = list.removeLast();
        System.out.println(last);                  // expected: b
        System.out.println(list.size());            // expected: 1
        System.out.println(list.isEmpty());         // expected: false
        System.out.println(list);                   // expected: [c]
        System.out.println(list.toReverseString()); // expected: [c]
        System.out.println(list.getFirst());        // expected: c
        System.out.println(list.getLast());         // expected: c
        System.out.println();

        last = list.removeFirst();
        System.out.println(last);                  // expected: c
        System.out.println(list.size());            // expected: 0
        System.out.println(list.isEmpty());         // expected: true
        System.out.println(list);                   // expected: []
        System.out.println(list.toReverseString()); // expected: []
        System.out.println();

        try {
            list.getFirst();
            System.out.println("An exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
        }

        try {
            list.getLast();
            System.out.println("An exception should have occurred here, but didn't");
        } catch (NoSuchElementException ignored) {
        }
    }
}
