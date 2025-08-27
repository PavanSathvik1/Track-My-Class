import java.sql.*;
import java.util.HashMap;

public class MentorManager {
    private static HashMap<String, String> mentorMap = new HashMap<>();

    public static void loadMentorsFromDB() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM mentors");
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                stmt.executeUpdate("INSERT INTO mentors VALUES ('1st','Dr. Sharma')");
                stmt.executeUpdate("INSERT INTO mentors VALUES ('2nd','Prof. Rao')");
                stmt.executeUpdate("INSERT INTO mentors VALUES ('3rd','Dr. Meena')");
                stmt.executeUpdate("INSERT INTO mentors VALUES ('4th','Dr. Kulkarni')");
                System.out.println("✅ Default mentors inserted");
            }

            rs = stmt.executeQuery("SELECT * FROM mentors");
            mentorMap.clear();
            while (rs.next()) {
                mentorMap.put(rs.getString("year"), rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getMentor(String year) {
        return mentorMap.get(year);
    }
}
