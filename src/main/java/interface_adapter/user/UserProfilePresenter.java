package interface_adapter.user;

import interface_adapter.ViewManagerModel;
import use_case.user.UserProfileOutputBoundary;

/**
 * Presenter for user profile creation.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final UserViewModel userViewModel;

    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserViewModel userViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userViewModel = userViewModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        userViewModel.setSuccessMessage(message);

        userViewModel.firePropertyChanged("Place save successfuly!");

        viewManagerModel.setState("PlacesSaved");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        userViewModel.setErrorMessage(errorMessage);

        userViewModel.firePropertyChanged("Error in saving place");
    }
}
