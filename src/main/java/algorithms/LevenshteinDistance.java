package algorithms;

import java.util.Objects;

public class LevenshteinDistance {
    public static int levenshteinDistance(String s1, String s2, int replCost, int delCost, int insCost){

        if(s1.length() == 0 && s2.length() == 0){
            return 0;
        } else if (s1.length() > 0 && s2.length() == 0){
            return  s1.length();
        } else if (s2.length() > 0 && s1.length() == 0){
            return s2.length();
        }

        int[] row1 = new int[s1.length() + 1];
        int[] row2 = new int[s1.length() + 1];
        int[] temp;

        for (int i = 0; i < s1.length() + 1; i++) {
            row1[i] = i * insCost;
        }

        for (int i = 1; i < s2.length() + 1; ++i){
            for (int j = 0; j < s1.length() + 1; j++) {
                if (j == 0) {
                    row2[j] = i * delCost;
                }
                else if(Objects.equals(s2.charAt(i - 1), s1.charAt(j - 1))){
                    row2[j] = row1[j - 1];
                } else {
                    row2[j] = Math.min(Math.min(
                                    row2[j - 1] + insCost,
                                    row1[j] + delCost),
                            row1[j - 1] + replCost);
                }
            }
            temp = row1;
            row1 = row2;
            row2 = temp;
        }
        return row1[row1.length - 1];
    }


    public static int levenshteinDistance(String s1, String s2){
        return levenshteinDistance(s1, s2, 1, 1, 1);
    }


    public static int levenshteinDistance(String s1, String s2, int replCost){
        return levenshteinDistance(s1, s2, replCost, 1, 1);
    }


    public static int levenshteinDistance(String s1, String s2, int replCost, int delCost){
        return levenshteinDistance(s1, s2, replCost, delCost, 1);
    }


    public static double levenshteinScore(String s1, String s2){
        int a = levenshteinDistance(s1, s2);
        return (1 - (a * 1.0 / Math.max(s1.length(), s2.length()))) * 100;
    }

    public static void main(String[] args) {
        System.out.println(levenshteinDistance("Счастье", "Солнце"));
    }
}