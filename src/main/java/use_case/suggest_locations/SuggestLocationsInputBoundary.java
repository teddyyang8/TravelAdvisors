package use_case.suggest_locations;

/**
 * The Suggest Locations Use Case.
 */
public interface SuggestLocationsInputBoundary {

    void setAddress(String address);

    void setInterest(String interest);

    /**
     * Execute the Suggest Locations Use Case.
     * @param suggestLocationsInputData the input data for this use case
     */
    void execute(SuggestLocationsInputData suggestLocationsInputData) throws DataAccessException;
}
