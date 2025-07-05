📘 TimetableBacktrackingFull
🧠 Description
TimetableBacktrackingFull is a Java program that automatically generates class timetables for multiple departments using a backtracking algorithm. It ensures that:

No teacher is assigned to more than one department at the same time.

No subject exceeds the allowed usage constraint (e.g., max 2 times in same period slot across days).

Subjects are assigned randomly while maintaining constraints.

🚀 Features
Supports multiple departments.

Randomized timetable generation using backtracking.

Ensures no teacher overlap across departments.

Prevents subject overuse in the same time slot.

Command-line based interactive input.

Auto-restarts if timetable generation fails (up to 50 retries).

📂 Structure
Copy
Edit
TimetableBacktrackingFull.java
README.md
🛠️ How It Works
User is prompted to enter:

Number of departments

Names of each department

Subjects and corresponding teachers for each department

The program tries to fill all timetable slots using backtracking.

If generation fails due to constraints, it restarts generation.

Successfully generated timetables are printed department-wise.

🧪 Sample Constraints
Each subject in a department can appear at most twice in the same period index across the week.

A teacher can’t teach multiple departments at the same time slot.

🖥️ How to Run
✅ Requirements
Java 8 or later

Any Java IDE (e.g., Eclipse, IntelliJ, VS Code) or terminal

🧾 Compile & Run from Terminal
bash
Copy
Edit
javac TimetableBacktrackingFull.java
java TimetableBacktrackingFull
📌 Example Input
yaml
Copy
Edit
Enter number of departments: 2
Enter name for department 1: CSE
Enter name for department 2: ECE

Enter number of subjects for CSE: 2
  Enter subject 1: Java
    Enter teacher for Java: Smith
  Enter subject 2: DBMS
    Enter teacher for DBMS: Alice

Enter number of subjects for ECE: 2
  Enter subject 1: Circuits
    Enter teacher for Circuits: Bob
  Enter subject 2: Java
    Enter teacher for Java: Smith
✔ Here, "Java" is shared between CSE and ECE, and handled by the same teacher, so the system ensures no time conflict.

📊 Sample Output
markdown
Copy
Edit
📘 Timetable for CSE Department

Day/Period |  1 |  2 |  3 |  4 |  5 |  6 |  7
============================================
Monday     | Ja | DB | Ja | .....

📘 Timetable for ECE Department

Day/Period |  1 |  2 |  3 |  4 |  5 |  6 |  7
============================================
Monday     | Ci | Ja | DB | .....
(Ja = Java, DB = DBMS, Ci = Circuits, Fr = Free Period)

💡 Implementation Highlights
generateWithRestart(): Tries multiple attempts (up to 50) to generate a valid timetable.

generate(): Recursive backtracking function that fills the timetable slot by slot.

isTeacherFree(): Ensures a teacher doesn’t have a clash.

isSubjectUsageValid(): Prevents subject overload in a particular period slot.

printTimetable(): Beautifully prints the final timetable for all departments.

📌 License
This project is open-source and available under the MIT License.

🙋‍♂️ Author
Ashwin
📘 Computer Science Engineering
🔗 GitHub: [YourGitHubProfile]
✉️ Feel free to connect for contributions or improvements!

