import java.util.*;

public class Main {
    public static void main(String[] args) {
        MentorManager.loadMentorsFromDB();

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
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> deleteStudent(sc);
                case 5 -> System.out.println("Exiting...");
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
        System.out.print("Enter Year (e.g., 1st, 2nd, 3rd, 4th): ");
        String year = sc.nextLine();
        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        String mentor = MentorManager.getMentor(year);

        Student s = new Student(id, name, age, year, department, mentor);
        s.saveToDB();
        System.out.println("Student added with mentor: " + mentor);
    }

    static void viewStudents() {
        List<Student> students = Student.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.getId() +
                    " | Name: " + s.getName() +
                    " | Age: " + s.getAge() +
                    " | Year: " + s.getYear() +
                    " | Dept: " + s.getDepartment() +
                    " | Mentor: " + s.getMentor());
        }
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();
        Student s = Student.findById(id);
        if (s != null) {
            System.out.println("Found -> Name: " + s.getName() +
                    ", Age: " + s.getAge() +
                    ", Year: " + s.getYear() +
                    ", Dept: " + s.getDepartment() +
                    ", Mentor: " + s.getMentor());
        } else {
            System.out.println("Student not found.");
        }
    }

    static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();
        if (Student.deleteById(id)) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
