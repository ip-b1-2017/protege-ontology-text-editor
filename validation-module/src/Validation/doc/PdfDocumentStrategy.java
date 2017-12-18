package Validation.doc;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfDocumentStrategy implements IDocumentStrategy {
    @Override
    public String clean(String path) {
        // TODO implement processing of PDF files and return the resulted string
        //Loading an existing document
        PDDocument document = null;
        String text = null;
        try {

            File file = new File(path);
            document = PDDocument.load(file);

            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            //Retrieving text from PDF document
            text = pdfStripper.getText(document);
        } catch (IOException e) {
            //do smth
        } finally {
            try {
                if (document != null)
                    //Closing the document
                    document.close();
            } catch (IOException e) {
                //do smth
            }

            return text;
        }
    }
    
     public static void main(String[] args) {
        DocumentStrategyMapping documentMapping = new DocumentStrategyMapping();
        String text= documentMapping.process(DocumentEnum.PDF, "D:/randompdf.pdf");
        System.out.println(text);
    }
}
