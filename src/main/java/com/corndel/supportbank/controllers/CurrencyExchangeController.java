package com.corndel.supportbank.controllers;

import com.corndel.supportbank.models.CurrencyExchange;
import picocli.CommandLine.Command;

@Command(name = "exchange", subcommands = {CurrencyExchange.class})
public class CurrencyExchangeController {
}
