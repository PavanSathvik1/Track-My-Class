package StudentManagementSystem;
import java.io.*;
import java.util.*;

public class Main {
    static final String FILE_NAME = "students.txt";
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> deleteStudent(sc);
                case 5 -> saveStudents();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        Student s = new Student(id, name, age);
        students.add(s);
        System.out.println("Student added successfully.");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + " | Name: " + s.getName() + " | Age: " + s.getAge());
        }
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();
        for (Student s : students) {
            if (s.getId().equals(id)) {
                System.out.println("Found -> Name: " + s.getName() + ", Age: " + s.getAge());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();
        Iterator<Student> iterator = students.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                found = true;
                System.out.println("Student deleted.");
                break;
            }
        }
        if (!found) System.out.println("Student not found.");
    }

    static void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("Students saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    static void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromString(line);
                if (s != null) students.add(s);
            }
        } catch (IOException e) {
            System.out.println("No previous student data found.");
        }
    }
}
