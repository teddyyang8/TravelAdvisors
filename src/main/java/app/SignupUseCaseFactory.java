package app;

import entity.UserFactory;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

/**
 * Factory for creating the Signup use case.
 */
public final class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {

    }

    /**
     * Factory method for creating the SignupView.
     *
     * @param viewManagerModel the ViewManagerModel
     * @param loginViewModel the log in view model
     * @param signupViewModel the sign up view model
     * @param userDataAccessObject the user DAO
     * @return a SignupView instance
     */
    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                    SignupViewModel signupViewModel,
                                    SignupUserDataAccessInterface userDataAccessObject) {

        final SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel,
                loginViewModel, userDataAccessObject);
        return new SignupView(signupController, signupViewModel);
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            SignupViewModel signupViewModel,
                                                            LoginViewModel loginViewModel,
                                                            SignupUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);

        final UserFactory userFactory = new UserFactory();

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}


