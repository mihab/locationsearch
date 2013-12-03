package locationsearch.integration.goeuro;

import java.util.List;

import locationsearch.Location;
import locationsearch.integration.LocationService;
import locationsearch.integration.goeuro.HttpService;
import locationsearch.integration.goeuro.LocationServiceImpl;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test of the GoEuro location service implementation.
 * 
 * @author miha
 * 
 */
@Test
public class LocationServiceImplTest extends EasyMockSupport {

    private static String testResponse = "{\n" + "\"results\" : [ {\n" + "\"_type\" : \"Position\",\n" + "\"_id\" : 410978,\n"
            + "\"name\" : \"Potsdam, USA\",\n" + "\"type\" : \"location\",\n" + "\"geo_position\" : {\n" + "\"latitude\" : 44.66978,\n"
            + "\"longitude\" : -74.98131\n" + "}\n" + "}, {\n" + "\"_type\" : \"Position\",\n" + "\"_id\" : 377078,\n"
            + "\"name\" : \"Potsdam, Deutschland\",\n" + "\"type\" : \"location\",\n" + "\"geo_position\" : {\n" + "\"latitude\" : 52.39886,\n"
            + "\"longitude\" : 13.06566\n" + "}\n" + "} ]\n" + "}";

    /**
     * Class to test.
     */
    private LocationService locationService;

    /**
     * Test of findLocations response when no results were found.
     */
    public void testFindLocationsNoResults() {
        HttpService httpService = createMock(HttpService.class);
        locationService = new LocationServiceImpl("http://localhost/", httpService);
        resetAllToStrict();
        EasyMock.expect(httpService.doGet("http://localhost/123")).andReturn("{}");
        replayAll();
        List<Location> response = locationService.findLocations("123");
        Assert.assertEquals(response.size(), 0, "The number of returned locations should be 0.");
        verifyAll();
    }

    /**
     * Test of finding locations.
     */
    public void testFindLocations() {
        HttpService httpService = createMock(HttpService.class);
        locationService = new LocationServiceImpl("http://localhost/", httpService);
        resetAllToStrict();
        EasyMock.expect(httpService.doGet("http://localhost/123")).andReturn(testResponse);
        replayAll();
        List<Location> response = locationService.findLocations("123");
        Assert.assertEquals(response.size(), 2, "The number of returned locations should be 2.");
        Assert.assertEquals(response.get(0).get_type(), "Position");
        Assert.assertEquals(response.get(0).get_id(), 410978);
        Assert.assertEquals(response.get(0).getName(), "Potsdam, USA");
        Assert.assertEquals(response.get(0).getType(), "location");
        Assert.assertEquals(response.get(0).getGeo_position().getLatitude(), 44.66978d, 0.01);
        Assert.assertEquals(response.get(0).getGeo_position().getLongitude(), -74.98131, 0.01);
        Assert.assertEquals(response.get(1).get_id(), 377078);
        verifyAll();
    }

}
