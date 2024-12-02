package use_case.signup;

/**
 * Input boundary for the Signup use case.
 */
public interface SignupInputBoundary {
    /**
     * Executes the Signup use case with the provided information.
     *
     * @param inputData the input data
     */
    void execute(SignupInputData inputData);

    /**
     * Switches to the login view.
     */

    void switchToLoginView();
}

