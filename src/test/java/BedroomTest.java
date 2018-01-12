import guests.Guest;
import org.junit.Before;
import org.junit.Test;
import rooms.Bedroom;
import rooms.BedroomType;

import static org.junit.Assert.assertEquals;

public class BedroomTest {

    Bedroom basic_room;
    Guest guest1;


    @Before
    public void before(){
        Guest guest1 = new Guest("Mr. ");
        basic_room = new Bedroom(1, BedroomType.SINGLE);
    }

    @Test
    public void bedroomIsSingle() {
        assertEquals(1, basic_room.getCapacity());
    }

    @Test
    public void guestCanCheckIn() {
        Bedroom singleRoom = new Bedroom(2, BedroomType.SINGLE);
        singleRoom.checkIn(guest1);
    }

}
