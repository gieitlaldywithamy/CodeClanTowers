package guests;

import rooms.Bedroom;
import rooms.Room;

public class Guest {

    String name;

    public Guest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void checkIn(Room room) {
        room.checkIn(this);
    }

}
