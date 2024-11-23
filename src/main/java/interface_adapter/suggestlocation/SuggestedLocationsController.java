package interface_adapter.suggestlocation;

import entity.Place;
import use_case.suggest_locations.DataAccessException;
import use_case.suggested_locations.SuggestedLocationInputBoundary;
import use_case.suggested_locations.SuggestedLocationInputData;

import java.util.List;

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
     * @param places the suggested places found.
     */
    public void execute(List<Place> places) throws DataAccessException {
        final SuggestedLocationInputData suggestLocationInputData = new SuggestedLocationInputData(places);
        suggestLocationsInput.execute(suggestLocationInputData);
    }
}
