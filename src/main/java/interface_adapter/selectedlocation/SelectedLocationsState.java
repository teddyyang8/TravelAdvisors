package interface_adapter.selectedlocation;

import entity.Place;

import java.util.List;

/**
 * The state representing selected location-related data, including a list of
 * selected locations.
 */
public class SelectedLocationsState {

    private List<Place> selectedLocations;
    private String error;

    public List<Place> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<Place> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
