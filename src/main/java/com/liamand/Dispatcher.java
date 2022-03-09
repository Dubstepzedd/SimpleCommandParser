package com.liamand;

import com.liamand.tree.Root;

import java.util.ArrayList;

public class Dispatcher {
    //----- Fields -----//
    private final ArrayList<Root> commands;

    /** Initializes a new Dispatcher **/
    public Dispatcher() {
        commands = new ArrayList<>();
    }
    /** Register a new Root (command) **/
    public void register(final Root root) {
        commands.add(root);
    }

    //----- Getters -----//

    /** Returns a list of all the Roots (commands) **/
    public ArrayList<Root> getCommands() {
        return commands;
    }
}
