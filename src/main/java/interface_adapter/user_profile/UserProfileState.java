package interface_adapter.user_profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Place;

/**
 * Represents the state of a user.
 */
public class UserProfileState {
    private String username = "";
    private Map<String, List<Place>> savedPlaces = new HashMap<>();
    private String error;

    public UserProfileState(UserProfileState copy) {
        username = copy.username;
        savedPlaces = copy.savedPlaces;
    }

    public UserProfileState() {

    }

    public String getName() {
        return username;
    }

    public Map<String, List<Place>> getSavedPlaces() {
        return savedPlaces;
    }

    public void setSavedPlaces(Map<String, List<Place>> savedPlaces) {
        this.savedPlaces = savedPlaces;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
