package use_case.location;

/**
 * The output boundary for the Login Use Case.
 */
public interface LocationOutputBoundary {
    /**
     * Prepares the success view for the Note related Use Cases.
     * @param message the output data
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the failure view for the Note related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
