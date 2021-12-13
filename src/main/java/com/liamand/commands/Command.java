package com.liamand.commands;

@FunctionalInterface
public interface Command {

    /**This is the method that is called when a command is executed. **/
    void execute(final Object[] args);


}
