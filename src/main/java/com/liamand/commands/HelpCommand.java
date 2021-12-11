package com.liamand.commands;

import com.liamand.Argument;
import com.liamand.Parser;

public class HelpCommand extends Command {
    public HelpCommand(Parser parser) {
        super(parser);
    }

    @Override
    public String getKeyWord() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays all commands.";
    }

    @Override
    public Argument.TYPE[] getArguments() {
        return new Argument.TYPE[0];
    }

    @Override
    public void execute(Object[] args) {
        for(Command cmd : parser.getRegisteredCommands()) {
            System.out.println("%s : %s".formatted(cmd.getKeyWord(),cmd.getDescription()));
        }
    }
}
