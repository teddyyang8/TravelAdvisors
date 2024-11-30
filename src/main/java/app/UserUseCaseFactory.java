package app;

import data_access.DBUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.user.UserController;
import interface_adapter.user.UserPresenter;
import interface_adapter.user.UserViewModel;
import use_case.user.UserProfileInputBoundary;
import use_case.user.UserProfileInteractor;
import view.UserProfileView;

/**
 * This class contains the static factory function for creating a user use case.
 */
public class UserUseCaseFactory {

    private UserUseCaseFactory() {
        // Prevent instantiation
    }

    /**
     * Factory function for creating the UserProfileView.
     *
     * @param viewManagerModel the ViewManagerModel to inject
     * @return the UserProfileView created for the provided input classes.
     */
    public static UserProfileView create(ViewManagerModel viewManagerModel) {
        final DBUserDataAccessObject userDataAccess = new DBUserDataAccessObject();
        final UserViewModel userViewModel = new UserViewModel();
        final UserPresenter userPresenter = new UserPresenter(viewManagerModel, userViewModel);
        final UserFactory userFactory = new UserFactory();
        final UserProfileInputBoundary interactor = new UserProfileInteractor(
                userDataAccess, userPresenter, userFactory);
        final UserController userController = new UserController(interactor);

        return new UserProfileView(userController, userViewModel);
    }
}
