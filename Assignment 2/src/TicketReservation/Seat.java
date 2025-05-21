package TicketReservation;


import java.sql.SQLOutput;

public class Seat {



    private String seatName;

    public boolean isAvailable;


    private int clientId;
    public Seat(String seatName, int clientId) {
        this.clientId=clientId;
        isAvailable = true;
        this.seatName=seatName;

    }

    public Seat() {

    }

    public void book(Client client){
        this.clientId= (int) client.getClientId();
        this.isAvailable = false;
        System.out.println("Client " + clientId + " books" + seatName);
       // System.out.println(getSeatName() + " is booked by client: " + client.getName());
    }

    public boolean isAvailable() {
       return isAvailable;
    }

    public String getSeatName() {
        return this.seatName;
    }

    public int getClientId() {
        return clientId;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}
