package struct;


import java.util.Arrays;
import java.util.zip.CRC32;

import static algorithms.Algorithms.longToBytes;

public class CRC_32 implements Hash {
    private byte[] bytes;

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CRC_32 crc_32 = (CRC_32) o;

        return Arrays.equals(bytes, crc_32.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public void encode(String s) {
        CRC32 crc32 = new CRC32();
        crc32.update(s.getBytes());
        this.bytes = longToBytes(crc32.getValue());
    }

}
