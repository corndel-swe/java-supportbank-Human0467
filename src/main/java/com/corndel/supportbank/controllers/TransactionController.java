package com.corndel.supportbank.controllers;

import com.corndel.supportbank.services.ListTransactionsService;
import com.corndel.supportbank.services.SummariserService;
import picocli.CommandLine.Command;

@Command(name = "transaction", subcommands = {SummariserService.class, ListTransactionsService.class})
public class TransactionController {
}
