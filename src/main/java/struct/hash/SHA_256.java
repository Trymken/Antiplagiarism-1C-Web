package struct.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA_256 implements Hash{
    private byte[] bytes;

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public void encode(String s) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        this.bytes = sha256.digest(s.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SHA_256 sha_256 = (SHA_256) o;

        return Arrays.equals(bytes, sha_256.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }
}
