package com.liamand.commands;

import com.liamand.Argument;
import com.liamand.Parser;

public class StopCommand extends Command {
    public StopCommand(Parser parser) {
        super(parser);
    }

    @Override
    public String getKeyWord() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "This command exits the program.";
    }

    @Override
    public Argument.TYPE[] getArguments() {
        return new Argument.TYPE[0];
    }

    @Override
    public void execute(Object[] args) {
        for (Object obj : args) {
            System.out.println(obj.getClass());
        }
        System.out.println("[Stopping]");
        System.exit(-1);
    }
}
