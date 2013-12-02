package locationsearch.dao.csv;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

    /**
     * Ctor with full initialization.
     * 
     * @param fileName
     *            File name to save to.
     */
    public LocationDAOImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveLocations(List<Location> locations) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for (Location location : locations) {
                String line = "";
                line += location.get_type();
                line += ",";
                line += location.get_id();
                line += ",";
                line += location.getName();
                line += ",";
                line += location.getType();
                line += ",";
                line += location.getGeo_position().getLatitude();
                line += ",";
                line += location.getGeo_position().getLongitude();
                bw.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
