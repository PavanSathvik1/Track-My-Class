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

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + year + "," + department + "," + mentor;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {
            return new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], parts[5]);
        }
        return null;
    }
}
