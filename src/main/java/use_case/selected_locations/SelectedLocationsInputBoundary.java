package use_case.selected_locations;

import use_case.DataAccessException;

/**
 * The input boundary for the Selected Locations Use Case.
 */
public interface SelectedLocationsInputBoundary {
    /**
     * Execute the Selected Locations Use Case.
     *
     * @param selectedLocationsInputData the input data for this use case
     * @throws DataAccessException if data cannot be accessed at any time
     */
    void execute(SelectedLocationsInputData selectedLocationsInputData) throws DataAccessException;

}
