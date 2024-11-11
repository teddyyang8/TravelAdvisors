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

    private final SuggestLocationsInputBoundary locationInteractor;

    public LocationController(SuggestLocationsInputBoundary locationInteractor) {
        this.locationInteractor = locationInteractor;
    }

    /**
     * Handles the input of an address.
     * @param address the input address
     */
    public void handleAddressInput(String address) {
        locationInteractor.setAddress(address);
    }

    /**
     * Handles the selection of an interest.
     * @param interest the selected interest
     */
    public void handleInterestSelection(String interest) {
        locationInteractor.setInterest(interest);
    }

    /**
     * Executes the save or refresh operation based on the location.
     * @param location the location to save or refresh
     * @throws DataAccessException if the location is null
     */
    public void execute(SuggestLocationsInputData location) throws DataAccessException {
        if (location == null) {
            throw new DataAccessException("Location is null");
        }
        locationInteractor.execute(location);
    }
}
