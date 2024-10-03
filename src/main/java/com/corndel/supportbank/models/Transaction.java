package com.corndel.supportbank.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Transaction {

    private List<String> transactions = new ArrayList<String>();
    private String outputCurrency = "GBP";

    public Transaction(List<String> transactions) {
        this.transactions = transactions;
    }
    public Transaction(List<String> transactions, String outputCurrency) {
        this.transactions = transactions;
        this.outputCurrency = outputCurrency;
    }

    public String getOutputCurrency() {
        return outputCurrency;
    }

    public void setOutputCurrency(String outputCurrency) {
        this.outputCurrency = outputCurrency;
    }

    public HashMap<String, Double> summarise(){

        HashMap<String, Double> balances = new HashMap<>();

        // get column indices
        String[] colHeadings = transactions.getFirst().split(",");
        int fromNameIndex = Arrays.asList(colHeadings).indexOf("From");
        int toNameIndex = Arrays.asList(colHeadings).indexOf("To");
        int amountIndex = Arrays.asList(colHeadings).indexOf("Amount");

        // check if there is a currency column
        int currencyIndex;
        if(Arrays.asList(colHeadings).contains("Currency")) {
            currencyIndex = Arrays.asList(colHeadings).indexOf("Currency");
        }else{
            currencyIndex = -1;
        }

        for(int i =  1; i < this.transactions.size(); i++){
            String[] parts = transactions.get(i).split(",");
            String fromName = parts[fromNameIndex];
            String toName = parts[toNameIndex];
            double paid = Double.parseDouble(parts[amountIndex]);

            // if there is a currency column, convert
            if(currencyIndex != -1){
                String currency = parts[currencyIndex];
                if(!currency.equals(outputCurrency)){
                    Exchange exchange = new Exchange(currency, outputCurrency, paid);
                    paid = exchange.makeExchange();
                }
            }

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
        stringBuilder.append(transactions.get(0));
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
