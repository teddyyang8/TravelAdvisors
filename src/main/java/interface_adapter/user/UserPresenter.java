package interface_adapter.user;

import entity.User;
import interface_adapter.ViewManagerModel;
import use_case.user.UserProfileOutputBoundary;

/**
 * Presenter for user profile creation.
 */
public class UserPresenter implements UserProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final UserViewModel userViewModel;

    public UserPresenter(ViewManagerModel viewManagerModel, UserViewModel userViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userViewModel = userViewModel;
    }

    @Override
    public void prepareSuccessView(User user) {
        final UserState userState = new UserState();
        userState.setUsername(user.getName());
        userState.setPassword(user.getPassword());
        userState.setInterests(user.getInterests());

        userViewModel.setState(userState);
        userViewModel.firePropertyChanged();

        // Need to implement the login view
        viewManagerModel.setState("Login");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        UserState userState = userViewModel.getState();
        if (userState == null) {
            userState = new UserState();
        }
        userViewModel.setState(userState);
        userViewModel.firePropertyChanged();
    }
}
