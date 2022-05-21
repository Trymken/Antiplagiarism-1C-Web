package algorithms.comparators;

import struct.Row;

import java.util.Comparator;

public class ScoreComparatorASC implements Comparator<Row> {
    @Override
    public int compare(Row o1, Row o2) {
        return Double.compare(Double.parseDouble(o1.getScore().get(0)), Double.parseDouble(o2.getScore().get(0)));
    }
}
