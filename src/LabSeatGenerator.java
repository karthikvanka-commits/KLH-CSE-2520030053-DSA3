import java.util.ArrayList;
import java.util.List;

public class LabSeatGenerator {

    public static List<LabSeat> generateSeats(List<Lab> labs) {

        List<LabSeat> seats = new ArrayList<>();

        for (Lab lab : labs) {

            String[] slots = lab.getAvailableSlots().split(";");

            for (String slot : slots) {

                for (int seatNumber = 1;
                     seatNumber <= lab.getCapacity();
                     seatNumber++) {

                    LabSeat seat = new LabSeat(
                            lab.getLabId(),
                            lab.getLabName(),
                            slot.trim(),
                            seatNumber
                    );

                    seats.add(seat);
                }
            }
        }

        return seats;
    }
}