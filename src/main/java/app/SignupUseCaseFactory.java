package app;

import data_access.DBSignupUserDataAccessObject;
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

    private SignupUseCaseFactory() {

    }

    /**
     * Factory method for creating the SignupView.
     *
     * @param viewManagerModel the ViewManagerModel
     * @param userFactory the UserFactory
     * @return a SignupView instance
     */
    public static SignupView create(ViewManagerModel viewManagerModel, UserFactory userFactory) {
        final DBSignupUserDataAccessObject userDataAccess = new DBSignupUserDataAccessObject();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel);
        final SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, signupPresenter, userFactory);
        final SignupController signupController = new SignupController(signupInteractor);

        return new SignupView(signupController, signupViewModel);
    }
}
