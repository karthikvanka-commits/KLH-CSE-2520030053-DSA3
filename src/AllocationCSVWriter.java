import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AllocationCSVWriter {

    public static void write(
            AllocationResult result,
            String filePath) {

        File file = new File(filePath);

        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {

            // ==========================================
            // CSV HEADER
            // ==========================================

            writer.write(
                    "Student_ID,Department,Section,"
                    + "Preferred_Slot,Priority,"
                    + "Lab_ID,Lab_Name,Slot,Seat_Number\n"
            );

            // ==========================================
            // WRITE ALLOCATIONS
            // ==========================================

            List<Allocation> allocations =
                    result.getAllocations();

            for (Allocation allocation :
                    allocations) {

                Student student =
                        allocation.getStudent();

                writer.write(
                        csv(student.getStudentId()) + ","
                        + csv(student.getDepartment()) + ","
                        + csv(student.getSection()) + ","
                        + csv(student.getPreferredSlot()) + ","
                        + student.getPriority() + ","
                        + csv(allocation.getLabId()) + ","
                        + csv(allocation.getLabName()) + ","
                        + csv(allocation.getSlot()) + ","
                        + allocation.getSeatNumber()
                        + "\n"
                );
            }

            System.out.println(
                    "\nAllocation file created: "
                    + filePath
            );

        } catch (IOException e) {

            System.out.println(
                    "Error writing allocation file: "
                    + e.getMessage()
            );
        }
    }

    // ==========================================
    // CSV VALUE ESCAPING
    // ==========================================

    private static String csv(String value) {

        if (value == null) {
            return "";
        }

        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")) {

            return "\""
                    + value.replace(
                            "\"",
                            "\"\""
                    )
                    + "\"";
        }

        return value;
    }
}