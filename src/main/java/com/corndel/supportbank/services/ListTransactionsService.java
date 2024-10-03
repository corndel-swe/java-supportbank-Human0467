package com.corndel.supportbank.services;

import com.corndel.supportbank.models.FileIO;
import com.corndel.supportbank.models.Transaction;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "list")
public class ListTransactionsService implements Runnable{

    // get parameters
    @CommandLine.Parameters(index = "0")
    private String fileName;

    @CommandLine.Parameters(index = "1")
    private String accountName;


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
        System.out.println(transaction.getTransactions(accountName));

    }
}
