package algorithms;

import org.antlr.v4.runtime.CommonTokenStream;


public class Tokenizer {

    private static final char[] chars = {'!', '@', '#', '%', '^', '&', '*', '(', ')', '+', '?', ':', '~'};

    private static char getChar(int tokenType){
        if(tokenType == -1) return '.';
        if(tokenType < 26) return (char) ('a' + tokenType);
        if(tokenType < 52) return (char) ('A' + tokenType - 26);
        if(tokenType < 62) return (char) ('0' + tokenType - 52);
        return chars[tokenType - 62];
    }

    public static String setTokens(CommonTokenStream cts){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cts.size(); i++) {
            sb.append(getChar(cts.get(i).getType()));
        }
        return sb.toString();
    }
}
