package temp;

/**
 * Created by giosa on 18-Dec-17.
 */
public class LemaWord {

    private String word;
    private String lema;

    public LemaWord(String word){
        this.word = word;
        lema = word;
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
}
