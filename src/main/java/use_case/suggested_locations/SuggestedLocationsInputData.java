package use_case.suggested_locations;

import java.util.List;
import java.util.Map;

import entity.Place;

/**
 * The input data for the Suggested Locations Use Case.
 */
public class SuggestedLocationsInputData {

    private final Map<Place, String> calendarItems;

    public SuggestedLocationsInputData(Map<Place, String> calendarItems) {
        this.calendarItems = calendarItems;
    }

    public Map<Place, String> getSuggestedLocations() {
        return calendarItems;
    }

}
