import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStudentGenerator {

    private static final String[] DEPARTMENTS = {
            "CSE",
            "ECE"
    };

    private static final String[] SECTIONS = {
            "A",
            "B",
            "C",
            "D",
            "E"
    };

    private static final String[] SLOTS = {
            "09:00-10:00",
            "10:00-11:00",
            "11:00-12:00"
    };

    public static List<Student> generateStudents(
            int count) {

        List<Student> students =
                new ArrayList<>();

        Random random =
                new Random();

        for (int i = 1;
             i <= count;
             i++) {

            String studentId =
                    String.format(
                            "R%03d",
                            i
                    );

            String department =
                    DEPARTMENTS[
                            random.nextInt(
                                    DEPARTMENTS.length
                            )
                    ];

            String section =
                    SECTIONS[
                            random.nextInt(
                                    SECTIONS.length
                            )
                    ];

            String preferredSlot =
                    SLOTS[
                            random.nextInt(
                                    SLOTS.length
                            )
                    ];

            int priority =
                    random.nextInt(2) + 1;

            Student student =
                    new Student(
                            studentId,
                            department,
                            section,
                            preferredSlot,
                            priority
                    );

            students.add(student);
        }

        return students;
    }
}