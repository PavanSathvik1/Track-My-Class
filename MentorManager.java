import java.io.*;
import java.util.*;

public class MentorManager {
    private static Map<String, String> mentorMap = new HashMap<>();

    public static void loadMentors(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    mentorMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Mentor file not found.");
        }
    }

    public static String getMentor(String year) {
        return mentorMap.getOrDefault(year, "No Mentor Assigned");
    }
}
