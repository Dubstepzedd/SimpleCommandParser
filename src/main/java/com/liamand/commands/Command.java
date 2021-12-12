package com.liamand.commands;

@FunctionalInterface
public interface Command {

    /**This is the method that is run when one 'calls' the command. **/
    void execute(Object[] args);


}
