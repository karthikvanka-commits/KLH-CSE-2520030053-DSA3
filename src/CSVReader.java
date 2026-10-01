import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Student> readStudents(String filePath) {

        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Skip the header row
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String studentId = data[0].trim();
                String department = data[1].trim();
                String section = data[2].trim();
                String preferredSlot = data[3].trim();
                int priority = Integer.parseInt(data[4].trim());

                Student student = new Student(
                        studentId,
                        department,
                        section,
                        preferredSlot,
                        priority
                );

                students.add(student);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error reading student file: " + e.getMessage()
            );
        }

        return students;
    }


    public static List<Lab> readLabs(String filePath) {

        List<Lab> labs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Skip the header row
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String labId = data[0].trim();
                String labName = data[1].trim();
                int capacity = Integer.parseInt(data[2].trim());
                String availableSlots = data[3].trim();

                Lab lab = new Lab(
                        labId,
                        labName,
                        capacity,
                        availableSlots
                );

                labs.add(lab);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error reading lab file: " + e.getMessage()
            );
        }

        return labs;
    }
}