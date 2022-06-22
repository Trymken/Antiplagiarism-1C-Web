package algorithms;

import antlr4.oneC.OneCLexer;
import antlr4.oneC.OneCParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static algorithms.Tokenizer.setTokens;

public class initWinnowing {

    private CommonTokenStream tokensList1 = null;
    private CommonTokenStream tokensList2 = null;
    private final String s1;
    private final String s2;
    private ArrayList<int[]> intervals1, intervals2;
    private final Winnowing winnowing;


    public initWinnowing(String text1, String text2, String algorithm, int ngram, int window)
            throws IOException, NoSuchAlgorithmException {

        s1 = getTokens(text1);
        s2 = getTokens(text2);

        winnowing = new Winnowing(s1, s2, algorithm, ngram, window);

        ArrayList<Integer> positions1 = winnowing.getPositions1();
        ArrayList<Integer> positions2 = winnowing.getPositions2();

        if (positions1.size() > 0 && positions2.size() > 0) {
            Collections.sort(positions1);
            Collections.sort(positions2);

            intervals1 = getSequences(positions1, Winnowing.getNgramLength());
            intervals2 = getSequences(positions2, Winnowing.getNgramLength());

            intervals1 = getLineIntervals(intervals1, tokensList1);
            intervals2 = getLineIntervals(intervals2, tokensList2);
        }
    }


    public String getTokens(String str) throws IOException {
        String content = Files.readString(Path.of(str));
        content = content.toLowerCase();

        OneCLexer lexer = new OneCLexer(CharStreams.fromString(content));
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OneCParser parser = new OneCParser(tokens);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
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

        for (Integer position : positions) {
            lines[tokensList.get(position).getLine()][1]++;
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
        result.add(new int[]{start, end - 1});
        return result;
    }

    public static ArrayList<int[]> getLineIntervals(ArrayList<int[]> list, CommonTokenStream tokensList){
        ArrayList<int[]> result = new ArrayList<>();
        for (int[] ints : list) {
            result.add(new int[]{tokensList.get(ints[0]).getLine(), tokensList.get(ints[1]).getLine()});
        }
        return result;
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

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }
}
