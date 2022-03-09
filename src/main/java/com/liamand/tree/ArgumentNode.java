package com.liamand.tree;

import com.liamand.Token;
import com.liamand.commands.Command;
import com.liamand.exceptions.CommandCreationError;

import java.util.ArrayList;
import java.util.List;

public class ArgumentNode {
    //----- Fields -----//
    private boolean isCommandSet;
    private Token.TYPE type;
    private List<ArgumentNode> args;
    private Command cmd;

    /** Initializes a new ArgumentNode with a specified TYPE. **/
    public ArgumentNode(final Token.TYPE type) {
        isCommandSet = false;
        args = new ArrayList<>();
        this.type = type;
    }

    /** Defines what command that will execute **/
    public ArgumentNode executes(final Command cmd) throws CommandCreationError {
        if(isCommandSet)
            throw new CommandCreationError("Execute command is already set for this ArgumentNode");

        isCommandSet = true;
        this.cmd = cmd;
        return this;
    }

    /** Adds a node to this ArgumentNode, extending the allowed arguments for the command. **/
    public ArgumentNode then(final ArgumentNode node) {
        args.add(node);
        return this;
    }

    //----- Getters -----//
    /** Returns the TYPE of the ArgumentNode **/
    public Token.TYPE getType() {
        return type;
    }
    /** Returns the list of arguments which the ArgumentNode carries. **/
    public List<ArgumentNode> getArgs() {
        return args;
    }

    /** Returns the command of the ArgumentNode **/
    public Command getCommand() {
        return cmd;
    }

}
