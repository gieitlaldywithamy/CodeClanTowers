import guests.Guest;
import org.junit.Before;
import org.junit.Test;
import rooms.Bedroom;
import rooms.BedroomType;
import rooms.ConferenceRoom;

import static org.junit.Assert.assertEquals;

public class ConferenceRoomTest {

    ConferenceRoom meetingRoom1;
    Guest guests;


    @Before
    public void before(){
       this.meetingRoom1 = new ConferenceRoom("Meeting room 1", 8);
    }

    @Test
    public void conferenceRoomHasName() {
        assertEquals("Meeting room 1", meetingRoom1.getName() );
    }
}
