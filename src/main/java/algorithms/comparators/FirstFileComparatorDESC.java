package algorithms.comparators;

import struct.Row;

import java.util.Comparator;

public class FirstFileComparatorDESC implements Comparator<Row> {
    @Override
    public int compare(Row o1, Row o2) {
        String obj1 = o1.getPath1();
        String obj2 = o2.getPath1();
        return -obj1.compareTo(obj2);
    }
}
