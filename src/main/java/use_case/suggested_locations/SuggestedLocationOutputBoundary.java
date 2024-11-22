package use_case.suggested_locations;

/**
 * The output boundary for the Suggested Location Use Case.
 */
public interface SuggestedLocationOutputBoundary {
    /**
     * Prepares the sucess view for the Suggested Location Use Case.
     * @param outputData the explanation of the failure
     */
    void prepareSuccessView(SuggestedLocationOutputData outputData);

    /**
     * Prepares the failure view for the Suggested Location Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
