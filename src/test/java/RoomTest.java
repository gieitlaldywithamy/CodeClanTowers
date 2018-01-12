import org.junit.Before;
import org.junit.Test;
import rooms.Bedroom;
import rooms.Guest;
import rooms.Room;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    Room basic_room;


    @Before
    public void before(){
        basic_room = new Room(4);
    }

    @Test
    public void roomHasCapacity() {
        assertEquals(4, basic_room.getCapacity());
    }
}
