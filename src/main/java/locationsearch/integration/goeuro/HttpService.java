package locationsearch.integration.goeuro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Http service to execute http request.
 * 
 * @author miha
 * 
 */
public class HttpService {

    /**
     * Executes a GET request to a URL.
     * 
     * @param queryUrl
     *            URL to get.
     * @return Response body.
     */
    public String doGet(String queryUrl) {
        BufferedReader br = null;
        try {
            URL url = new URL(queryUrl);
            URLConnection urlConnection;
            urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            char[] arr = new char[1024];
            int bytesRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((bytesRead = br.read(arr)) != -1) {
                sb.append(arr, 0, bytesRead);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
