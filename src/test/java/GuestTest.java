import guests.Guest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuestTest {

    Guest soloBusinessMan;

    @Before
    public void before() {
        soloBusinessMan = new Guest("Mr.");
    }

    @Test
    public void guestHasName() {
        assertEquals("Mr.", soloBusinessMan.getName());
    }
}
