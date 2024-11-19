package interface_adapter.suggestlocation;

import java.util.List;

import entity.Place;

/**
 * The state representing suggested locations-related data.
 */
public class SuggestedLocationsSate {
    private List<Place> suggestedLocations;
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
