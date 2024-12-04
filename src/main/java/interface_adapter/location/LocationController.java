package interface_adapter.location;

import use_case.DataAccessException;
import use_case.locations.LocationsInputBoundary;
import use_case.locations.LocationsInputData;

/**
 * The LocationController class handles user input related to locations.
 * It interacts with the LocationInputBoundary to process the input and
 * perform actions such as setting the city, address, and interests,
 * as well as executing save or refresh operations.
 */
public class LocationController {

    private final LocationsInputBoundary locationInput;

    public LocationController(LocationsInputBoundary locationInteractor) {
        this.locationInput = locationInteractor;
    }

    /**
     * Executes the show location.
     * @param address the location to show.
     * @param locationType the type of location to search.
     * @param currentFilter the type of filter to search with.
     * @param username the username of the current user.
     * @throws DataAccessException if data cannot be accessed
     */
    public void execute(String address, String locationType, String currentFilter, String username) throws DataAccessException {
        final LocationsInputData locationInputData = new LocationsInputData(
                address, locationType, username);

        locationInput.execute(locationInputData, currentFilter);
    }
}
