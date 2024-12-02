package data_access;

import entity.Place;
import entity.SavedPlace;
import entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySavedPlacesDataAccessObject {

    private final Map<User, List<SavedPlace>> userListMap = new HashMap<>();

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
