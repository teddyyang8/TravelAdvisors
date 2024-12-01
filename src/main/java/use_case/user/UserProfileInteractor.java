package use_case.user;

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

    public UserProfileInteractor(UserProfileDataAccessInterface userProfileDataAccess,
                                 UserProfileOutputBoundary userProfileOutputBoundary) {
        this.userProfileDataAccess = userProfileDataAccess;
        this.userProfileOutputBoundary = userProfileOutputBoundary;
    }

    @Override
    public void savePlaces(String username, Map<String, List<Place>> places) {
        try {
            userProfileDataAccess.savePlaces(username, places);
            userProfileOutputBoundary.prepareSuccessView("Places saved successfully!");
        }
        catch (DataAccessException error) {
            userProfileOutputBoundary.prepareFailView("Failed to save places.");
        }
    }

    @Override
    public void logOut() {
        userProfileOutputBoundary.prepareFailView("Logging out is currently not implemented.");
    }
}
