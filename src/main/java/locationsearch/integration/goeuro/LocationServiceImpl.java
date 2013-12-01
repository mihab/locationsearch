package locationsearch.integration.goeuro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import locationsearch.Location;
import locationsearch.integration.LocationService;

import com.google.gson.Gson;

/**
 * Go euro location service implementation.
 * 
 * @author miha
 * 
 */
public class LocationServiceImpl implements LocationService {

    private String apiUrl;

    /**
     * Ctor that takes API url.
     * 
     * @param apiUrl
     *            GoEuro API url.
     */
    public LocationServiceImpl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public List<Location> findLocations(String query) {
        List<Location> locations = new ArrayList<Location>();
        try {
            String queryUrl = apiUrl + URLEncoder.encode(query, "UTF-8");
            URL url = new URL(queryUrl);
            URLConnection urlConnection;
            urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            char[] arr = new char[1024];
            int bytesRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((bytesRead = br.read(arr)) != -1) {
                sb.append(arr);
            }
            br.close();
            Response response = new Gson().fromJson(sb.toString(), Response.class);
            if (response.getResults() != null) {
                locations.addAll(response.getResults());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }

}
