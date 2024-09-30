package com.corndel.supportbank.exercises;

import picocli.CommandLine;
import picocli.CommandLine.Command;
// import picocli.CommandLine.Parameters;

@Command(name = "hello", description = "says hello")
public class HelloWorld implements Runnable {
    public static void main(String[] args) {
        // need to pass in an instance of the class that is decorated @Command
        CommandLine cli = new CommandLine(new HelloWorld());

        // execute returns a number indicating if command was successful
        int exitCode = cli.execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Hello, World!");
    }
}
