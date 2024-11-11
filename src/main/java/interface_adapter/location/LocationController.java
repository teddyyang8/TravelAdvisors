package interface_adapter.location;

import use_case.location.LocationInputBoundary;

/**
 * The LocationController class handles user input related to locations.
 * It interacts with the LocationInputBoundary to process the input and
 * perform actions such as setting the city, address, and interests,
 * as well as executing save or refresh operations.
 */
public class LocationController {

    private final LocationInputBoundary locationInteractor;

    public LocationController(LocationInputBoundary locationInteractor) {
        this.locationInteractor = locationInteractor;
    }

    /**
     * Handles the selection of a city.
     * @param city the selected city
     */
    public void handleCitySelection(String city) {
        locationInteractor.setCity(city);
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
     */
    public void execute(String location) {
        if (location != null) {
            locationInteractor.executeSave(location);
        }
        else {
            locationInteractor.executeRefresh();
        }
    }
}
