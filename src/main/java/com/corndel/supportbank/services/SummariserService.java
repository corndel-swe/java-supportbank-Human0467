package com.corndel.supportbank.services;

import com.corndel.supportbank.models.FileIO;
import com.corndel.supportbank.models.Transaction;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CommandLine.Command(name = "summarise")
public class SummariserService implements Runnable {

    // get parameters
    @CommandLine.Parameters(index = "0")
    private String fileName;

    @CommandLine.Option(names = {"-c", "--currency"})
    private String outputCurrency;

    @Override
    public void run() {
        List<String> transactions = new ArrayList<>();
        FileIO file = new FileIO(fileName);
        try {
            transactions = file.readLines();
        }catch (Exception e){
            e.printStackTrace();
        }


        Transaction transaction = this.outputCurrency == null ?
                new Transaction(transactions) :
                new Transaction(transactions, this.outputCurrency);

        // summarise
        HashMap<String, Double> balances = transaction.summarise();
        // print
        System.out.println("All amounts listed in " + transaction.getOutputCurrency());
        for(String name : balances.keySet()){
            System.out.println(String.format("%s : %.2f", name, balances.get(name)));
        }


    }
}
