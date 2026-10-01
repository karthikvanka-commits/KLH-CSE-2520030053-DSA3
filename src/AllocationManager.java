import java.util.List;

public class AllocationManager {

    private List<Student> students;
    private List<LabSeat> seats;
    private MaximumBipartiteMatching matching;

    public AllocationManager(
            List<Student> students,
            List<LabSeat> seats,
            MaximumBipartiteMatching matching) {

        this.students = students;
        this.seats = seats;
        this.matching = matching;
    }

    public AllocationResult createResult() {

        AllocationResult result =
                new AllocationResult();

        int[] studentMatch =
                matching.getStudentMatch();

        for (int i = 0; i < students.size(); i++) {

            Student student = students.get(i);

            if (studentMatch[i] != -1) {

                LabSeat seat =
                        seats.get(studentMatch[i]);

                Allocation allocation =
                        new Allocation(student, seat);

                result.addAllocation(allocation);

            } else {

                result.addUnallocatedStudent(student);
            }
        }

        return result;
    }
}