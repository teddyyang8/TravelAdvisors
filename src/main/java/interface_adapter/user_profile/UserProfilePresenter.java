package interface_adapter.user_profile;

import interface_adapter.ViewManagerModel;
import use_case.user_profile.UserProfileOutputBoundary;

/**
 * Presenter for user profile creation.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userViewModel;

    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userViewModel = userViewModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        //teddy u can implement this for urs
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final UserProfileState userProfileState = userViewModel.getState();
        userProfileState.setError(errorMessage);
        userViewModel.firePropertyChanged();
    }
}
