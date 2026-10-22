
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
        boolean running = true;
        String name = "";

        while (running) {

            System.out.println("Enter the student's name: ");
            name = scanner.nextLine().strip();

            if (name.isEmpty()) {
                System.out.println("Enter a valid name!");
                continue;
            }
            running = false;
        }

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

    public static void ListStudent(ArrayList<Student> studentList, Scanner scanner) {
        for (Student student : studentList) {
            System.out.println("Student: " + student.name + " | Grade: " + student.grade);
        }
        scanner.nextLine();
    }

    public static void DetailStudent(ArrayList<Student> studentList, Scanner scanner) {

        if (studentList.isEmpty()) {
            System.out.println("No students listed");
            return;
        }
        ListStudent(studentList, scanner);
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
        scanner.nextLine();

        if (!found) {
            System.out.println("fuck it");
            return;
        }
    }

    public static void Menu(ArrayList<Student> studentList, Scanner scanner) {
        boolean running = true;

        while (running) {

            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("\n\n\n===== STUDENT MANAGER =====");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Find student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a number!");
                System.out.println("Press ENTER to continue . . .");
                scanner.nextLine();
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    Student student = AddStudent(scanner);
                    studentList.add(student);
                    System.out.println("\nStudent added successfully!");
                    System.out.println("Press ENTER to continue . . .");
                    scanner.nextLine();
                    break;

                case 2:
                    if (studentList.isEmpty()) {
                        System.out.println("\nNo students listed.");
                        System.out.println("Press ENTER to continue . . .");
                        scanner.nextLine();
                        continue;
                    }

                    System.out.println("\nStudents: " + studentList.size());
                    ListStudent(studentList, scanner);

                    System.out.println("Press ENTER to continue . . .");
                    scanner.nextLine();
                    break;

                case 3:
                    DetailStudent(studentList, scanner);
                    System.out.println("Press ENTER to continue . . .");
                    scanner.nextLine();
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("\nInvalid option!");
                    System.out.println("Press ENTER to continue . . .");
                    scanner.nextLine();
            }
        }
    }
}
