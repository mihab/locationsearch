package locationsearch.integration;

import java.util.List;

import locationsearch.Location;

/**
 * Service for retrieving locations.
 * 
 * @author miha
 * 
 */
public interface LocationService {

    /**
     * Retrieve a list of locations.
     * 
     * @param query
     *            Location name to search for.
     * @return List of location (can be empty, but never null).
     */
    List<Location> findLocations(String query);

}
