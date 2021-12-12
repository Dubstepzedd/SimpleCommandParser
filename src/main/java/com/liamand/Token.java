package com.liamand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {
    private static Pattern stringPattern = Pattern.compile("[a-z]",Pattern.CASE_INSENSITIVE);
    private static Pattern integerPattern = Pattern.compile("^\\d+$");
    private static Pattern decimalPattern = Pattern.compile("^\\d+[.]\\d+$");

    public enum TYPE {
        STRING,
        INTEGER,
        FLOAT,
        BOOLEAN,
        NONE,
        UNIDENTIFIED
    }

    public static TYPE getType(String str) {
        //We check if it's a boolean first as the stringPattern will see it as a String.
        if(isBoolean(str))
            return TYPE.BOOLEAN;
        else if(isInteger(str))
            return TYPE.INTEGER;
        else if(isFloat(str))
            return TYPE.FLOAT;
        else if(isString(str))
            return TYPE.STRING;

        return TYPE.UNIDENTIFIED;
    }
    //TODO Should these be static?
    /**Handles a String with no spaces. e.g "Hello15". An empty String will return false. **/
    private static boolean isString(String str) {

        Matcher matcher = stringPattern.matcher(str);
        return matcher.find();
    }
    /**Handles an Integer with no spaces. e.g "1554". An empty String will return false. **/
    private static boolean isInteger(String str) {
        Matcher matcher = integerPattern.matcher(str);
        return matcher.find();
    }

    /**Handles a Boolean with no spaces. e.g "false". An empty String will return false. **/
    private static boolean isBoolean(String str) {
        return str.equals("false") || str.equals("true");
    }

    /**Handles a Float with no spaces. e.g "55.4565". An empty String will return false. **/
    public static boolean isFloat(String str) {
        Matcher matcher = decimalPattern.matcher(str);
        return matcher.find();
    }

}
