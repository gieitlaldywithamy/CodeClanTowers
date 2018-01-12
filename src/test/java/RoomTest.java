import org.junit.Before;
import org.junit.Test;
import guests.Guest;
import rooms.Room;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    Room basic_room;
    Guest guests;


    @Before
    public void before(){
        Guest guest1 = new Guest("Mr. ");
        basic_room = new Room(4);
    }

    @Test
    public void roomHasCapacity() {
        assertEquals(4, basic_room.getCapacity());
    }

    @Test
    public void roomStartsEmpty(){
        assertEquals(0, basic_room.getOccupants().size());
    }
}
