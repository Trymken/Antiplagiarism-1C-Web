package algorithms;


import struct.Form;
import struct.hash.Hash;
import struct.Row;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static algorithms.LevenshteinDistance.levenshteinScore;


public class Algorithms {

    public static boolean contains(byte[] b, ArrayList<byte[]> bytes){
        boolean flag;
        for (int i = 0; i < bytes.size(); i++){
            flag = true;
            for (int j = 0; j < bytes.get(0).length; j++) {
                if (b[j] != bytes.get(i)[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }


    public static ArrayList<byte[]> generateHash(String s, String algorithm, int MODULE, boolean USE_MODULE, int shingleLength)
            throws NoSuchAlgorithmException {
        return generateHash(s, Hash.getHashAlgo(algorithm), MODULE, USE_MODULE, shingleLength);
    }


    private static ArrayList<byte[]> generateHash(String s, Hash sequence, int MODULE, boolean USE_MODULE, int shingleLength)
            throws NoSuchAlgorithmException {
        ArrayList<byte[]> arrayList = new ArrayList<>();
        int length = s.length() - shingleLength + 1;

        for (int i = 0; i < length; i++) {
            String temp = s.substring(i, i + shingleLength);
            sequence.encode(temp);

            if (USE_MODULE) {
                if (bytesToLongModule(sequence.getBytes(), MODULE))
                    arrayList.add(sequence.getBytes());
            } else {
                arrayList.add(sequence.getBytes());
            }
        }
        return arrayList;
    }


    public static boolean bytesToLongModule(byte[] bytes, int MODULE) {
        BigInteger b = new BigInteger(bytes);
        b = b.mod(BigInteger.valueOf(MODULE));
        return b.equals(BigInteger.valueOf(0));
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
        StringBuilder result = new StringBuilder();

        for (int[] a : list) {
            for (int i = a[0]; i <= a[1]; i++) {
                strings.set(i - 1, "<span class=\"redflag\">" + strings.get(i - 1) + "</span>");
            }
        }

        for (String s: strings) {
            result.append(s).append('\n');
        }

        return result.toString();
    }


    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i+1);
        }
        return "";
    }


    public static void cartesianProduct(ArrayList<String> filesPath1,
                                        ArrayList<String> filesPath2,
                                        String uploadDir,
                                        boolean isXML,
                                        Form form) throws IOException, NoSuchAlgorithmException {
        Start start;
        for (int i = 0; i < filesPath1.size(); i++) {
            File file1 = new File(filesPath1.get(i));
            for (int j = 0; j < filesPath2.size(); j++) {
                File file2 = new File(filesPath2.get(j));

                start = new Start(file1.getAbsolutePath(), file2.getAbsolutePath(), "sha-1", isXML,
                        form.getNgramLength(), form.getWindowLength());

                String filenames = file1.getName() + '\n' +
                        file2.getName() + '\n' +
                        file1.getAbsolutePath() + '\n' +
                        file2.getAbsolutePath() + '\n';

                if(start.getIntervals1() != null) {
                    List<String> content1 = Files.readAllLines(Path.of(file1.getPath()));
                    List<String> content2 = Files.readAllLines(Path.of(file2.getPath()));


                    String result1, result2;
                    ArrayList<int[]> list1, list2;

                    list1 = start.getIntervals1();
                    list2 = start.getIntervals2();

                    for (int k = 0; k < content1.size(); k++) {
                        content1.set(k, content1.get(k).replaceAll("<", "&lt;"));
                        content1.set(k, content1.get(k).replaceAll(">", "&gt;"));
                    }

                    for (int k = 0; k < content2.size(); k++) {
                        content2.set(k, content2.get(k).replaceAll("<", "&lt;"));
                        content2.set(k, content2.get(k).replaceAll(">", "&gt;"));
                    }

                    result1 = highlightText(content1, list1);
                    result2 = highlightText(content2, list2);

                    String resultPath = uploadDir + '\\' + "Results";

                    new File(resultPath).mkdir();
                    resultPath += '\\' + file1.getName() + '_' + file2.getName() + '_' + i + '_' + j;
                    new File(resultPath).mkdir();

                    String score = String.valueOf(start.getWinnowing().getScore()) + '\n';
                    if(form.isLevenshtein()){
                        double levenScore = levenshteinScore(start.getS1(), start.getS2());
                        score += "Levenshtein distance: " + levenScore + '\n';
                    }

                    if(form.isShingles()){
                        double shingleScore = new Shingle(start.getS1(), start.getS2(), "sha-1",
                                form.getShingleLen(),form.getModuleVal(), form.isModule()).getScore();
                        score += "Shingle algorithm: " + shingleScore + '\n';
                    }


                    Files.write(Path.of(resultPath + '\\' + "filenames.txt"), filenames.getBytes());
                    Files.write(Path.of(resultPath + '\\' + "1.txt"), result1.getBytes());
                    Files.write(Path.of(resultPath + '\\' + "2.txt"), result2.getBytes());
                    Files.write(Path.of(resultPath + '\\' + "score.txt"),score.getBytes());

                } else {
                    String resultPath = uploadDir + '\\' + "Results";

                    new File(resultPath).mkdir();
                    resultPath += '\\' + file1.getName() + '_' + file2.getName() + '_' + i + '_' + j;
                    new File(resultPath).mkdir();

                    String content1 = Files.readString(Path.of(file1.getAbsolutePath()));
                    String content2 = Files.readString(Path.of(file2.getAbsolutePath()));

                    //TODO разобраться с этим ужасом!

                    String score = String.valueOf(start.getWinnowing().getScore()) + '\n';
                    if(form.isLevenshtein()){
                        double levenScore = levenshteinScore(start.getS1(), start.getS2());
                        score += "Levenshtein distance: " + levenScore + '\n';
                    }

                    if(form.isShingles()){
                        double shingleScore = new Shingle(start.getS1(), start.getS2(), "sha-1",
                                form.getShingleLen(),form.getModuleVal(), form.isModule()).getScore();
                        score += "Shingle algorithm: " + shingleScore + '\n';
                    }

                    Files.write(Path.of(resultPath + '\\' + "filenames.txt"), filenames.getBytes());
                    Files.write(Path.of(resultPath + '\\' + "1.txt"), content1.getBytes());
                    Files.write(Path.of(resultPath + '\\' + "2.txt"), content2.getBytes());
                    Files.write(Path.of(resultPath + '\\' + "score.txt"), score.getBytes());
                }
            }
        }
    }


   public static Row getTableRow(String path, String dir, int counter) throws IOException {
        List<String> score = Files.readAllLines(Path.of(path + '\\' + "Results" + '\\' + dir + '\\' + "score.txt"));
        List<String> filenames =
                Files.readAllLines(Path.of(path + '\\' + "Results" + '\\' + dir + '\\' + "filenames.txt"));
        return new Row(filenames.get(0), filenames.get(1), score, counter);
   }


   public static ArrayList<String> getFilesPath(String path, String uuid, int id) throws IOException {
       ArrayList<String> paths = new ArrayList<>();
       File root = new File(path + '\\' + "Results");
       File[] list = root.listFiles();
       int counter = 1;
       if (list == null) return paths;

       for ( File f : list ) {
           if (f.isDirectory()) {
               if (counter == id) {
                   List<String> filenames =
                           Files.readAllLines(Path.of(path + '\\'
                                   + "Results" + '\\' + f.getName() + '\\' + "filenames.txt"));
                   paths.add(filenames.get(2).split(uuid)[1].substring(3));
                   paths.add(filenames.get(3).split(uuid)[1].substring(3));
                   paths.add(f.getName());
                   return paths;
               }
               counter++;
           }
       }
       return paths;
   }
}
