package Hw4;

public class BoundedListTest {
    public static void main(String[] args) {
        testAddAll1();
        testCopyTo();
    }

    private static void testAddAll1() {
        System.out.println("Testing addAll(other):");

        BoundedList<Object> list = new ArrayBoundedList<>(7);
        list.add(1);
        list.add(2);

        BoundedList<Integer> other = new ArrayBoundedList<>(10);
        other.add(3);
        other.add(4);

        list.addAll(other);
        System.out.println(list);  // [1, 2, 3, 4]
        System.out.println(other); // [3, 4]

        other.add(5);

        list.addAll(other);
        System.out.println(list);  // [1, 2, 3, 4, 3, 4, 5]
        System.out.println(other); // [3, 4, 5]

        // Good, IllegalStateException thrown
        try {
            list.addAll(other);
            System.out.println("Exception not thrown!");
        } catch (IllegalStateException e) {
            System.out.println("Good, IllegalStateException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }

    private static void testCopyTo() {
        System.out.println("Testing copyTo(other):");

        BoundedList<Integer> list = new ArrayBoundedList<>(10);
        list.add(3);
        list.add(4);

        BoundedList<Object> other = new ArrayBoundedList<>(7);
        other.add(1);
        other.add(2);

        list.copyTo(other);
        System.out.println(list);  // [3, 4]
        System.out.println(other); // [1, 2, 3, 4]

        list.add(5);

        list.copyTo(other);
        System.out.println(list);  // [3, 4, 5]
        System.out.println(other); // [1, 2, 3, 4, 3, 4, 5]

        // Good, IllegalStateException thrown
        try {
            list.copyTo(other);
            System.out.println("Exception not thrown!");
        } catch (IllegalStateException e) {
            System.out.println("Good, IllegalStateException thrown");
        } catch (Exception e) {
            System.out.println("Wrong type of exception thrown!");
        }

        System.out.println();
    }
}