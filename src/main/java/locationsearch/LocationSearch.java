package locationsearch;

import java.util.List;

import locationsearch.dao.LocationDAO;
import locationsearch.integration.LocationService;

/**
 * Location search application. Queries the location service for a given name
 * and stores it into the location DAO.
 * 
 * @author miha
 * 
 */
public class LocationSearch {

    private LocationService locationService;

    private LocationDAO locationDAO;

    /**
     * Ctor with full initialization.
     * 
     * @param locationService
     *            Location service to retrieve results from.
     * @param locationDAO
     *            Location DAO to store the results.
     */
    public LocationSearch(LocationService locationService, LocationDAO locationDAO) {
        this.locationService = locationService;
        this.locationDAO = locationDAO;
    }

    /**
     * Retrieves a list of locations from the location service and stores them
     * in the DAO.
     * 
     * @param query
     *            Query string to search for.
     */
    public void execute(String query) {
        List<Location> locations = locationService.findLocations(query);
        locationDAO.saveLocations(locations);
    }

}
