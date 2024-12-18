package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The representation of a password-protected user for our program.
 */
public class User {

    private final String name;
    private final String password;
    private final Map<String, List<SavedPlace>> savedPlaces;

    public User(String name, String password, HashMap<String, List<SavedPlace>> savedPlaces) {
        this.name = name;
        this.password = password;
        this.savedPlaces = savedPlaces;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, List<SavedPlace>> getSavedPlaces() {
        return savedPlaces;
    }
}
