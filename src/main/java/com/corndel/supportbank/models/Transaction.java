package com.corndel.supportbank.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transaction {

    private List<String> transactions = new ArrayList<String>();

    public Transaction(List<String> transactions) {
        this.transactions = transactions;
    }

    public HashMap<String, Double> summarise(){

        HashMap<String, Double> balances = new HashMap<>();

        for(int i =  1; i < this.transactions.size(); i++){
            String[] parts = transactions.get(i).split(",");
            String fromName = parts[1];
            String toName = parts[2];
            double paid = Double.parseDouble(parts[4]);

            double toOldBalance = balances.getOrDefault(toName, 0.0);
            double toNewBalance = toOldBalance + paid;

            double fromOldBalance = balances.getOrDefault(fromName, 0.0);
            double fromNewBalance = fromOldBalance - paid;

            balances.put(fromName, fromNewBalance);
            balances.put(toName, toNewBalance);
        }

        return balances;
    }

    public String getTransactions(String accName){
        HashMap<String, Double> balances = new HashMap<>();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(transactions.get(1));
        stringBuilder.append("\n");

        for(int i =  1; i < this.transactions.size(); i++){
            String[] parts = transactions.get(i).split(",");
            String fromName = parts[1];
            String toName = parts[2];

            if(fromName.equals(accName) || toName.equals(accName)){
                stringBuilder.append(transactions.get(i));
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }


}
