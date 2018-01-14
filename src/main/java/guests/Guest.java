package guests;

import rooms.Bedroom;
import rooms.Room;

public class Guest {

    private String name;
    private double wallet;

    public Guest(String name, double wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    public String getName() {
        return this.name;
    }

    public double getWallet() {
        return this.wallet;
    }

    public boolean can_afford(double price) {
        return this.wallet >= price;
    }

    public void pay(double price) {
        this.wallet -= price;
    }
}
