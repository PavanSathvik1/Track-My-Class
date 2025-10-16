import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static String DB_NAME;

    static {
        try {
            // Load db.properties
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));

            String baseUrl = props.getProperty("db.baseUrl", "jdbc:mysql://localhost:3306/");
            DB_NAME = props.getProperty("db.name", "trackmyclass");
            URL = baseUrl + DB_NAME;
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            // Step 1: Create database if not exists
            try (Connection conn = DriverManager.getConnection(baseUrl, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                System.out.println("Database ready: " + DB_NAME);
            }

            // Step 2: Create tables if not exist
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS mentors (" +
                        "year VARCHAR(10) PRIMARY KEY," +
                        "name VARCHAR(50) NOT NULL" +
                        ")");

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                        "id VARCHAR(20) PRIMARY KEY," +
                        "name VARCHAR(100)," +
                        "age INT," +
                        "department VARCHAR(50)," +
                        "year VARCHAR(10)," +
                        "mentor VARCHAR(50)," +
                        "FOREIGN KEY (year) REFERENCES mentors(year)" +
                        ")");

                System.out.println("Tables ready (created if not existing)");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

