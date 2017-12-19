package Validation.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

public class HtmlDocumentStrategy implements IDocumentStrategy {
    @Override
    public String clean(String path) {
        // the file we want to upload
        File inFile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inFile);
            HttpClient httpclient = HttpClientBuilder.create().build();

            // server back-end URL
            HttpPost httppost = new HttpPost("http://ia-project.atwebpages.com/index.php");
            MultipartEntityBuilder entity = MultipartEntityBuilder.create();
            entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // set the file input stream and file name as arguments
            entity.addPart("document", new InputStreamBody(fis, inFile.getName()));
            httppost.setEntity(entity.build());
            // execute the request
            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity, "UTF-16");

            System.out.println("[" + statusCode + "] " + responseString);

        } catch (ClientProtocolException e) {
            System.err.println("Unable to make connection");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {}
        }

        //TODO - Write string to file

        return null;
    }
}
