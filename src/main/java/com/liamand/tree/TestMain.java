package com.liamand.tree;

import com.liamand.Dispatcher;
import com.liamand.Parser;
import com.liamand.Token;

public class TestMain {

    public static void main(String[] args) {
        Parser parser = new Parser(" ");
        Dispatcher dispatcher = new Dispatcher();

        dispatcher.register(new Root("help").then(new ArgumentNode(new Token.TYPE[] {Token.TYPE.INTEGER}).executes((arguments) -> {
                    System.out.println("Hello!");
                })

        ).then(new ArgumentNode(new Token.TYPE[] {Token.TYPE.INTEGER, Token.TYPE.INTEGER}).executes((arguments) -> System.out.println("Two args!"))).executes( (arguments) -> {
            System.out.println("No Arguments");
        }));

        try {
            parser.parse("help 15 15", dispatcher);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
