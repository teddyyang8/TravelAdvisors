package app;

import data_access.InMemoryUserDataAccess;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
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
     * @return a SignupView instance
     */
    public static SignupView create(ViewManagerModel viewManagerModel) {
        final InMemoryUserDataAccess userDataAccess = new InMemoryUserDataAccess();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel);
        final UserFactory userFactory = new UserFactory();
        final SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, signupPresenter, userFactory);
        final SignupController signupController = new SignupController(signupInteractor);

        return new SignupView(signupController, signupViewModel);
    }
}
