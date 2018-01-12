import guests.Guest;
import rooms.Bedroom;
import rooms.ConferenceRoom;
import rooms.Room;

import java.util.ArrayList;

public class Hotel {

    private ArrayList<Room> rooms;

    public Hotel(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getRoomCount() {
        return this.rooms.size();
    }

    public void checkIn(Guest guest, Room room) {
        room.checkIn(guest);
    }

    public void checkIn(ArrayList<Guest> guests, Room room) {
        room.checkIn(guests);
    }

    public ArrayList<Room> getOccupiedRooms(){
        ArrayList<Room> occupiedRooms = new ArrayList<>();
        for (Room room: this.rooms) {
            if (room.isOccupied()) {
                occupiedRooms.add(room);
            }
        }
        return occupiedRooms;
    }


    public ArrayList<ConferenceRoom> getConferenceRooms() {

        ArrayList<ConferenceRoom> conferenceRooms = new ArrayList<>();
        for (Room room: this.rooms) {

            if (room instanceof ConferenceRoom) {
                conferenceRooms.add((ConferenceRoom) room);
            }
        }
        return conferenceRooms;

    }

    public ArrayList<Bedroom> getBedrooms() {
        ArrayList<Bedroom> bedrooms = new ArrayList<>();
        for (Room room: this.rooms) {

            if (room instanceof Bedroom) {
                bedrooms.add((Bedroom) room);
            }
        }
        return bedrooms;
    }
}
