import java.util.*;

public class TimetableBacktrackingFull {

    String[] departments;
    String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int days = 6;
    int periods = 7;
    String[][][] timetable;
    Random rand = new Random();

    Map<String, Map<String, String>> deptSubjects = new HashMap<>();

    public TimetableBacktrackingFull(Scanner sc) {
        // Get number of departments
        System.out.print("Enter number of departments: ");
        int numDepartments = Integer.parseInt(sc.nextLine());

        departments = new String[numDepartments];
        timetable = new String[numDepartments][days][periods];

        // Get department names
        for (int i = 0; i < numDepartments; i++) {
            System.out.print("Enter name for department " + (i + 1) + ": ");
            departments[i] = sc.nextLine().toUpperCase();
        }

        // Get subjects and teachers for each department
        for (String dept : departments) {
            Map<String, String> subjects = new HashMap<>();
            System.out.print("Enter number of subjects for " + dept + ": ");
            int subjectCount = Integer.parseInt(sc.nextLine());

            for (int j = 0; j < subjectCount; j++) {
                System.out.print("  Enter subject " + (j + 1) + ": ");
                String subject = sc.nextLine();
                System.out.print("    Enter teacher for " + subject + ": ");
                String teacher = sc.nextLine();
                subjects.put(subject, teacher);
            }

            deptSubjects.put(dept, subjects);
        }
    }

    public boolean generateWithRestart() {
        int attempts = 0;
        while (true) {
            clearTimetable();
            if (generate(0)) return true;

            attempts++;
            if (attempts > 50) {
                System.out.println("❌ Too many retries. Could not generate a valid timetable.");
                return false;
            }
        }
    }

    public void clearTimetable() {
        for (int i = 0; i < departments.length; i++) {
            for (int d = 0; d < days; d++) {
                Arrays.fill(timetable[i][d], null);
            }
        }
    }

    public boolean generate(int slot) {
        if (slot == departments.length * days * periods)
            return true;

        int deptIdx = slot / (days * periods);
        int rest = slot % (days * periods);
        int day = rest / periods;
        int period = rest % periods;

        String dept = departments[deptIdx];
        List<String> subjects = new ArrayList<>(deptSubjects.get(dept).keySet());
        Collections.shuffle(subjects, rand); // Randomize subject choice

        for (String subject : subjects) {
            if (isTeacherFree(dept, subject, day, period) &&
                isSubjectUsageValid(dept, subject, period)) {

                timetable[deptIdx][day][period] = subject;
                if (generate(slot + 1)) return true;
                timetable[deptIdx][day][period] = null; // backtrack
            }
        }

        return false;
    }

    public boolean isTeacherFree(String currentDept, String subject, int day, int period) {
        String teacher = deptSubjects.get(currentDept).get(subject);
        for (int i = 0; i < departments.length; i++) {
            String otherDept = departments[i];
            String s = timetable[i][day][period];
            if (s != null) {
                String otherTeacher = deptSubjects.get(otherDept).get(s);
                if (teacher.equals(otherTeacher)) {
                    return false; // Conflict: teacher already assigned in another dept
                }
            }
        }
        return true;
    }

    // ✅ New constraint: subject must not appear more than 2 times in the same period slot across days
    public boolean isSubjectUsageValid(String dept, String subject, int period) {
        int deptIdx = Arrays.asList(departments).indexOf(dept);
        int count = 0;
        for (int d = 0; d < days; d++) {
            if (subject.equals(timetable[deptIdx][d][period])) {
                count++;
            }
        }
        return count < 2;
    }

    public void printTimetable() {
        for (int d = 0; d < departments.length; d++) {
            System.out.println("\n📘 Timetable for " + departments[d] + " Department\n");

            // Header row
            System.out.printf("%-10s", "Day/Period");
            for (int p = 1; p <= periods; p++) {
                System.out.printf("| %2d ", p);
            }
            System.out.println();
            System.out.println("=".repeat(10 + 5 * periods));

            // Timetable rows
            for (int day = 0; day < days; day++) {
                System.out.printf("%-10s", dayNames[day]);
                for (int period = 0; period < periods; period++) {
                    String subject = timetable[d][day][period];
                    String shortSub = (subject != null) ? subject.substring(0, Math.min(2, subject.length())) : "Fr";
                    System.out.printf("| %-2s ", shortSub);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TimetableBacktrackingFull obj = new TimetableBacktrackingFull(sc);
        if (obj.generateWithRestart())
            obj.printTimetable();
        else
            System.out.println("❌ Couldn't generate valid timetable after retries.");
        sc.close();
    }
}
