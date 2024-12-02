package use_case.selected_locations;

import entity.Place;
import use_case.DataAccessException;

/**
 * Interface for the LocationDAO. It consists of methods for
 * searching locations
 */
public interface CoordinateDataAccessInterface {

    /**
     * Gets a list of locations based on address given.
     * @param location the place the user gives
     * @return a HashMap of locations and their coordinates
     * @throws DataAccessException if the location can not be loaded for any reason
     */
    String searchCoordinates(Place location) throws DataAccessException;

}
