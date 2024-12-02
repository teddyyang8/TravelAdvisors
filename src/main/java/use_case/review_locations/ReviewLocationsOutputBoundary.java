package use_case.review_locations;

public interface ReviewLocationsOutputBoundary {
    /**
     * Prepares the success view for the Suggest Locations Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ReviewLocationsOutputData outputData);

    /**
     * Prepares the failure view for the SuggestLocations Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
