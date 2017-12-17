package Validation.src;

import java.math.BigInteger;
import java.security.MessageDigest;

public class WordLemaTuple {
    // attributes
    private String word;
    private String lema;

    // constructors
    public WordLemaTuple() {
        this.word = null;
        this.lema = null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public WordLemaTuple(String word, String lema) {
        this.word = word;
        this.lema = lema;
    }

    // methods
    @Override
    public int hashCode() {
        BigInteger bi;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update((this.word + this.lema).getBytes());
            byte[] hash = digest.digest();
            bi = new BigInteger(hash);
        } catch(java.security.NoSuchAlgorithmException ex) {
            bi = new BigInteger("0");
        }
        return bi.intValue();
    }

    @Override
    public String toString() {
        return this.word + " : " + this.lema;
    }
}
