package use_case.suggested_locations;

/**
 * The output boundary for the Suggested Locations Use Case.
 */
public interface SuggestedLocationsOutputBoundary {
    /**
     * Prepares the success view for the Suggest Locations Use Case.
     *
     * @param outputData the output data
     */
    void prepareSuccessView(SuggestedLocationsOutputData outputData);

    /**
     * Prepares the failure view for the SuggestLocations Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Executes the switch location view.
     */
    void switchToLocationView();
}
