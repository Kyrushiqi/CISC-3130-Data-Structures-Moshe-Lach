package Hw3;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ArrayStringBoundedListTest {
    public static void main(String[] args) {
        testConstructor();
        testCapacity();
        testSize();
        testAdd();
        testGet();
        testSet();
        testIndexOf();
        testLastIndexOf();
        testClear();
        testToString();

        testIsEmpty();
        testIsFull();
        testGetFirst();
        testGetLast();
        testSetFirst();
        testSetLast();
        testContains();

        testImplementationRequirements();
    }

    private static void testConstructor() {
        System.out.println("Testing constructor:");

        StringBoundedList list1 = new ArrayStringBoundedList(10);
        System.out.println(list1.size());     // 0
        System.out.println(list1.capacity()); // 10

        StringBoundedList list2 = new ArrayStringBoundedList(0);
        System.out.println(list2.size());     // 0
        System.out.println(list2.capacity()); // 0

        // Good, IllegalArgumentException thrown
        try {
            new ArrayStringBoundedList(-1);
            System.out.println("Exception not thrown!");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testCapacity() {
        System.out.println("Testing capacity");

        StringBoundedList list1 = new ArrayStringBoundedList(10);
        list1.add("a");
        list1.add("b");
        list1.add("c");
        System.out.println("Element at index 2: " + list1.get(2)); // c
        System.out.println(list1.capacity()); // 10

        StringBoundedList list2 = new ArrayStringBoundedList(5);
        list2.add("a");
        list2.add("b");
        System.out.println(list2.capacity()); // 5

        StringBoundedList list3 = new ArrayStringBoundedList(0);
        System.out.println(list3.capacity()); // 0

        System.out.println();
    }

    private static void testSize() {
        System.out.println("Testing size:");

        StringBoundedList list = new ArrayStringBoundedList(10);
        System.out.println(list.size()); // 0

        list.add("a");
        System.out.println(list.size()); // 1

        list.add("bb");
        System.out.println(list.size()); // 2

        list.add(null);
        System.out.println(list.size()); // 3

        list.add("ccc");
        System.out.println(list.size()); // 4

        list.add("a");
        System.out.println(list.size()); // 5

        list.add(null);
        System.out.println(list.size()); // 6

        list.add("bb");
        System.out.println(list.size()); // 7

        System.out.println();
    }

    private static void testAdd() {
        System.out.println("Testing add:");

        StringBoundedList list = new ArrayStringBoundedList(3);
        list.add("a");
        list.add(null);
        list.add("b");

        // Good, IllegalStateException thrown
        try {
            list.add("c");
            System.out.println("Exception not thrown!");
        } catch (IllegalStateException e) {
            System.out.println("Good, IllegalStateException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testGet() {
        System.out.println("Testing get:");
        StringBoundedList list = new ArrayStringBoundedList(10);

        // Good, IndexOutOfBoundsException thrown
        try {
            list.get(0);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add(null);
        list.add("a");
        list.add("b");
        list.add(null);
        list.add("a");

        System.out.println(list.get(0)); // null
        System.out.println(list.get(1)); // a
        System.out.println(list.get(2)); // b
        System.out.println(list.get(3)); // null
        System.out.println(list.get(4)); // a

        // Good, IndexOutOfBoundsException thrown
        try {
            list.get(5);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        // Good, IndexOutOfBoundsException thrown
        try {
            list.get(-1);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testSet() {
        System.out.println("Testing set:");

        StringBoundedList list = new ArrayStringBoundedList(10);

        // Good, IndexOutOfBoundsException thrown
        try {
            list.set(0, "a");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add("a");
        list.add(null);
        list.add("b");

        String old = list.set(0, null);
        System.out.println(old + " " + list.get(0)); // a null

        old = list.set(1, "x");
        System.out.println(old + " " + list.get(1)); // null x

        old = list.set(2, "b");
        System.out.println(old + " " + list.get(2)); // b b

        // Good, IndexOutOfBoundsException thrown
        try {
            list.set(-1, "a");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        // Good, IndexOutOfBoundsException thrown
        try {
            list.set(-1, "a");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testIndexOf() {
        System.out.println("Testing indexOf:");

        StringBoundedList list = new ArrayStringBoundedList(10);
        list.add("a");
        list.add("b");

        System.out.println(list.indexOf("a"));  // 0
        System.out.println(list.indexOf("b"));  // 1
        System.out.println(list.indexOf("c"));  // -1
        System.out.println(list.indexOf(null)); // -1
        System.out.println("Test case 1 Ends");

        list.add(null);
        list.add(null);
        list.add("b");
        list.add("c");
        list.add("b");
        list.add("d");

        System.out.println(list.indexOf("a"));  // 0
        System.out.println(list.indexOf("b"));  // 1
        System.out.println(list.indexOf(new String("c")));  // 5
        System.out.println(list.indexOf("d"));  // 7
        System.out.println(list.indexOf("e"));  // -1
        System.out.println(list.indexOf(null)); // 2

        System.out.println();
    }

    private static void testLastIndexOf() {
        System.out.println("Testing lastIndexOf:");

        StringBoundedList list = new ArrayStringBoundedList(10);
        list.add("a");
        list.add("b");

        System.out.println(list.lastIndexOf("a"));  // 0
        System.out.println(list.lastIndexOf("b"));  // 1
        System.out.println(list.lastIndexOf("c"));  // -1
        System.out.println(list.lastIndexOf(null)); // -1
        System.out.println("Test case 1 end");

        list.add(null);
        list.add(null);
        list.add("b");
        list.add("c");
        list.add("b");
        list.add("d");

        System.out.println(list.lastIndexOf("a"));  // 0
        System.out.println(list.lastIndexOf("b"));  // 6
        System.out.println(list.lastIndexOf(new String("c")));  // 5
        System.out.println(list.lastIndexOf("d"));  // 7
        System.out.println(list.lastIndexOf("e"));  // -1
        System.out.println(list.lastIndexOf(null)); // 3

        System.out.println();
    }

    private static void testClear() {
        System.out.println("Testing clear:");

        StringBoundedList list1 = new ArrayStringBoundedList(0);
        list1.clear();

        StringBoundedList list2 = new ArrayStringBoundedList(3);
        list2.add("a");
        list2.add(null);
        list2.add("b");
        list2.clear();
        System.out.println(list2.capacity());       // 3
        System.out.println(list2.size());           // 0
        System.out.println(list2.indexOf("a"));     // -1
        System.out.println(list2.lastIndexOf("a")); // -1

        // Good, IndexOutOfBoundsException thrown
        try {
            list2.get(0);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        // Good, IndexOutOfBoundsException thrown
        try {
            list2.set(0, "a");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list2.add("c");

        list2.clear();
        list2.clear();
        System.out.println(list2.size()); // 0

        System.out.println();
    }

    private static void testToString() {
        StringBoundedList list = new ArrayStringBoundedList(10);
        System.out.println(list); // []

        list.add("a");
        System.out.println(list); // [a]

        list.add(null);
        System.out.println(list); // [a, null]

        list.add(null);
        list.add("a");
        list.add("b");
        System.out.println(list); // [a, null, null, a, b]

        list.clear();
        System.out.println(list); // []

        System.out.println();
    }

    private static void testIsEmpty() {
        System.out.println("Testing isEmpty:");

        StringBoundedList list = new ArrayStringBoundedList(3);
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

        StringBoundedList list = new ArrayStringBoundedList(3);
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

        StringBoundedList list = new ArrayStringBoundedList(3);

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

        StringBoundedList list = new ArrayStringBoundedList(3);

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

        StringBoundedList list = new ArrayStringBoundedList(3);

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

        StringBoundedList list = new ArrayStringBoundedList(3);

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

        StringBoundedList list = new ArrayStringBoundedList(3);
        System.out.println(list.contains("a"));  // false
        System.out.println(list.contains(null)); // false

        list.add("a");
        list.add(null);
        list.add("b");

        System.out.println(list.contains("a"));  // true
        System.out.println(list.contains(null)); // true
        System.out.println(list.contains("b"));  // true * there was an error here before
        System.out.println(list.contains("c"));  // false

        System.out.println("Index 0: " + list.get(0)); // a
        System.out.println("Index 1: " + list.get(1)); // null
        System.out.println("Index 2: " + list.get(2)); // b

        list.clear();
        System.out.println(list.contains("a"));  // false
        System.out.println(list.contains(null)); // false

        System.out.println();
    }

    private static void testImplementationRequirements() {
        System.out.println("Testing implementation requirements:");

        Field[] fields = ArrayStringBoundedList.class.getDeclaredFields();

        if (fields.length > 2) {
            System.out.println("At most two fields allowed!");
        }

        boolean hasStringArray = false, hasInt = false;

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                System.out.println("Field " + field.getName() + " is not private!");
            }

            if (field.getType() == String[].class) {
                hasStringArray = true;
            } else if (field.getType() == int.class) {
                hasInt = true;
            }
        }

        if (!hasStringArray) {
            System.out.println("One of the fields must be an array of Strings (that is, a String[])!");
        }

        if (!hasInt) {
            System.out.println("One of the fields must be an int!");
        }

        System.out.println();
    }
}
