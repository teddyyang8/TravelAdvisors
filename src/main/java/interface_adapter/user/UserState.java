package interface_adapter.user;

import java.util.List;
import java.util.Map;

/**
 * State for the user.
 */
public class UserState {
    private String username = "";
    private String password = "";
    private List<String> interests;
    private Map<String, String> savedPlaces;
    private String errorMessage = "";
    private boolean loggedIn = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public Map<String, String> getSavedPlaces() {
        return savedPlaces;
    }

    public void setSavedPlaces(Map<String, String> savedPlaces) {
        this.savedPlaces = savedPlaces;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
