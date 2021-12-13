package com.liamand;

import com.liamand.exceptions.CommandCreationError;
import com.liamand.exceptions.CommandSyntaxError;
import com.liamand.exceptions.ExecutionCommandError;
import com.liamand.tree.ArgumentNode;
import com.liamand.tree.Root;

public class TestMain {

    public static void main(String[] args) throws CommandSyntaxError, CommandCreationError, ExecutionCommandError {
        Dispatcher dispatcher = new Dispatcher();

        dispatcher.register(new Root("help").then(
                new ArgumentNode(Token.TYPE.STRING).then(new ArgumentNode(Token.TYPE.INTEGER).executes(
                        (c) -> System.out.println("2 arg")
                ))
                ));

        Parser parser = new Parser(" ");

        parser.parse("help hello 15",dispatcher);
    }


}
