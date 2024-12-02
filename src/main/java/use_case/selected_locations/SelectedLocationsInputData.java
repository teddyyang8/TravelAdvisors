package use_case.selected_locations;

import entity.Place;

import java.util.List;

public class SelectedLocationsInputData {

    private final List<Place> selectedLocations;

    public SelectedLocationsInputData(List<Place> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }

    public List<Place> getSelectedLocations() {
        return selectedLocations;
    }
}
