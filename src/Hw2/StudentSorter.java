package Hw2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

/*
On each run of the program, the program should read through the file only once.

Used to achieve abstraction in Java:
An interface is a completely "abstract class" that is used to group related methods with empty bodies. --
Interfaces are full of abstract methods that must be overridden by a concrete class that implements the interface.

Tips Note:
When writing multiple classes in a single file, only one of the classes can be public.
Typically, the class containing the main method is public. The file name should match the name of the public class.

Using Comparators step-by-step guide:
    Method used: Separate Classes
    1) Create a separate class that implements the Comparator interface.
    2) In the separate class override the compare() method of the Comparator interface. Give it a method body.
    3) Create a Comparator<> variable referencing the separate class.
    4) Use List's sort() method to sort an ArrayList with the Comparator's variable/name.
    Examples of each step for byFirstName is numbered. // (1) (2) (3) (4)
*/
public class StudentSorter{
    public static void main(String[] args) throws FileNotFoundException {

        Scanner fileSc = new Scanner(new File("students.txt"));
        Scanner keybd = new Scanner(System.in);

        // Note: List interface's abstract methods must be overridden by ArrayList, the concrete class.
        List<Student> studentArrayList = new ArrayList<>();

        readFile(fileSc, studentArrayList);

        // Note: Comparator interface's abstract methods must be overridden by
        // StudentFirstNameComparator, the concrete class.
        Comparator<Student> byFirstName = new StudentFirstNameComparator(); // (3)
        Comparator<Student> byLastName = new StudentLastNameComparator();
        Comparator<Student> byId = new StudentIdComparator();
        Comparator<Student> byGrade = new StudentGradeComparator();

        char choice;

        do{
            printMenu();
            choice = keybd.next().toUpperCase().charAt(0);
            // next() A Scanner method: finds next complete token from Scanner, returns a String of the token;
            // toUpperCase() String method: Convert characters of String to upperCase, so that lowercase letters are accepted too;
            // charAt(0) String method: look at the first character of the String, returns it as a char.

            switch(choice){
                case 'F':
                    // List's sort() is invoked.
                    // The parenthesis should have a Comparator's reference name (variable) in it.
                    studentArrayList.sort(byFirstName); // (4)
                    System.out.println("students sorted by first name:");
                    printArrayList(studentArrayList);
                    break;
                case 'L':
                    studentArrayList.sort(byLastName);
                    System.out.println("students sorted by last name:");
                    printArrayList(studentArrayList);
                    break;
                case 'I':
                    studentArrayList.sort(byId);
                    System.out.println("students sorted by id:");
                    printArrayList(studentArrayList);
                    break;
                case 'G':
                    studentArrayList.sort(byGrade);
                    System.out.println("students sorted by grade:");
                    printArrayList(studentArrayList);
                    break;
                case 'Q':
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while(choice != 'Q');
    }


    public static void printMenu(){
        System.out.println("Choices:");
        System.out.println("F - sort by first name");
        System.out.println("L - sort by last name");
        System.out.println("I - sort by id");
        System.out.println("G - sort by grade");
        System.out.println("Q - quit");
    }

    /**
     * Reads in Student objects from the students.txt file and stores it in an ArrayList of Students.
     */
    public static void readFile(Scanner fileSc, List<Student> arr){
        while(fileSc.hasNextLine()){
            String firstN = fileSc.next();
            String lastN = fileSc.next();
            int id = fileSc.nextInt();
            float grade = fileSc.nextFloat();

            arr.add(new Student(firstN, lastN, id, grade));
        }
        // Return type is void b/c arr.add() directly inputs Students into the specified List array (studentArrayList).

        // Debugging enhanced for loop and size to check if objects were registered into Arraylist correctly.
        /* for(Student e : arr){
            System.out.println(e.toString());
        }

        System.out.println("ArrayList size: " + arr.size());*/
    }

    public static void printArrayList(List<Student> arr){
        for(Student e : arr){
            System.out.println(e.toString());
        }
    }
}


// Chosen Comparator method: Separate classes

/**
 * Separate Class Goal: F - Sort by first name in Alphabetical order.
 */
// (1)
class StudentFirstNameComparator implements Comparator<Student> {
    @Override // (2)
    public int compare(Student s1, Student s2){
        return s1.getFirstName().compareTo(s2.getFirstName());
    }
}
/**
 * Separate Class Goal: L - Sort by last name in Alphabetical order.
 */
class StudentLastNameComparator implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2){
        return s1.getLastName().compareTo(s2.getLastName());
    }
}
/**
 * Separate Class Goal: I - Sort by id in Standard Numerical order. (smallest to largest #)
 */
class StudentIdComparator implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2){
        return Integer.compare(s1.getId(), s2.getId());
    }
}
/**
 * Separate Class Goal: G - Sort by grade in Standard Numerical order. (smallest to largest #)
 */
class StudentGradeComparator implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2){
        return Float.compare(s1.getGrade(), s2.getGrade());
    }
}

class Student{
    private String firstName;
    private String lastName;
    private int id;
    private float grade;
    // A float data type in Java stores a decimal value with 6-7 total digits of precision.

    public Student(String firstName, String lastName, int id, float grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.grade = grade;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getId(){
        return id;
    }

    public float getGrade(){
        return grade;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName + " " + id + " " + grade;
    }
}