package com.liamand.tree;

import com.liamand.Token;
import com.liamand.commands.Command;

import java.util.ArrayList;

public class ArgumentNode {
    //----- VARIABLES -----//
    private Token.TYPE type;
    private ArrayList<Object> args;
    private Command cmd;

    /** Initializes a new ArgumentNode with a specified TYPE. **/
    public ArgumentNode(Token.TYPE type) {
        args = new ArrayList<>();
        this.type = type;
    }

    /** Defines what command that will execute **/
    public ArgumentNode executes(Command cmd) {
        this.cmd = cmd;
        return this;
    }

    /** Adds a node to this ArgumentNode, extending the allowed arguments for the command. **/
    public ArgumentNode then(ArgumentNode node) {
        args.add(new Object[] {node});
        return this;
    }

    //----- GETTERS -----//
    /** Returns the TYPE of the ArgumentNode **/
    public Token.TYPE getType() {
        return type;
    }
    /** Returns the list of arguments which the ArgumentNode carries. **/
    public ArrayList<Object> getArgs() {
        return args;
    }

    /** Returns the command of the ArgumentNode **/
    public Command getCommand() {
        return cmd;
    }

}
