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
     * Executes the save or refresh operation based on the location.
     *
     * @param address      the location to save or refresh
     * @param locationType the type of location to save or refresh
     * @throws DataAccessException if data cannot be accessed
     */


    public void execute(String address, String locationType) throws DataAccessException {
        final LocationsInputData locationInputData = new LocationsInputData(
                address, locationType);

        locationInput.execute(locationInputData);
    }

    public void clearSaved() {

    }

//    /**
//     * Switches to the suggested locations view.
//     */
//    public void switchToSuggestedLocationsView() {
//        locationInput.switchToSuggestedLocationsView();
//    }
}
