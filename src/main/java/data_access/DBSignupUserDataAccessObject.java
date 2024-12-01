package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import use_case.DataAccessException;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * A DAO for signup user data.
 */
public class DBSignupUserDataAccessObject implements SignupUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public User getSignupUser(String username) throws DataAccessException {
        return users.get(username);
    }

    @Override
    public void save(User user) throws DataAccessException {
        if (users.containsKey(user.getUsername())) {
            throw new DataAccessException("User already exists.");
        }
        users.put(user.getUsername(), user);
    }

    @Override
    public boolean existsByName(String username) throws DataAccessException {
        return users.containsKey(username);
    }
}
