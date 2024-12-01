package use_case.user;

import java.util.Map;

import use_case.DataAccessException;

/**
 * Interactor for the User Profile use case.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserProfileDataAccessInterface userProfileDataAccess;
    private final UserProfileOutputBoundary userProfileOutputBoundary;

    public UserProfileInteractor(UserProfileDataAccessInterface userProfileDataAccess,
                                 UserProfileOutputBoundary userProfileOutputBoundary) {
        this.userProfileDataAccess = userProfileDataAccess;
        this.userProfileOutputBoundary = userProfileOutputBoundary;
    }

    @Override
    public void savePlaces(String username, Map<String, String> places) {
        try {
            userProfileDataAccess.savePlaces(username, places);
            userProfileOutputBoundary.prepareSuccessView("Places saved successfully!");
        }
        catch (DataAccessException err) {
            userProfileOutputBoundary.prepareFailView("Failed to save places.");
        }
    }

    @Override
    public void logOut() {
        userProfileOutputBoundary.prepareFailView("Logging out is currently not implemented.");
    }
}
