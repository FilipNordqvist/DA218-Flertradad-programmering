package BankingTransactions;

import java.util.Random;
public class BankAccount {
    private int balance = 0;

    private Security security;

    private int numberOfTransactions;

    private int totalAmountOfErrors;

    public BankAccount(){
   this.security=new Security();

    }

    /**
     * Metod för de transaktioner som görs till kontot.
     *
     * När synchronized är bort kommenterat ser man hur många fel det blir.
     * När vi lägger till det blir det inga errors längre.
     *
     * @param amount som ska sättas in på kontot
     * @param clientId id på clienten
     */
    public void transaction(int amount,int clientId) {
        synchronized (this) {
            security.makePreTransactionStamp(balance, clientId, amount);
            balance += amount;
            numberOfTransactions++;
            security.makePostTransactionStamp(balance, clientId, amount);
            security.verifyLastTransaction();
        }
    }
    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getTotalAmountOfErrors() {
        return totalAmountOfErrors = security.getNumberOfErrors();
    }

}
