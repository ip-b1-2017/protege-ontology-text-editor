package validation.doc;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ClassNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class DocxDocumentStrategy implements IDocumentStrategy {
    @Override
    public String clean(String path) {
        // TODO implement processing of DOC(X) files and return the resulted string
        String theText = null;
        String surfix;
        surfix = path.substring(path.indexOf('.') + 1);
        File file = new File(path);
        try {

            FileInputStream fis = new FileInputStream(file);
            switch (surfix) {
                case "docx":
                XWPFDocument adoc = new XWPFDocument(fis);
                XWPFWordExtractor xwe = new XWPFWordExtractor(adoc);
                theText = xwe.getText();
                xwe.close();
                default:
                    System.err.println("Invalid file type.");
                    System.exit(0);
            }
            return theText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theText;

    }


/*
    public static void main(String[] args) {
        DocumentStrategyMapping documentMapping = new DocumentStrategyMapping();
        String text= documentMapping.process(DocumentEnum.DOCX, "D:/exemplu_docx.docx");
        System.out.println(text);
        DocxDocumentStrategy extractor = new DocxDocumentStrategy();
        String content = extractor.clean("D:/exemplu_docx.docx");
        System.out.println(content.trim());
    }
*/
}

