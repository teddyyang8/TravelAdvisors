package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup use case.
 */
public class SignupController {
    private final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    /**
     * Executes the Signup use case with the provided information.
     *
     * @param username the username
     * @param password the password
     * @param repeatPassword the repeated password
     */
    public void execute(String username, String password, String repeatPassword) {
        final SignupInputData inputData = new SignupInputData(username, password, repeatPassword);
        signupInteractor.execute(inputData);
    }

    /**
     * Switches to the login view.
     */
    public void switchToLoginView() {
        signupInteractor.switchToLoginView();
    }
}
