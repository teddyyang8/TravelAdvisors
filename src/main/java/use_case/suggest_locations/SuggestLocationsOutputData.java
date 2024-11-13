package use_case.suggest_locations;

import java.util.List;

import entity.Place;
import entity.SuggestedPlace;

/**
 * Output Data for the Suggest Locations Use Case.
 */
public class SuggestLocationsOutputData {

    private final List<SuggestedPlace> locations;
    private final boolean useCaseFailed;

    public SuggestLocationsOutputData(List<SuggestedPlace> locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<SuggestedPlace> getLocations() {
        return locations;
    }
}
