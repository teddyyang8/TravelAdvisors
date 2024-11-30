package entity;

import java.util.List;

/**
 * Factory for creating User objects.
 */
public class UserFactory {

    /**
     * Creates a new User object.
     *
     * @param name the name of the user
     * @param password the password of the user
     * @param interests the interests of the user
     * @return the new User object
     */
    public User create(String name, String password, List<String> interests) {
        final User user = new User(name, password);
        user.setInterests(interests);
        return user;
    }
}
