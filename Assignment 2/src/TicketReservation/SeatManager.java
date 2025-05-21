package TicketReservation;

import java.util.ArrayList;

public class SeatManager {

    private Client client;
    private Seat seat;
    private int numOfSeats = 10;
    private int bookedSeats;
    private ArrayList<Seat> seats = new ArrayList<Seat>(numOfSeats);

    public SeatManager(int numOfSeats){
        this.numOfSeats=numOfSeats;
        seat = new Seat();
        occupySeats();

    }

    private void occupySeats() {
        for(int i = 0;i <numOfSeats;i++){
            seats.add(new Seat("Seat nr: " + (i+1), -1));
        }
    }
//Lägg in synkroniserat här
    public boolean bookFirstAvailable(Client client){
        if (seatIsAvailable() == true){
            getFirstAvailable().book(client);
            bookedSeats++;
            return true;
        }

        return false;

    }

    private Seat getFirstAvailable() {
        for(Seat seat : seats){
            if (seat.isAvailable() == true) {
                return seat;
            }

        }

        return null;
    }

    public boolean seatIsAvailable() {
        for(Seat seat : seats){
            if(seat.isAvailable == true){
                return true;
            }
        }
        return false;
    }

    public void printStatus(){
        for(Seat seat : seats){
            System.out.printf("%s is booked by client: %d\n",seat.getSeatName(),seat.getClientId());
        }

        /*int numberOfBooked = 0;
        for(Seat seat: seats){

            }

        return "Number of seats booked " + numberOfBooked;*/
    }
}
