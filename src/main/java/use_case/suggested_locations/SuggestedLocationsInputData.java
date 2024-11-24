package use_case.suggested_locations;

import java.util.List;

import entity.Place;

/**
 * The input data for the Suggested Locations Use Case.
 */
public class SuggestedLocationsInputData {

    private final List<Place> suggestedLocations;

    public SuggestedLocationsInputData(List<Place> suggestedLocations) {
        this.suggestedLocations = suggestedLocations;
    }

    public List<Place> getSuggestedLocations() {
        return suggestedLocations;
    }

}
