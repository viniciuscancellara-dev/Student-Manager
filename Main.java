
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        // scanner
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<Student>();

        // menu
        Menu(studentList, scanner);
    }

    public static Student AddStudent(Scanner scanner) {

        System.out.println("Enter the student's name: ");
        String name = scanner.nextLine();

        int grade = 0;
        boolean validGrade = false;

        while (!validGrade) {
            try {
                System.out.println("Enter the student's grade: ");
                grade = scanner.nextInt();
                scanner.nextLine();

                if (grade < 0 || grade > 10) {
                    System.out.println("Grade must be between 0 and 10!");
                } else {
                    validGrade = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("The grade must be a number!");
                scanner.nextLine();
            }
        }

        return new Student(name, grade);

    }

    public static void ListStudent(ArrayList<Student> studentList) {
        for (Student student : studentList) {
            System.out.println("Student: " + student.name + " | Grade: " + student.grade);
        }
    }

    public static void DetailStudent(ArrayList<Student> studentList, Scanner scanner) {
        ListStudent(studentList);
        System.out.println("Enter a name to see the details: ");

        String chosenStudent = scanner.nextLine();

        if (chosenStudent.isEmpty()) {
            System.out.println("fuck it");
            return;
        }

        boolean found = false;

        for (Student student : studentList) {
            if (student.name.equals(chosenStudent)) {
                found = true;
                System.out.println("Student: " + student.name + " | Grade: " + student.grade);
            }
        }
        if (!found) {
            System.out.println("fuck it");
            return;
        }
    }

    private static void Menu(ArrayList<Student> studentList, Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== STUDENT MANAGER =====");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Find student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = scanner.nextInt();
                System.out.println("ESCOLHEU: " + choice);
                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Please enter a number!");
                scanner.nextLine();
                continue;
            }
            System.out.println("CHOICE RECEIVED: " + choice);
            switch (choice) {

                case 1:
                    Student student = AddStudent(scanner);
                    studentList.add(student);
                    break;

                case 2:
                    System.out.println("CASE 2 WORKS");
                    System.out.println("Students: " + studentList.size());
                    ListStudent(studentList);
                    break;

                case 3:
                    DetailStudent(studentList, scanner);
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
