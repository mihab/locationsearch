package locationsearch.integration.goeuro;

import java.util.List;

import locationsearch.Location;

/**
 * The return response from the Go Euro Service.
 * 
 * @author miha
 * 
 */
public class Response {

    private List<Location> results;

    public List<Location> getResults() {
        return results;
    }

    public void setResults(List<Location> results) {
        this.results = results;
    }

}
