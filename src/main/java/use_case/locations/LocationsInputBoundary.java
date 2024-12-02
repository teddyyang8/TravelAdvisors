package use_case.locations;

import use_case.DataAccessException;

/**
 * The Suggest Locations Use Case.
 */
public interface LocationsInputBoundary {
    /**
     * Execute the Suggest Locations Use Case.
     *
     * @param locationsInputData the input data for this use case
     * @param currentFilter
     * @throws DataAccessException if data cannot be accessed at any time
     */
    void execute(LocationsInputData locationsInputData, String currentFilter) throws DataAccessException;
}
