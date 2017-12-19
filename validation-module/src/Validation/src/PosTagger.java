package Validation.src;

import org.apache.pdfbox.io.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PosTagger {
    // attributes
    private String text;
    private String xml;
    private List<WordLemaTuple> wordTuples;

    // constructor
    public PosTagger(String text) {
        this.text = text;

        this.xml = postToWebPosRo();

        this.wordTuples = extractFromXml();
    }

    // setters


    public List<WordLemaTuple> getWordTuples() {
        return wordTuples;
    }

    // methods
    private String postToWebPosRo() {
        try {
            String body = "sent=" + URLEncoder.encode(this.text, "UTF-8");
            URL url = new URL("http://nlptools.info.uaic.ro/WebPosRo/PosTaggerRoServlet");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-length", String.valueOf(body.length()));
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(body);
            output.close();

            int code = con.getResponseCode();
            if (code != 200) {
                System.out.println("WebPosRo has returned the HTTP code " + Integer.toString(code));
                return null;
            }

            DataInputStream input = new DataInputStream(con.getInputStream());

            byte[] bytes = IOUtils.toByteArray(input);

            return new String(bytes);
        } catch(java.io.IOException ex) {
            System.out.println("Error occurred while trying to POST to WebPosRo tool.");
            return null;
        }
    }

    private List<WordLemaTuple> extractFromXml() {
        List<WordLemaTuple> tupleList = new ArrayList<>();
        String word;
        String lema;

        int endIndex = 0;
        int startIndex = this.xml.indexOf("LEMMA:\"", endIndex);

        while(startIndex > 0) {
            startIndex += 7;
            endIndex = this.xml.indexOf("\"", startIndex);
            lema = this.xml.substring(startIndex, endIndex);

            startIndex = this.xml.indexOf("class='pretty_word'>", endIndex) + 20;
            endIndex = this.xml.indexOf("</div>", startIndex);
            word = this.xml.substring(startIndex, endIndex);

            tupleList.add(new WordLemaTuple(word, lema));

            startIndex = this.xml.indexOf("LEMMA:\"", endIndex);
        }

        return tupleList;
    }
}
