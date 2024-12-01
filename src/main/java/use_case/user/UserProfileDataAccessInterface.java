package use_case.user;

import java.util.Map;

import use_case.DataAccessException;

/**
 * Data access interface for user profile data.
 */
public interface UserProfileDataAccessInterface {
    /**
     * Saves the given places for the given username.
     *
     * @param username the username
     * @param places the places
     * @throws DataAccessException if there is an issue accessing the data
     */
    void savePlaces(String username, Map<String, String> places) throws DataAccessException;

    /**
     * Gets the saved places for the given username.
     *
     * @param username the username
     * @return the saved places for the given username
     * @throws DataAccessException if there is an issue accessing the data
     */

    Map<String, String> getSavedPlaces(String username) throws DataAccessException;
}
