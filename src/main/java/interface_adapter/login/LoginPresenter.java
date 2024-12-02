package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
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

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel,
                          LocationViewModel locationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
        this.locationViewModel = locationViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final UserProfileState userProfileState = userProfileViewModel.getState();
        userProfileState.setUsername(response.getUsername());
        this.userProfileViewModel.setState(userProfileState);
        this.userProfileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(locationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
