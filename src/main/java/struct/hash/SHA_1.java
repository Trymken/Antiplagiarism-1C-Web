package struct.hash;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA_1 implements Hash{
    private byte[] bytes;

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public void encode(String s) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        this.bytes = sha1.digest(s.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SHA_1 sha_1 = (SHA_1) o;

        return Arrays.equals(bytes, sha_1.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }
}
