package use_case.add_to_calendar;

import java.util.Map;

import entity.Place;

/**
 * The output data for the Add to Calendar Use Case.
 */
public class AddToCalendarOutputData {

    private final Map<Place, String> calendarItems;

    public AddToCalendarOutputData(Map<Place, String> addToCalendarPlace) {
        this.calendarItems = addToCalendarPlace;
    }

    public Map<Place, String> getcalendarItems() {
        return calendarItems;
    }
}
