package Validation.doc;

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
import java.lang.*;
import java.util.regex.*;

public class DocxDocumentStrategy implements IDocumentStrategy {
    @Override
    public String clean(String path) {
        // TODO implement processing of DOC(X) files and return the resulted string
        String theText = null;
        String surfix;
        surfix = path.substring(path.indexOf('.') + 1);
        try {

            File file = new File(path);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            if("doc".equals(surfix))
            {
                WordExtractor aExtractor = new WordExtractor(fis);
                theText = aExtractor.getText();
                fis.close();
            }
            else if("docx".equals(surfix))
            {
                XWPFDocument aXWPFDocument = new XWPFDocument(fis);
                XWPFWordExtractor aXWPFExtractor = new XWPFWordExtractor(aXWPFDocument);
                theText = aXWPFExtractor.getText();
                fis.close();

            }
            else
            {
                System.err.println("Invalid file type. This is for doc and docx files");
                System.exit(0);
            }

        } catch (Exception e) {

            System.out.println("Eroare : " + e.toString());

        }finally {

            theText = theText.replaceAll("\\d","");
            return theText.replaceAll("[^ăîâșşțţĂÎÂȘȚŞŢ\\w]+", " ");

            }

    }
}

