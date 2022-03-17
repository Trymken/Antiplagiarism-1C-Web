package struct;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD_5 implements Hash{
    private byte[] bytes;

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public void encode(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        this.bytes = md5.digest(s.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MD_5 md_5 = (MD_5) o;

        return Arrays.equals(bytes, md_5.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }
}
