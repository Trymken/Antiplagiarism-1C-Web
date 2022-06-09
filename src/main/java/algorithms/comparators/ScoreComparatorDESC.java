package algorithms.comparators;

import struct.Row;

import java.util.Comparator;

public class ScoreComparatorDESC implements Comparator<Row> {

    @Override
    public int compare(Row o1, Row o2) {
        double x1, x2;
        try {
            x1 =  Double.parseDouble(o1.getScore().get(0));
        } catch (Exception e){
            x1 = 0.0;
        }
        try {
            x2 = Double.parseDouble(o2.getScore().get(0));
        } catch (Exception e){
            x2 = 0.0;
        }
        return -Double.compare(x1, x2);
    }
}
