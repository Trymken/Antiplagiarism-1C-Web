package algorithms;

import antlr4.OneCLexer;
import antlr4.OneCParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static algorithms.Tokenizer.setTokens;

public class Start {

    private CommonTokenStream tokensList1 = null;
    private CommonTokenStream tokensList2 = null;
    private ArrayList<int[]> intervals1, intervals2;
    private final Winnowing winnowing;


    public String getTokens(String str) throws IOException {
        String content = Files.readString(Path.of(str));
        content = content.toLowerCase();

        OneCLexer lexer = new OneCLexer(CharStreams.fromString(content));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OneCParser parser = new OneCParser(tokens);
        parser.startFile();
        if(tokensList1 == null)
            tokensList1 = tokens;
        else
            tokensList2 = tokens;

        return setTokens(tokens);
    }

    public static Set<Integer> getLines(ArrayList<Integer> positions, CommonTokenStream tokensList){
        int[][] lines = new int[100][2];
        for (int i = 0; i < tokensList.size(); i++) {
            lines[tokensList.get(i).getLine()][0]++;
        }

        for (int i = 0; i < positions.size(); i++) {
            lines[tokensList.get(positions.get(i)).getLine()][1]++;
        }

        int line;
        Set<Integer> pos = new HashSet<>();
        for (int i = 0; i < tokensList.size(); i++) {
            line = tokensList.get(i).getLine();
            if((lines[line][1] * 1. / lines[line][0]) >= 0.8){
                pos.add(line);
            }
        }
        return pos;
    }

    public static ArrayList<int[]> getSequences(ArrayList<Integer> list, int nGramLength){
        int start, end;
        ArrayList<int[]> result = new ArrayList<>();
        start = list.get(0);
        end = start + nGramLength;
        for (int i = 1; i < list.size(); i++) {
            int curr = list.get(i);
            if(curr - end > 1){
                result.add(new int[]{start, end});
                start = curr;
                end = start + nGramLength;
            } else {
                int temp = curr + nGramLength;
                if(temp > end){
                    end = temp;
                }
            }
        }
        result.add(new int[]{start, end});
        return result;
    }

    public static ArrayList<int[]> getLineIntervals(ArrayList<int[]> list, CommonTokenStream tokensList){
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(new int[]{tokensList.get(list.get(i)[0]).getLine(), tokensList.get(list.get(i)[1]).getLine()});
        }
        return result;
    }

    public Start(String text1, String text2, String algorithm) throws IOException, NoSuchAlgorithmException {
        String s1 = getTokens(text1);
        String s2 = getTokens(text2);

        swapTokens();

        winnowing = new Winnowing(s1, s2, algorithm);

        ArrayList<Integer> positions1 = winnowing.getPositions1();
        ArrayList<Integer> positions2 = winnowing.getPositions2();

        Collections.sort(positions1);
        Collections.sort(positions2);

        intervals1 = getSequences(positions1, 10);
        intervals2 = getSequences(positions2, 10);

        intervals1 = getLineIntervals(intervals1, tokensList1);
        intervals2 = getLineIntervals(intervals2, tokensList2);
    }

    public ArrayList<int[]> getIntervals1() {
        return intervals1;
    }

    public ArrayList<int[]> getIntervals2() {
        return intervals2;
    }

    public Winnowing getWinnowing() {
        return winnowing;
    }

    private void swapTokens(){
        if(tokensList1.size() > tokensList2.size()){
            CommonTokenStream temp;
            temp = tokensList1;
            tokensList1 = tokensList2;
            tokensList2 = temp;
        }
    }
}
