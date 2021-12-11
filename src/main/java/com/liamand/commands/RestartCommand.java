package com.liamand.commands;

import com.liamand.Argument;
import com.liamand.Main;
import com.liamand.Parser;


public class RestartCommand extends Command{
    public RestartCommand(Parser parser) {
        super(parser);
    }

    @Override
    public String getKeyWord() {
        return "restart";
    }

    @Override
    public String getDescription() {
        return "Restarts the program.";
    }

    @Override
    public Argument.TYPE[] getArguments() {
        return new Argument.TYPE[0];
    }

    @Override
    public void execute(Object[] args) {
        System.out.println("[Restarting]");
        parser.getRegisteredCommands().clear();
        Main.main(new String[0]);

    }
}
