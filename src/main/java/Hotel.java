import guests.Guest;
import guests.Reservation;
import rooms.Bedroom;
import rooms.ConferenceRoom;
import rooms.Room;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Hotel {

    private ArrayList<Room> rooms;
    private double till;
    private HashMap<Room, ArrayList<Reservation>> reservations;

    public Hotel(ArrayList<Room> rooms, double till) {
        this.rooms = rooms;
        this.till = till;

        this.reservations = new HashMap<>();

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

    public ArrayList<Reservation> getRoomReservations(Room room){
        ArrayList<Reservation> roomReservations = new ArrayList<>();
        if (this.reservations.get(room) != null)
            roomReservations.addAll(this.reservations.get(room));
        return roomReservations;
    }

    public HashMap<Room, ArrayList<Reservation>> getAllReservations() {
        return this.reservations;
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


//    public void checkIn(Guest guest, Room room, int nights) {
//        if (!room.isFull()) {
//            guest.pay(room.getRoomCharge()*nights);
//            room.addGuest(guest);
//        }
//
//    }

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

    public ArrayList<Bedroom> getAvailableBedroomsByDate(LocalDate date) {
        ArrayList<Bedroom> bedrooms = new ArrayList<>();
        for (Room room: this.rooms) {

            if (room instanceof Bedroom) {
                //bedrooms.add((Bedroom) room);
                ArrayList<Reservation> roomReservations = getRoomReservations(room);
                boolean roomAvailable = true;
                for (Reservation reservation: roomReservations){
                    System.out.println("in reservation");
                    System.out.println(reservation.getStartDate());
                    System.out.println(reservation.getEndDate());
                    System.out.println(date);
                    //need to change this to equal to as well!
                    if (date.equals(reservation.getStartDate()) ||( date.isAfter(reservation.getStartDate()) && date.isBefore(reservation.getEndDate()))) {
                        roomAvailable = false;
                        System.out.println("Room is not available");
                    }
                }
                if (roomAvailable) bedrooms.add((Bedroom) room);

            }
        }
        return bedrooms;
    }

    public void checkOut(Room room) {
        room.checkOut(room.getOccupants());
    }




    public ArrayList<Room> findGuest(Guest guest) {
        ArrayList<Room> guestIn = new ArrayList<>();

        for (Room hotel_room: this.rooms ){
            if (hotel_room.getOccupants().contains(guest)) {
                System.out.println(guest + " is in " + hotel_room);
                guestIn.add(hotel_room);
            }
        }

        return guestIn;
    }

    public boolean isRoomAvailable(Reservation new_reservation){
        boolean isRoomAvailable = false;

        ArrayList<Reservation> currentRoomReservations = getRoomReservations(new_reservation.getRoom());
        System.out.println(currentRoomReservations);
        double guestKitty = 0;
        for (Guest guest: new_reservation.getGuests()){
            guestKitty += guest.getWallet();
        }
        System.out.println(guestKitty);
        System.out.println(new_reservation.getRoom().getCapacity());

        if (new_reservation.getRoom().getCapacity() >= new_reservation.getGuests().size()){
            if (guestKitty >= new_reservation.getOutstandingBalance()) {
                if (currentRoomReservations.size() == 0)
                        isRoomAvailable = true;
                else {
                    for (Reservation reservation: currentRoomReservations) {
                        if (reservation.getStartDate().isBefore(new_reservation.getEndDate()) && new_reservation.getStartDate().isBefore(reservation.getEndDate()) ){

                            return false;
                        }
                    }
                }

            }

        }

        return isRoomAvailable;

    }

    public boolean checkIn(ArrayList<Guest> guests) {

        for (Bedroom emptyBedroom: availableBedrooms()){

            if (emptyBedroom.enoughSpace(guests)){
                emptyBedroom.checkIn(guests);
                System.out.println("All checked into one room");
                return true;
            } else {
                while (emptyBedroom.getCapacity() > emptyBedroom.occupantCount()){
                    Guest guest = guests.remove(0);
                    emptyBedroom.checkIn(guest);
                }
                System.out.println("Checked into multiple rooms");

            }
        }

        return false;
    }

    public void addReservation(Reservation newReservation) {

        if(isRoomAvailable(newReservation)) {
            if (this.reservations.containsKey(newReservation.getRoom())) {
                this.reservations.get(newReservation.getRoom()).add(newReservation);
            } else {
                ArrayList<Reservation> roomReservations = new ArrayList<>();
                roomReservations.add(newReservation);

                this.reservations.put(newReservation.getRoom(), roomReservations);
            }
        }
    }
}
