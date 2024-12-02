package interface_adapter.user_profile;

import java.util.List;
import java.util.Map;

import entity.Place;
import use_case.user_profile.UserProfileInputBoundary;

/**
 * Controller for the User Profile use case.
 */
public class UserProfileController {
    private final UserProfileInputBoundary userProfileInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    /**
     * Saves the places for the given username.
     *
     * @param username the username
     * @param places   the places to be saved, represented as a map with the place name as the key
     *                 and a list of place objects as the value
     */
    public void savePlaces(String username, Map<String, List<Place>> places) {
        userProfileInteractor.savePlaces(username, places);
    }

    /**
     * Logs out the user.
     */
    public void logOut() {
        userProfileInteractor.logOut();
    }
}
