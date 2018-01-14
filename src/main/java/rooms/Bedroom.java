package rooms;

import guests.Guest;

public class Bedroom extends Room {

    private final int room_number;
    private final BedroomType type;
    private final double roomCharge;

    public Bedroom(int room_number, BedroomType type) {

        super(type.getCapacity());
        this.room_number = room_number;
        this.type = type;
        this.roomCharge = type.getCharge();

    }

    public int getRoomNumber() {
        return this.room_number;
    }

    public double getNightRate() {
        return this.roomCharge;
    }

    public BedroomType getRoomType(){
        return this.type;
    }

}
