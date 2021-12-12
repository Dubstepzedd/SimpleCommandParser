package com.liamand;

import com.liamand.exceptions.CommandSyntaxError;
import com.liamand.tree.ArgumentNode;
import com.liamand.tree.Root;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    //----- VARIABLES -----//
    private final String SEPARATOR;

    /** Initializes a new Parser with a specified word separator. **/
    public Parser(String separator) {
        SEPARATOR = separator;
    }

    /** Parses a command string. We therefore assume that this is an actual command **/
    public void parse(final String str,final Dispatcher dispatcher) throws CommandSyntaxError{
        //Tokenize the String
        String[] tokenizedStr = str.split(SEPARATOR);

        //Get the literal and arguments
        String literal = tokenizedStr[0];
        String[] args = Arrays.copyOfRange(tokenizedStr,1,tokenizedStr.length);
        Token.TYPE[] types = getArgumentTypes(args);

        //If the arguments are empty, replace it with a new array with the single element TYPE.NONE.
        if(types.length == 0) {
            types = new Token.TYPE[] {Token.TYPE.NONE};
        }

        for(Root root : dispatcher.getCommands()) {

            //The keyword match
            if(root.getLiteral().equals(literal)) {

                //Create an array with the known depth. We check all nodes / leaves for the args pattern.
                ArrayList<ArgumentNode> output = new ArrayList<>();
                //Check arguments!
                boolean wasPatternFound = false;
                for(Object node : root.getArgs()) {

                    //Check if the command args the user entered are valid.
                    if(findTypePattern((Object[]) node,output,types)) {

                        ArgumentNode arg = output.get(output.size()-1);
                        //TODO Handle executions.
                        //Only execute the node if the node's command execute is specified
                        if(arg.getCommand() != null) {
                            wasPatternFound = true;

                            arg.getCommand().execute(convertArguments(args,types));
                        }
                        else
                            throw new CommandSyntaxError();
                    }
                }

                if(!wasPatternFound)
                    throw new CommandSyntaxError();
            }
            else
                throw new CommandSyntaxError();
        }

    }

    /** Deep searches for the Object[] nodeArray in hopes of finding a match with the arguments TYPE[]. **/
    private boolean findTypePattern(final Object[] nodeArray,ArrayList<ArgumentNode> output, final Token.TYPE[] args) {

        /* Base case, we don't want to go on if we know that we have made it
        this far and there are no more args to handle. */
        if(args.length == 0) {
            return true;
        }

        //Go through the nodes/object[] in the array.
        for(int i = 0; i < nodeArray.length; i++) {
            Object n = nodeArray[i];

            if(n instanceof Object[]) {
                //If n is an object array, we have to go one step deeper.
                return findTypePattern((Object[])n,output,args);
            }
            else if(n instanceof ArgumentNode) {

                //Retrieve the type
                Token.TYPE nodeType = ((ArgumentNode) n).getType();

                //If the types are equivalent, we want to return (go deeper), if not we increment.
                if(nodeType.equals(args[0])) {

                    //The arguments of the node
                    Object[] nodeArguments = ((ArgumentNode) n).getArgs().toArray();

                    //Add it to the output array
                    output.add((ArgumentNode) n);

                    return findTypePattern(nodeArguments,output,Arrays.copyOfRange(args,1,args.length));
                }
                else
                    //Increment, go to next in the array. If there are no more elements, we will return false.
                    i++;

            }
        }

        //Return false, no match.
        return false;
    }

    /** Returns the types of arguments **/
    private Token.TYPE[] getArgumentTypes(String[] args) {
        Token.TYPE[] types = new Token.TYPE[args.length];
        for(int i = 0; i < types.length; i++) {
            types[i] = Token.getType(args[i]);
        }

        return types;
    }

    /** Converts the arguments from type String to their actual data type. **/
    private Object[] convertArguments(String[] args, Token.TYPE[] types) throws CommandSyntaxError {

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

                    case INTEGER:
                        //Replace the String with the correct Object.
                        copyArgs[i] = Integer.valueOf(arg);
                        break;

                    case FLOAT:
                        copyArgs[i] = Float.valueOf(arg);
                        break;

                    case BOOLEAN:
                        copyArgs[i] = Boolean.valueOf(arg);
                        break;
                }
            }
            else {
                throw new CommandSyntaxError();
            }

        }

        return copyArgs;

    }

}
