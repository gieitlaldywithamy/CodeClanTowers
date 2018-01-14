package rooms;

public enum BedroomType {
    SINGLE(1, 45.60),
    DOUBLE(2, 98.50);

    private final int capacity;
    private final double charge;

    BedroomType(int capacity, double charge) {
        this.capacity = capacity;
        this.charge = charge;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public double getCharge() {
        return charge;
    }


}
