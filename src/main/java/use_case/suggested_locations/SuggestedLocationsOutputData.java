package use_case.suggested_locations;

import java.util.List;

import entity.Place;

/**
 * Output Data for the Suggested Locations Use Case.
 */
public class SuggestedLocationsOutputData {

    private final List<Place> suggestedLocations;
    private final boolean useCaseFailed;

    public SuggestedLocationsOutputData(List<Place> suggestedLocations, boolean useCaseFailed) {
        this.suggestedLocations = suggestedLocations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Place> getSuggestedLocations() {
        return suggestedLocations;
    }
}
