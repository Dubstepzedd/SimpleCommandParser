package com.liamand;

import com.liamand.exceptions.CommandSyntaxError;
import com.liamand.tree.ArgumentNode;
import com.liamand.tree.Root;

public class TestMain {

    public static void main(String[] args) throws CommandSyntaxError {
        Dispatcher dispatcher = new Dispatcher();

        dispatcher.register(new Root("help").then(
                new ArgumentNode(Token.TYPE.INTEGER).executes((argument) ->
                        System.out.println("Integer!"))).executes((arguments) ->
                System.out.println("No Args") ));

        Parser parser = new Parser(" ");

        parser.parse("help",dispatcher);
    }


}
