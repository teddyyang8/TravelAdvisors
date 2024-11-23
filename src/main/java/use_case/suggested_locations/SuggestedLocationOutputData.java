package use_case.suggested_locations;

import entity.Place;

import java.util.List;

/**
 * The output data for the Suggested Location Use Case.
 */
public class SuggestedLocationOutputData {

    private final List<Place> suggestedLocation;

    public SuggestedLocationOutputData(List<Place> suggestedLocation) {
        this.suggestedLocation = suggestedLocation;
    }

    public List<Place> getSuggestedLocation() {
        return suggestedLocation;
    }
}
