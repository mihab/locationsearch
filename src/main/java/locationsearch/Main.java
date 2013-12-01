package locationsearch;

import locationsearch.dao.LocationDAO;
import locationsearch.dao.csv.LocationDAOImpl;
import locationsearch.integration.LocationService;
import locationsearch.integration.goeuro.LocationServiceImpl;

/**
 * Entry point for go euro location service.
 * 
 * @author miha
 * 
 */
public class Main {

    private static final String GO_EURO_API_URL = "http://pre.dev.goeuro.de:12345/api/v1/suggest/position/en/name/";

    /**
     * Main method, takes query string to search for as argument.
     * 
     * @param args
     *            First argument contains query string.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar GoEuroTest.jar \"STRING\"");
            return;
        }
        String query = args[0];
        LocationService locationService = new LocationServiceImpl(GO_EURO_API_URL);
        LocationDAO locationDAO = new LocationDAOImpl("results.csv");
        LocationSearch locationSearch = new LocationSearch(locationService, locationDAO);
        locationSearch.execute(query);
    }

}
