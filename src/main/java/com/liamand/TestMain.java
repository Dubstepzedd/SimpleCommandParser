package com.liamand;

import com.liamand.exceptions.CommandCreationError;
import com.liamand.exceptions.CommandSyntaxError;
import com.liamand.tree.ArgumentNode;
import com.liamand.tree.Root;

public class TestMain {

    public static void main(String[] args) throws CommandSyntaxError, CommandCreationError {
        Dispatcher dispatcher = new Dispatcher();

        dispatcher.register(new Root("help").then(
                new ArgumentNode(Token.TYPE.INTEGER).executes((argument) ->
                        System.out.println("Integer!")).then(new ArgumentNode(Token.TYPE.STRING))).then(new ArgumentNode(Token.TYPE.STRING).executes((c) ->
                System.out.println("String as first arg"))));

        Parser parser = new Parser(" ");

        parser.parse("help 15 hej",dispatcher);
    }


}
