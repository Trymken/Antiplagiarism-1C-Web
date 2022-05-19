package algorithms;

import struct.Row;

import java.util.Comparator;

public class RowComparator implements Comparator<Row> {

    @Override
    public int compare(Row o1, Row o2) {
        return -Double.compare(Double.parseDouble(o1.getScore()), Double.parseDouble(o2.getScore()));
    }
}
