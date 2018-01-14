import guests.Guest;
import guests.Reservation;
import org.junit.Before;
import org.junit.Test;
import rooms.Bedroom;
import rooms.BedroomType;
import rooms.Room;
import rooms.ConferenceRoom;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HotelTest {

    Hotel clanTowers;
    ConferenceRoom cRoom1;
    ConferenceRoom cRoom2;
    ConferenceRoom cRoom3;
    ConferenceRoom cRoom4;
    ConferenceRoom cRoom5;
    ArrayList<Room> rooms;
    Guest guest1;
    Guest guest6;
    Guest guest2;
    Guest guest3;
    Guest guest4;
    Guest guest5;

//    Create a Hotel class, which has rooms of different types.
//    The hotel should be able check guests in/out of rooms,
//    and be able to check which guest(s), if any, are checked into a particular room.

    @Before
    public void before(){
        rooms = new ArrayList<Room>();

        for (int i = 1; i < 11; i++) {
            Bedroom room;
            if (i % 2 == 0) {
                room = new Bedroom(i, BedroomType.SINGLE);
            } else {
                room = new Bedroom(i, BedroomType.DOUBLE);
            }
            rooms.add(room);
        }

        cRoom1 = new ConferenceRoom( "Tiananmen Square", 14, 850.30);
        cRoom2 = new ConferenceRoom("Tahirir Square", 5, 240.60);
        cRoom3 = new ConferenceRoom("Victory Square", 5, 240.60);
        cRoom4 = new ConferenceRoom("Maastricht", 5, 240.60);
        cRoom5 = new ConferenceRoom("Garrison", 5, 240.60);

        rooms.add(cRoom1);
        rooms.add(cRoom2);

         guest1 = new Guest("Bjork", 20);
         guest2 = new Guest("BP man", 430);
         guest3 = new Guest("Lorna", 12.30);
         guest4 = new Guest("David", 4099.60);
         guest5 = new Guest("Olga", 64.50);
         guest6 = new Guest("Douglas", 22.00);
        Guest guest7 = new Guest("Graeme", 0.40);

        clanTowers = new Hotel(rooms, 0);
    }

    @Test
    public void testHotelRoomCount(){
        assertEquals(12,clanTowers.getRoomCount() );
    }

    @Test
    public void hotelStartsWithNoReservations() {
        assertEquals(0, clanTowers.getAllReservations().size());
    }

    @Test
    public void testGetConferenceRooms(){
        System.out.println(clanTowers.getConferenceRooms());
        assertEquals(Arrays.asList(cRoom1, cRoom2) ,clanTowers.getConferenceRooms() );
    }

    @Test
    public void testCheckInToConferenceRoom() {

        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4, guest5, guest6));
        clanTowers.checkIn(businessMeeting, cRoom1);
    }

    @Test
    public void getOccupiedRooms(){
        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4, guest5, guest6));
        clanTowers.checkIn(businessMeeting, cRoom1);
        System.out.println(clanTowers.getOccupiedConferenceRooms());
    }

    @Test
    public void findGuest() {
        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4, guest5, guest6));
        clanTowers.checkIn(businessMeeting, cRoom1);
        ArrayList<Guest> family = new ArrayList<>();
        family.add(guest2);
        family.add(guest1);
        clanTowers.checkIn(family, rooms.get(2));
        ArrayList<Room> guestIn = new ArrayList<>();
        guestIn.add(cRoom1);
        guestIn.add(rooms.get(2));
        System.out.println(guestIn);
        System.out.println(clanTowers.findGuest(guest2));
        ArrayList<Room> result = clanTowers.findGuest(guest2);
        assertEquals(guestIn, result);
        //this fails because of arraylist order

    }

    @Test
    public void checkOutGuests() {
        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4, guest5, guest6));
        clanTowers.checkIn(businessMeeting, cRoom1);
        cRoom1.checkOut(businessMeeting);
    }

    @Test public void whosInThere() {
        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4, guest5, guest6));
        clanTowers.checkIn(businessMeeting, cRoom1);
        assertEquals(businessMeeting, cRoom1.getOccupants());
    }

    @Test public void getOccupantsOfEmptyRoom(){
        assertEquals(new ArrayList<Guest>(), cRoom2.getOccupants());
    }

    @Test public void sortConferenceRooms() {
        for (ConferenceRoom room: clanTowers.sortConferenceRooms(clanTowers.getConferenceRooms())) {
            room.pretty_print();
        }

    }

    @Test public void getMostExpensiveRoom() {
        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4));
        clanTowers.findExpensiveConferenceRoom(businessMeeting).pretty_print();
    }

    @Test public void getUnOccupiedBedrooms(){
        ArrayList<Guest> guests = new ArrayList<>();
        guests.add(guest3);
        guests.add(guest4);
        guests.add(guest6);

        clanTowers.checkIn(guests);
    }

    @Test
    public void hotelChecksInCouple(){
        ArrayList<Guest> family = new ArrayList<>();
        family.add(guest2);
        family.add(guest3);
        family.add(guest1);
        clanTowers.checkIn(family);
        clanTowers.findGuest(guest1);
        clanTowers.findGuest(guest2);
        clanTowers.findGuest(guest3);


    }

    @Test
    public void roomAvailable() {
        ArrayList<Guest> groupHoliday = new ArrayList<Guest>(Arrays.asList(guest2, guest4));
        Reservation groupReservation = new Reservation(rooms.get(2), LocalDate.of(2018, 1, 11), LocalDate.of(2018, 1, 20), groupHoliday);
        assertEquals(true, clanTowers.isRoomAvailable(groupReservation));

    }

    @Test
    public void roomIsNotAvailable() {
        ArrayList<Guest> groupHoliday = new ArrayList<Guest>(Arrays.asList(guest2, guest4));
        Reservation groupReservation = new Reservation(rooms.get(2), LocalDate.of(2018, 1, 11), LocalDate.of(2018, 1, 20), groupHoliday);
        clanTowers.addReservation(groupReservation);
        Reservation groupReservation2 = new Reservation(rooms.get(2), LocalDate.of(2018, 1, 14), LocalDate.of(2018, 1, 22), groupHoliday);
        assertEquals(false, clanTowers.isRoomAvailable(groupReservation2));
    }

    @Test
    public void roomAvailableByDate() {
        ArrayList<Bedroom> allBedrooms = clanTowers.getBedrooms();
        assertEquals(allBedrooms, clanTowers.getAvailableBedroomsByDate(LocalDate.now()));

    }

    @Test
    public void roomAvailableByDate1Removed() {
        
        ArrayList<Bedroom> allBedrooms = clanTowers.getBedrooms();
        allBedrooms.remove(rooms.get(1));
        ArrayList<Guest> guests = new ArrayList<>();
        guests.add(guest2);
        Reservation reservation1 = new Reservation(rooms.get(1), LocalDate.now(), LocalDate.of(2018, 1, 16), guests);
        clanTowers.addReservation(reservation1);
        ArrayList<Reservation> reservations = clanTowers.getRoomReservations(rooms.get(1));
        System.out.println(reservations.get(0).getStartDate());
        assertEquals(allBedrooms.size(), clanTowers.getAvailableBedroomsByDate(LocalDate.now()).size());

    }
}
