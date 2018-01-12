import guests.Guest;
import org.junit.Before;
import org.junit.Test;
import rooms.Bedroom;
import rooms.BedroomType;
import rooms.Room;
import rooms.ConferenceRoom;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HotelTest {

    Hotel clanTowers;
    ConferenceRoom cRoom1;
    ConferenceRoom cRoom2;
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
        ArrayList<Room> rooms = new ArrayList<Room>();

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

        rooms.add(cRoom1);
        rooms.add(cRoom2);

         guest1 = new Guest("Bjork");
         guest2 = new Guest("BP man");
         guest3 = new Guest("Lorna");
         guest4 = new Guest("David");
         guest5 = new Guest("Olga");
         guest6 = new Guest("Douglas");
        Guest guest7 = new Guest("Graeme");

        clanTowers = new Hotel(rooms);
    }

    @Test
    public void testHotelRoomCount(){
        assertEquals(12,clanTowers.getRoomCount() );
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
        System.out.println(clanTowers.getOccupiedRooms());
    }

    @Test
    public void checkOutGuests() {
        ArrayList<Guest> businessMeeting = new ArrayList<Guest>(Arrays.asList(this.guest1, guest2, guest3, guest4, guest5, guest6));
        clanTowers.checkIn(businessMeeting, cRoom1);
        cRoom1.checkOut(businessMeeting);
    }
}
