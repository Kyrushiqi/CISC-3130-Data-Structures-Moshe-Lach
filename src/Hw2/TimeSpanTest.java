package Hw2;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TimeSpanTest {
    public static void main(String[] args) {
        testStaticFactoryMethods();
        testToString();
        testEquals();
        testHashCode();
        testCompareTo();
        testPlus();
        testPlusHours();
        testPlusMinutes();
        testPlusHoursAndMinutes();
        testMaxTwoFieldsAllIntsAllPrivate();
    }

    private static void testStaticFactoryMethods() {
        System.out.println("Static factory methods:\n");

        System.out.println("ofHours:\n");
        testOfHoursAndGetters();
        System.out.println();

        System.out.println("ofMinutes:\n");
        testOfMinutesAndGetters();
        System.out.println();

        System.out.println("ofHoursAndMinutes:\n");
        testOfHoursAndMinutesAndGetters();
        System.out.println();
    }

    private static void testOfHoursAndGetters() {
        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHours(0):");
        TimeSpan timeSpan = TimeSpan.ofHours(0);
        System.out.println("hours: " + timeSpan.getHours()); // 0
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 0
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHours(1):");
        timeSpan = TimeSpan.ofHours(1);
        System.out.println("hours: " + timeSpan.getHours()); // 1
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 60
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHours(2):");
        timeSpan = TimeSpan.ofHours(2);
        System.out.println("hours: " + timeSpan.getHours()); // 2
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 120
        System.out.println();

        System.out.println("Testing IllegalArgumentException throwing:");
        try {
            TimeSpan.ofHours(-1);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }
        System.out.println();
    }

    private static void testOfMinutesAndGetters() {
        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofMinutes(0):");
        TimeSpan timeSpan = TimeSpan.ofMinutes(0);
        System.out.println("hours: " + timeSpan.getHours()); // 0
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 0
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofMinutes(1):");
        timeSpan = TimeSpan.ofMinutes(1);
        System.out.println("hours: " + timeSpan.getHours()); // 0
        System.out.println("minutes: " + timeSpan.getMinutes()); // 1
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 1
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofMinutes(2):");
        timeSpan = TimeSpan.ofMinutes(2);
        System.out.println("hours: " + timeSpan.getHours()); // 0
        System.out.println("minutes: " + timeSpan.getMinutes()); // 2
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 2
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofMinutes(60):");
        timeSpan = TimeSpan.ofMinutes(60);
        System.out.println("hours: " + timeSpan.getHours()); // 1
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 60
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofMinutes(200):");
        timeSpan = TimeSpan.ofMinutes(200);
        System.out.println("hours: " + timeSpan.getHours()); // 3
        System.out.println("minutes: " + timeSpan.getMinutes()); // 20
        System.out.println("total minutes " + timeSpan.getTotalMinutes()); // 200
        System.out.println();

        System.out.println("Testing IllegalArgumentException throwing:");
        try {
            TimeSpan.ofMinutes(-1);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }
        System.out.println();
    }

    private static void testOfHoursAndMinutesAndGetters() {
        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHoursAndMinutes(0, 0):");
        TimeSpan timeSpan = TimeSpan.ofHoursAndMinutes(0, 0);
        System.out.println("hours: " + timeSpan.getHours()); // 0
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes: " + timeSpan.getTotalMinutes()); // 0
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHoursAndMinutes(0, 1):");
        timeSpan = TimeSpan.ofHoursAndMinutes(0, 1);
        System.out.println("hours: " + timeSpan.getHours()); // 0
        System.out.println("minutes: " + timeSpan.getMinutes()); // 1
        System.out.println("total minutes: " + timeSpan.getTotalMinutes()); // 1
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHoursAndMinutes(1, 0):");
        timeSpan = TimeSpan.ofHoursAndMinutes(1, 0);
        System.out.println("hours: " + timeSpan.getHours()); // 1
        System.out.println("minutes: " + timeSpan.getMinutes()); // 0
        System.out.println("total minutes: " + timeSpan.getTotalMinutes()); // 60
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHoursAndMinutes(1, 1):");
        timeSpan = TimeSpan.ofHoursAndMinutes(1, 1);
        System.out.println("hours: " + timeSpan.getHours()); // 1
        System.out.println("minutes: " + timeSpan.getMinutes()); // 1
        System.out.println("total minutes: " + timeSpan.getTotalMinutes()); // 61
        System.out.println();

        System.out.println("Hours, minutes, and total minutes of TimeSpan.ofHoursAndMinutes(100, 100):");
        timeSpan = TimeSpan.ofHoursAndMinutes(100, 100);
        System.out.println("hours: " + timeSpan.getHours()); // 101
        System.out.println("minutes: " + timeSpan.getMinutes()); // 40
        System.out.println("total minutes: " + timeSpan.getTotalMinutes()); // 6100
        System.out.println();

        System.out.println("Testing IllegalArgumentException throwing:");
        try {
            TimeSpan.ofHoursAndMinutes(-1, 90);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }

        try {
            TimeSpan.ofHoursAndMinutes(90, -1);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }
        System.out.println();
    }

    private static void testToString() {
        System.out.println("Testing toString");

        TimeSpan timeSpan = TimeSpan.ofMinutes(0);
        String result = timeSpan.toString();
        System.out.println("TimeSpan.ZERO.toString(): " + result); // 0h0m

        timeSpan = TimeSpan.ofMinutes(1);
        result = timeSpan.toString();
        System.out.println("TimeSpan.ONE_MINUTE.toString(): " + result); // 0h1m

        timeSpan = TimeSpan.ofHours(1);
        result = timeSpan.toString();
        System.out.println("TimeSpan.ONE_HOUR.toString(): " + result); // 1h0m

        timeSpan = TimeSpan.ofHours(3);
        result = timeSpan.toString();
        System.out.println("TimeSpan.ofHours(3).toString(): " + result); // 3h0m

        timeSpan = TimeSpan.ofMinutes(127);
        result = timeSpan.toString();
        System.out.println("TimeSpan.ofMinutes(127).toString(): " + result); // 2h7m

        timeSpan = TimeSpan.ofHoursAndMinutes(5, 100);
        result = timeSpan.toString();
        System.out.println("TimeSpan.ofHoursAndMinutes(5, 100): " + result); // 6h40m

        System.out.println();
    }

    private static void testEquals() {
        System.out.println("Testing equals");

        TimeSpan ts1, ts2;

        ts1 = TimeSpan.ofMinutes(0);
        ts2 = ts1;
        boolean result = ts1.equals(ts2);
        System.out.println(result);             // true

        ts1 = TimeSpan.ofMinutes(0);
        ts2 = TimeSpan.ofMinutes(0);
        System.out.println(ts1.equals(ts2)); // true

        System.out.println(TimeSpan.ofMinutes(0).equals(TimeSpan.ofMinutes(1))); // false

        System.out.println(TimeSpan.ofMinutes(0).equals(TimeSpan.ofHours(1)));  // false

        System.out.println(TimeSpan.ofHours(3).equals(TimeSpan.ofMinutes(180))); // true

        System.out.println(TimeSpan.ofHoursAndMinutes(2, 30).equals(TimeSpan.ofHoursAndMinutes(1, 90))); // true

        System.out.println(TimeSpan.ofHours(3).equals(TimeSpan.ofMinutes(3))); // false

        TimeSpan ts = TimeSpan.ofHoursAndMinutes(4, 56);
        TimeSpan o = TimeSpan.ofHoursAndMinutes(4, 56);
        System.out.println(ts.equals(o)); // true

        System.out.println(ts.equals("4h56m")); // false

        System.out.println(ts.equals(null)); // false

        System.out.println();
    }

    private static void testHashCode() {
        System.out.println("Testing hashCode:");

        TimeSpan ts1 = TimeSpan.ofHoursAndMinutes(2, 30);
        TimeSpan ts2 = TimeSpan.ofHoursAndMinutes(1, 90);

        System.out.println(ts1.equals(ts2)); // true
        System.out.println(ts1.hashCode() == ts2.hashCode()); // true

        System.out.println();
    }

    private static void testCompareTo() {
        System.out.println("Testing compareTo:");

        System.out.println(TimeSpan.ofMinutes(0) instanceof Comparable); // true

        System.out.println(TimeSpan.ofMinutes(0).compareTo(TimeSpan.ofMinutes(1)) < 0);  // true
        System.out.println(TimeSpan.ofMinutes(1).compareTo(TimeSpan.ofHours(1))   < 0);  // true
        System.out.println(TimeSpan.ofMinutes(117).compareTo(TimeSpan.ofHours(2)) < 0);  // true

        System.out.println(TimeSpan.ofMinutes(1).compareTo(TimeSpan.ofMinutes(1)) == 0); // true
        System.out.println(TimeSpan.ofHoursAndMinutes(2, 30).compareTo(TimeSpan.ofHoursAndMinutes(1, 90)) == 0); // true

        System.out.println(TimeSpan.ofMinutes(117).compareTo(TimeSpan.ofHours(1)) > 0);  // true
        System.out.println(TimeSpan.ofHours(1).compareTo(TimeSpan.ofMinutes(1))   > 0);  // true
        System.out.println(TimeSpan.ofMinutes(1).compareTo(TimeSpan.ofMinutes(0)) > 0);  // true
        System.out.println(TimeSpan.ofHours(2).compareTo(TimeSpan.ofMinutes(117)) > 0);  // true
        System.out.println(TimeSpan.ofHours(1).compareTo(TimeSpan.ofMinutes(117)) < 0);  // true

        System.out.println();
    }

    private static void testPlus() {
        System.out.println("Testing plus");

        TimeSpan timeSpan1 = TimeSpan.ofHoursAndMinutes(2, 30);
        TimeSpan timeSpan2 = TimeSpan.ofHoursAndMinutes(1, 45);
        TimeSpan sum = timeSpan1.plus(timeSpan2);

        System.out.println(timeSpan1); // 2h30m
        System.out.println(timeSpan2); // 1h45m
        System.out.println(sum);       // 4h15m

        System.out.println(timeSpan1.plus(TimeSpan.ofMinutes(0))); // 2h30m

        System.out.println();
    }

    private static void testPlusHours() {
        System.out.println("Testing plusHours");

        TimeSpan original = TimeSpan.ofHoursAndMinutes(2, 30);
        TimeSpan updated = original.plusHours(5);
        System.out.println(original.toString()); // 2h30m
        System.out.println(updated.toString());  // 7h30m

        System.out.println(original.plusHours(0).toString()); // 2h30m

        System.out.println("Testing IllegalArgumentException throwing:");
        try {
            original.plusHours(-1);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }

        System.out.println();
    }

    private static void testPlusMinutes() {
        System.out.println("Testing plusMinutes");

        TimeSpan original = TimeSpan.ofHoursAndMinutes(2, 30);
        TimeSpan updated = original.plusMinutes(90);
        System.out.println(original.toString()); // 2h30m
        System.out.println(updated.toString());  // 4h0m

        System.out.println(original.plusMinutes(0).toString()); // 2h30m

        System.out.println("Testing IllegalArgumentException throwing:");
        try {
            original.plusMinutes(-1);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }

        System.out.println();
    }

    private static void testPlusHoursAndMinutes() {
        System.out.println("Testing plusHoursAndMinutes");

        TimeSpan original = TimeSpan.ofHoursAndMinutes(2, 30);
        TimeSpan updated = original.plusHoursAndMinutes(2, 75);
        System.out.println(original); // 2h30m
        System.out.println(updated);  // 5h45m

        System.out.println("Testing IllegalArgumentException throwing:");
        try {
            original.plusHoursAndMinutes(-1, 90);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }

        try {
            original.plusHoursAndMinutes(90, -1);
            System.out.println("An IllegalArgumentException was supposed to be thrown here, but wasn't.");
        } catch (IllegalArgumentException e) {
            System.out.println("Good, IllegalArgumentException was thrown.");
        }

        System.out.println();
    }

    private static void testMaxTwoFieldsAllIntsAllPrivate() {
        Field[] fields = TimeSpan.class.getDeclaredFields();

        if (fields.length > 2) {
            System.out.println("TimeSpan contains more than two fields. Max two fields allowed.");
        }

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                System.out.println("Field " + field + " is not private. All fields must be private.");
            }

            if (field.getType() != int.class) {
                System.out.println("Field " + field + " is not an int. All fields must be of type int.");
            }
        }
    }
}