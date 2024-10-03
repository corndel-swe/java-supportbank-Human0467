package com.corndel.supportbank.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;

import io.github.cdimascio.dotenv.Dotenv;

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
    public double makeExchange() {
        return this.amount*fetchRateFree(this.fromCurrency, this.toCurrency);

    }

    //~~~~~~~~~~~~~~~ depreciated - use fetchRate instead! ~~~~~~~~~~~~
//    public double getRate(String fromCurrency, String toCurrency){
//
//        HashMap<String, Integer> currencyToIndex = new HashMap<String, Integer>();
//        currencyToIndex.put("USD", 0);
//        currencyToIndex.put("GBP", 1);
//        currencyToIndex.put("EUR", 2);
//
//        // hold exchange rates in 2s array of doubles
//        //USD   GBP   EUR  --> toCurrency
//        double[][] exchangeRates = { {1.00, 0.75, 0.89 }, // USD
//                                     {1.34, 1.00, 1.20},  // GBP
//                                     {1.12, 0.84, 1.00}}; // EUR  fromCurrency
//
//        return exchangeRates[currencyToIndex.get(fromCurrency)][currencyToIndex.get(toCurrency)];
//    }

//    public double fetchRate(String fromCurrency, String toCurrency){
//        Dotenv dotenv = Dotenv.load();
//        String appID = dotenv.get("OPEN_EXCHANGE_RATES_APP_ID");
//        String url = "https://openexchangerates.org/api/latest.json";
//        String response = Unirest.get(url)
//                .header("Content-Type", "application/json")
//                .queryString("app_id", appID)
//                .queryString("base", fromCurrency)
//                .queryString("symbols", toCurrency)
//                .asString()
//                .getBody();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        double rate = 0;
//        try {
//             rate = objectMapper.readTree(response)
//                    .get("rates")
//                    .get(toCurrency).asDouble();
//        }catch (Exception e){
//            System.out.println("Something went wrong!");
//            e.printStackTrace();
//        }
//
//        return rate;
//    }

    // makes 2 requests to circumvent limitation of API - can only get base = "USD"
    // if USD * rate1 = XXX and USD * rate2 = YYY
    // .: XXX = (rate2/rate1)*YYYY
    public double fetchRateFree(String fromCurrency, String toCurrency){
        Dotenv dotenv = Dotenv.load();
        String appID = dotenv.get("OPEN_EXCHANGE_RATES_APP_ID");
        String url = "https://openexchangerates.org/api/latest.json";

        // get rate USD to currency1:
        String rate1 = Unirest.get(url)
                .header("Content-Type", "application/json")
                .queryString("app_id", appID)
                .queryString("base", "USD")
                .queryString("symbols", fromCurrency)
                .asString()
                .getBody();

        // get rate USD to currency2:
        String rate2 = Unirest.get(url)
                .header("Content-Type", "application/json")
                .queryString("app_id", appID)
                .queryString("base", "USD")
                .queryString("symbols", toCurrency)
                .asString()
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        double effectiveRate = 0;
        try {
            effectiveRate =
                            objectMapper.readTree(rate2)
                            .get("rates")
                            .get(toCurrency).asDouble()
                    /
                            objectMapper.readTree(rate1)
                            .get("rates")
                            .get(fromCurrency).asDouble();
        }catch (Exception e){
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }

        return effectiveRate;
    }


}
