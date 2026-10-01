import java.util.ArrayList;
import java.util.List;

public class AllocationResult {

    private List<Allocation> allocations;
    private List<Student> unallocatedStudents;

    public AllocationResult() {
        allocations = new ArrayList<>();
        unallocatedStudents = new ArrayList<>();
    }

    public void addAllocation(Allocation allocation) {
        allocations.add(allocation);
    }

    public void addUnallocatedStudent(Student student) {
        unallocatedStudents.add(student);
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public List<Student> getUnallocatedStudents() {
        return unallocatedStudents;
    }

    public int getAllocatedCount() {
        return allocations.size();
    }

    public int getUnallocatedCount() {
        return unallocatedStudents.size();
    }

    public void displayResult() {

        System.out.println("\n===== FINAL ALLOCATIONS =====");

        for (Allocation allocation : allocations) {
            System.out.println(allocation);
        }

        System.out.println("\n===== UNALLOCATED STUDENTS =====");

        if (unallocatedStudents.isEmpty()) {
            System.out.println("None");
        } else {
            for (Student student : unallocatedStudents) {
                System.out.println(student.getStudentId());
            }
        }
    }
}