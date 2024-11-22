package interface_adapter.suggestlocation;

import use_case.suggest_locations.DataAccessException;
import use_case.suggested_locations.SuggestedLocationInputBoundary;
import use_case.suggested_locations.SuggestedLocationInputData;

/**
 * The SuggestedLocationsController class handles user input related to suggested locations.
 */
public class SuggestedLocationsController {

    private final SuggestedLocationInputBoundary suggestLocationsInput;

    public SuggestedLocationsController(SuggestedLocationInputBoundary suggestLocationsInput) {
        this.suggestLocationsInput = suggestLocationsInput;
    }

    /**
     * Executes the suggest locations operation.
     * @param address the address to suggest locations for
     * @param locationType the type of location to suggest
     */
    public void execute(String address, String locationType) throws DataAccessException {
        final SuggestedLocationInputData suggestLocationInputData = new SuggestedLocationInputData(address, locationType);
        suggestLocationsInput.execute(suggestLocationInputData);
    }
}
