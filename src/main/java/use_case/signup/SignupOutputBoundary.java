package use_case.signup;

/**
 * Output boundary for the Signup use case.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares the view with the output data.
     *
     * @param outputData the output data
     */
    void prepareSuccessView(SignupOutputData outputData);

    /**
     * Prepares the view with an error message.
     *
     * @param errorMessage the error message
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the view to switch to the login view.
     */
    void switchToLoginView();
}
