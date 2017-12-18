package Validation.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PosTagger {
    // attributes
    private String text;
    private String xml;
    private List<WordLemaTuple> tupleList;

    // constructor
    public PosTagger(String text) {
        this.text = text;
        xml = postToWebPosRo();
        System.out.println(xml);
    }

    // methods
    String postToWebPosRo() {
        try {
            URL url = new URL("http://nlptools.info.uaic.ro/WebPosRo/PosTaggerRoServlet");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-length", String.valueOf(this.text.length()));

            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(this.text);
            output.close();

            int code = con.getResponseCode();
            if (code != 200) {
                System.out.println("WebPosRo has returned the HTTP code " + Integer.toString(code));
                return null;
            }

            DataInputStream input = new DataInputStream(con.getInputStream());
            int c;
            StringBuilder resultBuf = new StringBuilder();
            while ((c = input.read()) != -1) {
                resultBuf.append((char) c);
            }
            input.close();

            return resultBuf.toString();
        } catch(java.io.IOException ex) {
            System.out.println("Error occurred while trying to POST to WebPosRo tool.");
            return null;
        }
    }
}
