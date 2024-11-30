package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import use_case.DataAccessException;
import use_case.user.UserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements UserDataAccessInterface {
    private final Map<String, User> userStorage = new HashMap<>();

    @Override
    public User findUserByName(String name) throws DataAccessException {
        if (!userStorage.containsKey(name)) {
            throw new DataAccessException("User not found");
        }
        return userStorage.get(name);
    }

    @Override
    public void saveUser(User user) {
        userStorage.put(user.getName(), user);
    }
}
