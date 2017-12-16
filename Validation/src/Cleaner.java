package Validation.src;

public class Cleaner {
    private String text;
    private String cleanedText;

    Cleaner(String text) {
        this.text = text;
        clean();
    }

    private void clean() {
        // TODO make a general clean over text and assignate it to cleanedText variable
    }

    public String getCleanedText() {
        return cleanedText;
    }
}
