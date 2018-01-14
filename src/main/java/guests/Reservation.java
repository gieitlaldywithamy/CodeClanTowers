package guests;

import rooms.Bedroom;
import rooms.ConferenceRoom;
import rooms.Room;
import sun.jvm.hotspot.utilities.Interval;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Reservation {

    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private double outstandingBalance;
    ArrayList<Guest> guests;

    public Reservation(Room room, LocalDate startDate, LocalDate endDate, ArrayList<Guest> guests ) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.outstandingBalance = getReservationCost();
        this.guests = guests;
    }

    public int getDuration() {
        return Period.between(this.startDate,this.endDate).getDays();
    }



    public double getReservationCost(){
        double totalAmount = 0.00;

        if (room instanceof  Bedroom){
            totalAmount = ((Bedroom) room).getNightRate() * getDuration();
        } else if (room instanceof ConferenceRoom){
            totalAmount = ((ConferenceRoom) room).getCharge() * getDuration();
        }

        return totalAmount;
    }

    public Room getRoom() {
        return this.room;
    }

    public double getOutstandingBalance() {
        return this.outstandingBalance;
    }

    public void guestPay(Guest guest, double payment) {
        if (guest.can_afford(payment)){
            guest.pay(payment);
            this.outstandingBalance -= payment;
        }
    }

    public ArrayList<Guest> getGuests() {
        return this.guests;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }
}
