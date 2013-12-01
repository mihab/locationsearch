package locationsearch.dao;

import java.util.List;

import locationsearch.Location;

/**
 * Location data access object.
 * 
 * @author miha
 * 
 */
public interface LocationDAO {

    /**
     * Save locations.
     * 
     * @param locations
     *            List of locations to serve.
     */
    void saveLocations(List<Location> locations);

}
