package entity;

import java.util.HashMap;
import java.util.List;

/**
 * Factory for creating User entities.
 */
public class UserFactory {

    /**
     * Create a new user with the given username and password.
     * @param username the user's username
     * @param password the user's password
     * @return a new User instance
     */
    public User create(String username, String password) {
        return new User(username, password);
    }
}
