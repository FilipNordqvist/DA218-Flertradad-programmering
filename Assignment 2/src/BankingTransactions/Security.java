package BankingTransactions;

import java.util.ArrayList;

public class Security {

    private ArrayList<Stamp> transactionHistory = new ArrayList<>();

    private int numberOfErrors;

    private Stamp preStamp;

    private Stamp postStamp;


    public void makePreTransactionStamp(int balance,int clientId,int transactionAmount) {
        preStamp = new Stamp(balance,clientId,transactionAmount);
    }

    public void makePostTransactionStamp(int balance,int clientId,int transactionAmount) {
        postStamp = new Stamp(balance,clientId,transactionAmount);
    }



    public void verifyLastTransaction(){
        if(preStamp.balance + preStamp.transactionAmount != postStamp.balance){
            numberOfErrors++;
        }else{
            transactionHistory.add(preStamp);
            transactionHistory.add(postStamp);
        }

    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

}