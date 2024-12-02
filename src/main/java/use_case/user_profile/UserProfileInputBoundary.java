package use_case.user_profile;

import java.util.List;
import java.util.Map;

import entity.SavedPlace;

/**
 * Input boundary for managing user profiles.
 */
public interface UserProfileInputBoundary {
    /**
     * Save the places for the given username.
     *
     * @param places the places to be saved, represented as a map with the place name as the key
     *               and a list of place objects as the value
     */
    void savePlaces(String username, Map<String, List<SavedPlace>> places);

    /**
     * Logs out the user.
     */

    void logOut();
}
