package use_case.add_to_calendar;

import entity.Place;

/**
 * Interface for the AddToCalendar DAO. It consists of methods for adding locations to the calendar.
 */
public interface AddToCalendarDataAccessInterface {

    /**
     * Checks if the given Place exists.
     * @param location the name of the location to look for.
     * @return true if a Place with the given name exists; false otherwise
     */
    boolean hasLocation(Place location);

    /**
     * Checks if the given time is available.
     * @param time the time of the visit to look for
     * @return true if the timeslot is already full; false otherwise
     */
    boolean hasTime(String time);

    /**
     * Saves the location and time to calendar.
     * @param location the location to save
     * @param time the time to save
     */
    void save(Place location, String time);


}
