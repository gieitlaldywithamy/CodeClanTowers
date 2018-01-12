package rooms;

public class ConferenceRoom extends Room {

    String name;

    public ConferenceRoom(String name, int room_capacity) {
        super(room_capacity);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
