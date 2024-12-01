package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Place;
import use_case.add_to_calendar.AddToCalendarDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing calendar data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryCalendarDataAccessObject implements AddToCalendarDataAccessInterface {

    private final Map<Place, String> calendarItems = new HashMap<>();

    @Override
    public boolean hasLocation(Place location) {
        return calendarItems.containsKey(location);
    }

    @Override
    public boolean hasTime(String time) {
        return calendarItems.containsValue(time);
    }

    @Override
    public void save(Place location, String time) {
        calendarItems.put(location, time);
    }
}
