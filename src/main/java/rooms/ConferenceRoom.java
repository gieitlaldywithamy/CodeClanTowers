package rooms;

public class ConferenceRoom extends Room {

    final String name;
    final double dailyRate;

    public ConferenceRoom(String name, int room_capacity, double dailyRate) {
        super(room_capacity);
        this.name = name;
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return this.name;
    }

    public double getCharge() {
        return this.dailyRate;
    }

    public void pretty_print(){
        System.out.println(this.name  + " " + getCapacity() + " " + this.dailyRate);
    }
}
