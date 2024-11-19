package interface_adapter.suggestlocation;

import use_case.suggest_locations.SuggestLocationsInputBoundary;
import use_case.suggest_locations.SuggestLocationsInputData;

/**
 * The SuggestedLocationsController class handles user input related to suggested locations.
 */
public class SuggestedLocationsController {

    private final SuggestLocationsInputBoundary suggestLocationsInput;

    public SuggestedLocationsController(SuggestLocationsInputBoundary suggestLocationsInput) {
        this.suggestLocationsInput = suggestLocationsInput;
    }

    /**
     * Executes the suggest locations operation.
     * @param address the address to suggest locations for
     * @param locationType the type of location to suggest
     */
    public void execute(String address, String locationType) {
        final SuggestLocationsInputData suggestLocationInputData = new SuggestLocationsInputData(
                address, locationType);

        suggestLocationsInput.execute(suggestLocationInputData);
    }
}
