package rooms;

import java.util.ArrayList;

public class Room {

    int capacity;
    ArrayList<Guest> occupants;


    public Room(int room_capacity) {
        this.capacity = room_capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
