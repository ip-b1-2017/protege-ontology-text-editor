package view.DTO;

/**
 * TODO:An word containing the form from the text, the normalized form and the offset
 */
public class Word {
    private String normalizeForm;
    private  String textForm;
    private  int offset;

    @Override
    public String toString() {
        return "Word{" +
                "normalizeForm='" + normalizeForm + '\'' +
                ", textForm='" + textForm + '\'' +
                ", offset=" + offset +
                '}';
    }

    public String getNormalizeForm() {
        return normalizeForm;
    }

    public void setNormalizeForm(String normalizeForm) {
        this.normalizeForm = normalizeForm;
    }

    public String getTextForm() {
        return textForm;
    }

    public void setTextForm(String textForm) {
        this.textForm = textForm;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Word(String normalizeForm, String textForm, int offset) {

        this.normalizeForm = normalizeForm;
        this.textForm = textForm;
        this.offset = offset;
    }

    public Word() {
    }


}
