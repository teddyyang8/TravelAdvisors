package interface_adapter.selectedlocation;

import entity.Place;

import java.util.List;
import java.util.Map;

/**
 * The state representing selected location-related data, including a list of
 * selected locations.
 */
public class SelectedLocationsState {

    private List<Place> selectedLocations;
    private Map<Place, String> placeToCoordinates;
    private String error;

    public List<Place> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<Place> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }

    public void setPlaceToCoordinates(Map<Place, String> placeToCoordinates) {
        this.placeToCoordinates = placeToCoordinates;
    }

    public Map<Place, String> getPlaceToCoordinates() {
        return placeToCoordinates;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
