package BankingTransactions;

import java.util.ArrayList;
import java.util.Scanner;


public class Controller {

    Client client;

    Security security;
    Scanner scanner = new Scanner(System.in);

    boolean isRunning = true;

    BankAccount bankAccount;
    ArrayList<Client> clients = new ArrayList<Client>();

    public Controller() throws InterruptedException {
        this.bankAccount = new BankAccount();
        Menu();
    }

    private void Menu() throws InterruptedException {
        while(isRunning) {
            System.out.println("Make a choice: 1.Start 2.Stop 3.Pause");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Starting Threads");

                    startClients();
                    break;
                case 2:
                    System.out.println("Stopping Threads");
                    stopClients();
                    break;
                case 3:
                    System.out.println("Pause");
                    client.setOperating(false);
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }


    private void stopClients() throws InterruptedException {
    for(Client client : clients){
        client.setOperating(false);
    }
    gatherResults();
    }

    /**
     * Metod för att skriva ut kontots balansa,errors och antal transaktioner.
     */
    private void gatherResults(){
        System.out.println(bankAccount.getBalance());
        System.out.println("Errors: " + bankAccount.getTotalAmountOfErrors());
        System.out.println("Transactions: " + bankAccount.getNumberOfTransactions());

    }

    /**
     * Startar trådar
     */
    private void startClients() {
        for (int i = 0; i < 5; i++) {
            client = new Client(bankAccount);
            clients.add(client);
            client.setOperating(true);
            client.start();
        }
    }
}
