package Validation.doc;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfDocumentStrategy implements IDocumentStrategy {
    @Override
    public String clean(String path) {

        File file = new File(path);
        PDDocument document = PDDocument.load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        System.out.println(text);

        document.close();

        return text;
    }
}
