package use_case.suggested_locations;

import use_case.DataAccessException;

/**
 * The Suggested Locations Use Case.
 */
public interface SuggestedLocationsInputBoundary {
    /**
     * Execute the Suggested Locations Use Case.
     *
     * @param suggestedLocationsInputData the input data for this use case
     * @throws DataAccessException if data cannot be accessed at any time
     */
    void execute(SuggestedLocationsInputData suggestedLocationsInputData) throws DataAccessException;
}
