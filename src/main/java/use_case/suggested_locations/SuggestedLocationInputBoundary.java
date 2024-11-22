package use_case.suggested_locations;

import use_case.suggest_locations.DataAccessException;

/**
 * The Suggested Locations Use Case.
 */
public interface SuggestedLocationInputBoundary {
    /**
     * Execute the Suggested Locations Use Case.
     * @param suggestedLocationsInputData the input data for this use case
     */
    void execute(SuggestedLocationInputData suggestedLocationsInputData) throws DataAccessException;
}
