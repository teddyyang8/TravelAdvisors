package use_case.suggested_locations;

import java.util.List;
import java.util.Map;

import entity.Place;

/**
 * Output Data for the Suggested Locations Use Case.
 */
public class SuggestedLocationsOutputData {

    private final Map<Place, String> calendarItems;
    private final boolean useCaseFailed;

    public SuggestedLocationsOutputData(Map<Place, String> calendarItems, boolean useCaseFailed) {
        this.calendarItems = calendarItems;
        this.useCaseFailed = useCaseFailed;
    }

    public Map<Place, String> getCalendarItems() {
        return calendarItems;
    }
}
