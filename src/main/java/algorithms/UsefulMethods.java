package algorithms;


import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import struct.Form;
import struct.hash.Hash;
import struct.Row;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static algorithms.LevenshteinDistance.levenshteinScore;
import static algorithms.XmlParser.getUuidFromXml;


public class UsefulMethods {

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
                                        Form form,
                                        int index1,
                                        int index2) throws IOException, NoSuchAlgorithmException {
        Start start;
        for (int i = 0; i < filesPath1.size(); i++) {
            File file1 = new File(filesPath1.get(i));
            for (int j = 0; j < filesPath2.size(); j++) {
                File file2 = new File(filesPath2.get(j));

                start = new Start(file1.getAbsolutePath(), file2.getAbsolutePath(), "sha-1",
                        form.getNgramLength(), form.getWindowLength());

                if(!form.isShowZeroScore())
                    if(start.getWinnowing().getScore() == 0)
                        return;

                String filenames = file1.getName() + '\n' +
                        file2.getName() + '\n' +
                        file1.getAbsolutePath() + '\n' +
                        file2.getAbsolutePath() + '\n';

                String resultPath = createResultDir(uploadDir, index1, index2, i, file1, j, file2);

                if(start.getIntervals1() != null) {
                    List<String> content1 = Files.readAllLines(Path.of(file1.getPath()));
                    List<String> content2 = Files.readAllLines(Path.of(file2.getPath()));

                    String result1, result2;
                    ArrayList<int[]> list1, list2;

                    list1 = start.getIntervals1();
                    list2 = start.getIntervals2();

                    result1 = highlightText(content1, list1);
                    result2 = highlightText(content2, list2);

                    writeToFiles(form, start, filenames, resultPath, result1, result2);

                } else {
                    String content1 = Files.readString(Path.of(file1.getAbsolutePath()));
                    String content2 = Files.readString(Path.of(file2.getAbsolutePath()));

                    writeToFiles(form, start, filenames, resultPath, content1, content2);
                }
            }
        }
    }

    private static String createResultDir(String uploadDir, int index1, int index2, int i, File file1, int j, File file2) {
        String resultPath = uploadDir + '\\' + "Results";

        new File(resultPath).mkdir();
        resultPath = resultPath + '\\' + index1 + '_' + index2;
        new File(resultPath).mkdir();
        resultPath = resultPath + '\\' + file1.getName() + '_' + file2.getName() + '_' + i + '_' + j;
        new File(resultPath).mkdir();

        return resultPath;
    }

    private static void writeToFiles(Form form, Start start, String filenames,
                                     String resultPath,
                                     String content1, String content2) throws NoSuchAlgorithmException, IOException {

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

        writeToFiles(resultPath, filenames, content1, content2, score);
    }


    private static void writeToFiles(String path, String filenames, String content1, String content2, String score)
            throws IOException {

        Files.write(Path.of(path + '\\' + "filenames.txt"), filenames.getBytes());
        Files.write(Path.of(path + '\\' + "1.txt"), content1.getBytes());
        Files.write(Path.of(path + '\\' + "2.txt"), content2.getBytes());
        Files.write(Path.of(path + '\\' + "score.txt"), score.getBytes());
    }


    public static void cartesianProductXml(ArrayList<String> filesPath1,
                                           ArrayList<String> filesPath2,
                                           String uploadDir,
                                           Form form,
                                           int index1,
                                           int index2)
            throws ParserConfigurationException, IOException, SAXException {


        for (int i = 0; i < filesPath1.size(); i++) {
            File file1 = new File(filesPath1.get(i));
            for (int j = 0; j < filesPath2.size(); j++) {
                File file2 = new File(filesPath2.get(j));

                String filenames = file1.getName() + '\n' +
                        file2.getName() + '\n' +
                        file1.getAbsolutePath() + '\n' +
                        file2.getAbsolutePath() + '\n';

                String uuid1 = getUuidFromXml(filesPath1.get(i));
                String uuid2 = getUuidFromXml(filesPath2.get(j));

                String score = "0.0";
                if (uuid1 != null && uuid2 != null){
                    if (uuid1.equals(uuid2)){
                        score = "100.0";
                    } else if (!form.isShowZeroScore()) return;
                } else {
                    if (!form.isShowZeroScore()) return;
                    score += '\n' + "Uuid not found";
                }

                String resultPath = createResultDir(uploadDir, index1, index2, i, file1, j, file2);

                String content1 = Files.readString(Path.of(file1.getAbsolutePath()));
                String content2 = Files.readString(Path.of(file2.getAbsolutePath()));

                content1 = replaceBracketsXml(content1);
                content2 = replaceBracketsXml(content2);

                writeToFiles(resultPath, filenames, content1, content2, score);
            }
        }
    }

    private static String replaceBracketsXml(String content){
        String temp;
        temp = content.replaceAll("<", "&lt;");
        return temp.replaceAll(">", "&gt;");
    }

    private static void replaceBracketsXml(List<String> list){
        for (int k = 0; k < list.size(); k++) {
            list.set(k, list.get(k).replaceAll("<", "&lt;"));
            list.set(k, list.get(k).replaceAll(">", "&gt;"));
        }
    }

    public static Row getResultTableRow(String path, String dir, int counter) throws IOException {
        List<String> score = Files.readAllLines(Path.of(path + '\\' + dir + '\\' + "score.txt"));
        List<String> filenames =
                Files.readAllLines(Path.of(path + '\\' + dir + '\\' + "filenames.txt"));
        return new Row(filenames.get(0), filenames.get(1), score, counter);
   }

    public static Row getTableRow(String path, String dir, int counter) throws IOException {
        List<String> filenames = Files.readAllLines(Path.of(path + '\\' + dir + '\\' + "archivenames.txt"));
        return new Row(filenames.get(0), filenames.get(1), null, counter);
    }

   public static String getDirNameByID(String path, int id) {
       File root = new File(path);
       File[] list = root.listFiles();
       int counter = 1;
       if (list == null) return "";

       for (File f : list) {
           if (f.isDirectory()) {
               if (counter == id) {
                   return f.getName();
               }
               counter++;
           }
       }
       return "";
   }

   public static boolean isValidExtension(MultipartFile[] files, ArrayList<String> extensions){
       for (MultipartFile file : files) {
           String filename = file.getOriginalFilename();
           if (filename == null) {
               return false;
           }
           if (!extensions.contains(getFileExtension(filename)))
               return false;
       }
       return true;
    }

    public static void createDirectories(int length1, String uploadDir){
        for (int i = 0; i < length1; i++) {
            new File(uploadDir + '\\' + i).mkdir();
        }
    }

    public static void unzipMultipartFiles(MultipartFile[] files1, String uploadDir)
            throws IOException {

        UnZip unZip = new UnZip();
        String filename, dir;
        InputStream is;
        for (int i = 0; i < files1.length; i++) {
            filename = files1[i].getOriginalFilename();
            is = files1[i].getInputStream();

            dir = uploadDir + '\\' + i;
            Path path = Paths.get(dir, filename);
            unZip.unZipFile(is, path.toString());
        }
    }

    public static ArrayList<Row> getTableRows(File[] list, String dir, boolean flag) throws IOException {
        ArrayList<Row> rows = new ArrayList<>();
        int counter = 1;

        for (File f : list) {
            if (f.isDirectory()) {
                if(flag)
                    rows.add(getTableRow(dir, f.getName(), counter));
                else
                    rows.add(getResultTableRow(dir, f.getName(), counter));
                counter++;
            }
        }
        return rows;
    }
}
