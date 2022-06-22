package algorithms;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static algorithms.Utils.contains;
import static algorithms.Utils.generateHash;

public class Shingle {

    private int shingleLength = 10;
    private int MODULE = 4;
    private boolean USE_MODULE = false;

    private double score;
    private ArrayList<byte[]> hashNgrams1;
    private ArrayList<byte[]> hashNgrams2;

    Shingle(){
        this.score = 0;
        this.hashNgrams1 = null;
        this.hashNgrams2 = null;
    }

    Shingle(String s1, String s2, String algo, int shingleLength, int module, boolean useModule)
            throws NoSuchAlgorithmException {

        this.shingleLength = shingleLength;
        this.MODULE = module;
        this.USE_MODULE = useModule;
        this.init(s1, s2, algo);
    }


    private void compare(ArrayList<byte[]> l1, ArrayList<byte[]> l2){
        long count = 0;

        if(l1.size() > l2.size()){
            ArrayList<byte[]> temp = l1;
            l1 = l2;
            l2 = temp;
        }

        for (int i = 0; i < l1.size(); i++) {
            if(contains(l1.get(i), l2)){
                count++;
            }
        }

        this.score = (count * 1. / Math.max(l1.size(), l2.size())) * 100;
    }

    public void init(String s1, String s2, String algorithm) throws NoSuchAlgorithmException {
        this.hashNgrams1 = generateHash(s1, algorithm, MODULE, USE_MODULE, shingleLength);
        this.hashNgrams2 = generateHash(s2, algorithm, MODULE, USE_MODULE, shingleLength);
        this.compare(hashNgrams1, hashNgrams2);
    }

    public double getScore() {
        return score;
    }

    public ArrayList<byte[]> getHashNgrams1() {
        return hashNgrams1;
    }

    public ArrayList<byte[]> getHashNgrams2() {
        return hashNgrams2;
    }
}
