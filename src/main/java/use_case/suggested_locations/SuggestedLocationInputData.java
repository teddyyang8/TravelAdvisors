package use_case.suggested_locations;

import entity.Place;

import java.util.List;

/**
 * The input data for the Suggested Locations Use Case.
 */
public class SuggestedLocationInputData {

    private final List<Place> places;

    public SuggestedLocationInputData(List<Place> places) {
        this.places = places;
    }

    public List<Place> getPlaces() {
        return places;
    }

}
