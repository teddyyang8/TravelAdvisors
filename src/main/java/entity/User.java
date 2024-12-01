package entity;

import java.util.HashMap;
import java.util.List;

/**
 * The representation of a password-protected user for our program.
 */
public class User {

    private final String name;
    private final String password;
    private final HashMap<String, List<Place>> savedPlaces;

    public User(String name, String password, HashMap<String, List<Place>> savedPlaces) {
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

    public HashMap<String, List<Place>> getSavedPlaces() {
        return savedPlaces;
    }
}
