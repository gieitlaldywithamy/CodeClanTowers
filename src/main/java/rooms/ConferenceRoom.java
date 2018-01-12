package rooms;

public class ConferenceRoom extends Room {

    final String name;
    final double roomCharge;

    public ConferenceRoom(String name, int room_capacity, double roomCharge) {
        super(room_capacity);
        this.name = name;
        this.roomCharge = roomCharge;
    }

    public String getName() {
        return this.name;
    }

    public double getCharge() {
        return this.roomCharge;
    }
}
