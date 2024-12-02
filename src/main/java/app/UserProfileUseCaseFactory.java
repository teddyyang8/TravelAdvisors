package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.user.UserProfileController;
import interface_adapter.user.UserProfilePresenter;
import interface_adapter.user.UserViewModel;
import use_case.user.UserProfileInputBoundary;
import use_case.user.UserProfileInteractor;
import view.UserProfileView;

/**
 * Factory for creating the User Profile use case.
 */
public final class UserProfileUseCaseFactory {

    /** Prevent instantiation. */
    private UserProfileUseCaseFactory() {
    }

    /**
     * Factory method for creating the UserProfileView.
     *
     * @param viewManagerModel the ViewManagerModel
     * @return a UserProfileView instance
     */
    public static UserProfileView create(ViewManagerModel viewManagerModel) {
        final InMemoryUserDataAccessObject userDataAccess = new InMemoryUserDataAccessObject();
        final UserViewModel userViewModel = new UserViewModel();
        final UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManagerModel, userViewModel);
        final UserProfileInputBoundary userProfileInteractor =
                new UserProfileInteractor(userDataAccess, userProfilePresenter);
        final UserProfileController userProfileController = new UserProfileController(userProfileInteractor);

        return new UserProfileView(userProfileController, userViewModel);
    }
}
