public class Lab {

    private String labId;
    private String labName;
    private int capacity;
    private String availableSlots;

    public Lab(String labId, String labName, int capacity, String availableSlots) {
        this.labId = labId;
        this.labName = labName;
        this.capacity = capacity;
        this.availableSlots = availableSlots;
    }

    public String getLabId() {
        return labId;
    }

    public String getLabName() {
        return labName;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public String toString() {
        return labId + " | " + labName + " | " +
               capacity + " | " + availableSlots;
    }
}