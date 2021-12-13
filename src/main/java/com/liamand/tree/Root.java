package com.liamand.tree;

import com.liamand.Token;
import com.liamand.commands.Command;
import com.liamand.exceptions.CommandCreationError;

import java.util.ArrayList;

public class Root {
    //----- VARIABLES -----//
    private boolean isCommandSet;
    private ArrayList<ArgumentNode> args;
    private String literal;

    /** Initializes a new Root with a specified literal. **/
    public Root(final String literal) {
        isCommandSet = false;
        args = new ArrayList<>();
        this.literal = literal;
    }

    /** Defines what command the root, that being when no arguments are given, will execute. **/
    public Root executes(final Command cmd) throws CommandCreationError {
        if(isCommandSet)
            throw new CommandCreationError("Execute command is already set for this Root");

        isCommandSet = true;
        return then(new ArgumentNode(Token.TYPE.NONE).executes(cmd));
    }

    /** Adds an ArgumentNode to the root, that being the first argument after the literal **/
    public Root then(final ArgumentNode node) {
        args.add(node);
        return this;
    }

    //----- GETTERS -----//
    /** Returns the literal of the Root **/
    public String getLiteral() {
        return literal;
    }
    /** Returns the list of arguments that the Root carries **/
    public ArrayList<ArgumentNode> getArgs() {
        return args;
    }

}
