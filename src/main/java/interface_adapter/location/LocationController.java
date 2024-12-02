package interface_adapter.location;

import interface_adapter.ViewManagerModel;
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
    private final ViewManagerModel viewManagerModel;

    public LocationController(LocationsInputBoundary locationInteractor, ViewManagerModel viewManagerModel) {
        this.locationInput = locationInteractor;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Executes the show location.
     * @param address the location to show.
     * @param locationType the type of location to search.
     * @throws DataAccessException if data cannot be accessed
     */
    public void execute(String address, String locationType, String currentFilter) throws DataAccessException {
        final LocationsInputData locationInputData = new LocationsInputData(address, locationType);
        locationInput.execute(locationInputData, currentFilter);
    }

    /**
     * Navigates to the profile page.
     */
    public void goToProfile() {
        viewManagerModel.setState("Profile");
        viewManagerModel.firePropertyChanged();
    }
}
