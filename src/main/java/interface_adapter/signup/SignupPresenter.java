package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * Presenter for the signup use case.
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        signupViewModel.setUsername(outputData.getUsername());
        viewManagerModel.setState("LoggedIn");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        signupViewModel.setErrorMessage(errorMessage);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSwitchToLoginView() {
        viewManagerModel.setState("Login");
        viewManagerModel.firePropertyChanged();
    }
}
