package validation.doc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Document {
    private String path;
    private DocumentStrategyMapping doc;
    private DocumentEnum type;

    public Document(String path) {
        doc = new DocumentStrategyMapping();
        this.path = path;

        Path file = Paths.get(path);
        if (Files.exists(file)) {
            File filePath = new File(this.path);
            String fileName = filePath.getName();
            if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                switch (fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase()) {
                    case "PDF":
                        type = DocumentEnum.PDF;
                        break;
                    case "HTML":
                        type = DocumentEnum.HTML;
                        break;
                    case "DOCX":
                        type = DocumentEnum.DOCX;
                        break;
                    case "TXT":
                        type = DocumentEnum.TXT;
                        break;
                }
            }
        }
    }

    public String getText() {
        return doc.process(type, path);
    }
}
