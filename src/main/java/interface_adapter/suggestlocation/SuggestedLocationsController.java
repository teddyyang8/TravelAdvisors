package interface_adapter.suggestlocation;

import java.util.List;

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
     * @param suggestedLocations the suggestedLocations produced.
     * @throws DataAccessException if data cannot be accessed.
     */
    public void execute(List<Place> suggestedLocations) throws DataAccessException {
        final SuggestedLocationsInputData suggestedLocationInputData = new SuggestedLocationsInputData(
                suggestedLocations);

        suggestedLocationsInteractor.execute(suggestedLocationInputData);
    }
}
