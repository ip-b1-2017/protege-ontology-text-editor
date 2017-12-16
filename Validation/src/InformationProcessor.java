package Validation.src;

import Validation.doc.Document;

import java.util.List;

public class InformationProcessor {
    // attributes
    final private int SLICE_SIZE = 1000;
    private String path;
    private String extractedText;
    private String cleanedText;
    private List<WordLemaTuple> wordTuples;

    // constructor/s
    public InformationProcessor(String path) {
        this.path = path;

        Document doc = new Document(path);
        this.extractedText = doc.getText();

        Cleaner cleaner = new Cleaner(this.extractedText);
        this.cleanedText = cleaner.getCleanedText();

        // TODO study WebPosRo API
    }

    // methods

}
