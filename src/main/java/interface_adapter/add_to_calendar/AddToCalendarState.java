package interface_adapter.add_to_calendar;

import java.util.Map;

import entity.Place;

/**
 * The state representing calendar-related data, including locations, times, and any error messages.
 */
public class AddToCalendarState {
    private Map<Place, String> calendarItems;
    private String addError;

    public Map<Place, String> getCalendarItems() {
        return calendarItems;
    }

    public String getAddError() {
        return addError;
    }

    public void setCalendarItems(Map<Place, String> calendarItems) {
        this.calendarItems = calendarItems;
    }

    public void setAddError(String addError) {
        this.addError = addError;
    }
}
