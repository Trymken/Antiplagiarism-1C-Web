package algorithms;

import struct.Hash;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class Algorithms {

    public static boolean contains(byte[] b, byte[][] bytes){
        boolean flag;
        for (int i = 0; i < bytes.length; i++){
            flag = true;
            for (int j = 0; j < bytes[0].length; j++) {
                if (b[j] != bytes[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }


    public static byte[][] generateHash(String s, String algorithm, int MODULE, boolean USE_MODULE, int shingleLength) throws NoSuchAlgorithmException {
        return generateHash(s, Hash.getHashAlgo(algorithm), MODULE, USE_MODULE, shingleLength);
    }


    private static byte[][] generateHash(String s, Hash sequence, int MODULE, boolean USE_MODULE, int shingleLength) throws NoSuchAlgorithmException {
        byte[][] arrayList = new byte[s.length() - shingleLength + 1][];

        for (int i = 0; i < arrayList.length; i++) {

            String temp = s.substring(i, i + shingleLength);
            sequence.encode(temp);

            if (USE_MODULE) {
                if (bytesToLong(sequence.getBytes()) % MODULE == 0)
                    arrayList[i] = sequence.getBytes();
            } else {
                arrayList[i] = sequence.getBytes();
            }
        }
        return arrayList;
    }


    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        buffer.reset();
        return buffer.getLong();
    }


    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }


    public static byte[] hashing(String s, String algorithm) throws NoSuchAlgorithmException {
      return hashing(s, Hash.getHashAlgo(algorithm));
    }

    public static byte[] hashing(String s, Hash algo) throws NoSuchAlgorithmException {
        algo.encode(s);
        return algo.getBytes();
    }

    public static boolean lessOrEquals(byte[] b1, byte[] b2){
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] > b2[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean greaterOrEquals(byte[] b1, byte[] b2){
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] < b2[i]){
                return false;
            }
        }
        return true;
    }

    public static String highlightText(List<String> strings, ArrayList<int[]> list){
        String result = "";

        for (int[] a : list) {
            for (int i = a[0]; i <= a[1]; i++) {
                strings.set(i - 1, "<span id=\"redflag\">" + strings.get(i - 1) + "</span>");
            }
        }

        for (String s: strings) {
            result += s + '\n';
        }

        return result;
    }
}
