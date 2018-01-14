import guests.Guest;
import guests.Reservation;
import org.junit.Before;
import org.junit.Test;
import rooms.Bedroom;
import rooms.BedroomType;
import rooms.ConferenceRoom;
import rooms.Room;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReservationTest {


    Reservation singleReservation;
    ConferenceRoom cRoom1;
    ConferenceRoom cRoom2;
    Bedroom singleRoom;
    Reservation singleRoom1;
    Bedroom doubleRoom;
    Reservation singleRoom2;
    Bedroom doubleRoom2;
    ArrayList<Room> rooms;
    Guest guest1;
    Guest guest6;
    Guest guest2;
    Guest guest3;
    Guest guest4;
    Guest guest5;
    ArrayList<Guest> guests;
    Reservation reservationSingleRoom;

    @Before
    public void before(){
        cRoom1 = new ConferenceRoom( "Tiananmen Square", 14, 850.30);
        cRoom2 = new ConferenceRoom("Tahirir Square", 5, 240.60);
        singleRoom = new Bedroom(1, BedroomType.SINGLE);
        doubleRoom = new Bedroom(2, BedroomType.DOUBLE);
        guest1 = new Guest("Enda Kenny", 640.89);
        guests = new ArrayList<>();
        guests.add(guest1);
        reservationSingleRoom = new Reservation(singleRoom, LocalDate.of(2018, 1, 11), LocalDate.of(2018, 1, 20), guests);
    }

    @Test
    public void canGetDuration(){
        assertEquals(9, reservationSingleRoom.getDuration());
    }

    @Test
    public void canGetRoom(){
        assertEquals(singleRoom, reservationSingleRoom.getRoom());

    }

    @Test
    public void canGetTotalCost(){
        assertEquals(410.4, reservationSingleRoom.getReservationCost(), 0.01);
    }

    @Test
    public void canGetOutstandingBalance(){
        assertEquals(410.4, reservationSingleRoom.getOutstandingBalance(), 0.01);
    }

    @Test
    public void hasOutstandingBalanceAfterPaying(){
        reservationSingleRoom.guestPay(guests.get(0),49.51);
        assertEquals(360.89, reservationSingleRoom.getOutstandingBalance(), 0.01 );
    }
}
