package BankingTransactions;

public class Stamp {

    int clientId;
    int balance;
    int transactionAmount;


    public Stamp(int balance,int clientId, int transactionAmount) {
        this.clientId = clientId;
        this.balance = balance;
        this.transactionAmount = transactionAmount;

    }
}
