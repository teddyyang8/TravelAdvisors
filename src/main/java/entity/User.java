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

    public User(String name, String password, HashMap<String, List<Place>> savedPlaces) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
