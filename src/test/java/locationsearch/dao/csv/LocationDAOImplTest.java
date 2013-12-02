package locationsearch.dao.csv;

import java.util.ArrayList;
import java.util.Arrays;

import locationsearch.GeoPosition;
import locationsearch.Location;
import locationsearch.dao.LocationDAO;

import org.easymock.EasyMockSupport;
import org.testng.annotations.Test;

/**
 * Test of the Location DAO CSV implementation.
 * 
 * @author miha
 * 
 */
@Test
public class LocationDAOImplTest extends EasyMockSupport {

    /**
     * Class to test.
     */
    private LocationDAO locationDAO;

    /**
     * Test of saving locations with an empty locations list.
     */
    public void testSaveLocationsEmpty() {
        StringFileWriter sfw = createMock(StringFileWriter.class);
        locationDAO = new LocationDAOImpl("file.csv", sfw);
        resetAllToStrict();
        replayAll();
        locationDAO.saveLocations(new ArrayList<Location>());
        verifyAll();
    }

    /**
     * Test of saving location to a file.
     */
    public void testSaveLocations() {
        StringFileWriter sfw = createMock(StringFileWriter.class);
        locationDAO = new LocationDAOImpl("file.csv", sfw);
        resetAllToStrict();
        Location location = new Location();
        location.set_type("Position");
        location.set_id(123);
        location.setName("Potsdam, USA");
        location.setType("Location");
        GeoPosition geo_position = new GeoPosition();
        geo_position.setLatitude(1.1);
        geo_position.setLongitude(2.2);
        location.setGeo_position(geo_position);
        sfw.write("Position,123,\"Potsdam, USA\",Location,1.1,2.2" + System.lineSeparator(), "file.csv");
        replayAll();
        locationDAO.saveLocations(Arrays.asList(location));
        verifyAll();
    }

}
