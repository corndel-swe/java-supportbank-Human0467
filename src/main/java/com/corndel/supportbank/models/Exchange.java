package com.corndel.supportbank.models;

import java.util.HashMap;

public class Exchange {

    String fromCurrency;
    String toCurrency;
    double amount;

    public Exchange(String fromCurrency, String toCurrency, double amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // use hashmap to link currency to index
    public double makeExchange(Double amount, String fromCurrency, String toCurrency) {
        return amount*getRate(fromCurrency, toCurrency);

    }

    public double getRate(String fromCurrency, String toCurrency){

        HashMap<String, Integer> currencyToIndex = new HashMap<String, Integer>();
        currencyToIndex.put("USD", 0);
        currencyToIndex.put("GBP", 1);
        currencyToIndex.put("EUR", 2);

        // hold exchange rates in 2s array of doubles
        //USD   GBP   EUR  --> toCurrency
        double[][] exchangeRates = { {1.00, 0.75, 0.89 }, // USD
                {1.34, 1.00, 1.20},  // GBP
                {1.12, 0.84, 1.00}}; // EUR  fromCurrency

        return exchangeRates[currencyToIndex.get(fromCurrency)][currencyToIndex.get(toCurrency)];
    }
}
