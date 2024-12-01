package use_case.signup;

import entity.User;
import entity.UserFactory;
import use_case.DataAccessException;

/**
 * The interactor for signing up a new user.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccess;
    private final SignupOutputBoundary signupOutputBoundary;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccess,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccess = userDataAccess;
        this.signupOutputBoundary = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        final String username = signupInputData.getUsername();
        final String password = signupInputData.getPassword();
        final String repeatPassword = signupInputData.getRepeatPassword();

        try {
            if (userDataAccess.existsByName(username)) {
                signupOutputBoundary.prepareFailView("User already exists.");
            }
            else if (!password.equals(repeatPassword)) {
                signupOutputBoundary.prepareFailView("Passwords don't match.");
            }
            else {
                final User newUser = userFactory.create(username, password);
                userDataAccess.save(newUser);

                final SignupOutputData signupOutputData = new SignupOutputData(username, false);
                signupOutputBoundary.prepareSuccessView(signupOutputData);
            }
        }
        catch (DataAccessException error) {
            signupOutputBoundary.prepareFailView("An error occurred while signing up.");
        }
    }

    @Override
    public void switchToLoginView() {
        signupOutputBoundary.prepareSwitchToLoginView();
    }
}
