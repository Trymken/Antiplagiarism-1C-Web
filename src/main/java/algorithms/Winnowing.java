package algorithms;

import struct.Ngram;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static algorithms.UsefulMethods.greaterOrEquals;
import static algorithms.UsefulMethods.hashing;


public class Winnowing {

    private static int ngramLength = 20; //10
    private static int windowLength = 5; //4

    private ArrayList<Integer> positions1, positions2;
    private double scoreMin, scoreMax, scoreLength, score;


    public Winnowing(){
        this.positions1 = null;
        this.positions2 = null;
        this.score = 0;
        this.scoreMin = 0;
        this.scoreMax = 0;
        this.scoreLength = 0;
    }


    public Winnowing(String s1, String s2, String algo, int ngram, int window) throws NoSuchAlgorithmException {
        setParams(ngram, window);
        this.init(s1, s2, algo);
    }


    private static ArrayList<byte[]> getHashNgrams(String s, String algo) throws NoSuchAlgorithmException {
        ArrayList<byte[]> arrayList = new ArrayList<>();
        for (int i = 0; i <= s.length() - ngramLength; i++) {
            String temp = s.substring(i, i + ngramLength);
            arrayList.add(hashing(temp, algo));
        }
        return arrayList;
    }


    private ArrayList<Integer> compare(ArrayList<Ngram> l1, ArrayList<Ngram> l2){
        ArrayList<Integer> result = new ArrayList<>();

        for (Ngram ngram : l1) {
            if (l2.contains(ngram)) {
                result.add(ngram.getPosition());
            }
        }

        return result;
    }

    private static ArrayList<Ngram> winnowing(ArrayList<byte[]> l1){
        ArrayList<Ngram> result = new ArrayList<>();
        int position = 0;
        byte[] hash = new byte[0];

        for (int i = 0; i < l1.size() - windowLength + 1; i++) {
            int counter = 0;
            if(i == 0){
                for (int j = 0; j < windowLength; j++) {
                    if(j == 0){
                        hash = l1.get(j);
                        position = 0;
                    } else if (greaterOrEquals(hash, l1.get(j))) {
                        hash = l1.get(j);
                        position = j;
                    }
                }
                result.add(new Ngram(hash, position, ngramLength));
                continue;
            }

            for (int j = i; j < windowLength + i; j++) {
                if (j == i){
                    hash = l1.get(j);
                    position = i + counter;
                } else if(greaterOrEquals(hash, l1.get(j))){
                    hash = l1.get(j);
                    position = i + counter;
                }
                counter++;
            }
            if(position != result.get(result.size() - 1).getPosition()){
                result.add(new Ngram(hash, position, ngramLength));
            }

        }
        return result;
    }


    public void init(String s1, String s2, String algo) throws NoSuchAlgorithmException {
        ArrayList<byte[]> ngrams1 = getHashNgrams(s1, algo);
        ArrayList<byte[]> ngrams2 = getHashNgrams(s2, algo);

        ArrayList<Ngram> winnowing1 = winnowing(ngrams1);
        ArrayList<Ngram> winnowing2 = winnowing(ngrams2);

        this.positions1 = compare(winnowing1, winnowing2);
        this.positions2 = compare(winnowing2, winnowing1);

        int min = Math.min(positions1.size(), positions2.size());

        this.scoreMin = (min * 1. / Math.min(winnowing1.size(), winnowing2.size())) * 100;
        this.scoreMax = (min * 1. / Math.max(winnowing1.size(), winnowing2.size())) * 100;
        this.scoreLength = (min * 2. / (winnowing1.size() + winnowing2.size())) * 100;
        this.score = (this.scoreMin + this.scoreMax + this.scoreLength) / 3.;
    }

    private static void setParams(int ngram, int window){
        ngramLength = ngram;
        windowLength = window;
    }

    public ArrayList<Integer> getPositions1() {
        return positions1;
    }

    public double getScore() {
        return score;
    }

    public double getScoreMin() {
        return scoreMin;
    }

    public double getScoreMax() {
        return scoreMax;
    }

    public double getScoreLength() {
        return scoreLength;
    }

    public ArrayList<Integer> getPositions2() {return positions2;}

    public static int getNgramLength() {
        return ngramLength;
    }

    public static void setNgramLength(int ngramLength) {
        Winnowing.ngramLength = ngramLength;
    }

    public static int getWindowLength() {
        return windowLength;
    }

    public static void setWindowLength(int windowLength) {
        Winnowing.windowLength = windowLength;
    }
}
