package Hw4;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ArrayBoundedListTest {
    public static void main(String[] args) {
        testGeneric();

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

        testAddIntString();
        testRemoveInt();
        testRemoveString();
    }

    private static void testGeneric() {
        System.out.println("Testing generic:");

        BoundedList<String> stringList = new ArrayBoundedList<>(10);
        stringList.add("a");
        String s = stringList.get(0);

        BoundedList<Integer> integerList = new ArrayBoundedList<>(10);
        integerList.add(1);
        int i = integerList.get(0);

        System.out.println();
    }

    private static void testConstructor() {
        System.out.println("Testing constructor:");

        BoundedList<String> list1 = new ArrayBoundedList<>(10);
        System.out.println(list1.size());     // 0
        System.out.println(list1.capacity()); // 10

        BoundedList<String> list2 = new ArrayBoundedList<>(0);
        System.out.println(list2.size());     // 0
        System.out.println(list2.capacity()); // 0

        // Good, IllegalArgumentException thrown
        try {
            new ArrayBoundedList<>(-1);
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

        BoundedList<String> list1 = new ArrayBoundedList<>(10);
        list1.add("a");
        list1.add("b");
        list1.add("c");
        System.out.println(list1.capacity()); // 10

        BoundedList<String> list2 = new ArrayBoundedList<>(5);
        list2.add("a");
        list2.add("b");
        System.out.println(list2.capacity()); // 5

        BoundedList<String> list3 = new ArrayBoundedList<>(0);
        System.out.println(list3.capacity()); // 0

        System.out.println();
    }

    private static void testSize() {
        System.out.println("Testing size:");

        BoundedList<String> list = new ArrayBoundedList<>(10);
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

        BoundedList<String> list = new ArrayBoundedList<>(3);
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
        BoundedList<String> list = new ArrayBoundedList<>(10);

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

        BoundedList<String> list = new ArrayBoundedList<>(10);

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

        BoundedList<String> list = new ArrayBoundedList<>(10);
        list.add("a");
        list.add("b");

        System.out.println(list.indexOf("a"));  // 0
        System.out.println(list.indexOf("b"));  // 1
        System.out.println(list.indexOf("c"));  // -1
        System.out.println(list.indexOf(null)); // -1

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

        BoundedList<String> list = new ArrayBoundedList<>(10);
        list.add("a");
        list.add("b");

        System.out.println(list.lastIndexOf("a"));  // 0
        System.out.println(list.lastIndexOf("b"));  // 1
        System.out.println(list.lastIndexOf("c"));  // -1
        System.out.println(list.lastIndexOf(null)); // -1

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

        BoundedList<String> list1 = new ArrayBoundedList<>(0);
        list1.clear();

        BoundedList<String> list2 = new ArrayBoundedList<>(3);
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
        BoundedList<String> list = new ArrayBoundedList<>(10);
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

        BoundedList<String> list = new ArrayBoundedList<>(3);
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

        BoundedList<String> list = new ArrayBoundedList<>(3);
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

        BoundedList<String> list = new ArrayBoundedList<>(3);

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

        BoundedList<String> list = new ArrayBoundedList<>(3);

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

        BoundedList<String> list = new ArrayBoundedList<>(3);

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

        BoundedList<String> list = new ArrayBoundedList<>(3);

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

        BoundedList<String> list = new ArrayBoundedList<>(3);
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

    private static void testImplementationRequirements() {
        System.out.println("Testing implementation requirements:");

        Field[] fields = ArrayBoundedList.class.getDeclaredFields();

        if (fields.length > 2) {
            System.out.println("At most two fields allowed!");
        }

        boolean hasObjectArray = false, hasInt = false;

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                System.out.println("Field " + field.getName() + " is not private!");
            }

            if (field.getType() == Object[].class) {
                hasObjectArray = true;
            } else if (field.getType() == int.class) {
                hasInt = true;
            }
        }

        if (!hasObjectArray) {
            System.out.println("One of the fields must be an array of Objects (that is, an Object[])!");
        }

        if (!hasInt) {
            System.out.println("One of the fields must be an int!");
        }

        System.out.println();
    }

    private static void testAddIntString() {
        System.out.println("Testing add(int index, String s):");

        BoundedList<String> list = new ArrayBoundedList<>(7);
        System.out.println(list); // []

        list.add(0, "a");
        System.out.println(list); // [a]

        list.add(0, "z");
        System.out.println(list); // [z, a]

        list.add(2, "c");
        System.out.println(list); // [z, a, c]

        list.add(3, null);
        System.out.println(list); // [z, a, c, null]

        list.add(2, "b");
        System.out.println(list); // [z, a, b, c, null]

        list.add(2, null);
        System.out.println(list); // [z, a, null, b, c, null]

        // Good, IndexOutOfBoundsException thrown
        try {
            list.add(7, "d");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        // Good, IndexOutOfBoundsException thrown
        try {
            list.add(-1, "y");
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.add(1, null);
        System.out.println(list); // [z, null, a, null, b, c, null]

        // Good, IllegalStateException thrown
        try {
            list.add(1, "y");
            System.out.println("Exception not thrown!");
        } catch (IllegalStateException e) {
            System.out.println("Good, IllegalStateException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testRemoveInt() {
        System.out.println("Testing remove(int index):");

        BoundedList<String> list = new ArrayBoundedList<>(10);
        list.add("a");
        list.add(null);
        list.add(null);
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        System.out.println(list); // [a, null, null, b, c, d, e, f]

        String removed = list.remove(4);
        System.out.println(removed); // c
        System.out.println(list);    // [a, null, null, b, d, e, f]

        removed = list.remove(0);
        System.out.println(removed); // a
        System.out.println(list);    // [null, null, b, d, e, f]

        removed = list.remove(1);
        System.out.println(removed); // null
        System.out.println(list);    // [null, b, d, e, f]

        removed = list.remove(4);
        System.out.println(removed); // f
        System.out.println(list);    // [null, b, d, e]

        // Good, IndexOutOfBoundsException thrown
        try {
            list.remove(4);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        // Good, IndexOutOfBoundsException thrown
        try {
            list.remove(-1);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        list.clear();

        // Good, IndexOutOfBoundsException thrown
        try {
            list.remove(0);
            System.out.println("Exception not thrown!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Good, IndexOutOfBoundsException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testRemoveString() {
        System.out.println("Testing remove(String):");

        BoundedList<String> list = new ArrayBoundedList<>(10);
        list.add("a");
        list.add("b");
        list.add(null);
        list.add("b");
        list.add(null);
        list.add("c");
        list.add("d");
        System.out.println(list);    // [a, b, null, b, null, c, d]

        boolean removed = list.remove(new String("b"));
        System.out.println(removed); // true
        System.out.println(list);    // [a, null, b, null, c, d]

        removed = list.remove(null);
        System.out.println(removed); // true
        System.out.println(list);    // [a, b, null, c, d]

        removed = list.remove("b");
        System.out.println(removed); // true
        System.out.println(list);    // [a, null, c, d]

        removed = list.remove("b");
        System.out.println(removed); // false
        System.out.println(list);    // [a, null, c, d]

        removed = list.remove("d");
        System.out.println(removed); // true
        System.out.println(list);    // [a, null, c]

        removed = list.remove("a");
        System.out.println(removed); // true
        System.out.println(list);    // [null, c]

        removed = list.remove(null);
        System.out.println(removed); // true
        System.out.println(list);    // [c]

        removed = list.remove(null);
        System.out.println(removed); // false
        System.out.println(list);    // [c]

        removed = list.remove("c");
        System.out.println(removed); // true
        System.out.println(list);    // []

        removed = list.remove(null);
        System.out.println(removed);  // false
        System.out.println(list);     // []

        removed = list.remove("a");
        System.out.println(removed);  // false
        System.out.println(list);     // []

        System.out.println();
    }
}