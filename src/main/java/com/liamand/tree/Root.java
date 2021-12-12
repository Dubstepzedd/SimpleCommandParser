package com.liamand.tree;

import com.liamand.Token;
import com.liamand.commands.Command;

import java.util.ArrayList;

public class Root {
    //----- VARIABLES -----//
    private ArrayList<Object> args;
    private String literal;

    /** Initializes a new Root with a specified literal. **/
    public Root(String literal) {
        args = new ArrayList<>();
        this.literal = literal;
    }

    /** Defines what command the root, that being when no arguments are given, will execute. **/
    public Root executes(Command cmd)  {

        return then(new ArgumentNode(Token.TYPE.NONE).executes(cmd));
    }

    /** Adds an ArgumentNode to the root, that being the first argument after the literal **/
    public Root then(ArgumentNode node) {

        args.add(new Object[] {node});
        return this;
    }

    //----- GETTERS -----//
    /** Returns the literal of the Root **/
    public String getLiteral() {
        return literal;
    }
    /** Returns the list of arguments that the Root carries **/
    public ArrayList<Object> getArgs() {
        return args;
    }

}
