package TicketReservation;

import java.util.ArrayList;

public class Controller {
    private final int seatsAvailable = 10;

    private final int currNumOfClients = 100;

    private SeatManager seatManager;

    private ArrayList<Client> clients = new ArrayList<Client>(currNumOfClients);

    public Controller(){
        seatManager = new SeatManager(seatsAvailable);
        createClients();
        seatManager.printStatus();
    }

    private void createClients() {
        for(int i = 0 ; i<currNumOfClients;i++){
        Client client = new Client(seatManager);
        client.setName(String.valueOf(i));
        clients.add(client);
        client.start();

        }

    }


}
