package validation.doc;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfDocumentStrategy implements IDocumentStrategy {
    @Override
    public String clean(String path) {
        // TODO implement processing of PDF files and return the resulted string

        PDDocument document = null;
        String text = null;
        try {

            File file = new File(path);
            document = PDDocument.load(file);

            PDFTextStripper pdfStripper = new PDFTextStripper();

            text = pdfStripper.getText(document);
        } catch (IOException e) {
            System.out.println("Retrieving text from PDF doesn't work");
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
            } catch (IOException e) {
                System.out.println("Document doesn't exist");
            }

            return text.replaceAll("[^A-Za-z0-9.,\\p{L}\\s]+", "");
        }
    }
}
