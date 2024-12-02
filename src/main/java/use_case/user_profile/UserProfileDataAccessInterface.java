package use_case.user_profile;

import java.util.List;
import java.util.Map;

import entity.Place;
import entity.SavedPlace;
import entity.User;
import use_case.DataAccessException;

/**
 * Interface for accessing user profile data.
 */
public interface UserProfileDataAccessInterface {
    /**
     * Get the user with the given username.
     *
     * @param username the username
     * @return the user
     * @throws DataAccessException if the user does not exist
     */
    User getUser(String username) throws DataAccessException;

    /**
     * Save the places for the given username.
     *
     * @param username the username
     * @param places   the places to be saved, represented as a map with the place name as the key
     *                 and a list of place objects as the value
     * @throws DataAccessException if the user does not exist
     */

    void savePlaces(String username, Map<String, List<SavedPlace>> places) throws DataAccessException;

    /**
     * Get the saved places for the given username.
     *
     * @param username the username
     * @return the saved places, represented as a map with the place name as the key
     *         and a list of place objects as the value
     * @throws DataAccessException if the user does not exist
     */

    Map<String, List<SavedPlace>> getSavedPlaces(String username) throws DataAccessException;
}
