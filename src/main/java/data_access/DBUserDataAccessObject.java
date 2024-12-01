package data_access;

import java.util.HashMap;
import java.util.Map;

import use_case.DataAccessException;
import use_case.user.UserProfileDataAccessInterface;

/**
 * A DAO for user profile data.
 */
public class DBUserDataAccessObject implements UserProfileDataAccessInterface {
    private final Map<String, Map<String, String>> userPlaces = new HashMap<>();

    @Override
    public void savePlaces(String username, Map<String, String> places) throws DataAccessException {
        if (!userPlaces.containsKey(username)) {
            userPlaces.put(username, new HashMap<>());
        }
        userPlaces.get(username).putAll(places);
    }

    @Override
    public Map<String, String> getSavedPlaces(String username) throws DataAccessException {
        return userPlaces.getOrDefault(username, new HashMap<>());
    }
}
