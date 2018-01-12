import guests.Guest;
import rooms.Bedroom;
import rooms.ConferenceRoom;
import rooms.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hotel {

    private ArrayList<Room> rooms;

    public Hotel(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getRoomCount() {
        return this.rooms.size();
    }

    public ArrayList<Bedroom> availableBedrooms (){
        ArrayList<Bedroom> unOccupiedBedrooms = getBedrooms();
        unOccupiedBedrooms.removeAll(getOccupiedBedrooms());
        return unOccupiedBedrooms;
    }

    public ArrayList<ConferenceRoom> availableConferenceRooms (){
        ArrayList<ConferenceRoom> unOccupied = getConferenceRooms();
        unOccupied.removeAll(getOccupiedConferenceRooms());
        return unOccupied;
    }


    public ArrayList<ConferenceRoom> sortConferenceRooms(ArrayList<ConferenceRoom> rooms){

        ArrayList<ConferenceRoom> conferenceRooms = rooms;

        Collections.sort(rooms, new Comparator<ConferenceRoom>() {
            public int compare(ConferenceRoom cr1, ConferenceRoom cr2) {
                return cr1.getCharge() > cr2.getCharge() ? -1 :(cr1.getCharge() < cr2.getCharge() ? 1 : 0);

            }
        });

        return conferenceRooms;
    }

    public ConferenceRoom findExpensiveConferenceRoom(ArrayList<Guest> businessGuests) {
        ArrayList<ConferenceRoom> suitableRooms = availableConferenceRooms();
        suitableRooms.removeIf(conferenceRoom -> !conferenceRoom.enoughSpace(businessGuests));
        System.out.println(suitableRooms);
        suitableRooms = sortConferenceRooms(suitableRooms);
        System.out.println(suitableRooms);
        return suitableRooms.get(0);
    }


    public void checkIn(Guest guest, Room room) {
        room.checkIn(guest);
    }

    public void checkIn(ArrayList<Guest> guests, Room room) {
        room.checkIn(guests);
    }


    public ArrayList<Bedroom> getOccupiedBedrooms(){
        ArrayList<Bedroom> occupiedRooms = new ArrayList<>();
        for (Bedroom bedroom: getBedrooms()) {
            if (bedroom.isOccupied()) {
                occupiedRooms.add(bedroom);
            }
        }
        return occupiedRooms;
    }

    public ArrayList<ConferenceRoom> getOccupiedConferenceRooms(){
        ArrayList<ConferenceRoom> occupiedRooms = new ArrayList<>();
        for (ConferenceRoom room: getConferenceRooms()) {
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

    public void checkOut(Room room) {
        room.checkOut(room.getOccupants());
    }

    public void checkOut(Guest guest) {
        findGuest(guest).checkOut(guest);
    }


    public Room findGuest(Guest guest) {
        Room room = null;

        for (Room hotel_room: this.rooms ){
            if (hotel_room.getOccupants().contains(guest)) {
                return hotel_room;
            }
        }

        return room;
    }
}
