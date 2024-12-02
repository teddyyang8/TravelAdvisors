package interface_adapter.suggestlocation;

import java.util.List;
import java.util.Map;

import entity.Place;
import use_case.DataAccessException;
import use_case.suggested_locations.SuggestedLocationsInputBoundary;
import use_case.suggested_locations.SuggestedLocationsInputData;

/**
 * The SuggestedLocationsController class handles user input related to suggested locations.
 */
public class SuggestedLocationsController {

    private final SuggestedLocationsInputBoundary suggestedLocationsInteractor;

    public SuggestedLocationsController(SuggestedLocationsInputBoundary suggestedLocationsInteractor) {
        this.suggestedLocationsInteractor = suggestedLocationsInteractor;
    }

    /**
     * Executes the suggested locations' operation.
     * @param calendarItems the calendarItems produced.
     * @throws DataAccessException if data cannot be accessed.
     */
    public void execute(Map<Place, String> calendarItems) throws DataAccessException {
        final SuggestedLocationsInputData suggestedLocationInputData = new SuggestedLocationsInputData(
                calendarItems);

        suggestedLocationsInteractor.execute(suggestedLocationInputData);
    }
}
