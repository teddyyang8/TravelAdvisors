package interface_adapter.suggestlocation;

import java.util.ArrayList;
import java.util.List;

import entity.Place;

/**
 * The state representing suggested location-related data, including a list of suggested locations.
 */
public class SuggestedLocationsState {
    private List<Place> suggestedLocations = new ArrayList<>();
    private String error;

    public List<Place> getSuggestedLocations() {
        return suggestedLocations;
    }

    public void setSuggestedLocations(List<Place> suggestedLocations) {
        this.suggestedLocations = suggestedLocations;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
