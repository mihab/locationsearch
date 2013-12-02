package locationsearch.dao.csv;

import java.util.List;

import locationsearch.Location;
import locationsearch.dao.LocationDAO;

/**
 * CSV implementation of data storage.
 * 
 * @author miha
 * 
 */
public class LocationDAOImpl implements LocationDAO {

    private String fileName;

    private StringFileWriter stringFileWriter;

    /**
     * Ctor with full initialization.
     * 
     * @param fileName
     *            File name to save to.
     * @param stringFileWriter
     *            String file writer to use.
     */
    public LocationDAOImpl(String fileName, StringFileWriter stringFileWriter) {
        this.fileName = fileName;
        this.stringFileWriter = stringFileWriter;
    }

    @Override
    public void saveLocations(List<Location> locations) {
        if (locations.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Location location : locations) {
            String line = "";
            line += location.get_type();
            line += ",";
            line += location.get_id();
            line += ",";
            line += (location.getName().contains(",")) ? "\"" + location.getName() + "\"" : location.getName();
            line += ",";
            line += location.getType();
            line += ",";
            line += location.getGeo_position().getLatitude();
            line += ",";
            line += location.getGeo_position().getLongitude();
            sb.append(line + System.lineSeparator());
        }
        stringFileWriter.write(sb.toString(), fileName);
    }

}
