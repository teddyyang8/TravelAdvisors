package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final LocationViewModel locationViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signUpViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel,
                          LocationViewModel locationViewModel,
                          SignupViewModel signUpViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
        this.locationViewModel = locationViewModel;
        this.signUpViewModel = signUpViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final UserProfileState userProfileState = userProfileViewModel.getState();
        userProfileState.setUsername(response.getUsername());
        this.userProfileViewModel.setState(userProfileState);
        this.userProfileViewModel.firePropertyChanged();
        final LocationState locationState = locationViewModel.getState();
        locationState.setUsername(response.getUsername());
        this.locationViewModel.setState(locationState);

        this.viewManagerModel.setState(locationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSignUpView() {
        viewManagerModel.setState(signUpViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
