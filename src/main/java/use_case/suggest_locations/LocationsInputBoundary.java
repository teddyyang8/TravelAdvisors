package use_case.suggest_locations;

/**
 * The Suggest Locations Use Case.
 */
public interface LocationsInputBoundary {
    /**
     * Execute the Suggest Locations Use Case.
     * @param locationsInputData the input data for this use case
     */
    void execute(LocationsInputData locationsInputData) throws DataAccessException;

    /**
     * Switch to the Suggested Locations View.
     */
    void switchToSuggestedLocationsView();
}
