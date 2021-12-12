package com.liamand;

import com.liamand.commands.Command;
import com.liamand.tree.ArgumentNode;
import com.liamand.tree.Root;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private ArrayList<Command> registeredCommands;
    private final String SEPARATOR;

    public Parser(String separator) {
        SEPARATOR = separator;
        registeredCommands = new ArrayList<>();
    }

    /** Parses a command string. We therefore assume that this is an actual command **/
    public void parse(String str,Dispatcher dispatcher) throws ClassNotFoundException{
        //Tokenize the String
        String[] tokenizedStr = str.split(SEPARATOR);

        //Get the keyword and arguments
        String literal = tokenizedStr[0];
        String[] args = Arrays.copyOfRange(tokenizedStr,1,tokenizedStr.length);

        //Check that the keyword, str, exists.
        for(Root root : dispatcher.getCommands()) {
            //TODO Add (1) Arguments and (2) handle commands with or without arguments.
            if(literal.equals(root.getLiteral())) {

                Token.TYPE[] parsedArgsTypes = getArgumentTypes(args);
                if(parsedArgsTypes.length == 0)
                    parsedArgsTypes = new Token.TYPE[] {Token.TYPE.NONE};

                //Loop through the arguments that the root has.
                boolean argsMatched = false;
                for(ArgumentNode node : root.getArguments()) {

                    //Check if the argument types match
                    if(Arrays.equals(node.getArgTypes(), parsedArgsTypes)) {
                        argsMatched = true;
                        //Convert the String array to the actual types
                        Object[] convertedArgs = parseArguments(args,parsedArgsTypes);
                        //Execute the command for that node.
                        node.getCommand().execute(convertedArgs);
                    }

                }


                if(!argsMatched) {
                    throwSyntaxError();
                }

            }
        }

    }

    private void throwSyntaxError() throws ClassNotFoundException {
        throw new ClassNotFoundException("The command specified was not found");
    }

    private Token.TYPE[] getArgumentTypes(String[] args) {
        Token.TYPE[] types = new Token.TYPE[args.length];
        for(int i = 0; i < types.length; i++) {
            types[i] = Token.getType(args[i]);
        }

        return types;
    }

    //TODO change this
    /** Parse the arguments and execute the command if everything works out **/
    private Object[] parseArguments(String[] args, Token.TYPE[] types) throws ClassNotFoundException {

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
                throwSyntaxError();
            }

        }


        return copyArgs;

    }

    public void registerCommand(Command cmd) {
        registeredCommands.add(cmd);
    }

    public ArrayList<Command> getRegisteredCommands() {
        return registeredCommands;
    }
}
