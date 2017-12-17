package Validation.doc;

public class Document {
    private String path;
    private DocumentStrategyMapping doc;
    private DocumentEnum type;

    public Document(String path) {
        doc = new DocumentStrategyMapping();
        this.path = path;

        // TODO consider for now that the document is local and verify it's existence

        // TODO check what type of document is and make the assignation
        type = DocumentEnum.PDF;
        type = DocumentEnum.HTML;
        type = DocumentEnum.DOCX;
    }

    public String getText() {
        return doc.process(type, path);
    }
}
