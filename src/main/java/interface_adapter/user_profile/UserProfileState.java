package interface_adapter.user_profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Place;
import entity.SavedPlace;

/**
 * Represents the state of a user.
 */
public class UserProfileState {
    private String username = "";
    private Map<String, List<SavedPlace>> savedPlaces = new HashMap<>();
    private String error;

    public UserProfileState() {

    }

    public String getName() {
        return username;
    }

    public Map<String, List<SavedPlace>> getSavedPlaces() {
        return savedPlaces;
    }

    public void setSavedPlaces(Map<String, List<SavedPlace>> savedPlaces) {
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
