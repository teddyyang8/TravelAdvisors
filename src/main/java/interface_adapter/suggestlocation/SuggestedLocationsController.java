package interface_adapter.suggestlocation;

import use_case.suggest_locations.DataAccessException;
import use_case.suggest_locations.LocationsInputBoundary;
import use_case.suggest_locations.LocationsInputData;

/**
 * The SuggestedLocationsController class handles user input related to suggested locations.
 */
public class SuggestedLocationsController {

    private final LocationsInputBoundary suggestLocationsInput;

    public SuggestedLocationsController(LocationsInputBoundary suggestLocationsInput) {
        this.suggestLocationsInput = suggestLocationsInput;
    }

    /**
     * Executes the suggest locations operation.
     * @param address the address to suggest locations for
     * @param locationType the type of location to suggest
     */
    public void execute(String address, String locationType) throws DataAccessException {
        final LocationsInputData suggestLocationInputData = new LocationsInputData(
                address, locationType);

        suggestLocationsInput.execute(suggestLocationInputData);
    }
}
