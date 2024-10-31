package Hw5;

/*
This file tests the following methods of DoublyLinkedList:
- size, isEmpty
- toString, toReverseString
- addFirst, addLast
- getFirst, getLast
 */

import java.util.NoSuchElementException;

public class DoublyLinkedListTest1 {
    public static void main(String[] args) {
        testAddFirst();
        testAddLast();
        testAddFirstAndAddLast();
        testGetFirst();
        testGetLast();
    }

    private static void testAddFirst() {
        System.out.println("testing addFirst");

        // new DoublyLinkedList<>() creates an empty DoublyLinkedList object. Refer to DoublyLinkedList constructor.
        // tail = head = null;
        // size = 0;

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
}