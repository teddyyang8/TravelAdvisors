package use_case.signup;

import entity.User;
import entity.UserFactory;
import use_case.DataAccessException;

/**
 * The Signup Interactor handles the logic for user signup.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject,
                            SignupOutputBoundary userPresenter,
                            UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        final String username = signupInputData.getUsername();
        final String password = signupInputData.getPassword();
        final String repeatPassword = signupInputData.getRepeatPassword();

        try {
            if (userDataAccessObject.existsByName(username)) {
                userPresenter.prepareFailView("User already exists.");
            }
            else if (!password.equals(repeatPassword)) {
                userPresenter.prepareFailView("Passwords don't match.");
            }
            else {
                final User newUser = userFactory.create(username, password);
                userDataAccessObject.save(newUser);

                final SignupOutputData signupOutputData = new SignupOutputData(username, false);
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
        catch (DataAccessException err) {
            userPresenter.prepareFailView("An error occurred while signing up: " + err.getMessage());
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.prepareSwitchToLoginView();
    }
}
