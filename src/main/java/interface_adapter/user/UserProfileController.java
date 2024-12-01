package interface_adapter.user;

import java.util.Map;

import use_case.user.UserProfileInputBoundary;

/**
 * Controller for the User Profile use case.
 */
public class UserProfileController {
    private final UserProfileInputBoundary userProfileInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    /**
     * Retrieves the saved places for the given username.
     *
     * @param username the username
     * @param places the places
     */
    public void savePlaces(String username, Map<String, String> places) {
        userProfileInteractor.savePlaces(username, places);
    }

    public void logOut() {
        userProfileInteractor.logOut();
    }
}
