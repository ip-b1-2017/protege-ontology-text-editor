package Validation.doc;

import java.util.HashMap;

public class DocumentStrategyMapping {
    private HashMap<DocumentEnum, IDocumentStrategy> documentMap = new HashMap<>();

    DocumentStrategyMapping() {
        documentMap.put(DocumentEnum.PDF, new PdfDocumentStrategy());
        documentMap.put(DocumentEnum.DOCX, new DocxDocumentStrategy());
        documentMap.put(DocumentEnum.HTML, new HtmlDocumentStrategy());
        documentMap.put(DocumentEnum.TXT, new TxtDocumentStrategy());
    }

    public String process(DocumentEnum doc, String path) {
        return documentMap.get(doc).clean(path);
    }
}
