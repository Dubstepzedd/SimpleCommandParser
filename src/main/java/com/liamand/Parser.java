package com.liamand;

import com.liamand.commands.Command;

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
    public void parse(String str) throws ClassNotFoundException{
        //Tokenize the String
        String[] tokenizedStr = str.split(SEPARATOR);

        //Get the keyword and arguments
        String keyword = tokenizedStr[0];
        String[] args = Arrays.copyOfRange(tokenizedStr,1,tokenizedStr.length);

        boolean wasFound = false;
        //Check that the keyword, str, exists.
        for(Command cmd : getRegisteredCommands()) {
            //TODO Add (1) Arguments and (2) handle commands with or without arguments.
            if(keyword.equals(cmd.getKeyWord())) {

                //Check if there are any arguments
                if(args.length > 0) {
                    if(cmd.getArguments().length == args.length) {

                        wasFound = parseArguments(cmd,args);
                    }
                }
                //We have 0 arguments
                else {
                    wasFound = true;
                    cmd.execute(new String[0]);
                }

            }
        }

        //TODO Check if it's possible to do without boolean
        if(!wasFound) {
            throw new ClassNotFoundException("The command specified was not found");
        }
    }

    /** Parse the arguments and execute the command if everything works out **/
    private boolean parseArguments(Command cmd, String[] args) {

        /*
            Create a copy so we can insert Object types. Not possible with the current
            String array as String inherits from Object.
        */
        Object[] copyArgs = new Object[args.length];

        System.arraycopy(args,0,copyArgs,0,args.length);

        for(int i = 0; i < copyArgs.length; i++) {
            //Retrieve the type of the current argument.
            Argument.TYPE argType = Argument.getType((String)copyArgs[i]);

            if(argType.equals(cmd.getArguments()[i])) {
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
                return false;
            }

        }

        cmd.execute(copyArgs);

        return true;

    }

    public void registerCommand(Command cmd) {
        registeredCommands.add(cmd);
    }

    public ArrayList<Command> getRegisteredCommands() {
        return registeredCommands;
    }
}
