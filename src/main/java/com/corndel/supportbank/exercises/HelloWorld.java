package com.corndel.supportbank.exercises;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "hello", description = "says hello", subcommands = {ColorCommand.class})
public class HelloWorld {
    @Parameters(index = "0", description = "The name to greet",
            defaultValue = "World")
    private String name;

//    @Override
//    public void run() {
//        String msg = String.format("Hello, %s!", name);
//        System.out.println(msg);
//    }

    public static void main(String[] args) {
        // need to pass in an instance of the class that is decorated @Command
        CommandLine cli = new CommandLine(new HelloWorld());

        // execute returns a number indicating if command was successful
        int exitCode = cli.execute(args);
        System.exit(exitCode);
    }
}
