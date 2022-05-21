package algorithms.comparators;

import struct.Row;

import java.util.Comparator;

public class IndexComparatorASC implements Comparator<Row> {
    @Override
    public int compare(Row o1, Row o2) {
        return Integer.compare(o1.getIndex(), o2.getIndex());
    }
}
