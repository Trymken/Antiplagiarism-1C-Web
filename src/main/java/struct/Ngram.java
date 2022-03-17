package struct;

import java.util.Arrays;

public class Ngram {

    private byte[] hash;
    private int position;

    public Ngram(){
        this.hash = new byte[0];
        this.position = 0;
    }

    public Ngram(byte[] hash, int position, int length){
        this.hash = hash;
        this.position = position;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ngram that = (Ngram) o;

        return Arrays.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(hash);
        result = 31 * result + position;
        return result;
    }
}
