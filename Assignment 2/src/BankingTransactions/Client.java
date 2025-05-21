package BankingTransactions;

import java.util.Random;

public class Client extends Thread{

    Controller controller;

    Random randomNumber = new Random();

    private int id = 0;
    private int sum;
    private boolean operating = false;
    private BankAccount bankAccount;
    private double totalAmountTransactioned;

    public Client(BankAccount bankAccount){

        this.bankAccount=bankAccount;
    }

    public void run(){
        while(operating){
            sum = randomNumber.nextInt(100);
            bankAccount.transaction(sum,id);
            totalAmountTransactioned += sum;
            try{
               sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public double getTotalAmountTransactioned() {
        return totalAmountTransactioned;
    }

    public void setTotalAmountTransactioned(double totalAmountTransactioned) {
        this.totalAmountTransactioned = totalAmountTransactioned;
    }

    public void setOperating(boolean operating) {
        this.operating = operating;
    }

}
