package use_case.user_profile;

import java.util.List;
import java.util.Map;

import entity.Place;
import use_case.DataAccessException;

/**
 * Interactor for managing user profiles.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserProfileDataAccessInterface userProfileDataAccess;
    private final UserProfileOutputBoundary userProfileOutputBoundary;

    public UserProfileInteractor(UserProfileDataAccessInterface userProfileDataAccessObject,
                                 UserProfileOutputBoundary userProfileOutputBoundary) {
        this.userProfileDataAccess = userProfileDataAccessObject;
        this.userProfileOutputBoundary = userProfileOutputBoundary;
    }

    @Override
    public void logOut() {
        userProfileOutputBoundary.prepareFailView("Logging out is currently not implemented.");
    }
}
