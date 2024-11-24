package use_case.locations;

import java.util.List;

import entity.Place;
import use_case.DataAccessException;

/**
 * Interface for the LocationDAO. It consists of methods for
 * searching locations
 */
public interface LocationDataAccessInterface {

    /**
     * Gets a list of locations based on address given.
     * @param address address the user gives
     * @param locationType the type of location
     * @return a list of locations
     * @throws DataAccessException if the location can not be loaded for any reason
     */
    List<Place> searchLocation(String address, String locationType) throws DataAccessException;
  
}
