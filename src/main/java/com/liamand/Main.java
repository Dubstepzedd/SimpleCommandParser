package com.liamand;

import com.liamand.commands.Command;

import java.util.Scanner;

/*
public class Main {
    //This is an example of how to run the Command Parser
    public static final char IDENTIFIER = '/';

    public static void main(String[] args) {
        System.out.println("[Command Executor Running]");

        Parser parser = new Parser(" ");

        //Register commands
        parser.registerCommand(new Command() {

            @Override
            public String getKeyWord() {
                return "stop";
            }

            @Override
            public String getDescription() {
                return "Stops the server.";
            }

            @Override
            public Token.TYPE[] getArguments() {
                return new Token.TYPE[] {Token.TYPE.STRING};
            }

            @Override
            public void execute(Object[] args) {
                for (Object obj : args) {
                    System.out.println(obj.getClass());
                }
                System.out.println("[Stopping]");
                System.exit(-1);
            }
        });

        parser.registerCommand(new Command() {

            @Override
            public String getKeyWord() {
                return "restart";
            }

            @Override
            public String getDescription() {
                return "Restarts the server.";
            }

            @Override
            public Token.TYPE[] getArguments() {
                return new Token.TYPE[0];
            }

            @Override
            public void execute(Object[] args) {
                System.out.println("[Restarting]");
                Main.main(new String[0]);
            }
        });
        parser.registerCommand(new Command() {

            @Override
            public String getKeyWord() {
                return "help";
            }

            @Override
            public String getDescription() {
                return "Displays all commands.";
            }

            @Override
            public Token.TYPE[] getArguments() {
                return new Token.TYPE[0];
            }

            @Override
            public void execute(Object[] args) {
                for(Command cmd : parser.getRegisteredCommands()) {
                    System.out.println("%s : %s".formatted(cmd.getKeyWord(),cmd.getDescription()));
                }
            }
        });

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
*/