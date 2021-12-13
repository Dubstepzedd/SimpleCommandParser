package com.liamand;

import com.liamand.exceptions.CommandSyntaxError;
import com.liamand.exceptions.ExecutionCommandError;
import com.liamand.tree.ArgumentNode;
import com.liamand.tree.Root;
import java.util.Arrays;

public class Parser {
    //----- VARIABLES -----//
    private final String SEPARATOR;

    /** Initializes a new Parser with a specified word separator. **/
    public Parser(String separator) {
        SEPARATOR = separator;
    }

    /** Parses a String and checks if it matches any of the registered commands in the Dispatcher. **/
    public void parse(final String str,final Dispatcher dispatcher) throws CommandSyntaxError,ExecutionCommandError {
        //Tokenize the String
        String[] tokenizedStr = str.split(SEPARATOR);

        //Get the literal and arguments
        String literal = tokenizedStr[0];
        String[] args = Arrays.copyOfRange(tokenizedStr,1,tokenizedStr.length);
        Token.TYPE[] types = convertArgumentToTypes(args);

        //If the arguments are empty, replace it with a new array with the single element TYPE.NONE.
        if(types.length == 0) {
            types = new Token.TYPE[] {Token.TYPE.NONE};
        }

        for(Root root : dispatcher.getCommands()) {

            //The keyword match
            if(root.getLiteral().equals(literal)) {

                //We create this to check if there are any matches in the pattern. If not we throw a CommandSyntax error.
                boolean wasPatternFound = false;
                //Go through all the ArgumentNodes that the Root contains.
                for(ArgumentNode node : root.getArgs()) {

                    //Check if the command args the user entered are valid.
                    ArgumentNode outputNode = findTypePattern(node,types);
                    //If the output is not null, meaning that it found something, we do something
                    if(outputNode != null) {

                        //Only execute the node if the node's command execute is specified
                        if(outputNode.getCommand() != null) {
                            wasPatternFound = true;

                            //We execute that specific node.
                            outputNode.getCommand().execute(convertArguments(args,types));
                        }
                        //Otherwise, if the command is not created, we throw a CommandSyntaxError.
                        //NOTE This only works on ArgumentNodes, and not the Root.
                        else
                            throw new ExecutionCommandError("Executed ArgumentNode command was not initiated in " +
                                    "root '" + root.getLiteral() + "' at a node with the TYPE " + outputNode.getType() + ".");
                    }
                }

                if(!wasPatternFound)
                    throw new CommandSyntaxError();
            }
            else
                throw new CommandSyntaxError();
        }

    }

    /** Checks if the TYPE array exists in a node, and it's sub nodes. Returns null if nothing was found and
     * the node if it was found. **/
    private ArgumentNode findTypePattern(final ArgumentNode node, Token.TYPE[] args){

        //Check if the current node is equal to the first argument type.
        if(node.getType().equals(args[0])) {
            //Splice the args array for recursion
            args = Arrays.copyOfRange(args,1,args.length);

            //Check the length of args. If it is zero we return this node as it is complete.
            if(args.length == 0)
                return node;
            //Otherwise, we check the size of the node's arguments to see if it has any.
            if(node.getArgs().size() > 0) {
                //If there are arguments, we loop through the nodes.
                for(ArgumentNode subNode : node.getArgs()) {

                    //Then we use recursion by using the same method on the nodes.
                    subNode = findTypePattern(subNode,args);

                    //If the subNode is not null, then we have found it "at this level". So we return that
                    if(subNode != null)
                        return subNode;
                }

            }
            //If it does not, we return as it is a syntax error.
            else {
                return null;
            }

        }

        //Return null, no match.
        return null;
    }

    /** Converts a String array to a TYPE array.  **/
    private Token.TYPE[] convertArgumentToTypes(final String[] args) {
        Token.TYPE[] types = new Token.TYPE[args.length];
        for(int i = 0; i < types.length; i++) {
            types[i] = Token.getType(args[i]);
        }

        return types;
    }

    /** Converts the arguments from type String to their actual data type. **/
    private Object[] convertArguments(final String[] args, final Token.TYPE[] types) throws CommandSyntaxError {

        /*
            Create a copy so we can insert Object types. Not possible with the current
            String array as String inherits from Object.
        */

        Object[] copyArgs = new Object[args.length];
        System.arraycopy(args,0,copyArgs,0,args.length);

        for(int i = 0; i < copyArgs.length; i++) {
            //Retrieve the type of the current argument.
            Token.TYPE argType = Token.getType((String)copyArgs[i]);

            if(argType.equals(types[i])) {
                //Retrieve the argument in String form.
                String arg = args[i];

                switch (argType) {
                    //We don't need to handle String as that Object is already used in the array.
                    //Replace the String with the correct Object.
                    case INTEGER -> copyArgs[i] = Integer.valueOf(arg);
                    case FLOAT -> copyArgs[i] = Float.valueOf(arg);
                    case BOOLEAN -> copyArgs[i] = Boolean.valueOf(arg);
                }
            }
            else {
                throw new CommandSyntaxError();
            }

        }

        return copyArgs;

    }

}
