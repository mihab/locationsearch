package locationsearch.integration.goeuro;

import java.io.UnsupportedEncodingException;
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

    private HttpService httpService;

    /**
     * Ctor that takes API url.
     * 
     * @param apiUrl
     *            GoEuro API url.
     * @param httpService
     *            Http service to use.
     */
    public LocationServiceImpl(String apiUrl, HttpService httpService) {
        this.apiUrl = apiUrl;
        this.httpService = httpService;
    }

    @Override
    public List<Location> findLocations(String query) {
        List<Location> locations = new ArrayList<Location>();
        String queryUrl = null;
        try {
            queryUrl = apiUrl + URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String responseString = httpService.doGet(queryUrl);
        Response response = new Gson().fromJson(responseString, Response.class);
        if (response.getResults() != null) {
            locations.addAll(response.getResults());
        }
        return locations;
    }

}
