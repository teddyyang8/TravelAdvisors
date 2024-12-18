package use_case.locations;

/**
 * The output boundary for the Suggest Locations Use Case.
 */
public interface LocationsOutputBoundary {
    /**
     * Prepares the success view for the Suggest Locations Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LocationsOutputData outputData);

    /**
     * Prepares the failure view for the SuggestLocations Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
