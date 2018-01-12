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

    private int occupantCount(){
        return this.occupants.size();
    }

    public void checkIn(Guest guest){
        if (capacity >  occupantCount()){
            this.occupants.add(guest);
        }
    }

    public void checkIn(ArrayList<Guest> guests) {
        if (occupantCount()+ guests.size() <= capacity) {
            this.occupants.addAll(guests);
        }
    }

    public boolean isOccupied(){
        return !this.occupants.isEmpty();
    }
}
