import java.util.ArrayList;
import java.util.List;

public class SeatBipartiteGraph {

    private List<Student> students;
    private List<LabSeat> seats;
    private List<List<Integer>> adjacencyList;

    public SeatBipartiteGraph(List<Student> students, List<LabSeat> seats) {

        this.students = students;
        this.seats = seats;

        adjacencyList = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void buildGraph() {

        for (int i = 0; i < students.size(); i++) {

            Student student = students.get(i);

            for (int j = 0; j < seats.size(); j++) {

                LabSeat seat = seats.get(j);

                // Student can use a seat if the preferred
                // time slot matches the seat's time slot.
                if (student.getPreferredSlot()
                        .equals(seat.getSlot())) {

                    adjacencyList.get(i).add(j);
                }
            }
        }
    }

    public List<Integer> getConnections(int studentIndex) {
        return adjacencyList.get(studentIndex);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<LabSeat> getSeats() {
        return seats;
    }

    public void displayGraph() {

        System.out.println("\n===== SEAT BIPARTITE GRAPH =====");

        for (int i = 0; i < students.size(); i++) {

            System.out.print(
                    students.get(i).getStudentId() + " -> "
            );

            System.out.println(
                    adjacencyList.get(i).size()
                    + " compatible seats"
            );
        }
    }
}