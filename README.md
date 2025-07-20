# Track My Class (Java Console App)

A Java console-based application to manage student records. Automatically assigns mentors based on the student's academic year using a mentor mapping file.

## 🚀 Features
- Add student details (ID, Name, Age, Department, Year)
- Auto-assign mentor based on student's year using `mentors.txt`
- View all students
- Search student by ID
- Delete student by ID
- Data saved in a local text file (`students.txt`)

## 📂 Technologies Used
- Core Java (OOP, File Handling)
- Java Collections (ArrayList, HashMap)
- Console-based interface

## 📄 Project Structure

- `Student.java` - Represents a student with ID, name, age, department, year, mentor
- `MentorManager.java` - Loads mentor mappings from `mentors.txt`
- `Main.java` - Main driver file with menu logic
- `students.txt` - Stores student data persistently
- `mentors.txt` - Stores mapping of year to mentor (e.g., `1st=Dr. Sharma`)

## 📦 Sample `mentors.txt` Format

```txt
 1st=Dr. Sharma
 2nd=Prof. Rao
 3rd=Dr. Meena
 4th=Dr. Kulkarni
```


## 🧪 Sample Output

- Student added successfully!
- Mentor assigned: Prof. Rao

## 🛠️ How to Run

### Prerequisites
- Java (JDK 17 or above recommended)

- VS Code or any Java IDE (IntelliJ, Eclipse, etc.)

### Steps
1. Clone the repository or download the source files.

2. Ensure these files are in the same folder:

   - Student.java

   - Main.java

   - MentorManager.java

   - mentors.txt

3. Compile the files using terminal:
   ```bash

   javac Student.java MentorManager.java Main.java

   java Main
   ```
   
   -Or use the Run button in VS Code or your IDE.

## 📌 Future Improvements

- Add database support (MySQL or SQLite)

- GUI using JavaFX or Swing

- Export student report as PDF

- Add search by department or mentor

- Login/authentication system

## 🧑‍💻 Author
 - Pavan Sathvik
