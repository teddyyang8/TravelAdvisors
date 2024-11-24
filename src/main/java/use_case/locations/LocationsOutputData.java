package use_case.locations;

import java.util.List;

import entity.Place;

/**
 * Output Data for the Suggest Locations Use Case.
 */
public class LocationsOutputData {

    private final List<Place> locations;
    private final boolean useCaseFailed;

    public LocationsOutputData(List<Place> locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Place> getLocations() {
        return locations;
    }
}
