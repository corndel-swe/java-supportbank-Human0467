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

    @Override
    public void run() {
        List<String> transactions = new ArrayList<>();
        FileIO file = new FileIO(fileName);
        try {
            transactions = file.readLines();
        }catch (Exception e){
            e.printStackTrace();
        }

        Transaction transaction = new Transaction(transactions);

        // summarise
        HashMap<String, Double> balances = transaction.summarise();
        // print
        for(String name : balances.keySet()){
            System.out.println(String.format("%s : %.2f", name, balances.get(name)));
        }


    }
}
