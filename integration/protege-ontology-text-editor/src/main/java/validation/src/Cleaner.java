package validation.src;

public class Cleaner {
    private String text;
    private String cleanedText;

    Cleaner(String text) {
        this.text = text;
        clean();
    }

    private void clean() {
        cleanedText = "";
        if (!this.text.isEmpty()) {
            for (int i = 0; i < this.text.length(); i++) {
                String a_letter = Character.toString(this.text.charAt(i));
                switch (a_letter) {
                    case "\u0163":
                        cleanedText += "\u021B";
                        break;
                    case "\u0162":
                        cleanedText += "\u021A";
                        break;
                    case "\u015E":
                        cleanedText += "\u0218";
                        break;
                    case "\u015F":
                        cleanedText += "\u0219";
                        break;
                    default:
                        cleanedText += this.text.charAt(i);
                        break;
                }
            }
        }
    }

    public String getCleanedText() {
        return cleanedText;
    }
}
