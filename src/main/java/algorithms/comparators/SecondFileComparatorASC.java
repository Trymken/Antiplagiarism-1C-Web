package algorithms.comparators;

import struct.Row;

import java.util.Comparator;

public class SecondFileComparatorASC implements Comparator<Row> {
    @Override
    public int compare(Row o1, Row o2) {
        String obj1 = o1.getPath2();
        String obj2 = o2.getPath2();
        return obj1.compareTo(obj2);
    }
}
