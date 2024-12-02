package use_case.add_to_calendar;

import java.util.Map;

import entity.Place;

/**
 * The input data for the Add to Calendar Use Case.
 */
public class AddToCalendarInputData {

    private final Map<Place, String> addToCalendarPlace;

    public AddToCalendarInputData(Map<Place, String> addToCalendarPlace) {
        this.addToCalendarPlace = addToCalendarPlace;
    }

    public Map<Place, String> getAddToCalendarPlace() {
        return addToCalendarPlace;
    }
}
