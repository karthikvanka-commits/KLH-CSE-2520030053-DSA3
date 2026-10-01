public class LabSeat {

    private String labId;
    private String labName;
    private String slot;
    private int seatNumber;

    public LabSeat(String labId, String labName, String slot, int seatNumber) {
        this.labId = labId;
        this.labName = labName;
        this.slot = slot;
        this.seatNumber = seatNumber;
    }

    public String getLabId() {
        return labId;
    }

    public String getLabName() {
        return labName;
    }

    public String getSlot() {
        return slot;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return labId + " | " + labName + " | " +
               slot + " | Seat " + seatNumber;
    }
}