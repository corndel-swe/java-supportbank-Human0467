package com.corndel.supportbank.services;

import com.corndel.supportbank.models.Exchange;
import picocli.CommandLine;

@CommandLine.Command(name = "exchange")
public class CurrencyService implements Runnable{
    @CommandLine.Parameters(index = "0")
    private double amount;

    @CommandLine.Parameters(index = "1")
    private String fromCurrency;

    @CommandLine.Parameters(index = "2")
    private String toCurrency;


    @Override
    public void run() {
        Exchange exchange = new Exchange(fromCurrency, toCurrency, amount);
        System.out.println(exchange);
        System.out.println(String.format("%.2f %s in %s is %.2f", amount, fromCurrency, toCurrency,
                exchange.makeExchange(amount, fromCurrency, toCurrency)));
    }
}
