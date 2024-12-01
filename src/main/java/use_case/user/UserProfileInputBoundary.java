package use_case.user;

import java.util.Map;

/**
 * Input boundary for the User Profile use case.
 */
public interface UserProfileInputBoundary {
    /**
     * Saves the given places for the given username.
     *
     * @param username the username
     * @param places the places
     */
    void savePlaces(String username, Map<String, String> places);

    /**
     * Logs out the user.
     */
    void logOut();
}
