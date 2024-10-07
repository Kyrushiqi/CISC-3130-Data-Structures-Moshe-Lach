package Hw3;

import java.util.ArrayList;
import java.util.List;

public class StringBoundedListTest {
    public static void main(String[] args) {
        testIsEmpty();
        testIsFull();
        testGetFirst();
        testGetLast();
        testSetFirst();
        testSetLast();
        testContains();
    }

    private static void testIsEmpty() {
        System.out.println("Testing is Empty:");

        StringBoundedList list = new StringBoundedListCapacity3();
        System.out.println(list.isEmpty()); // true

        list.add("a");
        System.out.println(list.isEmpty()); // false

        list.clear();
        System.out.println(list.isEmpty()); // true

        list.add(null);
        System.out.println(list.isEmpty()); // false

        list.clear();
        System.out.println(list.isEmpty()); // true

        System.out.println();
    }

    private static void testIsFull() {
        System.out.println("Testing isFull:");

        StringBoundedList list = new StringBoundedListCapacity3();
        System.out.println(list.isFull()); // false

        list.add("a");
        System.out.println(list.isFull()); // false

        list.add(null);
        System.out.println(list.isFull()); // false

        list.add("b");
        System.out.println(list.isFull()); // true

        list.clear();
        System.out.println(list.isFull()); // false

        System.out.println();
    }

    private static void testGetFirst() {
        System.out.println("Testing getFirst:");

        StringBoundedList list = new StringBoundedListCapacity3();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.getFirst();
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add("a");
        System.out.println(list.getFirst()); // a

        list.add("b");
        System.out.println(list.getFirst()); // a

        list.clear();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.getFirst();
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testGetLast() {
        System.out.println("Testing getLast:");

        StringBoundedList list = new StringBoundedListCapacity3();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.getLast();
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add("a");
        System.out.println(list.getLast()); // a

        list.add("b");
        System.out.println(list.getLast()); // b

        list.add("c");
        System.out.println(list.getLast()); // c

        list.clear();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.getLast();
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testSetFirst() {
        System.out.println("Testing setFirst:");

        StringBoundedList list = new StringBoundedListCapacity3();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.setFirst("x");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add("a");
        String old = list.setFirst("x");
        System.out.println(old + " " + list.get(0)); // a x

        list.add("b");
        old = list.setFirst("y");
        System.out.println(old + " " + list.get(0)); // x y

        list.clear();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.setFirst("x");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testSetLast() {
        System.out.println("Testing setLast:");

        StringBoundedList list = new StringBoundedListCapacity3();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.setLast("x");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add("a");
        String old = list.setLast("x");
        System.out.println(old + " " + list.get(0)); // a x

        list.add("b");
        old = list.setLast("y");
        System.out.println(old + " " + list.get(1)); // b y

        list.add("c");
        old = list.setLast("z");
        System.out.println(old + " " + list.get(2)); // c z

        list.clear();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.setFirst("x");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testContains() {
        System.out.println("Testing contains:");

        StringBoundedList list = new StringBoundedListCapacity3();
        System.out.println(list.contains("a"));  // false
        System.out.println(list.contains(null)); // false

        list.add("a");
        list.add(null);
        list.add("b");

        System.out.println(list.contains("a"));  // true
        System.out.println(list.contains(null)); // true
        System.out.println(list.contains("b"));  // true
        System.out.println(list.contains("c"));  // false

        list.clear();
        System.out.println(list.contains("a"));  // false
        System.out.println(list.contains(null)); // false

        System.out.println();
    }

    private static class StringBoundedListCapacity3 implements StringBoundedList {
        private final List<String> list = new ArrayList<>();
        private static final int CAPACITY = 3;

        @Override
        public int capacity() {
            return CAPACITY;
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public void add(String s) {
            if (size() >= capacity()) {
                throw new IllegalStateException();
            } else {
                list.add(s);
            }
        }

        @Override
        public String get(int index) {
            return list.get(index);
        }

        @Override
        public String set(int index, String element) {
            return list.set(index, element);
        }

        @Override
        public int indexOf(String s) {
            return list.indexOf(s);
        }

        @Override
        public int lastIndexOf(String s) {
            return list.lastIndexOf(s);
        }

        @Override
        public void clear() {
            list.clear();
        }
    }
}