package validation.src;

import validation.doc.Document;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class InformationProcessor {
    // attributes
    final private int SLICE_SIZE = 1000;
    private int current;
    private int listSize;

    private String path;
    private String extractedText;
    private String cleanedText;
    private List<WordLemaTuple> wordTuples;

    // constructor/s
    public InformationProcessor(String path) {
        this.path = path;

        Document doc = new Document(this.path);
        this.extractedText = doc.getText();

        Cleaner cleaner = new Cleaner(this.extractedText);
        this.cleanedText = cleaner.getCleanedText();

        PosTagger tagger = new PosTagger(this.cleanedText);
        this.wordTuples = tagger.getWordTuples();

        current = 0;
        listSize = this.wordTuples.size();
    }

    // methods
    public List<WordLemaTuple> getWordLemaTuples() {
        if(current >= listSize) {
            return null;
        }

        int startIndex = current;
        int endIndex;
        if(current + SLICE_SIZE > listSize) {
            endIndex = listSize;
        }
        else {
            endIndex = current + SLICE_SIZE;
        }

        current = endIndex;

        return new ArrayList<>(this.wordTuples.subList(startIndex, endIndex));
    }
}
