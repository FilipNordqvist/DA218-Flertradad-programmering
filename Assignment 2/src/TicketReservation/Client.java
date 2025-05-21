package TicketReservation;

public class Client extends Thread {

    private int id = 0;

    private static int counter = 0;
    private SeatManager manager;
    private boolean lucky = false;

    public Client(SeatManager manager){
        this.id=counter;
        counter++;
        this.manager=manager;
    }
    public int getClientId(){
        return id;
    }

    @Override
    public void run() {
        synchronized (this){
        }
            lucky = manager.bookFirstAvailable(this);
        try{
            sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //manager.printStatus();
    }


}
