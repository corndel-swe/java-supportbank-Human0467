package com.corndel.supportbank.models;

import java.util.HashMap;

public class CurrencyExchange {

    String fromCurrency;
    String toCurrency;
    double amount;

    public CurrencyExchange(String fromCurrency, String toCurrency, double amount) {
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
    HashMap<String, Double> currencyToIndex= new HashMap<String, Double>();

    // hold exchange rates in 2s array of doubles
}
