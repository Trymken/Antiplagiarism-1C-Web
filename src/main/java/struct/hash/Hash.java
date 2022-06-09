
package struct.hash;
import java.security.NoSuchAlgorithmException;


public interface Hash {
    byte[] getBytes();
    void encode(String s) throws NoSuchAlgorithmException;

    static Hash getHashAlgorithm(String s) {
        s = s.toLowerCase();
        return switch (s) {
            case "crc32" -> new CRC_32();
            case "md5" -> new MD_5();
            case "sha-1" -> new SHA_1();
            case "sha-256" -> new SHA_256();
            default -> null;
        };
    }
}
