package StudentManagementSystem;
public class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return id + "," + name + "," + age;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            return new Student(parts[0], parts[1], Integer.parseInt(parts[2]));
        }
        return null;
    }
}
