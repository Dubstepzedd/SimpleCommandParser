package com.liamand.exceptions;

public class CommandSyntaxError extends Exception {
    /** Initializes a new CommandSyntaxError, thrown when no command is found **/
    public CommandSyntaxError() {
        super("Invalid command syntax");
    }
    /** Initializes a new CommandSyntaxError with a custom message, thrown when no command is found **/
    public CommandSyntaxError(final String msg) {
        super(msg);
    }


}
