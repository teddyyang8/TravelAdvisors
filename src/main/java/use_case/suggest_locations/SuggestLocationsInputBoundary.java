package use_case.suggest_locations;

import use_case.note.DataAccessException;

/**
 * The Suggest Locations Use Case.
 */
public interface SuggestLocationsInputBoundary {

    /**
     * Execute the Suggest Locations Use Case.
     * @param suggestLocationsInputData the input data for this use case
     */
    void execute(SuggestLocationsInputData suggestLocationsInputData) throws DataAccessException;
}
