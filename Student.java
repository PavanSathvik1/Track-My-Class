import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private int age;
    private String year;
    private String department;
    private String mentor;

    public Student(String id, String name, int age, String year, String department, String mentor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.year = year;
        this.department = department;
        this.mentor = mentor;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getYear() { return year; }
    public String getDepartment() { return department; }
    public String getMentor() { return mentor; }

    // Save student to DB
    public void saveToDB() {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO students (id, name, age, department, year, mentor) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, department);
            pstmt.setString(5, year);
            pstmt.setString(6, mentor);
            pstmt.executeUpdate();
            System.out.println("✅ Student saved to DB!");
        } catch (SQLException e) {
            System.out.println("❌ Error saving student: " + e.getMessage());
        }
    }

    // Get all students
    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("year"),
                        rs.getString("department"),
                        rs.getString("mentor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Find student by ID
    public static Student findById(String id) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM students WHERE id=?")) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("year"),
                        rs.getString("department"),
                        rs.getString("mentor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete student by ID
    public static boolean deleteById(String id) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
            pstmt.setString(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
