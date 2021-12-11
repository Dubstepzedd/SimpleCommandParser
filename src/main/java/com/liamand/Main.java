package com.liamand;

import com.liamand.commands.HelpCommand;
import com.liamand.commands.RestartCommand;
import com.liamand.commands.StopCommand;

import java.util.Scanner;


public class Main {
    //This is an example of how to run the Command Parser
    public static final char IDENTIFIER = '/';

    public static void main(String[] args) {
        System.out.println("[Command Executor Running]");

        Parser parser = new Parser(" ");

        //Register commands
        parser.registerCommand(new StopCommand(parser));
        parser.registerCommand(new HelpCommand(parser));
        parser.registerCommand(new RestartCommand(parser));

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String str = scanner.nextLine();

            if(str.charAt(0) == IDENTIFIER) {
                System.out.println("[Command Detected]");

                try {
                    parser.parse(str.substring(1));
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Error in command syntax");
                }
            }

        }
    }

}
