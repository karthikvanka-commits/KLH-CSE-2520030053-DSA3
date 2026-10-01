import java.util.List;

public class Main {

    public static void main(String[] args) {

        String studentFile =
                "data/student_lab_allocation_students.csv";

        String labFile =
                "data/student_lab_allocation_labs.csv";

        String outputFile =
                "output/allocation_result.csv";

        // ==========================================
        // 1. READ INPUT DATA
        // ==========================================

        List<Student> students =
                CSVReader.readStudents(studentFile);

        List<Lab> labs =
                CSVReader.readLabs(labFile);

        // ==========================================
        // 2. GENERATE LAB SEATS
        // ==========================================

        List<LabSeat> seats =
                LabSeatGenerator.generateSeats(labs);

        // ==========================================
        // 3. BUILD BIPARTITE GRAPH
        // ==========================================

        SeatBipartiteGraph graph =
                new SeatBipartiteGraph(
                        students,
                        seats
                );

        graph.buildGraph();

        // ==========================================
        // 4. MAXIMUM BIPARTITE MATCHING
        // ==========================================

        MaximumBipartiteMatching matching =
                new MaximumBipartiteMatching(graph);

        matching.maximumMatching();

        // ==========================================
        // 5. CREATE ALLOCATION RESULT
        // ==========================================

        AllocationManager manager =
                new AllocationManager(
                        students,
                        seats,
                        matching
                );

        AllocationResult result =
                manager.createResult();

        // ==========================================
        // 6. DISPLAY SUMMARY
        // ==========================================

        System.out.println(
                "===== ALLOCATION SUMMARY ====="
        );

        System.out.println(
                "Total Students: "
                + students.size()
        );

        System.out.println(
                "Total Available Seats: "
                + seats.size()
        );

        System.out.println(
                "Students Allocated: "
                + result.getAllocatedCount()
        );

        System.out.println(
                "Students Unallocated: "
                + result.getUnallocatedCount()
        );

        // ==========================================
        // 7. DISPLAY ALLOCATIONS
        // ==========================================

        result.displayResult();

        // ==========================================
        // 8. DISPLAY STATISTICS
        // ==========================================

        Statistics statistics =
                new Statistics(result);

        statistics.displaySummary();

        statistics.displayLabWiseStatistics();

        // ==========================================
        // 9. WRITE CSV OUTPUT
        // ==========================================

        AllocationCSVWriter.write(
                result,
                outputFile
        );
    }
}