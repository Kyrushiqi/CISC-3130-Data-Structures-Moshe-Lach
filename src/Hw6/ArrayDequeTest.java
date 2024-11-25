package Hw6;

import java.lang.reflect.Field;    // magic
import java.lang.reflect.Modifier; // magic
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayDequeTest {
    public static void main(String[] args) {
        fieldTests();

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        printInfo(deque);

        testExceptionThrowing(deque);

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.addLast(40);
        deque.addLast(50);

        printInfo(deque);

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        printInfo(deque);

        deque.addLast(60);
        deque.addFirst(70);
        deque.addFirst(80);

        printInfo(deque);

        deque.addFirst(90);
        deque.addFirst(100);
        deque.addFirst(200);

        printInfo(deque);

        deque.addFirst(300);

        printInfo(deque);

        deque.addFirst(400);

        printInfo(deque);

        for (int i = 1; i <= 4; i++) {
            deque.removeFirst();
            deque.addLast(i);
        }

        printInfo(deque);

        deque.addLast(500);
        deque.addLast(600);
        deque.addFirst(700);
        deque.addFirst(800);

        printInfo(deque);

        while (!deque.isEmpty()) {
            deque.removeLast();
        }

        printInfo(deque);

        testExceptionThrowing(deque);

        deque.addFirst(10);
        printInfo(deque);

        ArrayDeque<Integer> deque2 = new ArrayDeque<>(5);
        deque2.addFirst(1);
        deque2.addLast(2);
        deque2.addFirst(3);
        printInfo(deque2);
    }

    private static void fieldTests() {
        Field[] fields = ArrayDeque.class.getDeclaredFields();
        int instanceFieldCount = 0;
        boolean foundArrayInstanceField = false;

        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                instanceFieldCount++;

                if (field.getType().isArray()) {
                    foundArrayInstanceField = true;
                }
            }
        }

        if (instanceFieldCount > 3) {
            System.out.println("ArrayDeque has more than three instance fields!");
        }

        if (!foundArrayInstanceField) {
            System.out.println("ArrayDeque doesn't have an array as an instance field!");
        }
    }

    private static void testExceptionThrowing(ArrayDeque<Integer> deque) {
        try {
            deque.getFirst();
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        } catch (NoSuchElementException ignored) {
        } catch (Exception ignored) {
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        }

        try {
            deque.getLast();
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        } catch (NoSuchElementException ignored) {
        } catch (Exception ignored) {
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        }

        try {
            deque.removeFirst();
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        } catch (NoSuchElementException ignored) {
        } catch (Exception ignored) {
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        }

        try {
            deque.removeLast();
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        } catch (NoSuchElementException ignored) {
        } catch (Exception ignored) {
            System.out.println("A NoSuchElementException was supposed to be thrown here!");
        }
    }

    private static void printInfo(ArrayDeque<Integer> deque) {
        System.out.println("internal array: " + Arrays.toString(getInternalArray(deque)));
        System.out.println("size: " + deque.size());
        System.out.println("is empty: " + deque.isEmpty());

        if (!deque.isEmpty()) {
            System.out.println("first: " + deque.getFirst());
            System.out.println("last: " + deque.getLast());
        }

        System.out.println("String representation: " + deque);

        System.out.print("Iterating: ");
        for (Integer element : deque) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.println();
    }

    private static Object[] getInternalArray(ArrayDeque<Integer> deque) {
        try {
            Field[] fields = ArrayDeque.class.getDeclaredFields();

            for (Field field : fields) {
                if (field.getType().isArray()) {
                    field.setAccessible(true);
                    return (Object[]) field.get(deque);
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("unknown exception cause -- please contact instructor!");
        }

        return null;
    }
}