package com.liamand.tree;

import com.liamand.Token;
import com.liamand.commands.Command;

public class ArgumentNode {

    private Command command;
    private Token.TYPE[] argTypes;
    public ArgumentNode(Token.TYPE[] argTypes) {
        this.argTypes = argTypes;
    }

    public ArgumentNode executes(Command command) {
        this.command = command;
        return this;
    }

    public Token.TYPE[] getArgTypes() {
        return argTypes;
    }

    public Command getCommand() {
        return command;
    }

}
