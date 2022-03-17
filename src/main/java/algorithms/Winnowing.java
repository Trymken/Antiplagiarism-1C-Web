package algorithms;

import struct.Ngram;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static algorithms.Algorithms.greaterOrEquals;
import static algorithms.Algorithms.hashing;


public class Winnowing {

    private static final int shingleLength = 10;
    private static final int windowLength = 4;

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


    public Winnowing(String s1, String s2, String algo) throws NoSuchAlgorithmException {
        this.init(s1, s2, algo);
    }


    private static ArrayList<byte[]> getHashNgrams(String s, String algo) throws NoSuchAlgorithmException {
        ArrayList<byte[]> arrayList = new ArrayList<>();
        for (int i = 0; i <= s.length() - shingleLength; i++) {
            String temp = s.substring(i, i + shingleLength);
            arrayList.add(hashing(temp, algo));
        }
        return arrayList;
    }


    private ArrayList<Integer> compare1(ArrayList<Ngram> l1, ArrayList<Ngram> l2){
        long count = 0;
        ArrayList<Integer> result = new ArrayList<>();

        if(l1.size() > l2.size()){
            ArrayList<Ngram> temp = l1;
            l1 = l2;
            l2 = temp;
        }

        for (int i = 0; i < l1.size(); i++) {
            if(l2.contains(l1.get(i))){
                result.add(l1.get(i).getPosition()); //TODO поменять
                count++;
            }
        }

        this.scoreMin = (count * 1. / Math.min(l1.size(), l2.size())) * 100;
        this.scoreMax = (count * 1. / Math.max(l1.size(), l2.size())) * 100;
        this.scoreLength = (count * 1. / (l1.size() + l2.size())) * 100;
        this.score = (this.scoreMin + this.scoreMax + this.scoreLength) / 3.;

        return result;
    }


    private ArrayList<Integer> compare2(ArrayList<Ngram> l1, ArrayList<Ngram> l2){
        ArrayList<Integer> result = new ArrayList<>();

        if(l1.size() < l2.size()){
            ArrayList<Ngram> temp = l1;
            l1 = l2;
            l2 = temp;
        }

        for (int i = 0; i < l1.size(); i++) {
            if(l2.contains(l1.get(i))){
                result.add(l1.get(i).getPosition());
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
                result.add(new Ngram(hash, position, shingleLength));
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
                result.add(new Ngram(hash, position, shingleLength));
            }

        }
        return result;
    }


    public void init(String s1, String s2, String algo) throws NoSuchAlgorithmException {
        ArrayList<byte[]> ngrams1 = getHashNgrams(s1, algo);
        ArrayList<byte[]> ngrams2 = getHashNgrams(s2, algo);

        ArrayList<Ngram> winnowing1 = winnowing(ngrams1);
        ArrayList<Ngram> winnowing2 = winnowing(ngrams2);

        this.positions1 = compare1(winnowing1, winnowing2);
        this.positions2 = compare2(winnowing2, winnowing1);
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
}
