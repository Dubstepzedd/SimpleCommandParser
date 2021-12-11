package com.liamand.commands;

import com.liamand.Argument;

public interface Command {

    /**This is the "command name". e.g /spawn has the key word "spawn" **/
    String getKeyWord();

    /**Lets you describe the command. Will be displayed in /help **/
    String getDescription();
    /**Lets you define the argument types. If you don't want arguments, return an empty array. **/
    Argument.TYPE[] getArguments();

    /**This is the method that is run when one 'calls' the command. **/
    void execute(Object[] args);


}
