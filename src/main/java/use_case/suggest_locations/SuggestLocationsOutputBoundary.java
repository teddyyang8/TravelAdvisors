package use_case.suggest_locations;

/**
 * The output boundary for the Suggest Locations Use Case.
 */
public interface SuggestLocationsOutputBoundary {
    /**
     * Prepares the success view for the Suggest Locations Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SuggestLocationsOutputData outputData);

    /**
     * Prepares the failure view for the SuggestLocations Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
