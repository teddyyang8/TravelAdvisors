package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.reviewlocation.ReviewLocationViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.user_profile.UserProfileDataAccessInterface;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import view.UserProfileView;

/**
 * Factory for creating the User Profile use case.
 */
public final class UserProfileUseCaseFactory {

    /** Prevent instantiation. */
    private UserProfileUseCaseFactory() {
    }

    /**
     * Factory function for creating the LocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param userProfileViewModel the SuggestedLocationsViewModel to inject
     * @param userDataAccessObject the In memory user data access object
     * @param reviewLocationViewModel the review location view model
     * @return the LocationView created for the provided input classes.
     */
    public static UserProfileView create(
            ViewManagerModel viewManagerModel,
            UserProfileViewModel userProfileViewModel,
            UserProfileDataAccessInterface userDataAccessObject, ReviewLocationViewModel reviewLocationViewModel) {

        final UserProfileController userProfileController = createUserProfileUseCase(viewManagerModel,
                userProfileViewModel, userDataAccessObject);

        return new UserProfileView(userProfileController, userProfileViewModel, reviewLocationViewModel);
    }

    private static UserProfileController createUserProfileUseCase(
            ViewManagerModel viewManagerModel,
            UserProfileViewModel userProfileViewModel,
            UserProfileDataAccessInterface userDataAccessObject) {

        final UserProfileOutputBoundary userProfileOutputBoundary = new UserProfilePresenter(
                viewManagerModel, userProfileViewModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(userDataAccessObject,
                userProfileOutputBoundary);

        return new UserProfileController(userProfileInteractor);
    }
}
