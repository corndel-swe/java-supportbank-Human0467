package com.corndel.supportbank;

import com.corndel.supportbank.controllers.BillController;
import com.corndel.supportbank.controllers.CurrencyController;
import com.github.tomaslanger.chalk.Chalk;
import picocli.CommandLine;

import picocli.CommandLine.Command;


@Command(name = "bank", subcommands = {BillController.class, CurrencyController.class})
public class SupportBank implements Runnable {

  public static void main(String[] args) {
    CommandLine commandLine = new CommandLine(new SupportBank());
    int exitCode = commandLine.execute(args);
    System.exit(exitCode);
  }

  @Override
  public void run() {
    System.out.println(Chalk.on("Welcome to the support bank!").green());
  }
}