package com.liamand.commands;

import com.liamand.Argument;
import com.liamand.Parser;

public abstract class Command {

    protected Parser parser;
    public Command(Parser parser) {
        this.parser = parser;
    }

    /**This is the "command name". e.g /spawn has the key word "spawn" **/
    public abstract String getKeyWord();

    /**Lets you describe the command. Will be displayed in /help **/
    public abstract String getDescription();
    /**Lets you define the argument types. If you don't want arguments, return an empty array. **/
    public abstract Argument.TYPE[] getArguments();

    /**This is the method that is run when one 'calls' the command. **/
    public abstract void execute(Object[] args);


}
