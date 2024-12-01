package entity;

/**
 * Represents a User entity.
 */
public class UserFactory {
    /**
     * Creates a new User instance.
     *
     * @param username the username
     * @param password the password
     * @return a new User instance
     */
    public User create(String username, String password) {
        return new User(username, password);
    }
}
