import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {

    private AllocationResult result;

    public Statistics(AllocationResult result) {
        this.result = result;
    }

    public void displaySummary() {

        int allocated = result.getAllocatedCount();
        int unallocated = result.getUnallocatedCount();
        int total = allocated + unallocated;

        double percentage = 0;

        if (total > 0) {
            percentage = (allocated * 100.0) / total;
        }

        System.out.println("\n===== ALLOCATION STATISTICS =====");

        System.out.println("Total Students: " + total);
        System.out.println("Allocated: " + allocated);
        System.out.println("Unallocated: " + unallocated);
        System.out.printf("Allocation Rate: %.2f%%%n", percentage);
    }

    public void displayLabWiseStatistics() {

        Map<String, Integer> labCounts =
                new HashMap<>();

        List<Allocation> allocations =
                result.getAllocations();

        for (Allocation allocation : allocations) {

            String labId = allocation.getLabId();

            labCounts.put(
                    labId,
                    labCounts.getOrDefault(labId, 0) + 1
            );
        }

        System.out.println("\n===== LAB-WISE ALLOCATION =====");

        for (Map.Entry<String, Integer> entry :
                labCounts.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " -> "
                    + entry.getValue()
                    + " students"
            );
        }
    }

    public int getAllocatedCount() {
        return result.getAllocatedCount();
    }

    public int getUnallocatedCount() {
        return result.getUnallocatedCount();
    }
}