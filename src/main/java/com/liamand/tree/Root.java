package com.liamand.tree;

import com.liamand.Token;
import com.liamand.commands.Command;

import java.util.ArrayList;

public class Root {

    private String literal;
    private ArrayList<ArgumentNode> arguments;

    public Root(String literal) {
        arguments = new ArrayList<>();
        this.literal = literal;
    }

    public Root executes(Command command) {
        return then(new ArgumentNode(new Token.TYPE[] { Token.TYPE.NONE }).executes(command));
    }

    public Root then(ArgumentNode arg) {
        arguments.add(arg);
        return this;
    }

    public ArrayList<ArgumentNode> getArguments() {
        return arguments;
    }

    public String getLiteral() {
        return literal;
    }
}
