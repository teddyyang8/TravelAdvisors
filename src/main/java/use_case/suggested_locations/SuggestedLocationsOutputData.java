package use_case.suggested_locations;

import java.util.List;

import entity.Place;

/**
 * Output Data for the Suggested Locations Use Case.
 */
public class SuggestedLocationsOutputData {

    private final List<Place> selectedLocations;
    private final boolean useCaseFailed;

    public SuggestedLocationsOutputData(List<Place> selectedLocations, boolean useCaseFailed) {
        this.selectedLocations = selectedLocations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Place> getSelectedLocations() {
        return selectedLocations;
    }
}
