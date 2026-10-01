public class Student {

    private String studentId;
    private String department;
    private String section;
    private String preferredSlot;
    private int priority;

    public Student(String studentId, String department, String section,
                   String preferredSlot, int priority) {
        this.studentId = studentId;
        this.department = department;
        this.section = section;
        this.preferredSlot = preferredSlot;
        this.priority = priority;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }

    public String getSection() {
        return section;
    }

    public String getPreferredSlot() {
        return preferredSlot;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return studentId + " | " + department + " | " +
               section + " | " + preferredSlot + " | " + priority;
    }
}