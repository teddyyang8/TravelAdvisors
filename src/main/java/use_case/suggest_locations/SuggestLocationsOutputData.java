package use_case.suggest_locations;

import java.util.List;

import entity.Place;

/**
 * Output Data for the Suggest Locations Use Case.
 */
public class SuggestLocationsOutputData {

    private final List<Place> locations;
    private final boolean useCaseFailed;

    public SuggestLocationsOutputData(List<Place> locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Place> getLocations() {
        return locations;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
