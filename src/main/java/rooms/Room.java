package rooms;

import guests.Guest;

import java.util.ArrayList;

public class Room {

    private final int capacity;
    private ArrayList<Guest> occupants;


    public Room(int room_capacity) {
        this.capacity = room_capacity;
        this.occupants = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public ArrayList<Guest> getOccupants(){
        return this.occupants;
    }
}
