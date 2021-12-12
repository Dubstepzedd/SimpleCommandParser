package com.liamand;

import com.liamand.tree.Root;

import java.util.ArrayList;

public class Dispatcher {

    private ArrayList<Root> commands;
    public Dispatcher() {
        commands = new ArrayList<>();
    }
    public void register(Root root) {
        commands.add(root);
    }

    public ArrayList<Root> getCommands() {
        return commands;
    }
}
