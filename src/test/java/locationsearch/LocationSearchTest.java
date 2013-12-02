package locationsearch;

import java.util.Arrays;
import java.util.List;

import locationsearch.dao.LocationDAO;
import locationsearch.integration.LocationService;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.testng.annotations.Test;

/**
 * Test of the location search class.
 * 
 * @author miha
 * 
 */
@Test
public class LocationSearchTest extends EasyMockSupport {

    /**
     * Class under test.
     */
    private LocationSearch locationSearch;

    /**
     * Test of the execute method.
     */
    public void testExecute() {
        LocationService locationService = createMock(LocationService.class);
        LocationDAO locationDAO = createMock(LocationDAO.class);
        locationSearch = new LocationSearch(locationService, locationDAO);
        Location location = new Location();
        location.set_id(1);
        List<Location> locations = Arrays.asList(location);
        resetAllToStrict();
        // expect
        EasyMock.expect(locationService.findLocations("123")).andReturn(locations);
        locationDAO.saveLocations(EasyMock.same(locations));
        replayAll();
        // execute
        locationSearch.execute("123");
        // verify
        verifyAll();
    }
}
