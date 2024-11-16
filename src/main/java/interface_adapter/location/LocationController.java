package interface_adapter.location;

import use_case.suggest_locations.DataAccessException;
import use_case.suggest_locations.SuggestLocationsInputBoundary;
import use_case.suggest_locations.SuggestLocationsInputData;

/**
 * The LocationController class handles user input related to locations.
 * It interacts with the LocationInputBoundary to process the input and
 * perform actions such as setting the city, address, and interests,
 * as well as executing save or refresh operations.
 */
public class LocationController {

    private final SuggestLocationsInputBoundary locationInput;

    public LocationController(SuggestLocationsInputBoundary locationInteractor) {
        this.locationInput = locationInteractor;
    }

    /**
     * Executes the show location.
     * @param location the location to show
     * Executes the save or refresh operation based on the location.
     * @param address the location to save or refresh
     * @param locationType the type of location to save or refresh
     * @throws DataAccessException if the location is null
     */
    public void execute(String address, String locationType) throws DataAccessException {
        if (address == null) {
            throw new DataAccessException("Address is null");
        }
        final SuggestLocationsInputData suggestLocationInputData = new SuggestLocationsInputData(
                address, locationType);

        locationInput.execute(suggestLocationInputData);
    }
}
