public class Allocation {

    private Student student;
    private LabSeat seat;

    public Allocation(Student student, LabSeat seat) {
        this.student = student;
        this.seat = seat;
    }

    public Student getStudent() {
        return student;
    }

    public LabSeat getSeat() {
        return seat;
    }

    public String getStudentId() {
        return student.getStudentId();
    }

    public String getLabId() {
        return seat.getLabId();
    }

    public String getLabName() {
        return seat.getLabName();
    }

    public String getSlot() {
        return seat.getSlot();
    }

    public int getSeatNumber() {
        return seat.getSeatNumber();
    }

    @Override
    public String toString() {

        return student.getStudentId()
                + " -> "
                + seat.getLabId()
                + " | "
                + seat.getLabName()
                + " | "
                + seat.getSlot()
                + " | Seat "
                + seat.getSeatNumber();
    }
}